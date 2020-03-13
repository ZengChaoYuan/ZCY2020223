package com.cyzy.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyzy.bean.OrderTime;
import com.cyzy.bean.User;
import com.cyzy.service.OrderTimeService;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class OrderTimeServlet
 */
public class OrderTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderTimeServlet() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderTimeAction=request.getParameter("orderTimeAction");
		if(orderTimeAction!=null&&orderTimeAction.equals("queryOrderTime")) {
			queryOrderTime(request,response);
		}
	}
    
	private void queryOrderTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ·ÀÖ¹ÂÒÂë
		response.setContentType("text/html");
		User loginUser=(User)request.getSession().getAttribute("loginUser");
		int userId=loginUser.getUserId();
		String date=request.getParameter("orderDate");
		if(date==null||date.equals("")) {
			Date d=new Date();
			date=new SimpleDateFormat("yyyy-MM-dd").format(d);
		}
		System.out.println(date);
		
		OrderTimeService orderTimeService=(OrderTimeService)ServiceFactory.getServiceImpl(OrderTimeService.class.getName());
		List<OrderTime> orderTimeList=orderTimeService.queryOrderTime(userId, date);
		String json=JSONObject.toJSONString(orderTimeList);
		response.getWriter().println(json);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
