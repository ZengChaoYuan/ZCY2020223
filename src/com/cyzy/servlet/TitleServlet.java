package com.cyzy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyzy.bean.Item;
import com.cyzy.bean.Title;

/**
 * Servlet implementation class TitleServlet
 */
public class TitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TitleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询出某个领域的题目列表,数据查询
		List<Title> titles=new ArrayList<Title>();
		for(int i=0;i<5;i++) {
			Title title=new Title();
			titles.add(title);
			List<Item> items=new ArrayList<Item>();
			for(int j=0; j<4;j++) {
				Item item=new Item(j,i,title.getName()+"题目"+i,j+1);
				items.add(item);
			}
			title.setItems(items);
		}
		request.setAttribute("titles", titles);
		request.getRequestDispatcher("/admin/title/title_list.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
