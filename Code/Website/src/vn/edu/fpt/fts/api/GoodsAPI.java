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

import vn.edu.fpt.fts.common.Common;
import vn.edu.fpt.fts.dao.GoodsDAO;
import vn.edu.fpt.fts.dao.RouteDAO;
import vn.edu.fpt.fts.pojo.Goods;
import vn.edu.fpt.fts.process.MatchingProcess;

@Path("Goods")
public class GoodsAPI {
	private final static String TAG = "GoodsAPI";
	GoodsDAO goodsDao = new GoodsDAO();
	RouteDAO routeDAO = new RouteDAO();
	MatchingProcess matchingProcess = new MatchingProcess();

	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Goods> JSON() {
		List<Goods> l_goods = goodsDao.getAllGoods();
		return l_goods;
	}

	@POST
	@Path("Create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String createGoods(MultivaluedMap<String, String> goodsParams) {
		Goods goods = new Goods();
		int ret = 0;
		try {
			goods.setWeight(Integer.valueOf(goodsParams.getFirst("weight")));
			goods.setPrice(Double.valueOf(goodsParams.getFirst("price")));
			goods.setPickupTime(goodsParams.getFirst("pickupTime"));
			goods.setPickupAddress(goodsParams.getFirst("pickupAddress"));
			goods.setDeliveryTime(goodsParams.getFirst("deliveryTime"));
			goods.setDeliveryAddress(goodsParams.getFirst("deliveryAddress"));
			goods.setPickupMarkerLongtitude(Float.valueOf(goodsParams
					.getFirst("pickupMarkerLongtitude")));
			goods.setPickupMarkerLatidute(Float.valueOf(goodsParams
					.getFirst("pickupMarkerLatidute")));
			goods.setDeliveryMarkerLongtitude(Float.valueOf(goodsParams
					.getFirst("deliveryMarkerLongtitude")));
			goods.setDeliveryMarkerLatidute(Float.valueOf(goodsParams
					.getFirst("deliveryMarkerLatidute")));
			goods.setNotes(goodsParams.getFirst("notes"));
			goods.setCreateTime(Common.getCreateTime());
			goods.setActive(Integer.valueOf(goodsParams.getFirst("active")));
			goods.setOwnerID(Integer.valueOf(goodsParams.getFirst("ownerID")));
			goods.setGoodsCategoryID(Integer.valueOf(goodsParams
					.getFirst("goodsCategoryID")));
			System.out.println(goodsParams.getFirst("deliveryAddress"));

			ret = goodsDao.insertGoods(goods);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		} catch (NullPointerException e) {
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
	public String updateGoods(MultivaluedMap<String, String> goodsParams) {
		Goods goods = new Goods();
		int ret = 0;
		try {
			// Truyền thêm goodsID về
			int goodsID = Integer.valueOf(goodsParams.getFirst("goodsID"));

			if (routeDAO.getListRouteInDealByGoodsID(goodsID).size() > 0) {
				ret = 2;
			} else {
				goods.setGoodsID(goodsID);

				goods.setWeight(Integer.valueOf(goodsParams.getFirst("weight")));
				goods.setPrice(Double.valueOf(goodsParams.getFirst("price")));
				goods.setPickupTime(goodsParams.getFirst("pickupTime"));
				goods.setPickupAddress(goodsParams.getFirst("pickupAddress"));
				goods.setDeliveryTime(goodsParams.getFirst("deliveryTime"));
				goods.setDeliveryAddress(goodsParams
						.getFirst("deliveryAddress"));
				goods.setPickupMarkerLongtitude(Float.valueOf(goodsParams
						.getFirst("pickupMarkerLongtitude")));
				goods.setPickupMarkerLatidute(Float.valueOf(goodsParams
						.getFirst("pickupMarkerLatidute")));
				goods.setDeliveryMarkerLongtitude(Float.valueOf(goodsParams
						.getFirst("deliveryMarkerLongtitude")));
				goods.setDeliveryMarkerLatidute(Float.valueOf(goodsParams
						.getFirst("deliveryMarkerLatidute")));
				goods.setNotes(goodsParams.getFirst("notes"));
				goods.setCreateTime(goodsParams.getFirst("createTime"));
				goods.setActive(Integer.valueOf(goodsParams.getFirst("active")));
				goods.setOwnerID(Integer.valueOf(goodsParams
						.getFirst("ownerID")));
				goods.setGoodsCategoryID(Integer.valueOf(goodsParams
						.getFirst("goodsCategoryID")));
				System.out.println(goodsParams.getFirst("pickupAddress"));
				System.out.println(goodsParams.getFirst("deliveryAddress"));

				ret = goodsDao.updateGoods(goods);
			}

		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		}
		return String.valueOf(ret);
	}

	@POST
	@Path("getListGoodsByOwnerID")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Goods> getListGoodsByOwnerID(
			MultivaluedMap<String, String> params) {
		List<Goods> l_goods = new ArrayList<Goods>();
		try {
			l_goods = goodsDao.getListGoodsByOwnerID(Integer.valueOf(params
					.getFirst("ownerID")));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		}
		return l_goods;
	}

	@POST
	@Path("getGoodsByID")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Goods getGoodsByID(MultivaluedMap<String, String> params) {
		Goods goods = new Goods();
		goods = goodsDao.getGoodsByID(Integer.valueOf(params
				.getFirst("goodsID")));
		return goods;
	}

	@POST
	@Path("getSuggestionGoods")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Goods> getSuggestionGoods(MultivaluedMap<String, String> params) {
		List<Goods> list = new ArrayList<Goods>();
		try {
			list = matchingProcess.getSuggestionGoods(Integer.valueOf(params
					.getFirst("routeID")));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		}
		return list;
	}

	@POST
	@Path("Delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String removeGoods(MultivaluedMap<String, String> params) {
		int ret = 0;
		try {
			int goodsID = Integer.valueOf(params.getFirst("goodsID"));
			if (routeDAO.getListRouteInDealPendingOrAcceptByGoodsID(goodsID)
					.size() > 0) {
				ret = 2;
			} else {
				ret = goodsDao.updateGoodsStatus(goodsID, Common.deactivate);
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		}
		return String.valueOf(ret);
	}
}
