package BBS.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import BBS.beans.Category;
import BBS.beans.Comment;
import BBS.beans.Post_comment;
import BBS.beans.ShowComment;
import BBS.beans.User;
import BBS.service.CommentService;
import BBS.service.PostService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {

		String startDate = null;
		String endDate = null;
		String category = null;

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtils.isEmpty(request.getParameter("category"))){
			category = request.getParameter("category");
		}
		if(StringUtils.isEmpty(request.getParameter("startDate")) && (StringUtils.isEmpty(request.getParameter("endDate")))){
			startDate = "2017-05-01";
			endDate = sdf.format(date).toString();
		}else {
			startDate = request.getParameter("startDate");
			endDate = request.getParameter("endDate");
		}

		List<Category> categories = new PostService().getCategory(category);
		List<Post_comment> posts = new PostService().getMessage(startDate, endDate, category);
		List<ShowComment> comments = new CommentService().getMessage();
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		request.setAttribute("loginUser", loginUser);
		request.setAttribute("categories", categories);
		request.setAttribute("posts", posts);
		request.setAttribute("comments", comments);
		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		List<String> comments = new ArrayList<String>();

		if (isValid(request, comments) == true) {

			User user = (User) session.getAttribute("loginUser");

			Comment comment = new Comment();
			comment.setUser_id(user.getId());
			comment.setPost_id(Integer.parseInt(request.getParameter("post_id")));
			comment.setText(request.getParameter("comment"));

			new CommentService().register(comment);

			response.sendRedirect("./");
		} else {
			Comment comment = new Comment();
			comment.setText(request.getParameter("comment"));
			comment.setPost_id(Integer.parseInt(request.getParameter("post_id")));

			session.setAttribute("errorMessages", comments);
			session.setAttribute("comment", comment);
			response.sendRedirect("./");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> comments) {

		String Comment = request.getParameter("comment");

		if (StringUtils.isEmpty(Comment) == true) {
			comments.add("コメントを入力してください");
		}
		if (500 <= Comment.length()) {
			comments.add("コメントは500文字以下で入力してください");
		}
		if (comments.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}