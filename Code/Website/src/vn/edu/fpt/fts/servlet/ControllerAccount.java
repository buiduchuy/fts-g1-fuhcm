package vn.edu.fpt.fts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import vn.edu.fpt.fts.pojo.GoodsCategory;
import vn.edu.fpt.fts.pojo.Owner;

/**
 * Servlet implementation class ControllerAccount
 */
public class ControllerAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

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
			/* TODO output your page here. You may use following sample code. */
			if ("login".equals(action)) {
				String email = request.getParameter("txtEmail");
				String password = request.getParameter("txtPassword");
				session.removeAttribute("errorLogin");
				session.removeAttribute("account");
				if (acc.checkLoginAccount(email, password) != null) {

					Owner owner = ow.getOwnerByEmail(acc.checkLoginAccount(email,
							password).getEmail());
					List<GoodsCategory> list = goodCa.getAllGoodsCategory();

					GoodsCategory[] typeGoods = new GoodsCategory[list.size()];
					list.toArray(typeGoods);
					session.setAttribute("typeGoods", typeGoods);
					session.setAttribute("owner", owner);
					session.setAttribute("account", owner.getLastName());
					if (session.getAttribute("namePage") != null) {
						String prePage = (String) session.getAttribute("namePage");
						RequestDispatcher rd = request
								.getRequestDispatcher(prePage);
						rd.forward(request, response);
					} else {
						RequestDispatcher rd = request
								.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
					}
				} else {
					session.setAttribute("errorLogin",
							"Email hoặc mật khẩu không đúng. Xin đăng nhập lại !");
					RequestDispatcher rd = request
							.getRequestDispatcher("dang-nhap.jsp");
					rd.forward(request, response);
				}
			}if ("offAccount".equals(action)) {
				session.removeAttribute("account");
				session.removeAttribute("router");
				session.removeAttribute("good");
				session.removeAttribute("price");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			// out.println("<!DOCTYPE html>");
			// out.println("<html>");
			// out.println("<head>");
			// out.println("<title>Servlet Controller</title>");
			// out.println("</head>");
			// out.println("<body>");
			// out.println("<h1>Servlet Controller at " +
			// request.getContextPath() + "</h1>");
			// out.println("</body>");
			// out.println("</html>");
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
