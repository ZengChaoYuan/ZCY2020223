package com.cyzy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyzy.bean.CountData;

/**
 * Servlet implementation class CountServlet
 */
public class CountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//来自数据库的数据
		//CountService service=ServiceFactory()
	    List<CountData> datas=new ArrayList<CountData>();
	    datas.add(new CountData("2020-01",30));
	    datas.add(new CountData("2020-02",60));
	    datas.add(new CountData("2020-03",90));
	    datas.add(new CountData("2020-04",120));
	    datas.add(new CountData("2020-05",150));
	    request.setAttribute("datas", datas);
	    request.getRequestDispatcher("/admin/count/table.jsp").forward(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
