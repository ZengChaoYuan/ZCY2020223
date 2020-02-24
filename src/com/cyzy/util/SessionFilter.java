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
		HttpServletRequest req=(HttpServletRequest)request;//����ת�ͣ�ת�ɺ�HTTPЭ����ص��������Ӧ
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("loginUser");
		String servletPath=req.getServletPath();
		//�ж��Ƿ��ǵ�¼ҳ����¼servlet
		//indexOf��Ϊ����
		if(servletPath!=null && servletPath.indexOf("/index.jsp")!=-1
				||servletPath.indexOf("/LoginServlet")!=-1
				||servletPath.indexOf(".css")!=-1
				||servletPath.indexOf(".js")!=-1
				||servletPath.indexOf(".jpg")!=-1
				||servletPath.indexOf(".png")!=-1) {
			chain.doFilter(request, response);//ִ����һ����������û����һ������������ֱ�ӷ��ʵ�Ŀ��servlet
		}else {
			//�����¼���桢��¼servlet�����ǲ���Ҫ��¼״̬�Ϳ���ֱ�ӷ��ʵ�
			if(user==null) {
				//û�о���servlet,���Ի���Ҫд
				response.setContentType("text/html");
				request.setCharacterEncoding("UTF-8");
				
				PrintWriter out =response.getWriter();
				out.println("<script>");
				out.println("window.alert('�ỰʧЧ��!');");
				out.println("window.top.location.href='"+req.getContextPath()+"/index.jsp';");
				out.println("</script>");
				
			}else {
				chain.doFilter(request, response);//ִ����һ����������û����һ������������ֱ�ӷ��ʵ�Ŀ��servlet
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
