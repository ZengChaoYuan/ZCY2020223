package com.cyzy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyzy.bean.Item;
import com.cyzy.bean.JsonMessage;
import com.cyzy.bean.Title;
import com.cyzy.service.ItemService;
import com.cyzy.service.TitleService;
import com.cyzy.util.ServiceFactory;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titleAction = request.getParameter("titleAction");
		if (titleAction != null && titleAction.equals("list")) {
			queryTitleList(request, response);
		}else if(titleAction != null && titleAction.equals("countScope")) {
			countScope(request,response);
		}else if(titleAction != null && titleAction.equals("answer")) {
			answer(request,response);
		}else if(titleAction != null && titleAction.equals("add")) {
			addTitleItem(request,response);
		}else if(titleAction != null && titleAction.equals("delete")) {
			deleteTitleItem(request,response);
		}else if(titleAction != null && titleAction.equals("updateBefore")) {
			updateBeforeTitleItem(request,response);
		}else if(titleAction != null && titleAction.equals("update")) {
			updateTitleItem(request,response);
		}

	}
	//提交,计算得分
	private void countScope(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	request.getRequestDispatcher("/front/customer/index.jsp").forward(request, response);
	}
	
	
	
	//评估答题
	private void answer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int areaId = Integer.parseInt(request.getParameter("areaId"));
		TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
		//查找该领域下面所有的题目
		List<Title> titleList =titleService.queryTitles(areaId);
		for(int i=0;i<titleList.size();i++) {
			//得到所有的题目
			Title title=titleList.get(i);
			//得到题目的ID
			int titleId=title.getTitleId();
		ItemService itemService=(ItemService)ServiceFactory.getServiceImpl(ItemService.class.getName());
		    //通过题目的ID找到对应的选项
			List<Item> itemList=itemService.queryItems(titleId);
			//把选项的集合放到题目里面
			title.setItems(itemList);
		}
		
		request.setAttribute("titleList", titleList);
		request.getRequestDispatcher("/front/customer/assess_answer.jsp").forward(request, response);
	}
	
	private void addTitleItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		//取值
		int areaId=1;
		String titleName=request.getParameter("titleName");
		String[] itemNames=request.getParameterValues("itemName");
		
		String[] scope=request.getParameterValues("scope");
		int scopes[] = new int[scope.length];
		for(int i=0;i<scope.length;i++) {
			scopes[i] = Integer.parseInt(scope[i]);
		}
			
		TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
		//新增题目Id
		 int titleId=titleService.createTitleId();
		 System.out.println(titleId);
        //新增题目表
		 Title title=new Title(titleId,areaId,titleName);
		
		 titleService.addTitle(title);
		
		//新增选项表
		ItemService itemService=(ItemService)ServiceFactory.getServiceImpl(ItemService.class.getName());
		int result=0;
		try {
			result=itemService.addItem(titleId, itemNames, scopes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result>0) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.alert('新增成功');");
			out.println("</script>");
			queryTitleList(request,response);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.alert('新增失败');");
			out.println("</script>");
			queryTitleList(request,response);
		}	 
	}
	
	private void queryTitleList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		//题目的集合:传领域id进去查找题目的集合
		int areaId=1;
		TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
		//查找该领域下面所有的题目
		List<Title> titleList =titleService.queryTitles(areaId);
		for(int i=0;i<titleList.size();i++) {
			//得到所有的题目
			Title title=titleList.get(i);
			//得到题目的ID
			int titleId=title.getTitleId();
		ItemService itemService=(ItemService)ServiceFactory.getServiceImpl(ItemService.class.getName());
		    //通过题目的ID找到对应的选项
			List<Item> itemList=itemService.queryItems(titleId);
			//把选项的集合放到题目里面
			title.setItems(itemList);
		}
		
		request.setAttribute("titleList", titleList);
		request.getRequestDispatcher("/admin/title/title_list.jsp").forward(request, response);
	}
    
	private void deleteTitleItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		//获取当前的题目ID
		int titleId = Integer.parseInt(request.getParameter("titleId"));
	    System.out.println(titleId);
		//删除题目
		TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
		int titleResult=titleService.deleteTitleBytitleId(titleId);
		if(titleResult>0) {
			//删除选项
			ItemService itemService=(ItemService)ServiceFactory.getServiceImpl(ItemService.class.getName());
			int result=itemService.deleteItemBytitleId(titleId);
			if(result>0) {
				JsonMessage msg=new JsonMessage();
				msg.setId(1);
				msg.setMsg("删除成功!");
				String json=JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}else {
				JsonMessage msg=new JsonMessage();
				msg.setId(2);
				msg.setMsg("删除失败!");
				String json=JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}
		
	}
	
	private void updateBeforeTitleItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		//获取当前题目ID
		int titleId = Integer.parseInt(request.getParameter("titleId"));
	    System.out.println(titleId);
	    //通过当前ID拿到题目的完整信息
	    TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
	    Title title=titleService.getTitleBytitleId(titleId);
		ItemService itemService=(ItemService)ServiceFactory.getServiceImpl(ItemService.class.getName());
		List<Item> itemList=itemService.queryItems(titleId);
	    request.setAttribute("title", title);
	    request.setAttribute("itemList", itemList);
	    request.getRequestDispatcher("/admin/title/title_update.jsp").forward(request, response);
	}
	//修改题目和选项
	private void updateTitleItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int areaId=1;
		int titleId = Integer.parseInt(request.getParameter("titleId"));
		String titleName=request.getParameter("titleName");
		String[] itemNames=request.getParameterValues("itemNames");
		String[] scope=request.getParameterValues("scopes");
		int scopes[] = new int[scope.length];
		for(int i=0;i<scope.length;i++) {
			scopes[i] = Integer.parseInt(scope[i]);
		}
		//修改题目表
		Title title=new Title(titleId,areaId,titleName);
		TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
		titleService.updateTitle(title);
		//删除选项表
		ItemService itemService=(ItemService)ServiceFactory.getServiceImpl(ItemService.class.getName());
		itemService.deleteItemBytitleId(titleId);
		//新增选项表
		int result=0;
		try {
			result=itemService.addItem(titleId, itemNames, scopes);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		if(result>0) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.alert('修改成功');");
			out.println("</script>");
			queryTitleList(request,response);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.alert('修改失败');");
			out.println("</script>");
			queryTitleList(request,response);
		}
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
