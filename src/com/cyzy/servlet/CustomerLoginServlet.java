package com.cyzy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.cyzy.bean.Customer;
import com.cyzy.bean.JsonMessage;
import com.cyzy.service.CustomerService;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class CustomerLoginServlet
 */
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLoginServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerName = request.getParameter("customerName");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		String sessionCode = (String) request.getSession().getAttribute("code");
		if (!code.equals(sessionCode)) {
			JsonMessage msg = new JsonMessage();
			msg.setId(1);
			msg.setMsg("验证码错误!!!");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
		}else {
			CustomerService customerService=(CustomerService)ServiceFactory.getServiceImpl(CustomerService.class.getName());
			Customer customer=customerService.login(customerName, password);
			System.out.println(customer);
			if(customer == null) {
				JsonMessage msg = new JsonMessage();
				msg.setId(2);
				msg.setMsg("用戶名或者密码错误!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;

			}
				if(customer.getDeleteStatus()!=1) {
					JsonMessage msg = new JsonMessage();
					msg.setId(3);
					msg.setMsg("用户不存在!!!");
					String json = JSONObject.toJSONString(msg);
					response.getWriter().println(json);
					return;
				}else if (customer.getUseStatus() != 1) {

					JsonMessage msg = new JsonMessage();
					msg.setId(4);
					msg.setMsg("用户被禁用!!!");
					String json = JSONObject.toJSONString(msg);
					response.getWriter().println(json);
					return;
				}else {
					HttpSession session = request.getSession();
					session.setAttribute("loginCustomer", customer);
					JsonMessage msg = new JsonMessage();
					msg.setId(5);
					msg.setMsg("登录成功!");
					msg.setLocation(request.getContextPath() + "/front/customer/index.jsp");
					String json = JSONObject.toJSONString(msg);
					response.getWriter().println(json);
				}
 
		
			
		}
		
	}

}
