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
	//�ύ,����÷�
	private void countScope(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	request.getRequestDispatcher("/front/customer/index.jsp").forward(request, response);
	}
	
	
	
	//��������
	private void answer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int areaId = Integer.parseInt(request.getParameter("areaId"));
		TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
		//���Ҹ������������е���Ŀ
		List<Title> titleList =titleService.queryTitles(areaId);
		for(int i=0;i<titleList.size();i++) {
			//�õ����е���Ŀ
			Title title=titleList.get(i);
			//�õ���Ŀ��ID
			int titleId=title.getTitleId();
		ItemService itemService=(ItemService)ServiceFactory.getServiceImpl(ItemService.class.getName());
		    //ͨ����Ŀ��ID�ҵ���Ӧ��ѡ��
			List<Item> itemList=itemService.queryItems(titleId);
			//��ѡ��ļ��Ϸŵ���Ŀ����
			title.setItems(itemList);
		}
		
		request.setAttribute("titleList", titleList);
		request.getRequestDispatcher("/front/customer/assess_answer.jsp").forward(request, response);
	}
	
	private void addTitleItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		//ȡֵ
		int areaId=1;
		String titleName=request.getParameter("titleName");
		String[] itemNames=request.getParameterValues("itemName");
		
		String[] scope=request.getParameterValues("scope");
		int scopes[] = new int[scope.length];
		for(int i=0;i<scope.length;i++) {
			scopes[i] = Integer.parseInt(scope[i]);
		}
			
		TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
		//������ĿId
		 int titleId=titleService.createTitleId();
		 System.out.println(titleId);
        //������Ŀ��
		 Title title=new Title(titleId,areaId,titleName);
		
		 titleService.addTitle(title);
		
		//����ѡ���
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
			out.println("window.alert('�����ɹ�');");
			out.println("</script>");
			queryTitleList(request,response);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.alert('����ʧ��');");
			out.println("</script>");
			queryTitleList(request,response);
		}	 
	}
	
	private void queryTitleList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		//��Ŀ�ļ���:������id��ȥ������Ŀ�ļ���
		int areaId=1;
		TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
		//���Ҹ������������е���Ŀ
		List<Title> titleList =titleService.queryTitles(areaId);
		for(int i=0;i<titleList.size();i++) {
			//�õ����е���Ŀ
			Title title=titleList.get(i);
			//�õ���Ŀ��ID
			int titleId=title.getTitleId();
		ItemService itemService=(ItemService)ServiceFactory.getServiceImpl(ItemService.class.getName());
		    //ͨ����Ŀ��ID�ҵ���Ӧ��ѡ��
			List<Item> itemList=itemService.queryItems(titleId);
			//��ѡ��ļ��Ϸŵ���Ŀ����
			title.setItems(itemList);
		}
		
		request.setAttribute("titleList", titleList);
		request.getRequestDispatcher("/admin/title/title_list.jsp").forward(request, response);
	}
    
	private void deleteTitleItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		//��ȡ��ǰ����ĿID
		int titleId = Integer.parseInt(request.getParameter("titleId"));
	    System.out.println(titleId);
		//ɾ����Ŀ
		TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
		int titleResult=titleService.deleteTitleBytitleId(titleId);
		if(titleResult>0) {
			//ɾ��ѡ��
			ItemService itemService=(ItemService)ServiceFactory.getServiceImpl(ItemService.class.getName());
			int result=itemService.deleteItemBytitleId(titleId);
			if(result>0) {
				JsonMessage msg=new JsonMessage();
				msg.setId(1);
				msg.setMsg("ɾ���ɹ�!");
				String json=JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}else {
				JsonMessage msg=new JsonMessage();
				msg.setId(2);
				msg.setMsg("ɾ��ʧ��!");
				String json=JSONObject.toJSONString(msg);
				response.getWriter().println(json);
				return;
			}
		}
		
	}
	
	private void updateBeforeTitleItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		//��ȡ��ǰ��ĿID
		int titleId = Integer.parseInt(request.getParameter("titleId"));
	    System.out.println(titleId);
	    //ͨ����ǰID�õ���Ŀ��������Ϣ
	    TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
	    Title title=titleService.getTitleBytitleId(titleId);
		ItemService itemService=(ItemService)ServiceFactory.getServiceImpl(ItemService.class.getName());
		List<Item> itemList=itemService.queryItems(titleId);
	    request.setAttribute("title", title);
	    request.setAttribute("itemList", itemList);
	    request.getRequestDispatcher("/admin/title/title_update.jsp").forward(request, response);
	}
	//�޸���Ŀ��ѡ��
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
		//�޸���Ŀ��
		Title title=new Title(titleId,areaId,titleName);
		TitleService titleService=(TitleService)ServiceFactory.getServiceImpl(TitleService.class.getName());
		titleService.updateTitle(title);
		//ɾ��ѡ���
		ItemService itemService=(ItemService)ServiceFactory.getServiceImpl(ItemService.class.getName());
		itemService.deleteItemBytitleId(titleId);
		//����ѡ���
		int result=0;
		try {
			result=itemService.addItem(titleId, itemNames, scopes);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		if(result>0) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.alert('�޸ĳɹ�');");
			out.println("</script>");
			queryTitleList(request,response);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.alert('�޸�ʧ��');");
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
