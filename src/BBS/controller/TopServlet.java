package BBS.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BBS.beans.Post_comment;
import BBS.beans.User;
import BBS.service.PostService;

@WebServlet(urlPatterns = { "/index" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {

		User user = (User) request.getSession().getAttribute("loginUser");
		boolean isShowMessageForm;
		if (user != null){
			isShowMessageForm = true;
		}else{
			isShowMessageForm = false;
		}

		List<Post_comment> posts = new PostService().getMessage();

		request.setAttribute("posts", posts);
		request.setAttribute("isShowMessageForm", isShowMessageForm);
		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}
}