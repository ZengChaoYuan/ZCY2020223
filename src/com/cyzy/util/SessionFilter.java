package com.cyzy.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cyzy.bean.User;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
       
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;//向下转型，转成和HTTP协议相关的请求和响应
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("loginUser");
		String servletPath=req.getServletPath();
		//判断是否是登录页，登录servlet
		//indexOf是为包含
		if(servletPath!=null && servletPath.indexOf("/index.jsp")!=-1
				||servletPath.indexOf("/LoginServlet")!=-1
				||servletPath.indexOf(".css")!=-1
				||servletPath.indexOf(".js")!=-1
				||servletPath.indexOf(".jpg")!=-1
				||servletPath.indexOf(".png")!=-1) {
			chain.doFilter(request, response);//执行下一个过滤器，没有下一个过滤器，就直接访问到目标servlet
		}else {
			//比如登录界面、登录servlet请求，是不需要登录状态就可以直接访问的
			if(user==null) {
				//没有经过servlet,所以还是要写
				response.setContentType("text/html");
				request.setCharacterEncoding("UTF-8");
				
				PrintWriter out =response.getWriter();
				out.println("<script>");
				out.println("window.alert('会话失效了!');");
				out.println("window.top.location.href='"+req.getContextPath()+"/index.jsp';");
				out.println("</script>");
				
			}else {
				chain.doFilter(request, response);//执行下一个过滤器，没有下一个过滤器，就直接访问到目标servlet
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
