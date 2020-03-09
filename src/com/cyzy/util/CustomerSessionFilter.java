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

import com.cyzy.bean.Customer;

/**
 * Servlet Filter implementation class CustomerSessionFilter
 */
public class CustomerSessionFilter implements Filter {
    /**
     * Default constructor. 
     */
    public CustomerSessionFilter() {
       
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
		Customer customer=(Customer)session.getAttribute("loginCustomer");
		String servletPath=req.getServletPath();
		//判断是否是登录页，登录servlet
				//indexOf是为包含
				if(servletPath!=null && servletPath.indexOf("/index.jsp")!=-1
						||servletPath.indexOf("/front/login.jsp")!=-1
						||servletPath.indexOf("/front/register.jsp")!=-1
						||servletPath.indexOf("/CustomerLoginServlet")!=-1
						||servletPath.indexOf("/CustomerServlet")!=-1
						||servletPath.indexOf("/AjaxLoginServlet")!=-1
						||servletPath.indexOf("/ImageServlet")!=-1
						||servletPath.indexOf("/CountServlet")!=-1
						||servletPath.indexOf(".css")!=-1
						||servletPath.indexOf(".js")!=-1
						||servletPath.indexOf(".jpg")!=-1
						||servletPath.indexOf(".png")!=-1) {
					chain.doFilter(request, response);//执行下一个过滤器，没有下一个过滤器，就直接访问到目标servlet
				}else {
					
						chain.doFilter(request, response);//执行下一个过滤器，没有下一个过滤器，就直接访问到目标servlet
					
				}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
