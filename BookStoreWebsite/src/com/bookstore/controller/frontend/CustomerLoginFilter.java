package com.bookstore.controller.frontend;

import java.io.IOException;
import java.net.HttpRetryException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class CustomerLoginFilter implements Filter {
	private static final String[] loginRequiredURLs = { "/view_profile", "/edit_profile", "/update_profile",
			"/write_review","/checkout","/place_order" };

	public CustomerLoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		String requestURL = httpRequest.getRequestURL().toString();
		// ��������� ��������� ��������, ������� ���� �� /admin/. ��� �������� ������
		// ������
		if (path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}

		// ������ , ����������� customer ��� ���
		boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null;

		System.out.println("Path " + path);
		System.out.println("loggedIn " + loggedIn);

		// ������� ��� ������� customer ������������ �� login.jsp
		if (!loggedIn && isLoginRequired(requestURL)) {
			String queryString = httpRequest.getQueryString();
			String redirectURL = requestURL;

			if (queryString != null) {
				redirectURL = redirectURL.concat("?").concat(queryString);
			}

			session.setAttribute("redirectURL", redirectURL);

			String loginPath = "frontend/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(loginPath);
			dispatcher.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean isLoginRequired(String requestURL) {
		for (String loginRequiredURL : loginRequiredURLs) {
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
