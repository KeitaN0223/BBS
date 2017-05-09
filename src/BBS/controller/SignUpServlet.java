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

import BBS.beans.User;
import BBS.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		User user = new User();
		if (isValid(request, messages) == true) {
//TODO 確認用パスワード入力欄を追加する
			user.setAccount(request.getParameter("account"));
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			user.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));

			new UserService().register(user);

			//TODO 完了したら管理画面へ移行する
			response.sendRedirect("signup");
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("user", user);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String branch_id = request.getParameter("branch_id");
		String department_id = request.getParameter("department_id");

		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		if (StringUtils.isEmpty(branch_id) == true) {
			messages.add("支店番号を入力してください");
		}
		if (StringUtils.isEmpty(department_id) == true) {
			messages.add("部署または役職番号を入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
