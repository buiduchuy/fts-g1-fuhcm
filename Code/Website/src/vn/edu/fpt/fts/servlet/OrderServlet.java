package vn.edu.fpt.fts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.edu.fpt.fts.common.Common;
import vn.edu.fpt.fts.dao.OrderDAO;
import vn.edu.fpt.fts.dao.OrderStatusDAO;
import vn.edu.fpt.fts.pojo.Employee;
import vn.edu.fpt.fts.pojo.Order;
import vn.edu.fpt.fts.pojo.OrderStatus;
import vn.edu.fpt.fts.pojo.Owner;
import vn.edu.fpt.fts.process.NotificationProcess;
import vn.edu.fpt.fts.process.OrderProcess;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2214798247911372640L;
	OrderDAO orderDao = new OrderDAO();
	OrderStatusDAO orderStatusDao = new OrderStatusDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("btnAction");
			HttpSession session = request.getSession(true);

			if (action.equalsIgnoreCase("manageOrder")) {
				Owner owner = (Owner) session.getAttribute("owner");
				if (owner != null) {
					List<Order> listOrder = orderDao.getOrderByOwnerID(owner
							.getOwnerID());

					request.setAttribute("listOrder", listOrder);
					request.getRequestDispatcher("quan-ly-order.jsp").forward(
							request, response);
				} else {
					request.getRequestDispatcher("dang-nhap.jsp").forward(
							request, response);
				}

			} else if (action.equalsIgnoreCase("lostGoods")) {
				int orderID = Integer.valueOf(request.getParameter("orderID"));
				Order order = orderDao.getOrderByID(orderID);

				try {
					Date today = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss.SSS");
					Date deliveryDate = sdf.parse(order.getDeal().getGoods()
							.getDeliveryTime());
					System.out.println("Today" + today.getDate());
					System.out.println("Ngày giao hàng"
							+ deliveryDate.getDate());
					System.out.println("Khoảng thời gian giao hàng"
							+ (deliveryDate.getDate() + Common.periodDay));
					if (order.getOrderStatusID() != 3) {
						if (today.getDate() > deliveryDate.getDate()
								&& today.getDate() <= (deliveryDate.getDate() + Common.periodDay)) {
							if (orderDao.updateOrderStatusID(orderID,
									Common.order_refund) == 1) {
								request.setAttribute(
										"messageSuccess",
										"Báo mất hàng thành công. Chúng tôi sẽ kiểm tra và liên hệ trực tiếp trong thời gian sớm nhất!");
								request.getRequestDispatcher(
										"OrderServlet?btnAction=viewDetailOrder&orderID="
												+ orderID).forward(request,
										response);
							}
						} else {
							request.setAttribute(
									"messageError",
									"Không thể báo mất hàng vì hàng đang vận chuyển hoặc đã quá thời gian cho phép kể từ thời gian giao hàng là "
											+ Common.periodDay + " ngày.");
							request.getRequestDispatcher(
									"OrderServlet?btnAction=viewDetailOrder&orderID="
											+ orderID).forward(request,
									response);
						}
					} else {
						request.setAttribute(
								"messageError",
								"Hệ thống đã nhận được báo mất hàng của bạn. Chúng tôi sẽ kiểm tra và liên hệ trực tiếp trong thời gian sớm nhất!");
						request.getRequestDispatcher(
								"OrderServlet?btnAction=viewDetailOrder&orderID="
										+ orderID).forward(request, response);
					}

				} catch (Exception ex) {
					request.setAttribute("messageError",
							"Có lỗi xảy ra. Xin vui lòng kiểm tra lại hoá đơn trước khi báo mất hàng!");
					request.getRequestDispatcher(
							"OrderServlet?btnAction=viewDetailOrder&orderID="
									+ orderID).forward(request, response);
				}
			} else if (action.equalsIgnoreCase("viewDetailOrder")) {
				int orderID = Integer.valueOf(request.getParameter("orderID"));
				Order order = orderDao.getOrderByID(orderID);

				String message = request.getParameter("message");

				request.setAttribute("order", order);
				request.setAttribute("message", message);
				request.getRequestDispatcher("chi-tiet-order.jsp").forward(
						request, response);
			} else if (action.equalsIgnoreCase("employeeManageOrder")) {
				Employee employee = (Employee) session.getAttribute("employee");
				if (employee != null) {
					List<Order> listOrder = orderDao.getAllOrder();
					List<OrderStatus> listOrderStatus = orderStatusDao
							.getAllOrderStatus();

					request.setAttribute("listOrder", listOrder);
					request.setAttribute("listOrderStatus", listOrderStatus);

					request.getRequestDispatcher("admin/manage-order.jsp")
							.forward(request, response);
				} else {
					request.getRequestDispatcher("login.jsp").forward(request,
							response);
				}
			} else if (action.equalsIgnoreCase("employeeUpdateOrderStatus")) {
				int orderID = Integer.valueOf(request.getParameter("orderID"));
				try {
					orderDao.updateOrderStatusID(orderID, Common.order_finish);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					request.setAttribute("messageError",
							"Không thể cập nhật trạng thái hóa đơn này. Xin vui lòng thử lại sau!");
					request.getRequestDispatcher(
							"OrderServlet?btnAction=viewDetailOrder&orderID="
									+ orderID).forward(request, response);
				}
			} else if (action.equalsIgnoreCase("searchOrderByID")) {
				String orderIDStr = request.getParameter("txtOrderID");
				if (!orderIDStr.isEmpty()) {
					int orderID = Integer.valueOf(orderIDStr);
					try {
						List<Order> list = orderDao.searchOrderByID(orderID);
						request.setAttribute("listOrder", list);
						request.getRequestDispatcher(
								"OrderServlet?btnAction=manageOrderEmployee")
								.forward(request, response);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						request.setAttribute("messageError",
								"Có lỗi xảy ra trong quá trình tìm kiếm. Xin vui lòng thử lại sau!");
						request.getRequestDispatcher(
								"OrderServlet?btnAction=manageOrderEmployee")
								.forward(request, response);

					}
				}
			} else if (action.equals("employeeViewDetailOrder")) {
				int orderID = Integer.valueOf(request
						.getParameter("txtOrderID"));
				try {
					Order order = orderDao.getOrderByID(orderID);
					request.setAttribute("order", order);
					request.getRequestDispatcher("admin/detail-order.jsp")
							.forward(request, response);
				} catch (Exception e) {
					// TODO: handle exception
					request.setAttribute("messageError",
							"Có lỗi xảy ra trong truy vấn. Xin vui lòng thử lại sau!");
					request.getRequestDispatcher(
							"OrderServlet?btnAction=manageOrderEmployee")
							.forward(request, response);
				}
			} else if (action.equals("cancelOrder")) {
				int orderID = Integer.valueOf(request.getParameter("orderID"));
				try {
					OrderProcess orderProcess = new OrderProcess();
					orderProcess.cancelOrder(orderID);

					Order order = orderDao.getOrderByID(orderID);

					request.setAttribute("messageSuccess",
							"Hủy đơn hàng thành công.");
					request.setAttribute("order", order);
					request.getRequestDispatcher("chi-tiet-order.jsp").forward(
							request, response);
				} catch (Exception e) {
					// TODO: handle exception
					request.setAttribute("messageError",
							"Có lỗi xảy ra trong truy vấn. Xin vui lòng thử lại sau!");
					request.getRequestDispatcher("chi-tiet-order.jsp").forward(
							request, response);
				}
			} else if (action.equals("reportOrder")) {
				int orderID = Integer.valueOf(request.getParameter("orderID"));
				try {
					Order order = orderDao.getOrderByID(orderID);
					NotificationProcess notificationProcess = new NotificationProcess();
					notificationProcess.insertOwnerReportOrder(order);

					request.setAttribute(
							"messageSuccess",
							"Báo mất hàng thành công. Chúng tôi sẽ kiểm tra và liên hệ trực tiếp trong thời gian sớm nhất!");
					request.setAttribute("order", order);
					request.getRequestDispatcher("chi-tiet-order.jsp").forward(
							request, response);

				} catch (Exception e) {
					// TODO: handle exception
					request.setAttribute("messageError",
							"Có lỗi xảy ra trong truy vấn. Xin vui lòng thử lại sau!");
					request.getRequestDispatcher("chi-tiet-order.jsp").forward(
							request, response);
				}
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
