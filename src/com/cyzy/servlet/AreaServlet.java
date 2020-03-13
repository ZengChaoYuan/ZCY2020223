package com.cyzy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyzy.bean.Area;
import com.cyzy.service.AreaService;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class AreaServlet
 */
public class AreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AreaServlet() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String areaAction=request.getParameter("areaAction");
		if(areaAction != null && areaAction.equals("list")) {
			areaList(request,response);
		}
	}
	private void areaList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    AreaService areaService=(AreaService)ServiceFactory.getServiceImpl(AreaService.class.getName());
	    List<Area>areaList= areaService.queryArea();
	    request.setAttribute("areaList", areaList);
	    request.getRequestDispatcher("/front/customer/online_assess.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
