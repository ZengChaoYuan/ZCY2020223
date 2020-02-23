package com.cyzy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyzy.bean.Goods;
import com.cyzy.dao.GoodsDao;
import com.cyzy.dao.GoodsDaoImpl;

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
		
	}
	private void deleteGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	private void updateBefore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	private void updateGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	private void queryGoodsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		GoodsDao goodsDao=new GoodsDaoImpl();
		Goods goods=new Goods();
		List<Goods> goodsList=goodsDao.queryGoods(goods);
		request.setAttribute("goodsList", goodsList);
		request.getRequestDispatcher("/goods/goods_list.jsp").forward(request, response);
	}
	private void goodsDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
