package BBS.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BBS.beans.User;
import BBS.service.AccountService;
import BBS.service.UserService;

@WebServlet(urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		List<User> account = new AccountService().getAccount();

		request.setAttribute("account", account);
//		request.setAttribute("isShowUserAccount", isShowUserAccount);

		request.getRequestDispatcher("/admin.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		User isStopUser = new UserService().getUser(Integer.parseInt(request.getParameter("id")));

		if(isStopUser.getIs_stopped()==0){
			isStopUser.setIs_stopped(1);
			new UserService().stopUser(isStopUser);
		}else if(isStopUser.getIs_stopped()==1){
			isStopUser.setIs_stopped(0);
			new UserService().startUser(isStopUser);
		}


		response.sendRedirect("admin");
	}
}
