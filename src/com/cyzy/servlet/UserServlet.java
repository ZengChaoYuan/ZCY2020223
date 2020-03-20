package com.cyzy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyzy.bean.Area;
import com.cyzy.bean.JsonMessage;
import com.cyzy.bean.OrderCount;
import com.cyzy.bean.Param;
import com.cyzy.bean.Role;
import com.cyzy.bean.User;
import com.cyzy.service.AreaService;
import com.cyzy.service.ParamService;
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

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userAction = request.getParameter("userAction");
		if (userAction != null && userAction.equals("list")) {
			queryUserList(request, response);
		} else if (userAction != null && userAction.equals("listLike")) {
			listLike(request, response);
		} else if (userAction != null && userAction.equals("useStatus")) {
			useStatus(request, response);
		} else if (userAction != null && userAction.equals("deleteStatus")) {
			deleteStatus(request, response);
		} else if (userAction != null && userAction.equals("resetPassword")) {
			resetPassword(request, response);
		} else if (userAction != null && userAction.equals("checkName")) {
			checkName(request, response);
		} else if (userAction != null && userAction.equals("addBefore")) {
			addBefore(request, response);
		}else if (userAction != null && userAction.equals("addAdminUser")) {
			//����Ա�����̨�û�
			addAdminUser(request, response);
		} else if (userAction != null && userAction.equals("add")) {
			//����Ա�����̨�û�
			addUser(request, response);
		} else if (userAction != null && userAction.equals("delete")) {
			deleteUser(request, response);
		} else if (userAction != null && userAction.equals("updateBefore")) {
			updateBefore(request, response);
		} else if (userAction != null && userAction.equals("update")) {
			updateUser(request, response);
		} else if (userAction != null && userAction.equals("detail")) {
			userDetail(request, response);
		}else if (userAction != null && userAction.equals("queryOrderCount")) {
			//�鿴ԤԼ��
			queryOrderCount(request, response);
		}

	}
	
	//�鿴ԤԼ��
	private void queryOrderCount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ֹ����
		response.setContentType("text/html");
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		UserService userService=(UserService)ServiceFactory.getServiceImpl(UserService.class.getName());
		List<OrderCount>orderCountList=userService.queryAllOrderCountByUser(startTime, endTime);
		request.setAttribute("orderCountList", orderCountList);
		request.getRequestDispatcher("/admin/orderCount/orderCount_list.jsp").forward(request, response);
		
	}

	private void queryUserList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ֹ����
		response.setContentType("text/html");
		// ��ǰҳ��
		String currentPageNumStr = request.getParameter("currentPageNum");
		int currentPageNum = (currentPageNumStr == null ? 1 : Integer.parseInt(currentPageNumStr));

		User user = new User();
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		Page<User> page = userService.queryUserList(user, currentPageNum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/adminUser/user_list.jsp").forward(request, response);
	}

	private void checkName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ֹ����
		response.setContentType("text/html");
		String userName = request.getParameter("userName");
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		User user = userService.checkUserName(userName);
		JsonMessage msg = new JsonMessage();
		msg.setMsg(user == null ? "���û�������ʹ��" : "������ע�����");
		String json = JSONObject.toJSONString(msg);
		response.getWriter().println(json);
	}

	private void listLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ֹ����
		response.setContentType("text/html");

		// ��ǰҳ��
		String currentPageNumStr = request.getParameter("currentPageNum");
		int currentPageNum = (currentPageNumStr == null ? 1 : Integer.parseInt(currentPageNumStr));
		// ģ����ѯ
		String userName = request.getParameter("userName");
		String roleValue = request.getParameter("roleId");
		int roleId = Integer.parseInt(roleValue == null ? "0" : roleValue);

		User user = new User();
		user.setUserName(userName);
		user.setRoleId(roleId);
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		Page<User> page = userService.queryUserList(user, currentPageNum);
		request.setAttribute("page", page);
		// ��ȡ��ɫ�б�
		getRoleList(request, response);
		request.getRequestDispatcher("/user/user_list.jsp").forward(request, response);
	}

	//����/����
	private void useStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ͨ���û�ID���õ���̨�û���������Ϣ
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		User user = userService.getUserById(userId);
		if (user.getUseStatus() == 1) {
			user.setUseStatus(2);
			int result = userService.updateUseStatus(user);
			if (result > 0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(1);
				msg.setMsg("���óɹ�!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		} else if (user.getUseStatus() == 2) {
			user.setUseStatus(1);
			int result = userService.updateUseStatus(user);
			if (result > 0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(2);
				msg.setMsg("���óɹ�!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}

	}
    //ɾ��״̬
	private void deleteStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// ͨ���û�ID���õ���̨�û���������Ϣ
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		User user = userService.getUserById(userId);
        if(user.getDeleteStatus()==1) {
        	user.setDeleteStatus(0);
        	int result=userService.updateDeleteStatus(user);
        	if(result>0) {
        		JsonMessage msg=new JsonMessage();
				msg.setId(1);
				msg.setMsg("ɾ���ɹ�!");
				String json=JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
        	}else {
        		JsonMessage msg=new JsonMessage();
				msg.setId(2);
				msg.setMsg("ɾ��ʧ��!");
				String json=JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
        	}        	
        }else {
        	JsonMessage msg=new JsonMessage();
			msg.setId(3);
			msg.setMsg("�û��Ѿ���ɾ��,�����ظ�����!");
			String json=JSONObject.toJSONString(msg);
			response.getWriter().println(json);
			return;
        }
	}
    //��������
	private void resetPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        response.setContentType("text/html");		
		//��ѯ���������е�����������Ϣ
		int paramId=1;
		ParamService paramService=(ParamService)ServiceFactory.getServiceImpl(ParamService.class.getName());
		Param param=paramService.getParamById(paramId);
		String resetPassword=param.getResetPassword();
		// ͨ���û�ID���õ���̨�û���������Ϣ
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		User user = userService.getUserById(userId);
		user.setPassword(resetPassword);
		int result=userService.resetPassword(user);
		if(result>0) {
			JsonMessage msg=new JsonMessage();
			msg.setId(1);
			msg.setMsg("�������óɹ�!");
			String json=JSONObject.toJSONString(msg);
			response.getWriter().println(json);
			return;
		}else {
			JsonMessage msg=new JsonMessage();
			msg.setId(2);
			msg.setMsg("��������ʧ��!");
			String json=JSONObject.toJSONString(msg);
			response.getWriter().println(json);
			return;
		}
		
	}
	
	
	// �õ������û���Ϣ
	private void byUserId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		User user = userService.getUserById(Integer.parseInt(userId));
		request.setAttribute("user", user);
	}

	private void userDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ֹ����
		response.setContentType("text/html");

		byUserId(request, response);
		getRoleList(request, response);
		request.getRequestDispatcher("/user/user_detail.jsp").forward(request, response);
	}
    //����Ա�����û�ǰ
	private void addBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//������
		response.setContentType("text/html");
		// ��ȡ��ɫ�б�
		getRoleList(request, response);
		//��ȡ�����б�
		AreaService areaService=(AreaService)ServiceFactory.getServiceImpl(AreaService.class.getName());
		List<Area> areaList= areaService.queryArea();
		request.setAttribute("areaList", areaList);
		request.getRequestDispatcher("/admin/adminUser/user_add.jsp").forward(request, response);
	}

	// ��ȡ��ɫ�б�
	private void getRoleList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Role role = new Role();
		RoleService roleService = (RoleService) ServiceFactory.getServiceImpl(RoleService.class.getName());
		List<Role> roleList = roleService.queryRole(role);
		request.setAttribute("roleList", roleList);
	}

	private void addAdminUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}


	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ������
		response.setContentType("text/html");

		// �����û�
		String userName = request.getParameter("userName");
		UserService checkUserService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		User checkUser = checkUserService.checkUserName(userName);
		if (checkUser != null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.alert('" + (checkUser == null ? "���û����Ѵ���" : "���û�������") + "');");
			out.println("</script>");
			queryUserList(request, response);

		} else {
			String password = request.getParameter("password");
			String realName = request.getParameter("realName");
			int sex = Integer.parseInt(request.getParameter("sex"));
			String birthday = request.getParameter("birthday");
			int roleId = Integer.parseInt(request.getParameter("roleId"));

			User user = new User(0, userName, password, roleId, roleId, realName, sex, roleId, birthday, birthday,
					roleId, birthday, birthday);

			UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
			int result = 0;
			try {
				result = userService.addUser(user);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.alert('" + (result == 0 ? "���ʧ��" : "��ӳɹ�") + "');");
			out.println("</script>");
			queryUserList(request, response);
		}

	}

	// ִ���޸Ĳ���ǰ
	private void updateBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		byUserId(request, response);
		getRoleList(request, response);
		request.getRequestDispatcher("/user/user_update.jsp").forward(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int userId = Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String realName = request.getParameter("realName");
		int sex = Integer.parseInt(request.getParameter("sex"));
		String birthday = request.getParameter("birthday");
		int roleId = Integer.parseInt(request.getParameter("roleId"));

//		User user = new User(userId, userName, password, realName, sex, birthday, roleId);

//		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
//		int result = userService.updateUser(user);
//		System.out.println("result:" + result);
//		if (result > 0) {
//			queryUserList(request, response);
//		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String userId = request.getParameter("userId");
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		int result = userService.deleteUser(Integer.parseInt(userId));
		if (result > 0) {
			queryUserList(request, response);
		}
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
