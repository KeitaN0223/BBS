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
import BBS.beans.Post_comment;
import BBS.beans.User;
import BBS.service.PostService;
@WebServlet(urlPatterns = { "/post" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		List<Post_comment> posts = new PostService().getMessage();

		request.setAttribute("posts", posts);
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
		String Subject = request.getParameter("subject");
		String Category = request.getParameter("category");

		if (StringUtils.isEmpty(Subject) == true){
			post.add("件名を入力してください");
		}
		if (50 <= Subject.length()){
			post.add("件名は50文字以下で入力してください");
		}
		if (StringUtils.isEmpty(Post) == true) {
			post.add("投稿内容を入力してください");
		}
		if (1000 <= Post.length()) {
			post.add("投稿内容は1000文字以下で入力してください");
		}
		if (StringUtils.isEmpty(Category) == true){
			post.add("カテゴリーを入力してください");
		}
		if (post.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}