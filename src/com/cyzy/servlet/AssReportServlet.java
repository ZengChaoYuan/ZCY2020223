package com.cyzy.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyzy.bean.AssReport;
import com.cyzy.bean.Customer;
import com.cyzy.bean.JsonMessage;
import com.cyzy.bean.Stand;
import com.cyzy.service.AssReportService;
import com.cyzy.service.StandService;
import com.cyzy.util.Page;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class AssReportServlet
 */
public class AssReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AssReportServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String assAction = request.getParameter("assAction");
		if (assAction != null && assAction.equals("myReportList")) {
			// 我的报告列表
			myReportList(request, response);
		}else if (assAction != null && assAction.equals("addAssReport")) {
			// 生成评估报告
			addAssReport(request, response);
		} else if (assAction != null && assAction.equals("reportResult")) {
			// 报告结果
			reportResult(request, response);
		} else if (assAction != null && assAction.equals("userQueryReport")) {
			// 管理员查看所有客户的报告列表
			userQueryReport(request, response);
		} else if (assAction != null && assAction.equals("userQueryAss")) {
			// 管理员查看各个用户的评测结果
			userQueryAss(request, response);
		}
	}
	
	// 生成评估报告
	private void addAssReport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Customer loginCustomer = (Customer) request.getSession().getAttribute("loginCustomer");
		int customerId = loginCustomer.getCustomerId();
	    //获得总分
		int assScore=Integer.parseInt(request.getParameter("assScore"));
		//判断
		AssReport assReport=new AssReport();
		AssReportService assReportService = (AssReportService) ServiceFactory
				.getServiceImpl(AssReportService.class.getName());
		
		if(assScore>=0 && assScore<=4) {
			int standId=3;
			assReport.setStandId(standId);
			assReport.setAssScore(assScore);
			assReport.setCustomerId(customerId);
			int result=0;
			try {
				result=assReportService.addAssReport(assReport);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(result>0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(1);
				msg.setMsg("您的评测结果已经生成了，快去看看吧!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}else if(assScore>=5 && assScore<=8) {
			int standId=1;
			assReport.setStandId(standId);
			assReport.setAssScore(assScore);
			assReport.setCustomerId(customerId);
			int result=0;
			try {
				result=assReportService.addAssReport(assReport);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(result>0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(2);
				msg.setMsg("您的评测结果已经生成了，快去看看吧!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}else if(assScore>=9 && assScore<=12) {
			int standId=2;
			assReport.setStandId(standId);
			assReport.setAssScore(assScore);
			assReport.setCustomerId(customerId);
			int result=0;
			try {
				result=assReportService.addAssReport(assReport);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(result>0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(3);
				msg.setMsg("您的评测结果已经生成了，快去看看吧!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}else if(assScore>=13 && assScore<=50) {
			int standId=4;
			assReport.setStandId(standId);
			assReport.setAssScore(assScore);
			assReport.setCustomerId(customerId);
			int result=0;
			try {
				result=assReportService.addAssReport(assReport);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(result>0) {
				JsonMessage msg = new JsonMessage();
				msg.setId(4);
				msg.setMsg("您的评测结果已经生成了，快去看看吧!");
				String json = JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}
		
	}

	// 管理员查看各个用户的评测结果
	private void userQueryAss(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int assReportId = Integer.parseInt(request.getParameter("AssReportId"));
		AssReportService assReportService = (AssReportService) ServiceFactory
				.getServiceImpl(AssReportService.class.getName());
		Map<String, Object> singAss = assReportService.queryCustomerAss(assReportId);
		request.setAttribute("singAss", singAss);
		request.getRequestDispatcher("/admin/adminOnlineAssess/customer_report.jsp").forward(request, response);
	}

	// 管理员查看所有客户的报告列表
	private void userQueryReport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// 当前页码
		String currentPageNumStr = request.getParameter("currentPageNum");
		int currentPageNum = (currentPageNumStr == null ? 1 : Integer.parseInt(currentPageNumStr));
		AssReportService assReportService = (AssReportService) ServiceFactory
				.getServiceImpl(AssReportService.class.getName());
		Page<Map<String, Object>> page =assReportService.queryAllReports(currentPageNum);
		//List<Map<String, Object>> customerReportList = assReportService.queryCustomerReport(assReport);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/adminOnlineAssess/customer_asslist.jsp").forward(request, response);
	}

	// 客户查看自己的报告结果
	private void reportResult(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int standId = Integer.parseInt(request.getParameter("standId"));
		StandService standService = (StandService) ServiceFactory.getServiceImpl(StandService.class.getName());
		Stand stand = standService.getStandByStandId(standId);
		request.setAttribute("stand", stand);
		request.getRequestDispatcher("/front/customer/report_result.jsp").forward(request, response);
	}

	// 客户查看自己的报告列表
	private void myReportList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Customer loginCustomer = (Customer) request.getSession().getAttribute("loginCustomer");
		int customerId = loginCustomer.getCustomerId();
		AssReport assReport = new AssReport();
		assReport.setCustomerId(customerId);
		// 当前页码
		String currentPageNumStr = request.getParameter("currentPageNum");
		int currentPageNum = (currentPageNumStr == null ? 1 : Integer.parseInt(currentPageNumStr));
		AssReportService assReportService = (AssReportService) ServiceFactory
				.getServiceImpl(AssReportService.class.getName());
		Page<Map<String, Object>> page =assReportService.querymyReports(assReport, currentPageNum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/front/customer/myReport.jsp").forward(request, response);	
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
