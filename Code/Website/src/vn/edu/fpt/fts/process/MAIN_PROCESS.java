/**
 * 
 */
package vn.edu.fpt.fts.process;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Huy
 *
 */
public class MAIN_PROCESS {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// MatchingProcess matchingProcess = new MatchingProcess();

		// Vung Tau
		// Double latGoods = 10.4025053;
		// Double longGoods = 107.1255859;

		// Quang Trung, TP.HCM
		// Double latGoods = 10.853132;
		// Double longGoods = 106.626289;

		// Xuan Loc, Dong Nai
		// Double latEnd = 10.9234099;
		// Double longEnd = 107.4084806;

		// GoodsDAO goodsDao = new GoodsDAO();
		// LatLng goodsStartLocation = new LatLng();
		//
		// LatLng goodsFinishLocation = new LatLng();
		// Goods goods = goodsDao.getGoodsByID(79);
		//
		// if (goods != null) {
		// goodsFinishLocation.setLatitude(goods.getDeliveryMarkerLatidute());
		// goodsFinishLocation.setLongitude(goods
		// .getDeliveryMarkerLongtitude());
		// goodsStartLocation.setLatitude(goods.getPickupMarkerLatidute());
		// goodsStartLocation.setLongitude(goods.getPickupMarkerLongtitude());
		// RouteDAO routeDao = new RouteDAO();
		// List<Route> l_route = routeDao.getAllRoute();
		// if (l_route.size() != 0) {
		// for (int j = 0; j < l_route.size(); j++) {
		// System.out.println(mapProcess.checkDistance(l_route.get(j)
		// .getRouteID(), goodsStartLocation,
		// goodsFinishLocation, 2));
		// }
		//
		// }
		// }

		// List<Route> routeList = matchingProcess.getSuggestionRoute(11);
		// System.out.println("------------------- Number of Route: "
		// + routeList.size());
		//
		// List<Goods> goodsList = matchingProcess.getSuggestionGoods(8);
		// System.out.println("------------------- Number of Goods: "
		// + goodsList.size());

		// goodsStartLocation.setLatitude(21.0248455);
		// goodsStartLocation.setLongitude(105.8287365);
		//
		// goodsFinishLocation.setLatitude(10.824391);
		// goodsFinishLocation.setLongitude(106.628505);

		// System.out.println(mapProcess.checkDistance(9, goodsStartLocation,
		// goodsFinishLocation, 2));

		// GoodsDAO goodsDao = new GoodsDAO();
		// Goods goods = goodsDao.getGoodsByID(83);
		// RouteDAO routeDao = new RouteDAO();
		// routeDao.getActiveRouteByID(17);
		// List<Goods> listGoods = new ArrayList<Goods>();
		// listGoods = goodsDao.getAllGoods();
		//
		// MatchingProcess mp = new MatchingProcess();
		// System.out.println(mp.getSuggestionGoods(35).size());

		// RouteGoodsCategoryDAO routeGoodsCategoryDao = new
		// RouteGoodsCategoryDAO();
		//
		// List<RouteGoodsCategory>
		//
		// listRouteGoodsCategory = routeGoodsCategoryDao
		// .getListRouteGoodsCategoryByRouteID(27);
		// System.out.println(listRouteGoodsCategory.size());
		//
		// GoodsDAO goodsDao = new GoodsDAO();
		// RouteDAO routeDao = new RouteDAO();
		// List<Goods> l_goods = new ArrayList<Goods>();
		// Route route = routeDao.getActiveRouteByID(19);
		// List<RouteGoodsCategory> l_routeGoodsCategory = route
		// .getRouteGoodsCategory();
		// for (int i = 0; i < l_routeGoodsCategory.size(); i++) {
		// List<Goods> l_goodsTemp = goodsDao
		// .getListActiveGoodsByCategoryID(l_routeGoodsCategory.get(i)
		// .getGoodsCategoryID());
		// l_goods.addAll(l_goodsTemp);
		// }
		// System.out.println(l_goods.size());

		// List<Route> listRoute = routeDao.getListActiveRoute();
		//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// Date pickupDate = sdf.parse(goods.getPickupTime().toString());
			// Date deliveryDate =
			// sdf.parse(goods.getDeliveryTime().toString());
			// for (int i = 0; i < listRoute.size(); i++) {
			// Date routeStartDate = sdf
			// .parse(listRoute.get(i).getStartTime());
			// Date routeFinishDate = sdf.parse(listRoute.get(i)
			// .getFinishTime());
			// if (pickupDate.compareTo(routeStartDate) >= 0
			// && deliveryDate.compareTo(routeFinishDate) <= 0) {
			// System.out.println(routeStartDate.getTime() + " <= "
			// + pickupDate.getTime() + " <= "
			// + deliveryDate.getTime() + " <= "
			// + routeFinishDate.getTime());
			// }
			// }

			// System.out.println(pickupDate.compareTo(deliveryDate));

			Date date1 = sdf.parse("2009-12-31");
			Date date2 = sdf.parse("2010-01-31");

			System.out.println(sdf.format(date1));
			System.out.println(sdf.format(date2));
			
			System.out.println(date1.compareTo(date2));

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
