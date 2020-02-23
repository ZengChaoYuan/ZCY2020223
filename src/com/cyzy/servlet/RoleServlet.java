package com.cyzy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyzy.bean.Role;
import com.cyzy.dao.RoleDao;
import com.cyzy.dao.RoleDaoImpl;
import com.cyzy.service.RoleService;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class RoleServlet
 */
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoleServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleAction = request.getParameter("roleAction");
		if (roleAction != null && roleAction.equals("add")) {
			addRole(request, response);
		} else if (roleAction != null && roleAction.equals("delete")) {
			deleteRole(request, response);
		} else if (roleAction != null && roleAction.equals("updateBefore")) {
			updateBeforeRole(request, response);
		} else if (roleAction != null && roleAction.equals("update")) {
			updateRole(request, response);
		} else if (roleAction != null && roleAction.equals("list")) {
			queryRoleList(request, response);
		}else if (roleAction != null && roleAction.equals("detail")) {
			roleDetail(request, response);
		}
	}

	// 增加角色业务
	private void addRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// 删除角色业务
	private void deleteRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// 修改角色业务
	private void updateBeforeRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// 修改角色业务
	private void updateRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// 查询角色业务
	private void queryRoleList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		Role role = new Role();
		RoleService roleService=(RoleService)ServiceFactory.getServiceImpl(RoleService.class.getName());
		List<Role> roleList =roleService.queryRole(role);
		request.setAttribute("roleList", roleList);
		request.getRequestDispatcher("/role/role_list.jsp").forward(request, response);

	}
	
	private void roleDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
