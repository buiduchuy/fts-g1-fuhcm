package vn.edu.fpt.fts.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.edu.fpt.fts.common.Common;
import vn.edu.fpt.fts.dao.AccountDAO;
import vn.edu.fpt.fts.dao.DealDAO;
import vn.edu.fpt.fts.dao.DealOrderDAO;
import vn.edu.fpt.fts.dao.DriverDAO;
import vn.edu.fpt.fts.dao.GoodsCategoryDAO;
import vn.edu.fpt.fts.dao.GoodsDAO;
import vn.edu.fpt.fts.dao.OrderDAO;
import vn.edu.fpt.fts.dao.OwnerDAO;
import vn.edu.fpt.fts.dao.RouteDAO;
import vn.edu.fpt.fts.pojo.Deal;
import vn.edu.fpt.fts.pojo.DealOrder;
import vn.edu.fpt.fts.pojo.Driver;
import vn.edu.fpt.fts.pojo.Goods;
import vn.edu.fpt.fts.pojo.GoodsCategory;
import vn.edu.fpt.fts.pojo.Order;
import vn.edu.fpt.fts.pojo.Owner;
import vn.edu.fpt.fts.pojo.Route;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		RouteDAO rou = new RouteDAO();
		DealDAO dea = new DealDAO();
		DriverDAO dri = new DriverDAO();
		GoodsDAO goodDao = new GoodsDAO();
		String action = request.getParameter("btnAction");
		HttpSession session = request.getSession(true);
		
		
		if ("viewDetailRouter".equals(action)) {
			int idRouter = Integer.parseInt(request.getParameter("idRouter"));
			Route router = rou.getRouteById(idRouter);
			session.setAttribute("viewDetailRoute", router);
			RequestDispatcher rd = request
					.getRequestDispatcher("chi-tiet-route.jsp");
			rd.forward(request, response);
		}
		if ("sendSuggest".equals(action)) {
			if (session.getAttribute("viewDetailRoute") != null
					&& session.getAttribute("newGood") != null) {
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				String createTime = dateFormat.format(date);
				Route route = (Route) session.getAttribute("viewDetailRoute");
				Goods good = (Goods) session.getAttribute("newGood");
				Deal newDeal = new Deal(good.getPrice(), good.getNotes(),
						createTime, "Owner", route.getRouteID(),
						good.getGoodsID(), 1, 1,1);
				if (dea.insertDeal(newDeal) != -1) {
					Route[] listRouter = (Route[]) session
							.getAttribute("listRouter");
					List<Route> list = new ArrayList<Route>(
							Arrays.asList(listRouter));
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getRouteID() == route.getRouteID()) {
							list.remove(i);
						}
					}
					Route[] listRou = new Route[list.size()];
					list.toArray(listRou);
					session.setAttribute("listRouter", listRou);
					session.setAttribute("messageCreateGood",
							"Gửi deal thành công");
					RequestDispatcher rd = request
							.getRequestDispatcher("goi-y-he-thong.jsp");
					rd.forward(request, response);
				}

			}
		}
		if ("manageGoods".equals(action)) {
			Owner owner = (Owner) session.getAttribute("owner");
			List<Goods> manageGood = goodDao.getListGoodsByOwnerID(owner.getOwnerID());
			List<Goods> manageGood1 = new ArrayList<Goods>();
			List<Goods> manageGood2 = new ArrayList<Goods>();
			for (int i = 0; i < manageGood.size(); i++) {
				if (manageGood.get(i).getActive() == 1) {
					manageGood1.add(manageGood.get(i));
				}
				if (manageGood.get(i).getActive() == 2) {
					manageGood2.add(manageGood.get(i));
				}
			}
			Goods[] list1 = new Goods[manageGood1.size()];
			manageGood1.toArray(list1);
			Goods[] list2 = new Goods[manageGood2.size()];
			manageGood2.toArray(list2);
			session.setAttribute("listGood1", list1);
			session.setAttribute("listGood2", list2);
			RequestDispatcher rd = request
					.getRequestDispatcher("quan-ly-hang.jsp");
			rd.forward(request, response);
		}
		if ("viewDetailGood1".equals(action)) {
			try {
				int idGood = Integer.parseInt(request.getParameter("idGood"));
				Goods good = goodDao.getGoodsByID(idGood);
				session.setAttribute("detailGood1", good);
				RequestDispatcher rd = request
						.getRequestDispatcher("chi-tiet-hang.jsp");
				rd.forward(request, response);
			} catch (Exception ex) {

			}
		}if ("viewDetailGood2".equals(action)) {
			try {
				int idGood = Integer.parseInt(request.getParameter("idGood"));
				Goods good = goodDao.getGoodsByID(idGood);
				session.setAttribute("detailGood2", good);
				RequestDispatcher rd = request
						.getRequestDispatcher("chi-tiet-order.jsp");
				rd.forward(request, response);
			} catch (Exception ex) {

			}
		}
		if ("viewSuggest".equals(action)) {
			List<Deal> dealByOwner = new ArrayList<Deal>();
			List<Deal> dealByDriver = new ArrayList<Deal>();
			Goods goolast = (Goods) session.getAttribute("newGood");
			List<Deal> listDealByIdGood = dea.getDealByGoodsID(goolast
					.getGoodsID());
			for (int i = 0; i < listDealByIdGood.size(); i++) {
				if (listDealByIdGood.get(i).getActive() == 1) {
					if (listDealByIdGood.get(i).getSender().equals("Owner")) {
						dealByOwner.add(listDealByIdGood.get(i));
					}
					if (listDealByIdGood.get(i).getSender().equals("Driver")
							&& listDealByIdGood.get(i).getDealStatusID() == 2) {
						dealByDriver.add(listDealByIdGood.get(i));
					}
				}
			}
			Deal[] list1 = new Deal[dealByOwner.size()];
			dealByOwner.toArray(list1);
			Deal[] list2 = new Deal[dealByDriver.size()];
			dealByDriver.toArray(list2);
			List<Route> list = rou.getAllRoute();
			Route[] listRou = new Route[list.size()];
			list.toArray(listRou);
			List<Driver> listDriver = dri.getAllDriver();
			Driver[] listDri = new Driver[listDriver.size()];
			listDriver.toArray(listDri);
			session.setAttribute("listRouteSuggest", listRou);
			session.setAttribute("dealByOwner", list1);
			session.setAttribute("dealByDriver", list2);
			session.setAttribute("listDriver", listDri);
			RequestDispatcher rd = request
					.getRequestDispatcher("danh-sach-de-nghi.jsp");
			rd.forward(request, response);
		}
		if ("deleteDealSent".equals(action)) {
			int idDeal = Integer.parseInt(request.getParameter("idDeal"));
			Deal deleteDeal = dea.getDealByID(idDeal);
			deleteDeal.setActive(0);
			if (dea.insertDeal(deleteDeal) == 1) {
				List<Deal> dealByOwner = new ArrayList<Deal>();
				List<Deal> dealByDriver = new ArrayList<Deal>();
				Goods goolast = (Goods) session.getAttribute("newGood");
				List<Deal> listDealByIdGood = dea.getDealByGoodsID(goolast
						.getGoodsID());
				for (int i = 0; i < listDealByIdGood.size(); i++) {
					if (listDealByIdGood.get(i).getActive() == 1) {
						if (listDealByIdGood.get(i).getSender().equals("Owner")) {
							dealByOwner.add(listDealByIdGood.get(i));
						}
						if (listDealByIdGood.get(i).getSender()
								.equals("Driver")
								&& listDealByIdGood.get(i).getDealStatusID() == 2) {
							dealByDriver.add(listDealByIdGood.get(i));
						}
					}
				}
				Deal[] list1 = new Deal[dealByOwner.size()];
				dealByOwner.toArray(list1);
				Deal[] list2 = new Deal[dealByDriver.size()];
				dealByDriver.toArray(list2);
				List<Route> list = rou.getAllRoute();
				Route[] listRou = new Route[list.size()];
				list.toArray(listRou);
				List<Driver> listDriver = dri.getAllDriver();
				Driver[] listDri = new Driver[listDriver.size()];
				listDriver.toArray(listDri);
				session.setAttribute("listRouteSuggest", listRou);
				session.setAttribute("dealByOwner", list1);
				session.setAttribute("dealByDriver", list2);
				session.setAttribute("listDriver", listDri);
				RequestDispatcher rd = request
						.getRequestDispatcher("danh-sach-de-nghi.jsp");
				rd.forward(request, response);
			}
		}
		if ("viewDetailDeal".equals(action)) {
			int idDeal = Integer.parseInt(request.getParameter("idDeal"));
			Deal changeDealStatus = dea.getDealByID(idDeal);

			List<Deal> listHistory = new ArrayList<Deal>();
			if (dea.insertDeal(changeDealStatus) == 1) {
				List<Deal> list = dea.getDealByRouteID(changeDealStatus
						.getRouteID());
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getGoodsID() == changeDealStatus
							.getGoodsID()) {
						listHistory.add(list.get(i));
					}
				}
				Deal[] historyDeal = new Deal[listHistory.size()];
				listHistory.toArray(historyDeal);
				session.setAttribute("historyDeal", historyDeal);
				session.setAttribute("dealFromDriver", changeDealStatus);
				RequestDispatcher rd = request
						.getRequestDispatcher("chi-tiet-de-nghi.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("btnAction");
		HttpSession session = request.getSession(true);
		GoodsCategoryDAO goodCa = new GoodsCategoryDAO();
		AccountDAO acc = new AccountDAO();
		RouteDAO rou = new RouteDAO();
		GoodsDAO goodDao = new GoodsDAO();
		OwnerDAO ow = new OwnerDAO();
		DealDAO dea = new DealDAO();
		DriverDAO dri = new DriverDAO();
		DealOrderDAO dealOrderDao = new DealOrderDAO();
		OrderDAO orderDao = new OrderDAO();
		Common common= new Common();
		
		if("suggestFromSystem".equals(action)){
			int IdGood=Integer.parseInt(request.getParameter("txtIdGood"));
			List<Route> list = rou.getAllRoute();
			Route[] listRou = new Route[list.size()];
			list.toArray(listRou);
			List<Driver> listDriver = dri.getAllDriver();
			Driver[] listDri = new Driver[listDriver.size()];
			listDriver.toArray(listDri);
			session.removeAttribute("router");
			session.removeAttribute("good");
			session.removeAttribute("price");
			session.setAttribute("newGood", goodDao.getGoodsByID(IdGood));
			session.setAttribute("listRouter", listRou);
			session.setAttribute("listDriver", listDri);
			RequestDispatcher rd = request
					.getRequestDispatcher("goi-y-he-thong.jsp");
			rd.forward(request, response);
		}
		if ("updateGood".equals(action)) {
			Goods go = (Goods) session.getAttribute("detailGood1");
			String pickupAddress = request.getParameter("txtpickupAddress");
			String pickupTime = request.getParameter("txtpickupTime");
			String deliveryAddress = request.getParameter("txtdeliveryAddress");
			String deliveryTime = request.getParameter("txtdeliveryTime");
			int weight = Integer.parseInt(request.getParameter("txtWeight"));
			int goodsCategoryID = Integer.parseInt(request
					.getParameter("ddlgoodsCategoryID"));
			String notes = "";
			try {
				notes = notes + request.getParameter("txtNotes");
			} catch (Exception ex) {

			}
			float a = Float.parseFloat("10");
			double price = Double.parseDouble(request.getParameter("txtPrice"));
			Goods good = new Goods(go.getGoodsID(), weight, price, pickupTime,
					pickupAddress, deliveryTime, deliveryAddress, a, a, a, a,
					notes, go.getCreateTime().toString(), 1, go.getOwnerID(),
					goodsCategoryID);

			if (goodDao.updateGoods(good) == 1) {

				session.setAttribute("messageUpdateGood", "Cập nhật thành công");
				RequestDispatcher rd = request
						.getRequestDispatcher("Controller?btnAction=viewDetailGood1&idGood="
								+ go.getGoodsID());
				rd.forward(request, response);
			} else {

			}
		}
		if ("deleteGood".equals(action)) {
			Goods go = (Goods) session.getAttribute("detailGood1");
			go.setActive(0);
			if (goodDao.updateGoods(go) == 1) {
				Owner owner = (Owner) session.getAttribute("owner");
				List<Goods> manageGood = goodDao.getListGoodsByOwnerID(owner.getOwnerID());
				List<Goods> manageGood1 = new ArrayList<Goods>();
				List<Goods> manageGood2 = new ArrayList<Goods>();
				for (int i = 0; i < manageGood.size(); i++) {
					if (manageGood.get(i).getActive() == 1) {
						manageGood1.add(manageGood.get(i));
					}
					if (manageGood.get(i).getActive() == 2) {
						manageGood2.add(manageGood.get(i));
					}
				}
				Goods[] list1 = new Goods[manageGood1.size()];
				manageGood1.toArray(list1);
				Goods[] list2 = new Goods[manageGood2.size()];
				manageGood2.toArray(list2);
				session.setAttribute("listGood1", list1);
				session.setAttribute("listGood2", list2);
				RequestDispatcher rd = request
						.getRequestDispatcher("quan-ly-hang.jsp");
				rd.forward(request, response);
			} else {

			}
		}
		if ("viewDetailGood1".equals(action)) {
			try {
				int idGood = Integer.parseInt(request.getParameter("idGood"));
				Goods good = goodDao.getGoodsByID(idGood);
				session.setAttribute("detailGood1", good);
				RequestDispatcher rd = request
						.getRequestDispatcher("chi-tiet-hang.jsp");
				rd.forward(request, response);
			} catch (Exception ex) {

			}
		}
		if ("confirmDeal".equals(action)) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String createTime = dateFormat.format(date);
			Deal dealFromDriver = (Deal) session.getAttribute("dealFromDriver");
			dealFromDriver.setDealStatusID(3);
			if (dea.insertDeal(dealFromDriver) == 1) {
				Order order = new Order(dealFromDriver.getPrice(), false,
						false, false, createTime, 1);
				try {
					if (orderDao.insertOrder(order) != -1) {
						DealOrder dealOrder = new DealOrder(
								dealFromDriver.getDealID(),
								orderDao.insertOrder(order));
						if (dealOrderDao.insertDealOrder(dealOrder) != -1) {
							Goods goodChangeOrder = goodDao
									.getGoodsByID(dealFromDriver.getGoodsID());
							goodChangeOrder.setActive(2);
							if (goodDao.updateGoods(goodChangeOrder) != -1) {
								Owner owner = (Owner) session
										.getAttribute("owner");
								List<Goods> manageGood = goodDao
										.getListGoodsByOwnerID(owner.getOwnerID());
								List<Goods> manageGood1 = new ArrayList<Goods>();
								List<Goods> manageGood2 = new ArrayList<Goods>();
								for (int i = 0; i < manageGood.size(); i++) {
									if (manageGood.get(i).getActive() == 1) {
										manageGood1.add(manageGood.get(i));
									}
									if (manageGood.get(i).getActive() == 2) {
										manageGood2.add(manageGood.get(i));
									}
								}
								Goods[] list1 = new Goods[manageGood1.size()];
								manageGood1.toArray(list1);
								Goods[] list2 = new Goods[manageGood2.size()];
								manageGood2.toArray(list2);
								session.setAttribute("listGood1", list1);
								session.setAttribute("listGood2", list2);
								RequestDispatcher rd = request
										.getRequestDispatcher("quan-ly-hang.jsp");
								rd.forward(request, response);
							}
						}
					}
				} catch (Exception ex) {

				}
			}
		}
		if ("denyDeal".equals(action)) {
			Deal dealFromDriver = (Deal) session.getAttribute("dealFromDriver");
			dealFromDriver.setDealStatusID(4);
			if (dea.insertDeal(dealFromDriver) == 1) {
				List<Deal> dealByOwner = new ArrayList<Deal>();
				List<Deal> dealByDriver = new ArrayList<Deal>();
				Goods goolast = (Goods) session.getAttribute("newGood");
				List<Deal> listDealByIdGood = dea.getDealByGoodsID(goolast
						.getGoodsID());
				for (int i = 0; i < listDealByIdGood.size(); i++) {
					if (listDealByIdGood.get(i).getActive() == 1) {
						if (listDealByIdGood.get(i).getSender().equals("Owner")) {
							dealByOwner.add(listDealByIdGood.get(i));
						}
						if (listDealByIdGood.get(i).getSender()
								.equals("Driver")
								&& listDealByIdGood.get(i).getDealStatusID() == 2) {
							dealByDriver.add(listDealByIdGood.get(i));
						}
					}
				}
				Deal[] list1 = new Deal[dealByOwner.size()];
				dealByOwner.toArray(list1);
				Deal[] list2 = new Deal[dealByDriver.size()];
				dealByDriver.toArray(list2);
				List<Route> list = rou.getAllRoute();
				Route[] listRou = new Route[list.size()];
				list.toArray(listRou);
				List<Driver> listDriver = dri.getAllDriver();
				Driver[] listDri = new Driver[listDriver.size()];
				listDriver.toArray(listDri);
				session.setAttribute("listRouteSuggest", listRou);
				session.setAttribute("dealByOwner", list1);
				session.setAttribute("dealByDriver", list2);
				session.setAttribute("listDriver", listDri);
				RequestDispatcher rd = request
						.getRequestDispatcher("danh-sach-de-nghi.jsp");
				rd.forward(request, response);
			}
		}
		if ("sendOffer".equals(action)) {
			Deal dealFromDriver = (Deal) session.getAttribute("dealFromDriver");
			int price = Integer.parseInt(request.getParameter("txtPrice"));
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String createTime = dateFormat.format(date);
			String notes = "";
			try {
				notes = notes + request.getParameter("txtNotes");
			} catch (Exception ex) {

			}
			dealFromDriver.setDealStatusID(1);
			dealFromDriver.setPrice(price);
			dealFromDriver.setNotes(notes);
			dealFromDriver.setCreateTime(createTime);
			dealFromDriver.setSender("Owner");;
			if(dea.insertDeal(dealFromDriver)!= -1){
				List<Deal> dealByOwner = new ArrayList<Deal>();
				List<Deal> dealByDriver = new ArrayList<Deal>();
				Goods goolast = (Goods) session.getAttribute("newGood");
				List<Deal> listDealByIdGood = dea.getDealByGoodsID(goolast
						.getGoodsID());
				for (int i = 0; i < listDealByIdGood.size(); i++) {
					if (listDealByIdGood.get(i).getActive() == 1) {
						if (listDealByIdGood.get(i).getSender().equals("Owner")) {
							dealByOwner.add(listDealByIdGood.get(i));
						}
						if (listDealByIdGood.get(i).getSender()
								.equals("Driver")
								&& listDealByIdGood.get(i).getDealStatusID() == 2) {
							dealByDriver.add(listDealByIdGood.get(i));
						}
					}
				}
				Deal[] list1 = new Deal[dealByOwner.size()];
				dealByOwner.toArray(list1);
				Deal[] list2 = new Deal[dealByDriver.size()];
				dealByDriver.toArray(list2);
				List<Route> list = rou.getAllRoute();
				Route[] listRou = new Route[list.size()];
				list.toArray(listRou);
				List<Driver> listDriver = dri.getAllDriver();
				Driver[] listDri = new Driver[listDriver.size()];
				listDriver.toArray(listDri);
				session.setAttribute("listRouteSuggest", listRou);
				session.setAttribute("dealByOwner", list1);
				session.setAttribute("dealByDriver", list2);
				session.setAttribute("listDriver", listDri);
				RequestDispatcher rd = request
						.getRequestDispatcher("danh-sach-de-nghi.jsp");
				rd.forward(request, response);
			}
		}
	}

}
