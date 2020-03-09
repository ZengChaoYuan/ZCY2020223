package com.cyzy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyzy.bean.JsonMessage;
import com.cyzy.bean.Menu;
import com.cyzy.bean.Role;
import com.cyzy.bean.RoleMenu;
import com.cyzy.dao.RoleDao;
import com.cyzy.dao.RoleDaoImpl;
import com.cyzy.dao.RoleMenuDao;
import com.cyzy.service.MenuService;
import com.cyzy.service.RoleMenuService;
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
		} else if (roleAction != null && roleAction.equals("addBefore")) {
			addBefore(request, response);
		} else if (roleAction != null && roleAction.equals("delete")) {
			deleteRole(request, response);
		} else if (roleAction != null && roleAction.equals("updateBefore")) {
			updateBefore(request, response);
		} else if (roleAction != null && roleAction.equals("update")) {
			updateRole(request, response);
		} else if (roleAction != null && roleAction.equals("list")) {
			queryRoleList(request, response);
		} else if (roleAction != null && roleAction.equals("detail")) {
			roleDetail(request, response);
		} else if (roleAction != null && roleAction.equals("allMenu")) {
			allMenu(request, response);
		}
	}

	// 查询出所有的角色
	private void queryRoleList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Role role = new Role();
		RoleService roleService = (RoleService) ServiceFactory.getServiceImpl(RoleService.class.getName());
		List<Role> roleList = roleService.queryRole(role);
		request.setAttribute("roleList", roleList);
		// request.getRequestDispatcher("/admin/role/role_list.jsp").forward(request,
		// response);
		request.getRequestDispatcher("/admin/powerConfigure/configure_list.jsp").forward(request, response);
	}

	// 查询出所有的菜单
	private void allMenu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		MenuService menuService = (MenuService) ServiceFactory.getServiceImpl(MenuService.class.getName());
		List<Menu> menuList = new ArrayList<Menu>();
		menuList = menuService.queryAllMenu();
		String json = JSONObject.toJSONString(menuList);
		response.getWriter().println(json);
	}

	// 增加角色业务
	private void addBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 新增角色，取菜单的数据
		response.setContentType("text/html");
		MenuService menuService = (MenuService) ServiceFactory.getServiceImpl(MenuService.class.getName());
		List<Menu> menuList = new ArrayList<Menu>();
		menuList = menuService.queryAllMenu();
		request.setAttribute("menuList", menuList);
		request.getRequestDispatcher("/admin/role/role_addtree.jsp").forward(request, response);
	}

	// 增加角色业务
	private void addRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 防乱码
		response.setContentType("text/html");
		// 添加角色
		String menus = request.getParameter("menus");
		String roleName = request.getParameter("roleName");
		// 切割字符串
		String[] menuArr = menus.split(",");
		RoleService roleService = (RoleService) ServiceFactory.getServiceImpl(RoleService.class.getName());
		int result = roleService.addRole(roleName, menuArr);
		JsonMessage msg = new JsonMessage();
		msg.setMsg(result > 0 ? "创建成功" : "创建失败");
		String json = JSONObject.toJSONString(msg);
		response.getWriter().println(json);
	}

	// 删除角色业务
	private void deleteRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		RoleService roleService = (RoleService) ServiceFactory.getServiceImpl(RoleService.class.getName());
		int result = roleService.deleteRole(roleId);
		if (result > 0) {
			queryRoleList(request, response);
		}

	}

	// 修改角色业务
	private void updateBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String roleIdstr = request.getParameter("roleId");
		int roleId = Integer.parseInt(roleIdstr);
		// 查询该角色所拥有的菜单
		RoleService roleService = (RoleService) ServiceFactory.getServiceImpl(RoleService.class.getName());
		Role role = roleService.getRoleById(roleId);
		request.setAttribute("role", role);
		// 查询所有的菜单
		MenuService menuListService = (MenuService) ServiceFactory.getServiceImpl(MenuService.class.getName());
		List<Menu> menuList = new ArrayList<Menu>();
		menuList = menuListService.queryAllMenu();
		request.setAttribute("menuList", menuList);
		// 在这里判断，让menu.checked=true;
		for (Menu menu : menuList) {
			for (Menu menu1 : role.getMenuList()) {
				if (menu1.getMenuId() == menu.getMenuId()) {
					menu.setChecked(true);
				}
			}
		}
		request.getRequestDispatcher("/admin/powerConfigure/configure_update.jsp").forward(request, response);
		// request.getRequestDispatcher("/admin/role/role_update.jsp").forward(request,
		// response);
	}

	// 修改角色业务
	private void updateRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// 获取所选菜单
		String menuList = request.getParameter("menuList");//"2,12,15,3,18,19,"
		String[] menuIds = menuList.split(",");//[0] "2" [1] "12" [2] "15"....
		int menuId[] = new int[menuIds.length];//[0]  2  [1]  12  [2]  15....
		for (int i = 0; i < menuIds.length; i++) {
			menuId[i] = Integer.parseInt(menuIds[i]);
		}

		// 根据角色id
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		System.out.println(roleId);

		// 根据角色id删除角色菜单中间表
		RoleMenuService roleMenuService=(RoleMenuService)ServiceFactory.getServiceImpl(RoleMenuService.class.getName());

		int deleteResult = roleMenuService.deleteRoleMenu(roleId);
		if (deleteResult > 0) {
			// 根据角色id增加角色菜单中间表
			int result = 0;
			try {
				result = roleMenuService.addRoleMenu(roleId, menuId);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(1);
				msg.setMsg("修改成功!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			} else {
				JsonMessage msg = new JsonMessage();
				msg.setId(2);
				msg.setMsg("修改失败!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}

//		int roleId=Integer.parseInt(request.getParameter("roleId"));
//		String roleName=request.getParameter("roleName");
//		Role role=new Role(roleId,roleName);
//		RoleService roleService=(RoleService)ServiceFactory.getServiceImpl(RoleService.class.getName());
//		int result=roleService.updateRole(role);
//		queryRoleList(request,response);
	}

	private void roleDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String roleId = request.getParameter("roleId");
		RoleService roleService = (RoleService) ServiceFactory.getServiceImpl(RoleService.class.getName());
		Role role = roleService.getRoleById(Integer.parseInt(roleId));
		request.setAttribute("role", role);
		request.getRequestDispatcher("/admin/role/role_detail.jsp").forward(request, response);
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
