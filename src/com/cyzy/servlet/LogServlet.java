package com.cyzy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyzy.bean.Log;
import com.cyzy.bean.LogInf;
import com.cyzy.service.LogService;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class LogServlet
 */
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String logAction = request.getParameter("logAction");
		if (logAction != null && logAction.equals("weekList")) {
			queryLogByWeek(request,response);
		}else if (logAction != null && logAction.equals("userList")) {
			queryLogByUser(request,response);
		}else if (logAction != null && logAction.equals("yearList")) {
			queryLogByYear(request,response);
		}
			
	}
	
	private void queryLogByYear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Log log=new Log();
		LogService logService=(LogService)ServiceFactory.getServiceImpl(LogService.class.getName());
		List<Log> logList=logService.queryAllLogByYear(log);
		request.setAttribute("logList", logList);
		request.getRequestDispatcher("/admin/log/log_time.jsp").forward(request, response);
	}
	
	private void queryLogByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		LogService logService=(LogService)ServiceFactory.getServiceImpl(LogService.class.getName());
		List<LogInf> logInfList=logService.queryAllLogByUser(startTime, endTime);
		request.setAttribute("logInfList", logInfList);
		request.getRequestDispatcher("/admin/log/log_user.jsp").forward(request, response);
		
	}
	private void queryLogByWeek(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Log log=new Log();
		LogService logService=(LogService)ServiceFactory.getServiceImpl(LogService.class.getName());
		List<Log> logList=logService.queryAllLogByTime(log);
		request.setAttribute("logList", logList);
		request.getRequestDispatcher("/admin/log/log_time.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
