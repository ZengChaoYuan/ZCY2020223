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
			// �ҵı����б�
			myReportList(request, response);
		} else if (assAction != null && assAction.equals("reportResult")) {
			// ������
			reportResult(request, response);
		} else if (assAction != null && assAction.equals("userQueryReport")) {
			userQueryReport(request, response);
		} else if (assAction != null && assAction.equals("userQueryAss")) {
			userQueryAss(request, response);
		}
	}

	// ����Ա�鿴�����û���������
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

	// ����Ա�鿴���пͻ��ı����б�
	private void userQueryReport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		AssReport assReport = new AssReport();
		AssReportService assReportService = (AssReportService) ServiceFactory
				.getServiceImpl(AssReportService.class.getName());
		List<Map<String, Object>> customerReportList = assReportService.queryCustomerReport(assReport);
		request.setAttribute("customerReportList", customerReportList);
		request.getRequestDispatcher("/admin/adminOnlineAssess/customer_asslist.jsp").forward(request, response);
	}

	// �ͻ��鿴�Լ��ı�����
	private void reportResult(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int standId = Integer.parseInt(request.getParameter("standId"));
		StandService standService = (StandService) ServiceFactory.getServiceImpl(StandService.class.getName());
		Stand stand = standService.getStandByStandId(standId);
		request.setAttribute("stand", stand);
		request.getRequestDispatcher("/front/customer/report_result.jsp").forward(request, response);
	}

	// �ͻ��鿴�Լ��ı����б�
	private void myReportList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Customer loginCustomer = (Customer) request.getSession().getAttribute("loginCustomer");
		int customerId = loginCustomer.getCustomerId();
		AssReport assReport = new AssReport();
		assReport.setCustomerId(customerId);
		// ��ǰҳ��
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
