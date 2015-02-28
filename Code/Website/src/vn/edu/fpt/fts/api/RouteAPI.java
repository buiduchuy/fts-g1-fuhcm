package vn.edu.fpt.fts.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import vn.edu.fpt.fts.dao.RouteDAO;
import vn.edu.fpt.fts.dao.RouteGoodsCategoryDAO;
import vn.edu.fpt.fts.dao.RouteMarkerDAO;
import vn.edu.fpt.fts.pojo.Route;
import vn.edu.fpt.fts.pojo.RouteGoodsCategory;
import vn.edu.fpt.fts.pojo.RouteMarker;
import vn.edu.fpt.fts.process.MatchingProcess;

@Path("Route")
public class RouteAPI {
	private final static String TAG = "RouteAPI";
	RouteDAO routeDao = new RouteDAO();

	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> JSON() {
		List<Route> l_routes = routeDao.getAllRoute();
		return l_routes;
	}

	@POST
	@Path("Create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String createRoute(MultivaluedMap<String, String> params) {

		Route route = new Route();
		int ret = 0;

		try {

			route.setStartingAddress(params.getFirst("startingAddress"));
			route.setDestinationAddress(params.getFirst("destinationAddress"));
			route.setStartTime(params.getFirst("startTime"));
			route.setFinishTime(params.getFirst("finishTime"));
			route.setNotes(params.getFirst("notes"));
			route.setWeight(Integer.valueOf(params.getFirst("weight")));
			route.setCreateTime(params.getFirst("createTime"));
			route.setActive(Integer.valueOf(params.getFirst("active")));
			route.setDriverID(Integer.valueOf(params.getFirst("driverID")));

			ret = routeDao.insertRoute(route);

			// ------------------------------------------------------------------
			RouteMarkerDAO routeMarkerDao = new RouteMarkerDAO();
			RouteMarker routeMarker = new RouteMarker();
			routeMarker.setRouteID(ret);

			String marker1 = params.getFirst("routeMarkerLocation1");
			String marker2 = params.getFirst("routeMarkerLocation2");

			if (!marker1.isEmpty()) {
				routeMarker.setNumbering(1);
				routeMarker.setRouteMarkerLocation(marker1);

				routeMarkerDao.insertRouteMarker(routeMarker);

			}

			if (!marker2.isEmpty()) {
				routeMarker.setNumbering(2);
				routeMarker.setRouteMarkerLocation(marker2);

				routeMarkerDao.insertRouteMarker(routeMarker);
			}
			// ------------------------------------------------------------------

			RouteGoodsCategoryDAO routeGoodsCategoryDao = new RouteGoodsCategoryDAO();
			RouteGoodsCategory routeGoodsCategory = new RouteGoodsCategory();

			routeGoodsCategory.setRouteID(ret);

			// Get params category true/false
			if (Boolean.parseBoolean(params.getFirst("Food"))) {
				String goodsCategoryName = "Hàng thực phẩm";
				routeGoodsCategoryDao.insertRouteGoodsCategory(ret,
						goodsCategoryName);

			}
			if (Boolean.parseBoolean(params.getFirst("Freeze"))) {
				String goodsCategoryName = "Hàng đông lạnh";
				routeGoodsCategoryDao.insertRouteGoodsCategory(ret,
						goodsCategoryName);

			}
			if (Boolean.parseBoolean(params.getFirst("Broken"))) {
				String goodsCategoryName = "Hàng dễ vỡ";
				routeGoodsCategoryDao.insertRouteGoodsCategory(ret,
						goodsCategoryName);
			}
			if (Boolean.parseBoolean(params.getFirst("Flame"))) {
				String goodsCategoryName = "Hàng dễ cháy nổ";
				routeGoodsCategoryDao.insertRouteGoodsCategory(ret,
						goodsCategoryName);
			}

		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		}
		return String.valueOf(ret);
	}

	@POST
	@Path("Update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateRoute(MultivaluedMap<String, String> params) {

		Route route = new Route();
		int ret = 0;
		try {

			// Truyền thêm routeID về
			int routeID = Integer.valueOf(params.getFirst("routeID"));

			route.setRouteID(routeID);
			route.setStartingAddress(params.getFirst("startingAddress"));
			route.setDestinationAddress(params.getFirst("destinationAddress"));
			route.setStartTime(params.getFirst("startTime"));
			route.setFinishTime(params.getFirst("finishTime"));
			route.setNotes(params.getFirst("notes"));
			route.setWeight(Integer.valueOf(params.getFirst("weight")));
			route.setCreateTime(params.getFirst("createTime"));
			route.setActive(Integer.valueOf(params.getFirst("active")));
			route.setDriverID(Integer.valueOf(params.getFirst("driverID")));

			ret = routeDao.updateRoute(route);

			// ------------------------------------------------------------------
			RouteMarkerDAO routeMarkerDao = new RouteMarkerDAO();
			RouteMarker routeMarker = new RouteMarker();
			routeMarker.setRouteID(routeID);
			String marker1 = params.getFirst("routeMarkerLocation1");
			String marker2 = params.getFirst("routeMarkerLocation2");

			if (!marker1.isEmpty()) {
				routeMarker.setNumbering(1);
				routeMarker.setRouteMarkerLocation(marker1);
				routeMarkerDao
						.updateRouteMarkerByNumeringAndRouteID(routeMarker);
			}

			if (!marker2.isEmpty()) {
				routeMarker.setNumbering(2);
				routeMarker.setRouteMarkerLocation(marker2);
				routeMarkerDao
						.updateRouteMarkerByNumeringAndRouteID(routeMarker);
			}
			// ------------------------------------------------------------------

			RouteGoodsCategoryDAO routeGoodsCategoryDao = new RouteGoodsCategoryDAO();

			// Get params category true/false
			String goodsCategoryName = "";
			if (Boolean.parseBoolean(params.getFirst("Food"))) {
				goodsCategoryName = "Hàng thực phẩm";
				routeGoodsCategoryDao.insertRouteGoodsCategory(ret,
						goodsCategoryName);

			} else {
				routeGoodsCategoryDao.deleteRouteGoodsCategory(routeID,
						goodsCategoryName);
			}

			if (Boolean.parseBoolean(params.getFirst("Freeze"))) {
				goodsCategoryName = "Hàng đông lạnh";
				routeGoodsCategoryDao.insertRouteGoodsCategory(ret,
						goodsCategoryName);

			} else {
				routeGoodsCategoryDao.deleteRouteGoodsCategory(routeID,
						goodsCategoryName);
			}

			if (Boolean.parseBoolean(params.getFirst("Broken"))) {
				goodsCategoryName = "Hàng dễ vỡ";
				routeGoodsCategoryDao.insertRouteGoodsCategory(ret,
						goodsCategoryName);
			} else {
				routeGoodsCategoryDao.deleteRouteGoodsCategory(routeID,
						goodsCategoryName);
			}

			if (Boolean.parseBoolean(params.getFirst("Flame"))) {
				goodsCategoryName = "Hàng dễ cháy nổ";
				routeGoodsCategoryDao.insertRouteGoodsCategory(ret,
						goodsCategoryName);
			} else {
				routeGoodsCategoryDao.deleteRouteGoodsCategory(routeID,
						goodsCategoryName);
			}

			if (ret <= 0) {
				return "Fail";
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		}

		return "Success";
	}

	@POST
	@Path("getRouteByID")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Route getRouteByID(MultivaluedMap<String, String> params) {
		Route route = new Route();
		try {
			route = routeDao.getRouteByID(Integer.valueOf(params
					.getFirst("routeID")));
			return route;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		}
		return null;
	}

	@POST
	@Path("getSuggestionRoute")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> getSuggestionRoute(MultivaluedMap<String, String> params) {
		MatchingProcess matchingProcess = new MatchingProcess();
		List<Route> list = new ArrayList<Route>();
		try {
			list = matchingProcess.getSuggestionRoute(Integer.valueOf(params
					.getFirst("goodsID")));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		}
		return list;
	}
}
