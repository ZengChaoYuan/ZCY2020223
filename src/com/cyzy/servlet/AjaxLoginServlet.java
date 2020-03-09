package com.cyzy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.cyzy.bean.Customer;
import com.cyzy.bean.JsonMessage;
import com.cyzy.bean.Menu;
import com.cyzy.bean.User;
import com.cyzy.service.CustomerService;
import com.cyzy.service.MenuService;
import com.cyzy.service.UserService;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class AjaxLoginServlet
 */
public class AjaxLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxLoginServlet() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		String sessionCode = (String) request.getSession().getAttribute("code");
		if (!code.equals(sessionCode)) {
			JsonMessage msg = new JsonMessage();
			msg.setId(1);
			msg.setMsg("验证码错误!!!");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
		} else {
			UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
			User user = userService.login(userName, password);
			System.out.println(user);
			// 判断是否被禁用
			if (user == null) {
				JsonMessage msg = new JsonMessage();
				msg.setId(2);
				msg.setMsg("用戶名或者密码错误!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;

			} else {

				if (user.getDeleteStatus() != 1) {
					JsonMessage msg = new JsonMessage();
					msg.setId(3);
					msg.setMsg("用户不存在!!!");
					String json = JSONObject.toJSONString(msg);
					response.getWriter().println(json);
					return;
				} else if (user.getUseStatus() != 1) {

					JsonMessage msg = new JsonMessage();
					msg.setId(4);
					msg.setMsg("用户被禁用!!!");
					String json = JSONObject.toJSONString(msg);
					response.getWriter().println(json);
					return;
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("loginUser", user);
					List<com.cyzy.bean.Menu> menuList = new ArrayList<com.cyzy.bean.Menu>();
					MenuService menuService = (MenuService) ServiceFactory.getServiceImpl(MenuService.class.getName());
					menuList = menuService.queryMenuByuserName(userName);
					request.getSession().setAttribute("menuList", menuList);
					JsonMessage msg = new JsonMessage();
					msg.setId(5);
					msg.setMsg("登录成功!");
					msg.setLocation(request.getContextPath() + "/admin/adminMain.jsp");
					String json = JSONObject.toJSONString(msg);
					response.getWriter().println(json);
				}

			}

		}
	}

}
