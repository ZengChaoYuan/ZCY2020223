package com.cyzy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyzy.bean.Goods;
import com.cyzy.dao.GoodsDao;
import com.cyzy.dao.GoodsDaoImpl;
import com.cyzy.service.GoodsService;
import com.cyzy.util.ServiceFactory;

/**
 * Servlet implementation class GoodsServlet
 */
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoodsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String goodsAction = request.getParameter("goodsAction");
		if (goodsAction != null && goodsAction.equals("add")) {
			addGoods(request,response);
		} else if (goodsAction != null && goodsAction.equals("delete")) {
			deleteGoods(request,response);
		}else if (goodsAction != null && goodsAction.equals("updateBefore")) {
			updateBefore(request,response);
		} else if (goodsAction != null && goodsAction.equals("update")) {
			updateGoods(request,response);
		} else if (goodsAction != null && goodsAction.equals("list")) {
			queryGoodsList(request,response);
		}else if (goodsAction != null && goodsAction.equals("detail")) {
			goodsDetail(request,response);
		}
	}
	private void addGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 防乱码
		response.setContentType("text/html");
		//添加商品
		String goodsName=request.getParameter("goodsName");
		String goodsLClass=request.getParameter("goodsLClass");
		String goodsSClass=request.getParameter("goodsSClass");
		int status=Integer.parseInt(request.getParameter("status"));
		Goods goods=new Goods(0,goodsName,goodsLClass,goodsSClass,status);
		GoodsService goodsService=(GoodsService)ServiceFactory.getServiceImpl(GoodsService.class.getName());
		int result=goodsService.addGoods(goods);
		PrintWriter out =response.getWriter();
		out.println("<script>");
		out.println("window.alert('"+(result==0?"失败":"成功")+"');");
		out.println("</script>");
		queryGoodsList(request, response);
	}
	private void deleteGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int goodsId=Integer.parseInt(request.getParameter("goodsId"));
		GoodsService goodsService=(GoodsService)ServiceFactory.getServiceImpl(GoodsService.class.getName());
		int result=goodsService.deleteGoods(goodsId);
		if(result>0) {
			queryGoodsList(request, response);
		}
	}
	private void updateBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int goodsId=Integer.parseInt(request.getParameter("goodsId"));
		GoodsService goodsService=(GoodsService)ServiceFactory.getServiceImpl(GoodsService.class.getName());
		Goods goods=goodsService.getGoodsById(goodsId);
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("admin/goods/goods_update.jsp").forward(request, response);
	}
	private void updateGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		int goodsId=Integer.parseInt(request.getParameter("goodsId"));
		String goodsName=request.getParameter("goodsName");
		String goodsLClass=request.getParameter("goodsLClass");
		String goodsSClass=request.getParameter("goodsSClass");
		int status=Integer.parseInt(request.getParameter("status"));
		Goods goods=new Goods(goodsId,goodsName,goodsLClass,goodsSClass,status);
		GoodsService goodsService=(GoodsService)ServiceFactory.getServiceImpl(GoodsService.class.getName());
		int result=goodsService.updateGoods(goods);
		if(result>0) {
			queryGoodsList(request, response);
		}
		
	}
	private void queryGoodsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");

		GoodsService goodsService=(GoodsService)ServiceFactory.getServiceImpl(GoodsService.class.getName());
		Goods goods=new Goods();
		List<Goods> goodsList=goodsService.queryGoods(goods);
		request.setAttribute("goodsList", goodsList);
		request.getRequestDispatcher("/admin/goods/goods_list.jsp").forward(request, response);
	}
	private void goodsDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		int goodsId=Integer.parseInt(request.getParameter("goodsId"));
		GoodsService goodsService=(GoodsService)ServiceFactory.getServiceImpl(GoodsService.class.getName());
		Goods goods=goodsService.getGoodsById(goodsId);
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("/admin/goods/goods_detail.jsp").forward(request, response);
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
