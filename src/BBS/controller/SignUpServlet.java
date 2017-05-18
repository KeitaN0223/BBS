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

import BBS.beans.Branch;
import BBS.beans.Department;
import BBS.beans.User;
import BBS.service.BranchService;
import BBS.service.DepartmentService;
import BBS.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Branch> branches = new BranchService().getBranches();
		List<Department> departments = new DepartmentService().getDepartments();
		request.setAttribute("branches", branches);
		request.setAttribute("departments", departments);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		User user = new User();
		if (isValid(request, messages) == true) {
			user.setAccount(request.getParameter("account"));
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			user.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));

			new UserService().register(user);

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
		String confirm_password = request.getParameter("confirm_password");
		String name = request.getParameter("name");
		String branch_id = request.getParameter("branch_id");
		String department_id = request.getParameter("department_id");

		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (StringUtils.equals(password, confirm_password) != true){
			messages.add("パスワードが一致しません");
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
