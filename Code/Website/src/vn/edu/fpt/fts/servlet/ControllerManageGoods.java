package vn.edu.fpt.fts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import vn.edu.fpt.fts.common.Common;
import vn.edu.fpt.fts.dao.DriverDAO;
import vn.edu.fpt.fts.dao.GoodsDAO;
import vn.edu.fpt.fts.pojo.Driver;
import vn.edu.fpt.fts.pojo.Goods;
import vn.edu.fpt.fts.pojo.Owner;
import vn.edu.fpt.fts.pojo.Route;
import vn.edu.fpt.fts.process.MatchingProcess;

/**
 * Servlet implementation class ControllerManageGoods
 */
public class ControllerManageGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerManageGoods() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("btnAction");
			HttpSession session = request.getSession(true);
			GoodsDAO goodDao = new GoodsDAO();
			DriverDAO driverDao = new DriverDAO();
			MatchingProcess matchingProcess = new MatchingProcess();
			if ("suggestFromSystem".equals(action)) {
				session.removeAttribute("listRouter");
				Goods goods = (Goods) session.getAttribute("detailGood1");
				int IdGood = goods.getGoodsID();
				// int IdGood = Integer
				// .parseInt(request.getParameter("txtIdGood"));
				List<Route> list = matchingProcess.getSuggestionRoute(IdGood);
				if (list.size() != 0) {
					Route[] listRou = new Route[list.size()];
					list.toArray(listRou);
					List<Driver> listDriver = driverDao.getAllDriver();
					Driver[] listDri = new Driver[listDriver.size()];
					listDriver.toArray(listDri);
					session.setAttribute("detailGood",
							goodDao.getGoodsByID(IdGood));
					session.setAttribute("listRouter", listRou);
					session.setAttribute("listDriver", listDri);
					RequestDispatcher rd = request
							.getRequestDispatcher("goi-y-he-thong.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request
							.getRequestDispatcher("goi-y-he-thong.jsp");
					rd.forward(request, response);
				}
			}
			if ("manageGoods".equals(action)) {
				Owner owner = (Owner) session.getAttribute("owner");
				List<Goods> manageGood = goodDao.getListGoodsByOwnerID(owner
						.getOwnerID());
				List<Goods> manageGood1 = new ArrayList<Goods>();
				for (int i = 0; i < manageGood.size(); i++) {
					if (manageGood.get(i).getActive() == 1) {
						manageGood.get(i).setPickupTime(
								Common.changeFormatDate(manageGood.get(i)
										.getPickupTime(),
										"yyyy-MM-dd hh:mm:ss.s", "dd-MM-yyyy"));
						manageGood.get(i).setDeliveryTime(
								Common.changeFormatDate(manageGood.get(i)
										.getDeliveryTime(),
										"yyyy-MM-dd hh:mm:ss.s", "dd-MM-yyyy"));
						manageGood1.add(manageGood.get(i));
					}
				}
				Goods[] list1 = new Goods[manageGood1.size()];
				manageGood1.toArray(list1);
				session.setAttribute("listGood1", list1);
				RequestDispatcher rd = request
						.getRequestDispatcher("quan-ly-hang.jsp");
				rd.forward(request, response);
			}
			if ("viewDetailGood1".equals(action)) {
				try {
					int idGood = Integer.parseInt(request
							.getParameter("idGood"));
					Goods good = goodDao.getGoodsByID(idGood);
					good.setPickupTime(Common.changeFormatDate(
							good.getPickupTime(), "yyyy-MM-dd hh:mm:ss.s",
							"dd-MM-yyyy"));
					good.setDeliveryTime(Common.changeFormatDate(
							good.getDeliveryTime(), "yyyy-MM-dd hh:mm:ss.s",
							"dd-MM-yyyy"));
					session.setAttribute("detailGood1", good);
					session.setAttribute("priceCreateGood",
							Common.priceCreateGood);
					session.setAttribute("priceTotal", good.getPrice()
							+ Common.priceCreateGood);
					RequestDispatcher rd = request
							.getRequestDispatcher("chi-tiet-hang.jsp");
					rd.forward(request, response);
				} catch (Exception ex) {

				}
			}
			if ("viewDetailGood2".equals(action)) {
				try {
					int idGood = Integer.parseInt(request
							.getParameter("idGood"));
					Goods good = goodDao.getGoodsByID(idGood);
					session.setAttribute("detailGood2", good);
					RequestDispatcher rd = request
							.getRequestDispatcher("chi-tiet-order.jsp");
					rd.forward(request, response);
				} catch (Exception ex) {

				}
			}
			if ("updateGood".equals(action)) {
				Goods go = (Goods) session.getAttribute("detailGood1");
				String pickupAddress = request.getParameter("txtpickupAddress");
				String pickupTime = request.getParameter("txtpickupTime");
				String deliveryAddress = request
						.getParameter("txtdeliveryAddress");
				String deliveryTime = request.getParameter("txtdeliveryTime");
				int weight = Integer
						.parseInt(request.getParameter("txtWeight"));
				int goodsCategoryID = Integer.parseInt(request
						.getParameter("ddlgoodsCategoryID"));
				String notes = "";
				try {
					notes = notes + request.getParameter("txtNotes");
				} catch (Exception ex) {

				}

				double price = Double.parseDouble(request
						.getParameter("txtPrice"));
				if (price == 0) {
					DecimalFormat formatPrice = new DecimalFormat("#");
					try {
						price = Double
								.valueOf(formatPrice.format(Common.perKilometer
										* Common.perKilogram
										* weight
										* Common.distance(
												Common.latGeoCoding(pickupAddress),
												Common.lngGeoCoding(pickupAddress),
												Common.latGeoCoding(deliveryAddress),
												Common.lngGeoCoding(deliveryAddress),
												"K")));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (XPathExpressionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Goods good = new Goods();
				try {
					good = new Goods(go.getGoodsID(), weight, price,
							Common.changeFormatDate(pickupTime, "dd-MM-yyyy",
									"MM-dd-yyyy"), pickupAddress,
							Common.changeFormatDate(deliveryTime, "dd-MM-yyyy",
									"MM-dd-yyyy"), deliveryAddress,
							Common.lngGeoCoding(pickupAddress),
							Common.latGeoCoding(pickupAddress),
							Common.lngGeoCoding(deliveryAddress),
							Common.latGeoCoding(deliveryAddress), notes, go
									.getCreateTime().toString(),
							Common.activate, go.getOwnerID(), goodsCategoryID);
				} catch (XPathExpressionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (goodDao.updateGoods(good) == 1) {
					session.setAttribute("messageSuccess",
							"Cập nhật thành công");
					RequestDispatcher rd = request
							.getRequestDispatcher("ControllerManageGoods?btnAction=viewDetailGood1&idGood="
									+ go.getGoodsID());
					rd.forward(request, response);
				} else {
					session.setAttribute("messageError",
							"Cập nhật thất bại. Xin vui lòng thử lại sau!");
					RequestDispatcher rd = request
							.getRequestDispatcher("ControllerManageGoods?btnAction=viewDetailGood1&idGood="
									+ go.getGoodsID());
					rd.forward(request, response);
				}
			}
			if ("deleteGood".equals(action)) {
				int idGoodDelete = Integer.parseInt(request
						.getParameter("txtIdGood"));
				Goods goodDelete = goodDao.getGoodsByID(idGoodDelete);
				goodDelete.setActive(10);
				if (goodDao.updateGoods(goodDelete) == 1) {
					session.setAttribute("messageSuccess",
							"Xoá hàng thành công!");
					RequestDispatcher rd = request
							.getRequestDispatcher("ControllerManageGoods?btnAction=manageGoods");
					rd.forward(request, response);
				} else {
					session.setAttribute("messageError",
							"Xoá hàng thất bại. Xin vui lòng thử lại sau!");
					RequestDispatcher rd = request
							.getRequestDispatcher("ControllerManageGoods?btnAction=viewDetailGood1&idGood="
									+ idGoodDelete);
					rd.forward(request, response);
				}
			}
			if ("viewDetailGood1".equals(action)) {
				try {
					int idGood = Integer.parseInt(request
							.getParameter("idGood"));
					Goods good = goodDao.getGoodsByID(idGood);
					session.setAttribute("detailGood1", good);
					RequestDispatcher rd = request
							.getRequestDispatcher("chi-tiet-hang.jsp");
					rd.forward(request, response);
				} catch (Exception ex) {

				}
			}
			if ("filter".equals(action)) {
				// try {
				// String startDate = request.getParameter("txtstartdate");
				// } catch (Exception ex) {
				//
				// }
				// try {
				// String endDate = request.getParameter("txtenddate");
				// } catch (Exception ex) {
				//
				// }
				Owner owner = (Owner) session.getAttribute("owner");
				List<Goods> manageGood = goodDao.getListGoodsByOwnerID(owner
						.getOwnerID());
				List<Goods> manageGood1 = new ArrayList<Goods>();
				for (int i = 0; i < manageGood.size(); i++) {
					if (manageGood.get(i).getActive() == 1) {
						manageGood.get(i).setPickupTime(
								Common.changeFormatDate(manageGood.get(i)
										.getPickupTime(),
										"yyyy-MM-dd hh:mm:ss.s", "dd-MM-yyyy"));
						manageGood.get(i).setDeliveryTime(
								Common.changeFormatDate(manageGood.get(i)
										.getDeliveryTime(),
										"yyyy-MM-dd hh:mm:ss.s", "dd-MM-yyyy"));
						manageGood1.add(manageGood.get(i));
					}
				}
				Goods[] list1 = new Goods[manageGood1.size()];
				manageGood1.toArray(list1);
				session.setAttribute("listGood1", list1);
				RequestDispatcher rd = request
						.getRequestDispatcher("quan-ly-hang.jsp");
				rd.forward(request, response);
			}
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
