package com.cyzy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyzy.bean.Menu;
import com.cyzy.service.MenuService;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class MenuServlet
 */
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String menuAction = request.getParameter("menuAction");
		if (menuAction != null && menuAction.equals("list")) {
			queryMenuList(request,response);
		}
		
	}
	private void queryMenuList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ·ÀÖ¹ÂÒÂë
		response.setContentType("text/html");
		List<Menu> menuList=new ArrayList<Menu>();
		MenuService menuService=(MenuService)ServiceFactory.getServiceImpl(MenuService.class.getName());
		menuList=menuService.queryAllMenu();
		request.setAttribute("menuList", menuList);
		request.getRequestDispatcher("/admin/menu/menu_list.jsp").forward(request, response);		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
