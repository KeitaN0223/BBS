package BBS.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BBS.beans.User;
import BBS.service.UserService;

@WebFilter("/*")
public class UserStopDeleteFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest)request).getSession();
		User user = (User) session.getAttribute("loginUser");
		if(user != null){
			String target = ((HttpServletRequest)request).getServletPath();

			User checkUser = new UserService().getUser(user.getId());

			try{
				if(!target.equals("/login") && checkUser == null){
					String message = "アカウントが削除されています";
					session.setAttribute("errorMessages", message);
					((HttpServletResponse)response).sendRedirect("login");
					return;
				}else if(!target.equals("/login") && checkUser.getIs_stopped() == 1){
					String message = "アカウントが停止されています";
					session.setAttribute("errorMessages", message);
					((HttpServletResponse)response).sendRedirect("login");
				}else{
				chain.doFilter(request, response);
				}
			}catch (ServletException se){
			}catch (IOException e){

			}
		}else{
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) throws ServletException{
	}
	public void destroy() {
	}
}
