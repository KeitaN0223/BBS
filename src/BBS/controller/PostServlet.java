package BBS.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import BBS.beans.Post;
import BBS.beans.User;
import BBS.service.PostService;
@WebServlet(urlPatterns = { "/post" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		request.getRequestDispatcher("/post.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {

			User user = (User) session.getAttribute("loginUser");

			Post post = new Post();
			post.setUser_id(user.getId());
			post.setSubject(request.getParameter("subject"));
			post.setText(request.getParameter("post"));
			post.setCategory(request.getParameter("category"));

			new PostService().register(post);

			response.sendRedirect("index");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("post");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> post) {

		String Post = request.getParameter("post");

		if (StringUtils.isEmpty(Post) == true) {
			post.add("メッセージを入力してください");
		}
		if (1000 <= Post.length()) {
			post.add("1000文字以下で入力してください");
		}
		if (post.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}