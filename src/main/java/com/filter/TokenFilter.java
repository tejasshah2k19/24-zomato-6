package com.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.CustomerEntity;
import com.respository.CustomerRepository;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class TokenFilter implements Filter {

	@Autowired
	CustomerRepository customerRepository;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		// api -> public private
		String url = req.getRequestURL().toString();

		System.out.println(url);
		if (url.contains("public")) {
			chain.doFilter(request, response);
		} else {
			// header read
			System.out.println("PRIVATE");
			String token = req.getHeader("authToken");

			// token verify
			Optional<CustomerEntity> op = customerRepository.findByToken(token);
			if (op.isPresent()) {
				chain.doFilter(request, response);
			} else {
				response.getWriter().print("Please Login");
			}
		}

	}
}
