package BBS.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BBS.beans.Account;
import BBS.beans.User;
import BBS.service.AccountService;
import BBS.service.UserService;

@WebServlet(urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		User user = (User) request.getSession().getAttribute("loginUser");
		boolean isShowUserAccount;
		if (user != null){
			isShowUserAccount = true;
		}else{
			isShowUserAccount = false;
		}

		List<Account> account = new AccountService().getAccount();

		request.setAttribute("account", account);
		request.setAttribute("isShowUserAccount", isShowUserAccount);

		request.getRequestDispatcher("/admin.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		System.out.println(request.getParameter("id"));
		User stopUser = new UserService().getUser(Integer.parseInt(request.getParameter("id")));
		stopUser.setIs_stopped(1);

	}
}
