package com.cyzy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyzy.bean.Area;
import com.cyzy.bean.Customer;
import com.cyzy.bean.JsonMessage;
import com.cyzy.bean.MyAccount;
import com.cyzy.bean.PreOrder;
import com.cyzy.bean.PreOrderCount;
import com.cyzy.bean.User;
import com.cyzy.service.AreaService;
import com.cyzy.service.CustomerService;
import com.cyzy.service.DictService;
import com.cyzy.service.MyAccountService;
import com.cyzy.service.PreOrderService;
import com.cyzy.service.UserService;
import com.cyzy.util.Page;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class PreOrderServlet
 */
public class PreOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PreOrderServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String preOrderAction = request.getParameter("preOrderAction");
		if (preOrderAction != null && preOrderAction.equals("myPreOrderList")) {
			// �ҵ�ԤԼ�б�
			myPreOrderList(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("wantPreOrder")) {
			// ��ҪԤԼ
			wantPreOrder(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("surePreOrder")) {
			// ��ѯʦȷ�����
			surePreOrder(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("stopPreOrder")) {
			// ����Ա��ֹԤԼ
			stopPreOrder(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("allPreOrderList")) {
			// ����Ա�鿴���е�ԤԼ�б�(���е��û�����ѯʦ)
			allPreOrderList(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("allPreOrderListByuserId")) {
			// ��ѯʦ�鿴�Լ����û�ԤԼ�б�
			allPreOrderListByuserId(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("preOrderDetail")) {
			// �鿴����
			preOrderDetail(request, response);
		}else if (preOrderAction != null && preOrderAction.equals("assessReplyBefore")) {
			// ��ѯʦ��ϴ�ǰ
			assessReplyBefore(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("assessReply")) {
			// ��ѯʦ��ϴ�
			assessReply(request, response);
		}  else if (preOrderAction != null && preOrderAction.equals("consultercheckDetail")) {
			// ��ѯʦ�鿴����
			consultercheckDetail(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("usercheckDetail")) {
			// ����Ա�鿴����
			usercheckDetail(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("queryUserInfo")) {
			// �鿴��ѯʦ������Ϣ
			queryUserInfo(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("assessConsulterBefore")) {
			// ������ѯʦǰ
			assessConsulterBefore(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("assessConsulter")) {
			// ������ѯʦ
			assessConsulter(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("queryAreaBeforePre")) {
			// ��ҪԤԼ�е���ҪԤԼ,��ѯ������
			queryAreaBeforePre(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("queryAreaDownUser")) {
			// ��ҪԤԼ�е���ҪԤԼ,��ѯ������
			selectUserId(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("activeQueryUserInfo")) {
			// ��̬��ѯ���û���Ϣͨ��userId
			activeQueryUserInfo(request, response);
		}
	}

	// ��ҪԤԼ
	private void wantPreOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// ��ҪԤԼҪ������Щ�����أ�����ID����ѯʦID,�����������ޣ��ޣ�����۸�500������idΪ1���Լ���ID,ԤԼ״̬Ϊ2����ҪԤԼ����ԤԼʱ��Ϊ���ڼ���ʱ��
		int userId = Integer.parseInt(request.getParameter("userId"));
		String problemDesc = request.getParameter("problemDesc");
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		User user = userService.getUserById(userId);
		int preExpense = user.getPreExpense();
		// int preorderPrice=Integer.parseInt(request.getParameter("preorderPrice"));
		int areaId = Integer.parseInt(request.getParameter("areaId"));
		Customer customer = (Customer) request.getSession().getAttribute("loginCustomer");
		int customerId = customer.getCustomerId();
		// ԤԼʱ�����������
		String orderDate = request.getParameter("orderDate");
		String hours = request.getParameter("hours");
		String preorderTime = orderDate + " " + hours;
		System.out.println(preorderTime);
		PreOrder preOrder = new PreOrder();
		preOrder.setUserId(userId);
		preOrder.setProblemDesc(problemDesc);
		preOrder.setPreorderPrice(preExpense);
		preOrder.setAreaId(areaId);
		preOrder.setCustomerId(customerId);
		preOrder.setPreorderTime(preorderTime);
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		int result = 0;
		try {
			result = preOrderService.addPreOrder(preOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result > 0) {
			JsonMessage msg = new JsonMessage();
			msg.setId(1);
			msg.setMsg("��ԤԼ!");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
			return;
		}
	}

	// ȷ��ԤԼ(��ѯʦ��Ǯ���ͻ���Ǯ������˻������ԤԼ������)
	private void surePreOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int preorderId = Integer.parseInt(request.getParameter("preorderId"));
		// ͨ��ԤԼID�õ����е�ԤԼ��Ϣ
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		PreOrder preOrder = preOrderService.queryPreOrderById(preorderId);
		int userId = preOrder.getUserId();
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		User user = userService.getUserById(userId);
		// ��ѯʦ������
		String userName = user.getUserName();
		PreOrderCount preOrderCount = new PreOrderCount();
		preOrderCount.setUserName(userName);
		// �������ݵ�ԤԼ��������
		int countResult = 0;
		try {
			countResult = userService.addOrderCount(preOrderCount);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int customerId = preOrder.getCustomerId();
		CustomerService customerService = (CustomerService) ServiceFactory
				.getServiceImpl(CustomerService.class.getName());
		Customer customer = customerService.getCustomerById(customerId);
		// �˿����
		int customerMoney = customer.getBalance();
		// ��ѯʦ��ѯ����
		int userExpense = user.getPreExpense();

		MyAccount myAccount = new MyAccount();
		myAccount.setHappenThing("��ѯԤԼ");
		myAccount.setCustomerId(customerId);
		myAccount.setConsumpType("֧��");
		myAccount.setConsumpMoney(userExpense);
		myAccount.setUserId(userId);
		if (customerMoney >= userExpense) {
			// ԤԼ�ɹ���״̬Ϊ��ȷ��
			preOrder.setOrderStatus(3);
			int statusResult = preOrderService.stopPreOrder(preOrder);
			// �˿͵�������
			int customerBalance = customerMoney - userExpense;
			customer.setBalance(customerBalance);
			int customerResult = customerService.updateBalance(customer);
			// ��ѯʦ���������
			int oldUserBalance = user.getBalance();
			int userBalance = oldUserBalance + userExpense;
			user.setBalance(userBalance);
			int userResult = userService.updateBalance(user);
			// �������ݵ��˻�����
			MyAccountService myAccountService = (MyAccountService) ServiceFactory
					.getServiceImpl(MyAccountService.class.getName());
			int result = 0;
			try {
				result = myAccountService.addCustomerAccount(myAccount);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (statusResult > 0 && customerResult > 0 && userResult > 0 && result > 0 && countResult > 0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(1);
				msg.setMsg("��ȷ��ԤԼ!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		} else {
			// ԤԼʧ��
			preOrder.setOrderStatus(1);
			int result = preOrderService.stopPreOrder(preOrder);
			if (result > 0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(2);
				msg.setMsg("ԤԼʧ��!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}
	}

	// ����Ա��ֹԤԼ
	private void stopPreOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		int preorderId = Integer.parseInt(request.getParameter("preorderId"));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		// ͨ��ԤԼId�õ�ԤԼ��״̬
		PreOrder preOrder = preOrderService.queryPreOrderById(preorderId);
		if (preOrder.getOrderStatus() == 2) {
			preOrder.setOrderStatus(6);
			int result = preOrderService.stopPreOrder(preOrder);
			if (result > 0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(1);
				msg.setMsg("��ֹ�ɹ�!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			} else {
				JsonMessage msg = new JsonMessage();
				msg.setId(2);
				msg.setMsg("��ֹʧ��!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
			}
		}

	}

	private void allPreOrderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// ��ǰҳ��
		String currentPageNumStr = request.getParameter("currentPageNum");
		int currentPageNum = (currentPageNumStr == null ? 1 : Integer.parseInt(currentPageNumStr));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		// List<Map<String, Object>>
		// allPreOrderList=preOrderService.queryAllPreOrderList();
		Page<Map<String, Object>> page = preOrderService.queryAllPreOrders(currentPageNum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/preOrder/all_preorder.jsp").forward(request, response);
	}

	// ��ѯʦ��ѯ
	private void allPreOrderListByuserId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// ��ѯ�����״̬
		DictService dictService = (DictService) ServiceFactory.getServiceImpl(DictService.class.getName());
		List<Map<String, Object>> orderStatusList = dictService.queryOrderStatusList();
		request.setAttribute("orderStatusList", orderStatusList);
		User user = (User) request.getSession().getAttribute("loginUser");
		int userId = user.getUserId();
		PreOrder preOrder = new PreOrder();
		preOrder.setUserId(userId);
//		int areaId=1;
//		PreOrder preOrder=new PreOrder();
//		preOrder.setUserId(userId);
//		preOrder.setAreaId(areaId);
//		String startTime=request.getParameter("startTime");
//		String endTime=request.getParameter("endTime");
		// ��ǰҳ��
		String currentPageNumStr = request.getParameter("currentPageNum");
		int currentPageNum = (currentPageNumStr == null ? 1 : Integer.parseInt(currentPageNumStr));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		Page<Map<String, Object>> page = preOrderService.queryMyCustomerOrders(preOrder, currentPageNum);
		// Page<Map<String, Object>> page
		// =preOrderService.queryMyCustomerOrders(preOrder, startTime, endTime,
		// currentPageNum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/preOrder/consulter_allpreorder.jsp").forward(request, response);
//		PreOrderService preOrderService=(PreOrderService)ServiceFactory.getServiceImpl(PreOrderService.class.getName());
//		List<Map<String, Object>>  allPreOrderList=preOrderService.queryCustomerPreOrderList(userId);
//		request.setAttribute("allPreOrderList", allPreOrderList);

	}

	// �û���ѯ
	private void myPreOrderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// ��ǰҳ��

		Customer customer = (Customer) request.getSession().getAttribute("loginCustomer");
		int customerId = customer.getCustomerId();
		PreOrder preOrder = new PreOrder();
		preOrder.setCustomerId(customerId);
		String currentPageNumStr = request.getParameter("currentPageNum");
		int currentPageNum = (currentPageNumStr == null ? 1 : Integer.parseInt(currentPageNumStr));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		Page<Map<String, Object>> page = preOrderService.queryMyPreOrders(preOrder, currentPageNum);
		// List<Map<String, Object>>
		// myPreOrderList=preOrderService.queryMyPreOrderList(customerId);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/front/customer/preorder_list.jsp").forward(request, response);
	}

	// ��̬��ѯ���û���Ϣͨ��userId
	private void activeQueryUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int userId = Integer.parseInt(request.getParameter("userId"));
		// �õ��û���������Ϣ
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		Map<String, Object> singUser = userService.querySingUserByUserId(userId);
		// �õ����û�ӵ�е�����
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		List<Map<String, Object>> areaList = preOrderService.queryAreaListByUserId(userId);
		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("singUser", singUser);
		userInfo.put("areaList", areaList);
		String json = JSONObject.toJSONString(userInfo);
		response.getWriter().println(json);
	}

	private void selectUserId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int areaId = Integer.parseInt(request.getParameter("areaId"));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		List<Map<String, Object>> queryAreaDownUser = preOrderService.queryAllConsulterByAreaId(areaId);
		String json = JSONObject.toJSONString(queryAreaDownUser);
		response.getWriter().println(json);
	}

	private void queryAreaBeforePre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		AreaService areaService = (AreaService) ServiceFactory.getServiceImpl(AreaService.class.getName());
		List<Area> areaList = areaService.queryArea();
		request.setAttribute("areaList", areaList);
		request.getRequestDispatcher("/front/customer/want_preorder.jsp").forward(request, response);
	}

	private void assessConsulterBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//int userId = Integer.parseInt(request.getParameter("userId"));
		response.setContentType("text/html");
		int preorderId = Integer.parseInt(request.getParameter("preOrderId"));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		PreOrder preOrder = preOrderService.queryPreOrderById(preorderId);
		int userId = preOrder.getUserId();
		List<Map<String, Object>> areaList = preOrderService.queryAreaListByUserId(userId);
		Map<String, Object> userInfo = preOrderService.queryUserInfo(userId);
		Map<String, Object> preOrderDetail = preOrderService.queryMyPreOrderDetail(preorderId);
		request.setAttribute("areaList", areaList);
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("preOrderDetail", preOrderDetail);
		request.getRequestDispatcher("/front/customer/assess_consulter.jsp").forward(request, response);
	}
	
	//������ѯʦ
	private void assessConsulter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int preOrderId = Integer.parseInt(request.getParameter("preOrderId"));
		String assess=request.getParameter("assess");
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		PreOrder preOrder=new PreOrder();
		preOrder.setPreorderId(preOrderId);
		preOrder.setEvaluateContent(assess);
		int result=preOrderService.assessConsulter(preOrder);
		if(result>0) {
			JsonMessage msg = new JsonMessage();
			msg.setId(1);
			msg.setMsg("����ɸ���ѯʦ������");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
		}else {
			JsonMessage msg = new JsonMessage();
			msg.setId(2);
			msg.setMsg("������ѯʦʧ��,����ϵ����Ա����");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
		}
	}
	// �鿴��ѯʦ������Ϣ
	private void queryUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int preorderId = Integer.parseInt(request.getParameter("preOrderId"));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		PreOrder preOrder = preOrderService.queryPreOrderById(preorderId);
		int userId = preOrder.getUserId();
		// ͨ��userIdȥ���û���
		Map<String, Object> userInfo = preOrderService.queryUserInfo(userId);
		// ͨ��userIdȥ�������
		List<Map<String, Object>> areaList = preOrderService.queryAreaListByUserId(userId);
		// ͨ��userIdȥ��ԤԼ��
		List<Map<String, Object>> assessInfoList = preOrderService.queryAssessInfo(userId);
		request.setAttribute("areaList", areaList);
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("assessInfoList", assessInfoList);
		request.getRequestDispatcher("/front/customer/check_consulter.jsp").forward(request, response);
	}

	// ����Ա�鿴ԤԼ����
	private void usercheckDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int preOrderId = Integer.parseInt(request.getParameter("preorderId"));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		Map<String, Object> preOrderDetail = preOrderService.queryMyPreOrderDetail(preOrderId);
		request.setAttribute("preOrderDetail", preOrderDetail);
		request.getRequestDispatcher("/admin/preOrder/usercheck_detail.jsp").forward(request, response);
	}

	// �ͻ��鿴ԤԼ����
	private void preOrderDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int preOrderId = Integer.parseInt(request.getParameter("preOrderId"));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		Map<String, Object> preOrderDetail = preOrderService.queryMyPreOrderDetail(preOrderId);
		request.setAttribute("preOrderDetail", preOrderDetail);
		request.getRequestDispatcher("/front/customer/check_detail.jsp").forward(request, response);
	}

	// ��ѯʦ�鿴����
	private void consultercheckDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int preorderId = Integer.parseInt(request.getParameter("preorderId"));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		Map<String, Object> preOrderDetail = preOrderService.queryMyPreOrderDetail(preorderId);
		request.setAttribute("preOrderDetail", preOrderDetail);
		request.getRequestDispatcher("/admin/preOrder/consultercheck_detail.jsp").forward(request, response);
	}
	//��ѯʦ��ϴ�ǰ
	private void assessReplyBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int preorderId = Integer.parseInt(request.getParameter("preorderId"));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		Map<String, Object> preOrderDetail = preOrderService.queryMyPreOrderDetail(preorderId);
		request.setAttribute("preOrderDetail", preOrderDetail);
		request.getRequestDispatcher("/admin/preOrder/assess_reply.jsp").forward(request, response);
	}
	//��ϴ�
	private void assessReply(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int preOrderId = Integer.parseInt(request.getParameter("preOrderId"));
		String reply=request.getParameter("reply");
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		PreOrder preOrder=new PreOrder();
		preOrder.setPreorderId(preOrderId);
		preOrder.setDiagnoseReply(reply);
		int result=preOrderService.assessReply(preOrder);
		if(result>0) {
			JsonMessage msg = new JsonMessage();
			msg.setId(1);
			msg.setMsg("����ɸ��û�����ϴ�");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
		}else {
			JsonMessage msg = new JsonMessage();
			msg.setId(2);
			msg.setMsg("��ϴ�ʧ��,����ϵ����Ա����");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
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
