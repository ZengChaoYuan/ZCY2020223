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
			// 我的预约列表
			myPreOrderList(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("wantPreOrder")) {
			// 我要预约
			wantPreOrder(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("surePreOrder")) {
			// 咨询师确认完成
			surePreOrder(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("stopPreOrder")) {
			// 管理员终止预约
			stopPreOrder(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("allPreOrderList")) {
			// 管理员查看所有的预约列表(所有的用户和咨询师)
			allPreOrderList(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("allPreOrderListByuserId")) {
			// 咨询师查看自己的用户预约列表
			allPreOrderListByuserId(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("preOrderDetail")) {
			// 查看详情
			preOrderDetail(request, response);
		}else if (preOrderAction != null && preOrderAction.equals("assessReplyBefore")) {
			// 咨询师诊断答复前
			assessReplyBefore(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("assessReply")) {
			// 咨询师诊断答复
			assessReply(request, response);
		}  else if (preOrderAction != null && preOrderAction.equals("consultercheckDetail")) {
			// 咨询师查看详情
			consultercheckDetail(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("usercheckDetail")) {
			// 管理员查看详情
			usercheckDetail(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("queryUserInfo")) {
			// 查看咨询师档案信息
			queryUserInfo(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("assessConsulterBefore")) {
			// 评价咨询师前
			assessConsulterBefore(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("assessConsulter")) {
			// 评价咨询师
			assessConsulter(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("queryAreaBeforePre")) {
			// 我要预约中的我要预约,查询出领域
			queryAreaBeforePre(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("queryAreaDownUser")) {
			// 我要预约中的我要预约,查询出领域
			selectUserId(request, response);
		} else if (preOrderAction != null && preOrderAction.equals("activeQueryUserInfo")) {
			// 动态查询出用户信息通过userId
			activeQueryUserInfo(request, response);
		}
	}

	// 我要预约
	private void wantPreOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// 我要预约要插入哪些数据呢，自增ID，咨询师ID,问题描述，无，无，服务价格500，领域id为1，自己的ID,预约状态为2（我要预约），预约时间为日期加上时间
		int userId = Integer.parseInt(request.getParameter("userId"));
		String problemDesc = request.getParameter("problemDesc");
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		User user = userService.getUserById(userId);
		int preExpense = user.getPreExpense();
		// int preorderPrice=Integer.parseInt(request.getParameter("preorderPrice"));
		int areaId = Integer.parseInt(request.getParameter("areaId"));
		Customer customer = (Customer) request.getSession().getAttribute("loginCustomer");
		int customerId = customer.getCustomerId();
		// 预约时间是算出来的
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
			msg.setMsg("已预约!");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
			return;
		}
	}

	// 确认预约(咨询师收钱，客户扣钱，添加账户表，添加预约数量表)
	private void surePreOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int preorderId = Integer.parseInt(request.getParameter("preorderId"));
		// 通过预约ID得到所有的预约信息
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		PreOrder preOrder = preOrderService.queryPreOrderById(preorderId);
		int userId = preOrder.getUserId();
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		User user = userService.getUserById(userId);
		// 咨询师的名字
		String userName = user.getUserName();
		PreOrderCount preOrderCount = new PreOrderCount();
		preOrderCount.setUserName(userName);
		// 插入数据到预约数量表中
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
		// 顾客余额
		int customerMoney = customer.getBalance();
		// 咨询师咨询费用
		int userExpense = user.getPreExpense();

		MyAccount myAccount = new MyAccount();
		myAccount.setHappenThing("咨询预约");
		myAccount.setCustomerId(customerId);
		myAccount.setConsumpType("支出");
		myAccount.setConsumpMoney(userExpense);
		myAccount.setUserId(userId);
		if (customerMoney >= userExpense) {
			// 预约成功，状态为已确认
			preOrder.setOrderStatus(3);
			int statusResult = preOrderService.stopPreOrder(preOrder);
			// 顾客的余额减少
			int customerBalance = customerMoney - userExpense;
			customer.setBalance(customerBalance);
			int customerResult = customerService.updateBalance(customer);
			// 咨询师的余额增加
			int oldUserBalance = user.getBalance();
			int userBalance = oldUserBalance + userExpense;
			user.setBalance(userBalance);
			int userResult = userService.updateBalance(user);
			// 插入数据到账户表里
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
				msg.setMsg("已确认预约!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		} else {
			// 预约失败
			preOrder.setOrderStatus(1);
			int result = preOrderService.stopPreOrder(preOrder);
			if (result > 0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(2);
				msg.setMsg("预约失败!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}
	}

	// 管理员终止预约
	private void stopPreOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		int preorderId = Integer.parseInt(request.getParameter("preorderId"));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		// 通过预约Id得到预约的状态
		PreOrder preOrder = preOrderService.queryPreOrderById(preorderId);
		if (preOrder.getOrderStatus() == 2) {
			preOrder.setOrderStatus(6);
			int result = preOrderService.stopPreOrder(preOrder);
			if (result > 0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(1);
				msg.setMsg("终止成功!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			} else {
				JsonMessage msg = new JsonMessage();
				msg.setId(2);
				msg.setMsg("终止失败!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
			}
		}

	}

	private void allPreOrderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// 当前页码
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

	// 咨询师查询
	private void allPreOrderListByuserId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// 查询出完成状态
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
		// 当前页码
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

	// 用户查询
	private void myPreOrderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// 当前页码

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

	// 动态查询出用户信息通过userId
	private void activeQueryUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int userId = Integer.parseInt(request.getParameter("userId"));
		// 得到用户的所有信息
		UserService userService = (UserService) ServiceFactory.getServiceImpl(UserService.class.getName());
		Map<String, Object> singUser = userService.querySingUserByUserId(userId);
		// 得到该用户拥有的领域
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
	
	//评价咨询师
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
			msg.setMsg("已完成该咨询师的评价");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
		}else {
			JsonMessage msg = new JsonMessage();
			msg.setId(2);
			msg.setMsg("评价咨询师失败,请联系管理员！！");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
		}
	}
	// 查看咨询师档案信息
	private void queryUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int preorderId = Integer.parseInt(request.getParameter("preOrderId"));
		PreOrderService preOrderService = (PreOrderService) ServiceFactory
				.getServiceImpl(PreOrderService.class.getName());
		PreOrder preOrder = preOrderService.queryPreOrderById(preorderId);
		int userId = preOrder.getUserId();
		// 通过userId去查用户表
		Map<String, Object> userInfo = preOrderService.queryUserInfo(userId);
		// 通过userId去查领域表
		List<Map<String, Object>> areaList = preOrderService.queryAreaListByUserId(userId);
		// 通过userId去查预约表
		List<Map<String, Object>> assessInfoList = preOrderService.queryAssessInfo(userId);
		request.setAttribute("areaList", areaList);
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("assessInfoList", assessInfoList);
		request.getRequestDispatcher("/front/customer/check_consulter.jsp").forward(request, response);
	}

	// 管理员查看预约详情
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

	// 客户查看预约详情
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

	// 咨询师查看详情
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
	//咨询师诊断答复前
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
	//诊断答复
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
			msg.setMsg("已完成该用户的诊断答复");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
		}else {
			JsonMessage msg = new JsonMessage();
			msg.setId(2);
			msg.setMsg("诊断答复失败,请联系管理员！！");
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
