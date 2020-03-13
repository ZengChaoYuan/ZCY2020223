package com.cyzy.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyzy.bean.AssReport;
import com.cyzy.bean.Customer;
import com.cyzy.bean.Stand;
import com.cyzy.service.AssReportService;
import com.cyzy.service.StandService;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String assAction=request.getParameter("assAction");
		if(assAction != null && assAction.equals("myReportList")) {
			myReportList(request,response);
		}else if(assAction != null && assAction.equals("reportResult")) {
			reportResult(request,response);
		}else if(assAction != null && assAction.equals("userQueryReport")) {
			userQueryReport(request,response);
		}else if(assAction != null && assAction.equals("userQueryAss")) {
			userQueryAss(request,response);
		}
	}
	
	//管理员查看各个用户的评测结果
	private void userQueryAss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int assReportId=Integer.parseInt(request.getParameter("AssReportId"));
		AssReportService assReportService=(AssReportService)ServiceFactory.getServiceImpl(AssReportService.class.getName());
		Map<String,Object> singAss=assReportService.queryCustomerAss(assReportId);
		request.setAttribute("singAss", singAss);
		request.getRequestDispatcher("/admin/adminOnlineAssess/customer_report.jsp").forward(request, response);
	}
	
	//管理员查看所有客户的报告列表
	private void userQueryReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		AssReport assReport=new AssReport();
		AssReportService assReportService=(AssReportService)ServiceFactory.getServiceImpl(AssReportService.class.getName());
		List<Map<String,Object>> customerReportList=assReportService.queryCustomerReport(assReport);
		request.setAttribute("customerReportList", customerReportList);
		request.getRequestDispatcher("/admin/adminOnlineAssess/customer_asslist.jsp").forward(request, response);
	}
	
	//客户查看自己的报告结果
	private void reportResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int standId=Integer.parseInt(request.getParameter("standId"));
		StandService standService=(StandService)ServiceFactory.getServiceImpl(StandService.class.getName());
		Stand stand=standService.getStandByStandId(standId);
		request.setAttribute("stand", stand);
		request.getRequestDispatcher("/front/customer/report_result.jsp").forward(request, response);
	}
	//客户查看自己的报告列表
	private void myReportList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Customer loginCustomer=(Customer)request.getSession().getAttribute("loginCustomer");
		int customerId=loginCustomer.getCustomerId();
		AssReportService assReportService=(AssReportService)ServiceFactory.getServiceImpl(AssReportService.class.getName());
		List<AssReport> assReportList=assReportService.querymyReport(customerId);
		request.setAttribute("assReportList", assReportList);
		request.getRequestDispatcher("/front/customer/myReport.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
