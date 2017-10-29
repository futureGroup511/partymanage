package cn.edu.hist.partymanage.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hist.partymanage.entity.Role;
import cn.edu.hist.partymanage.entity.User;

/**
 * Servlet Filter implementation class BackendFilter
 */
public class BackendFilter implements Filter {

    /**
     * Default constructor. 
     */
    public BackendFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest)request;
		User user = (User) req.getSession().getAttribute("user");
		if(user == null){
			String returnUrl = req.getContextPath() + "/index.jsp";
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); // 转码
			response.getWriter().println(
							"<script language=\"javascript\">"
							+ "if(window.opener==null){window.top.location.href=\""
									+ returnUrl+ "\";}else{window.opener.top.location.href=\""
									+ returnUrl
									+ "\";window.close();}</script>");
			return;
		}
		switch (user.getType()) {
		case 1:
			req.getSession().setAttribute("userDepartmentId", user.getOrganizationId());
			req.getSession().setAttribute("userDepartment",user.getOrganizationName());
			break;
		case 2:
			req.getSession().setAttribute("userDepartmentId", user.getPartyId());
			req.getSession().setAttribute("userDepartment",user.getPartyName());
			break;
		case 3:
			req.getSession().setAttribute("userDepartmentId", user.getBranchId());
			req.getSession().setAttribute("userDepartment",user.getBranchName());
			break;
		case 4:
			req.getSession().setAttribute("userDepartmentId", user.getBranchId());
			req.getSession().setAttribute("userDepartment",user.getBranchName());
			break;
		case 5:
			req.getSession().setAttribute("userDepartmentId", user.getBranchId());
			req.getSession().setAttribute("userDepartment",user.getBranchName());
			break;
		default:
			return;
		}
		req.getSession().setAttribute("userRole",Role.getRole(user.getType()));
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
