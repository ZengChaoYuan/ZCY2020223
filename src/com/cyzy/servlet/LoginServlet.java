package com.cyzy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cyzy.bean.Menu;
import com.cyzy.bean.User;
import com.cyzy.service.MenuService;
import com.cyzy.service.UserService;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        
    }
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String loginAction=request.getParameter("loginAction");
		if (loginAction != null && loginAction.equals("login")) {
			login(request,response);
		}else if (loginAction != null && loginAction.equals("loginOut")) {
			loginOut(request,response);
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		//��ѯ�û��ǲ��Ǵ���
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
	    UserService userService=(UserService)ServiceFactory.getServiceImpl(UserService.class.getName());
	    User user=userService.login(userName, password);
	    if(user!=null) {
			HttpSession session=request.getSession();
			//session.setMaxInactiveInterval(10);//��ʱʱ������Ϊ��λ
			session.setAttribute("loginUser", user);
			//��Ҫ��ѯ���û���ӵ�еĲ˵�
			//�˵��б��ӵ�request/session��Χ
			List<Menu> menuList=new ArrayList<Menu>();
			MenuService menuService=(MenuService)ServiceFactory.getServiceImpl(MenuService.class.getName());
			menuList=menuService.queryMenuByuserName(userName);
			request.getSession().setAttribute("menuList", menuList);
			//��Ϊҳ������iframe���,����ҳ��requestû�а취ȡ��
			//request.setAttribute("menuList", menuList);
			request.getRequestDispatcher("/admin/adminMain.jsp").forward(request, response);
			
		}else {
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("window.alert('�û�������');");
			out.println("window.location.href='http://localhost:8080/JF190902/index.jsp';");
			out.println("</script>");
		}		
	}
	
	private void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		session.removeAttribute("loginUser");
		response.sendRedirect("http://localhost:8080/JF190902/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
