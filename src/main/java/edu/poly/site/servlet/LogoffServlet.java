package edu.poly.site.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import edu.poly.common.CookieUtils;
import edu.poly.common.SessionUtils;

@WebServlet("/Logoff")
public class LogoffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CookieUtils.add("usernam", null, 0, response);
		
		SessionUtils.invalidate(request);
		
		request.setAttribute("isLogin", false);
		request.getRequestDispatcher("/Homepage").forward(request, response);
	}

}
