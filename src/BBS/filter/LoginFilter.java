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

@WebFilter("/*")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest)request).getSession();
		String target = ((HttpServletRequest)request).getServletPath();
		//String thisURI = ((HttpServetRequest)request).getRequestURI();
		User user = (User) session.getAttribute("loginUser");
		try{
			if(!target.equals("/login") && user == null){
				String message = "ログインしてください";
				session.setAttribute("errorMessages", message);
				((HttpServletResponse)response).sendRedirect("login");
				return;
			}

			chain.doFilter(request, response);
		}catch (ServletException se){
		}catch (IOException e){

		}
	}

	public void init(FilterConfig config) throws ServletException{
	}
	public void destroy() {
	}
}
