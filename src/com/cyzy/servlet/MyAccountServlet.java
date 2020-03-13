package com.cyzy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyzy.bean.Customer;
import com.cyzy.bean.JsonMessage;
import com.cyzy.bean.MyAccount;
import com.cyzy.bean.User;
import com.cyzy.service.CustomerService;
import com.cyzy.service.MyAccountService;
import com.cyzy.util.ServiceFactory;


/**
 * Servlet implementation class MyAccountServlet
 */
public class MyAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAccountServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String accountAction=request.getParameter("accountAction");
		if(accountAction != null && accountAction.equals("list")) {
			customerAccountList(request,response);
		}else if(accountAction != null && accountAction.equals("fundList")) {
			userFundList(request,response);
		}else if(accountAction != null && accountAction.equals("consumpMoney")) {
			consumpMoney(request,response);
		}
	}
	//咨询师的账户列表
	private void userFundList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		User user=(User)request.getSession().getAttribute("loginUser");
		int userId=user.getUserId();
		MyAccountService myAccountService=(MyAccountService)ServiceFactory.getServiceImpl(MyAccountService.class.getName());
		List<MyAccount> fundList=myAccountService.queryfundAccount(userId);
		request.setAttribute("fundList", fundList);
		request.getRequestDispatcher("/admin/fundAccount/fund_account.jsp").forward(request, response);
	}
	
	//客户的账户列表
		private void customerAccountList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			Customer loginCustomer=(Customer)request.getSession().getAttribute("loginCustomer");
			int customerId=loginCustomer.getCustomerId();
			MyAccountService myAccountService=(MyAccountService)ServiceFactory.getServiceImpl(MyAccountService.class.getName());
			List<MyAccount> myAccountList=myAccountService.queryMyAccount(customerId);
			request.setAttribute("myAccountList", myAccountList);
			request.getRequestDispatcher("/front/customer/myAccount.jsp").forward(request, response);
		}
	//客户充值
	private void consumpMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int customerId=Integer.parseInt(request.getParameter("customerId"));
		int consumpMoney=Integer.parseInt(request.getParameter("consumpMoney"));//充值金额
		
		//根据客户id修改用户的余额
		Customer customer=(Customer)request.getSession().getAttribute("loginCustomer");
		int oldBalance=customer.getBalance();
		int balance=oldBalance+consumpMoney;
		System.out.println(balance);
		CustomerService  customerService=(CustomerService)ServiceFactory.getServiceImpl(CustomerService.class.getName());
		customer.setBalance(balance);
		customerService.updateBalance(customer);
		
		//插入数据到我的账户列表中	
		MyAccount myAccount=new MyAccount();
		myAccount.setHappenThing("充值");
		myAccount.setCustomerId(customerId);
		myAccount.setConsumpType("收入");
		myAccount.setConsumpMoney(consumpMoney);
		myAccount.setUserId(0);
		MyAccountService myAccountService=(MyAccountService)ServiceFactory.getServiceImpl(MyAccountService.class.getName());
		int result=0;
		try {
			result=myAccountService.addCustomerAccount(myAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result>0) {
			JsonMessage msg = new JsonMessage();
			msg.setId(1);
			msg.setMsg("充值成功!");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
			return;
		}else {
			JsonMessage msg = new JsonMessage();
			msg.setId(2);
			msg.setMsg("充值失败!");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
			return;
		}
		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
