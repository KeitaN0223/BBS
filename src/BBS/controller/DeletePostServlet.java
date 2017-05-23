package BBS.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BBS.service.PostService;

@WebServlet(urlPatterns = {"/deletePost"})
public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		new PostService().deletePost(Integer.parseInt(request.getParameter("post_id")));
		response.sendRedirect("./");
	}
}
