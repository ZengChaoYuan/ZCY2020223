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
import com.cyzy.dao.RoleDao;
import com.cyzy.dao.RoleDaoImpl;
import com.cyzy.service.MenuService;
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
		}else if (roleAction != null && roleAction.equals("allMenu")) {
			allMenu(request, response);
		}
	}
	    //��ѯ�����еĽ�ɫ
		private void queryRoleList(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			response.setContentType("text/html");
			Role role = new Role();
			RoleService roleService = (RoleService) ServiceFactory.getServiceImpl(RoleService.class.getName());
			List<Role> roleList = roleService.queryRole(role);
			request.setAttribute("roleList", roleList);
			request.getRequestDispatcher("/admin/role/role_list.jsp").forward(request, response);
			//request.getRequestDispatcher("/admin/powerConfigure/configure_list.jsp").forward(request, response);
		}
	
	
	//��ѯ�����еĲ˵�
	private void allMenu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		MenuService menuService=(MenuService)ServiceFactory.getServiceImpl(MenuService.class.getName());
		List<Menu> menuList=new ArrayList<Menu>();
		menuList=menuService.queryAllMenu();
		String json=JSONObject.toJSONString(menuList);
		response.getWriter().println(json);
	}

	// ���ӽ�ɫҵ��
	private void addBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//������ɫ��ȡ�˵�������
		response.setContentType("text/html");
		MenuService menuService=(MenuService)ServiceFactory.getServiceImpl(MenuService.class.getName());
		List<Menu> menuList=new ArrayList<Menu>();
		menuList=menuService.queryAllMenu();
		request.setAttribute("menuList", menuList);
		request.getRequestDispatcher("/admin/role/role_addtree.jsp").forward(request, response);
	}

	// ���ӽ�ɫҵ��
	private void addRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//������
		response.setContentType("text/html");
		//��ӽ�ɫ
		String menus=request.getParameter("menus");
		String roleName=request.getParameter("roleName");
		//�и��ַ���
		String[] menuArr=menus.split(",");
	    RoleService roleService=(RoleService)ServiceFactory.getServiceImpl(RoleService.class.getName());
		int result=roleService.addRole(roleName, menuArr);
		JsonMessage msg=new JsonMessage();
		msg.setMsg(result>0?"�����ɹ�":"����ʧ��");
		String json = JSONObject.toJSONString(msg);
		response.getWriter().println(json);
	}

	// ɾ����ɫҵ��
	private void deleteRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int roleId=Integer.parseInt(request.getParameter("roleId"));
		RoleService roleService=(RoleService)ServiceFactory.getServiceImpl(RoleService.class.getName());
		int result=roleService.deleteRole(roleId);
		if(result>0) {
			queryRoleList(request,response);
		}
		
	}

	// �޸Ľ�ɫҵ��
	private void updateBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String roleIdstr=request.getParameter("roleId");
//		int roleId=Integer.parseInt(roleIdstr);
//		//��ѯ�ý�ɫ��ӵ�еĲ˵�
//		RoleService roleService=(RoleService)ServiceFactory.getServiceImpl(RoleService.class.getName());
//		
//		//��ѯ���еĲ˵�
//		MenuService menuListService=(MenuService)ServiceFactory.getServiceImpl(MenuService.class.getName());
//        List<Menu> allMenu=new ArrayList<Menu>();
//        allMenu=menuListService.queryAllMenu();
       
		//�������ж��û���ӵ�еĲ˵������в˵�,��menu.checked=true
//        for(Menu menu:allMenu) {
//        	for(Menu menus:role) {
//        		if(role.==menu.getMenuId()) {
//        			menu.setChecked(true);
//        		}
//        	}
//        }
	  response.setContentType("text/html");
	  String roleIdstr=request.getParameter("roleId");
	  int roleId=Integer.parseInt(roleIdstr);
	  RoleService roleService=(RoleService)ServiceFactory.getServiceImpl(RoleService.class.getName());
	  Role role=roleService.getRoleById(roleId);
	  request.setAttribute("role", role);
	  
	  //����һ�����в˵���ǰ��չʾ
	  MenuService menuListService=(MenuService)ServiceFactory.getServiceImpl(MenuService.class.getName());
	  List<Menu> menuList=new ArrayList<Menu>();
	  menuList=menuListService.queryAllMenu();
	  request.setAttribute("menuList", menuList);
	  //�������жϣ���menu.checked=true;
	  for(Menu menu:menuList) {
		  for(Menu menu1:role.getMenuList()) {
			  if(menu1.getMenuId() == menu.getMenuId()) {
				  menu.setChecked(true);
			  }
		  }
	  }
	  
	  
	  request.getRequestDispatcher("/admin/role/role_update.jsp").forward(request, response);
	  
	}
	

	// �޸Ľ�ɫҵ��
	private void updateRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		int roleId=Integer.parseInt(request.getParameter("roleId"));
		String roleName=request.getParameter("roleName");
		Role role=new Role(roleId,roleName);
		RoleService roleService=(RoleService)ServiceFactory.getServiceImpl(RoleService.class.getName());
		int result=roleService.updateRole(role);

		queryRoleList(request,response);
	}

	

	private void roleDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  response.setContentType("text/html");
		  String roleId=request.getParameter("roleId");
		  RoleService roleService=(RoleService)ServiceFactory.getServiceImpl(RoleService.class.getName());
		  Role role=roleService.getRoleById(Integer.parseInt(roleId));
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
