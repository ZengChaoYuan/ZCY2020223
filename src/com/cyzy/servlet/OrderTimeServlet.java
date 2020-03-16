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
import com.cyzy.bean.JsonMessage;
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
		}else if(orderTimeAction!=null&&orderTimeAction.equals("saveOrderTime")) {
			saveOrderTime(request,response);
		}else if(orderTimeAction!=null&&orderTimeAction.equals("customerQueryOrderTime")) {
			customerQueryOrderTime(request,response);
		}
	}
	private void customerQueryOrderTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 防止乱码
		response.setContentType("text/html");
		int userId=Integer.parseInt(request.getParameter("consultId"));
		String date=request.getParameter("orderDate");
		if(date==null||date.equals("")) {
			Date d=new Date();
			date=new SimpleDateFormat("yyyy-MM-dd").format(d);
		}
		OrderTimeService orderTimeService=(OrderTimeService)ServiceFactory.getServiceImpl(OrderTimeService.class.getName());
		List<OrderTime> orderTimeList=orderTimeService.queryOrderTime(userId, date);
		String json=JSONObject.toJSONString(orderTimeList);
		response.getWriter().println(json);
	}
	private void saveOrderTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 防止乱码
		response.setContentType("text/html");
		int userId=Integer.parseInt(request.getParameter("userId"));
		String orderDate=request.getParameter("orderDate");
		String orderHours=request.getParameter("hours");
		String[] orderHour=orderHours.split(",");
		
		OrderTimeService orderTimeService=(OrderTimeService)ServiceFactory.getServiceImpl(OrderTimeService.class.getName());
		//删除当天的预约时间
		int deleteResult=orderTimeService.deleteTodayOrderTime(orderDate);
		if(deleteResult==0) {
			return;
		}
		int result=0;
		try {
		//新增当天的预约时间
			result=orderTimeService.saveOrderTime(userId, orderDate, orderHour);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result>0) {
			JsonMessage msg = new JsonMessage();
			msg.setId(1);
			msg.setMsg("保存成功!!!");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
		}else {
			JsonMessage msg = new JsonMessage();
			msg.setId(2);
			msg.setMsg("保存失败!!!");
			String json = JSONObject.toJSONString(msg);
			response.getWriter().println(json);
		}
	}
	private void queryOrderTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 防止乱码
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
