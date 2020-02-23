package com.cyzy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyzy.bean.City;

/**
 * Servlet implementation class CityServlet
 */
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cityAction=request.getParameter("cityAction");
		if(cityAction != null && cityAction.equals("list")) {
			cityList(request,response);
		}
	}
	private void cityList(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		//防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		List<City> cityList=new ArrayList<City>();
		
		City city1=new City(1,"福建省",0);
		City city2=new City(2,"广东省",0);
		City city3=new City(3,"福州市",1);
		City city4=new City(4,"漳州市",1);
		
		City city5=new City(5,"广州市",2);
		City city6=new City(6,"深圳市",2);
		City city7=new City(7,"雷州市",2);
		cityList.add(city1);
		cityList.add(city2);
		cityList.add(city3);
		cityList.add(city4);
		cityList.add(city5);
		cityList.add(city6);
		cityList.add(city7);
		request.setAttribute("cityList", cityList);
		request.getRequestDispatcher("/city/city_list.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
