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
import com.cyzy.bean.CustomerStatis;
import com.cyzy.bean.JsonMessage;

import com.cyzy.bean.Param;
import com.cyzy.service.CustomerService;
import com.cyzy.service.ParamService;
import com.cyzy.util.Page;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class CustomerServlet
 */
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String customerAction = request.getParameter("customerAction");
		if (customerAction != null && customerAction.equals("register")) {
			register(request, response);
		} else if (customerAction != null && customerAction.equals("list")) {
			customerList(request, response);
		} else if (customerAction != null && customerAction.equals("listLike")) {
			customerListLike(request, response);
		} else if (customerAction != null && customerAction.equals("queryByWeek")) {
			queryByWeek(request, response);
		} else if (customerAction != null && customerAction.equals("queryByMonths")) {
			queryByMonths(request, response);
		} else if (customerAction != null && customerAction.equals("queryByYear")) {
			queryByYear(request, response);
		} else if (customerAction != null && customerAction.equals("updateUseStatus")) {
			updateUseStatus(request, response);
		} else if (customerAction != null && customerAction.equals("updateDeleteStatus")) {
			updateDeleteStatus(request, response);
		} else if (customerAction != null && customerAction.equals("resetPassword")) {
			resetPassword(request, response);
		}
	}

	private void resetPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");	
		
		//查询出参数表中的重置密码信息
		int paramId=1;
		ParamService paramService=(ParamService)ServiceFactory.getServiceImpl(ParamService.class.getName());
		Param param=paramService.getParamById(paramId);
		String resetPassword=param.getResetPassword();
		// 通过客户ID得到客户的完整信息
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		CustomerService customerService = (CustomerService) ServiceFactory.getServiceImpl(CustomerService.class.getName());
		Customer customer = customerService.getCustomerById(customerId);
		//customer是完整的客户信息
		
		customer.setPassword(resetPassword);

		int result=customerService.resetPassword(customer);

		if(result>0) {
			JsonMessage msg=new JsonMessage();
			msg.setId(1);
			msg.setMsg("密码重置成功!");
			String json=JSONObject.toJSONString(msg);
			response.getWriter().println(json);
			return;
		}else {
			JsonMessage msg=new JsonMessage();
			msg.setId(2);
			msg.setMsg("密码重置失败!");
			String json=JSONObject.toJSONString(msg);
			response.getWriter().println(json);
			return;
		}
	}
	private void updateDeleteStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 修改客户状态为已删除
		response.setContentType("text/html");
		// 通过客户ID得到客户的完整信息
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		CustomerService customerService = (CustomerService) ServiceFactory
				.getServiceImpl(CustomerService.class.getName());
		Customer customer = customerService.getCustomerById(customerId);
		if (customer.getDeleteStatus() == 1) {
			customer.setDeleteStatus(0);
			int result = customerService.updateDeleteStatus(customer);

			if (result > 0) {
				JsonMessage msg=new JsonMessage();
				msg.setId(1);
				msg.setMsg("删除成功!");
				String json=JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}else {
				JsonMessage msg=new JsonMessage();
				msg.setId(2);
				msg.setMsg("删除失败!");
				String json=JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}else {
			JsonMessage msg=new JsonMessage();
			msg.setId(3);
			msg.setMsg("用户已经被删除,无须重复操作!");
			String json=JSONObject.toJSONString(msg);
			response.getWriter().println(json);
			return;
		}
	}

	private void updateUseStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// 通过客户ID得到客户的完整信息
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		CustomerService customerService = (CustomerService) ServiceFactory
				.getServiceImpl(CustomerService.class.getName());
		Customer customer = customerService.getCustomerById(customerId);

		if (customer.getUseStatus() == 1) {
			customer.setUseStatus(2);
			int result = customerService.updateUseStatus(customer);
			if (result > 0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(1);
				msg.setMsg("禁用成功!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}

		} else if (customer.getUseStatus() == 2) {
			customer.setUseStatus(1);
			int result = customerService.updateUseStatus(customer);
			if (result > 0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(2);
				msg.setMsg("启用成功!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}
	}

	private void queryByYear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {//查询近半年
		response.setContentType("text/html");
		CustomerStatis customerStatis = new CustomerStatis();
		CustomerService customerService = (CustomerService) ServiceFactory
				.getServiceImpl(CustomerService.class.getName());
		List<CustomerStatis> customerList = customerService.queryAllCustomerByYear(customerStatis);
		request.setAttribute("customerList", customerList);
		request.getRequestDispatcher("admin/customerStatis/query_time.jsp").forward(request, response);
	}
	
	private void queryByMonths(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {//查询本月
		response.setContentType("text/html");
		CustomerStatis customerStatis = new CustomerStatis();
		CustomerService customerService = (CustomerService) ServiceFactory.getServiceImpl(CustomerService.class.getName());
		List<CustomerStatis> customerList = customerService.queryAllCustomerByMonths(customerStatis);
		request.setAttribute("customerList", customerList);
		request.getRequestDispatcher("admin/customerStatis/query_time.jsp").forward(request, response);

	}

	private void queryByWeek(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {//查询本周
		response.setContentType("text/html");
		CustomerStatis customerStatis = new CustomerStatis();
		CustomerService customerService = (CustomerService) ServiceFactory
				.getServiceImpl(CustomerService.class.getName());
		List<CustomerStatis> customerList = customerService.queryAllCustomerByWeek(customerStatis);
		request.setAttribute("customerList", customerList);
		request.getRequestDispatcher("admin/customerStatis/query_time.jsp").forward(request, response);

	}

	private void customerListLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 防止乱码
		response.setContentType("text/html");
		// 当前页码
		String currentPageNumStr = request.getParameter("currentPageNum");
		int currentPageNum = (currentPageNumStr == null ? 1 : Integer.parseInt(currentPageNumStr));
		// 模糊查询
		String customerName = request.getParameter("customerName");
		String useStatusValue = request.getParameter("useStatus");
		int useStatus = Integer.parseInt(useStatusValue == null ? "0" : useStatusValue);

		Customer customer = new Customer();
		customer.setCustomerName(customerName);
		customer.setUseStatus(useStatus);
		CustomerService customerService = (CustomerService) ServiceFactory
				.getServiceImpl(CustomerService.class.getName());
		Page<Customer> page = customerService.queryCustomerLike(customer, currentPageNum);

		System.out.println(page);

		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/adminCustomer/customer_list.jsp").forward(request, response);
	}

	private void customerList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 防止乱码
		response.setContentType("text/html");
		// 当前页码
		String currentPageNumStr = request.getParameter("currentPageNum");
		int currentPageNum = (currentPageNumStr == null ? 1 : Integer.parseInt(currentPageNumStr));

		Customer customer = new Customer();
		CustomerService customerService = (CustomerService) ServiceFactory
				.getServiceImpl(CustomerService.class.getName());
		Page<Customer> page = customerService.queryCustomerList(customer, currentPageNum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/adminCustomer/customer_list.jsp").forward(request, response);
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 防止乱码
		response.setContentType("text/html");
		// 通过name
		String customerName = request.getParameter("customerName");
		String password = request.getParameter("password");
		int sex = Integer.parseInt(request.getParameter("sex"));
		int age = Integer.parseInt(request.getParameter("age"));
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		Customer customer = new Customer();
		customer.setCustomerName(customerName);
		customer.setPassword(password);
		customer.setSex(sex);
		customer.setAge(age);
		customer.setTel(tel);
		customer.setAddress(address);

		CustomerService customerService = (CustomerService) ServiceFactory
				.getServiceImpl(CustomerService.class.getName());
		int result = 0;
		try {
			result = customerService.addCustomer(customer);
			System.out.println(customer);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (result > 0) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.alert('注册成功了!');");
			out.println("window.top.location.href='" + request.getContextPath() + "/front/login.jsp';");
			out.println("</script>");
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
