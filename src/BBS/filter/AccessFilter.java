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

@WebFilter(urlPatterns = { "/admin", "/signup", "/setting" })
public class AccessFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User) session.getAttribute("loginUser");

		try {
			if (user != null) {
				if (user.getDepartment_id() == 1) {
					chain.doFilter(request, response);
				} else {
					String message = "この機能は本社総務部のみ利用できます";
					session.setAttribute("errorMessages", message);
					((HttpServletResponse) response).sendRedirect("./");
					return;
				}
			} else {
				chain.doFilter(request, response);
			}

		} catch (ServletException se) {
		} catch (IOException e) {

		}
	}

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}
}
