package com.cyzy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyzy.bean.Role;
import com.cyzy.bean.User;

import com.cyzy.service.RoleService;
import com.cyzy.service.UserService;
import com.cyzy.util.Page;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userAction = request.getParameter("userAction");
		if (userAction != null && userAction.equals("addBefore")) {
			addBefore(request, response);
		} else if (userAction != null && userAction.equals("add")) {
			addUser(request, response);
		} else if (userAction != null && userAction.equals("delete")) {
			deleteUser(request, response);
		} else if (userAction != null && userAction.equals("updateBefore")) {
			updateBefore(request, response);
		} else if (userAction != null && userAction.equals("update")) {
			updateUser(request, response);
		} else if (userAction != null && userAction.equals("list")) {
			queryUserList(request, response);
		} else if (userAction != null && userAction.equals("detail")) {
			userDetail(request, response);
		} else if (userAction != null && userAction.equals("listLike")) {
			listLike(request, response);
		}

//		else if (userAction != null && userAction.equals("userId")) {
//			byUserId(request, response);
//		}else if (userAction != null && userAction.equals("getRoleList")) {
//			getRoleList(request, response);
//		}
	}

	private void listLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		//当前页码
		String currentPageNumStr = request.getParameter("currentPageNum");
		int currentPageNum=(currentPageNumStr == null ? 1 : Integer.parseInt(currentPageNumStr));
		//模糊查询
		String userName=request.getParameter("userName");
		String roleValue=request.getParameter("roleId");
		int roleId=Integer.parseInt(roleValue == null ? "0":roleValue);
		
		User user = new User();
		user.setUserName(userName);
		user.setRoleId(roleId);
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		//List<User> userList = userService.queryUsers(user);
		//request.setAttribute("userList", userList);
		Page <User> page=userService.queryUsers(user, currentPageNum);
		request.setAttribute("page", page);
		//获取角色列表
		getRoleList(request, response);
		request.getRequestDispatcher("/user/user_list.jsp").forward(request, response);
	}
	
	

	private void userDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		byUserId(request, response);
		getRoleList(request, response);
		request.getRequestDispatcher("/user/user_detail.jsp").forward(request, response);
	}

	private void addBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 防乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		getRoleList(request, response);
		request.getRequestDispatcher("/user/user_add.jsp").forward(request, response);
	}

	// 获取角色列表
	private void getRoleList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Role role = new Role();
		RoleService roleService = (RoleService) ServiceFactory.getServiceImpl(RoleService.class.getName());
		List<Role> roleList = roleService.queryRole(role);
		request.setAttribute("roleList", roleList);
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 防乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		// 增加用户
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String realName = request.getParameter("realName");
		int sex = Integer.parseInt(request.getParameter("sex"));
		String birthday = request.getParameter("birthday");
		int roleId = Integer.parseInt(request.getParameter("roleId"));

		User user = new User(0, userName, password, realName, sex, birthday, roleId);

		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		int result = userService.addUser(user);
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("window.alert('"+(result==0?"失败":"成功")+"');");
			out.println("</script>");
			queryUserList(request, response);

	}

	// 执行修改操作前
	private void updateBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		byUserId(request, response);
		getRoleList(request, response);
		request.getRequestDispatcher("/user/user_update.jsp").forward(request, response);
	}

	// 得到个人用户信息
	private void byUserId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		User user = userService.getUserById(Integer.parseInt(userId));
		request.setAttribute("user", user);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		int userId = Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String realName = request.getParameter("realName");
		int sex = Integer.parseInt(request.getParameter("sex"));
		String birthday = request.getParameter("birthday");
		int roleId = Integer.parseInt(request.getParameter("roleId"));

		User user = new User(userId, userName, password, realName, sex, birthday, roleId);

		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		int result = userService.updateUser(user);
		System.out.println("result:" + result);
		if (result > 0) {
			queryUserList(request, response);
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		int result = userService.deleteUser(Integer.parseInt(userId));
		if (result > 0) {
			queryUserList(request, response);
		}
	}

	private void queryUserList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
			
		User user = new User();
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		List<User> userList = userService.queryUsers(user);
		request.setAttribute("userList", userList);
		
		getRoleList(request,response);
		request.getRequestDispatcher("/user/user_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
