package edu.poly.filter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import edu.poly.common.SessionUtils;

@WebServlet(urlPatterns = "/*")
public class AuthFilter extends HttpFilter {
 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute("isLogin", SessionUtils.isLogin((HttpServletRequest)request));
		
		chain.doFilter(request, response);
	}

}
