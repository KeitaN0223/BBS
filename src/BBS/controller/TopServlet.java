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

import BBS.beans.Comment;
import BBS.beans.Post_comment;
import BBS.beans.ShowComment;
import BBS.beans.User;
import BBS.service.CommentService;
import BBS.service.PostService;
//import BBS.beans.User;


@WebServlet(urlPatterns = { "/index" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {

/*		User user = (User) request.getSession().getAttribute("loginUser");
		boolean isShowMessageForm;
		if (user != null){
			isShowMessageForm = true;
		}else{
			isShowMessageForm = false;
		}
*/

		List<Post_comment> posts = new PostService().getMessage();
		List<ShowComment> comments = new CommentService().getMessage();
		request.setAttribute("posts", posts);
		request.setAttribute("comments", comments);
		//request.setAttribute("isShowMessageForm", isShowMessageForm);
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

			response.sendRedirect("index");
		} else {
			session.setAttribute("errorMessages", comments);
			response.sendRedirect("index");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> comments) {

		String Comment = request.getParameter("comment");

		if (StringUtils.isEmpty(Comment) == true) {
			comments.add("コメントを入力してください");
		}
		if (500 <= Comment.length()) {
			comments.add("500文字以下で入力してください");
		}
		if (comments.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}