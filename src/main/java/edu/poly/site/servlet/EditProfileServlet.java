package edu.poly.site.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDao;
import edu.poly.model.User;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = SessionUtils.getLoginedUsername(request);
		
		if(username == null) {
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		}
		
		try {
			UserDao dao = new UserDao();
			User user = dao.findById(username);
			
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			
			String username = SessionUtils.getLoginedUsername(request);
			UserDao dao = new UserDao();
			User oldUser = dao.findById(username);
			
			user.setAdmin(oldUser.getAdmin());
			dao.update(user);
			request.setAttribute("messag", "User profile update!!!");
			
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.SITE_EDIT_PROFILE_PAGE);
	}

}
