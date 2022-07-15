package edu.poly.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.UploadUtils;
import edu.poly.dao.VideoDao;
import edu.poly.model.Video;

@WebServlet({"/Admin/VideosManagement", "/Admin/VideosManagement/create",
		"/Admin/VideosManagement/update","/Admin/VideosManagement/delete",
		"/Admin/VideosManagement/reset", "/Admin/VideosManagement/edit"})
@MultipartConfig
public class VideosManagementServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if(url.contains("edit")) {
			edit(request, response);
			return;
		}
		if(url.contains("delete")) {
			edit(request, response);
			return;
		}
		if(url.contains("reset")) {
			edit(request, response);
			return;
		}
		
		Video video = new Video();
		video.setPoster("image/mb.jpg");
		request.setAttribute("video", video);
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if(url.contains("create")) {
			create(request, response);
			return;
		}
		if(url.contains("delete")) {
			create(request, response);
			return;
		}
		if(url.contains("update")) {
			create(request, response);
			return;
		}
		if(url.contains("reset")) {
			create(request, response);
			return;
		}
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		try {
			BeanUtils.populate(video, request.getParameterMap());
			
			video.setPoster("uploads/" + UploadUtils.processUploadField("cover", request, ".uploads", video.getVideoId()));
			
			VideoDao dao = new VideoDao();
			request.setAttribute("video", video);
			dao.insert(video);
			request.setAttribute("message", "Video is inserted!!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error","Error" + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

}
