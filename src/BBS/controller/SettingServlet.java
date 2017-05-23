package BBS.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import BBS.beans.Branch;
import BBS.beans.Department;
import BBS.beans.User;
import BBS.exception.NoRowsUpdatedRuntimeException;
import BBS.service.BranchService;
import BBS.service.DepartmentService;
import BBS.service.UserService;

@WebServlet(urlPatterns = { "/setting" })
@MultipartConfig(maxFileSize = 10000)
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		if (isValidURL(request, messages) == true) {

			int editUserId = Integer.parseInt(request.getParameter("id"));
			UserService userService = new UserService();
			User editUser = userService.getUser(editUserId);
			List<Branch> branches = new BranchService().getBranches();
			List<Department> departments = new DepartmentService().getDepartments();

			request.setAttribute("editUser", editUser);
			session.setAttribute("branches", branches);
			session.setAttribute("departments", departments);
			request.getRequestDispatcher("setting.jsp").forward(request, response);
		}else{
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("admin");
		}



	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		User editUser = getEditUser(request);

		if (isValid(request, messages) == true) {

			try {
				new UserService().update(editUser);
			} catch (NoRowsUpdatedRuntimeException e) {
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				request.setAttribute("errorMessages", messages);
				request.getRequestDispatcher("setting.jsp").forward(request, response);
			}

			session.setAttribute("loginUser", editUser);
			response.sendRedirect("admin");

		} else {
			request.setAttribute("errorMessages", messages);
			request.setAttribute("editUser", editUser);
			request.getRequestDispatcher("setting.jsp").forward(request, response);
		}
	}

	private User getEditUser(HttpServletRequest request) throws IOException, ServletException {

		User editUser = new UserService().getUser(Integer.parseInt(request.getParameter("id")));

		editUser.setAccount(request.getParameter("account"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		editUser.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
		editUser.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
		return editUser;
	}

	private boolean isValidURL(HttpServletRequest request, List<String> messages) {
		if(StringUtils.isEmpty(request.getParameter("id")) || !request.getParameter("id").matches("\\d{1,9}")) {
			messages.add("不正なURLです");
		} else{
			int id = Integer.parseInt(request.getParameter("id"));
			if((new UserService().getUser(id)) == null){
				messages.add("ユーザーが存在しません");
			}
		}

		if(messages.size()==0){
			return true;
			}else{
			return false;
			}
	}
	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String account = request.getParameter("account");
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}

		if (user.getAccount().equals(account)) {
			if (StringUtils.isEmpty(name) == true) {
				messages.add("名前を入力してください");
			}
			if (StringUtils.isEmpty(password) == true) {
				messages.add("パスワードを入力してください");
			}
		} else {
			if (new UserService().getUser(account) != null) {
				messages.add("すでに使われているアカウント名です");
			}
			if (StringUtils.isEmpty(name)== true) {
				messages.add("名前を入力してください");
			}
			if(StringUtils.isEmpty(password) == true){
			  messages.add("パスワードを入力してください");
			}
		}
		if(messages.size()==0){
		return true;
		}else{
		return false;
		}
	}
}
