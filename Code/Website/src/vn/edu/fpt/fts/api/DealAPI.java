/**
 * 
 */
package vn.edu.fpt.fts.api;

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

import vn.edu.fpt.fts.dao.DealDAO;
import vn.edu.fpt.fts.pojo.Deal;

/**
 * @author Huy
 *
 */
@Path("Deal")
public class DealAPI {

	private final static String TAG = "DealAPI";
	DealDAO dealDao = new DealDAO();

	@POST
	@Path("getDealByRouteID")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Deal> getDealByRouteID(MultivaluedMap<String, String> params) {
		List<Deal> l_deals = dealDao.getDealByRouteID(Integer.valueOf(params
				.getFirst("routeID")));
		return l_deals;
	}

	@GET
	@Path("getDealByGoodsID")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Deal> getDealByGoodsID(MultivaluedMap<String, String> params) {
		List<Deal> l_deals = dealDao.getDealByGoodsID(Integer.valueOf(params
				.getFirst("gouteID")));
		return l_deals;
	}

	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Deal> getAllDeal() {
		List<Deal> l_deals = dealDao.getAllDeal();
		return l_deals;
	}

	@POST
	@Path("Create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String createDeal(MultivaluedMap<String, String> params) {
		Deal deal;
		try {
			deal = new Deal();
			deal.setPrice(Double.valueOf(params.getFirst("price")));
			deal.setNotes(params.getFirst("notes"));
			deal.setCreateTime(params.getFirst("createTime"));
			deal.setSender(params.getFirst("sender"));
			deal.setRouteID(Integer.valueOf(params.getFirst("routeID")));
			deal.setGoodsID(Integer.valueOf(params.getFirst("goodsID")));
			deal.setDealStatusID(Integer.valueOf(params
					.getFirst("dealStatusID")));
			deal.setActive(Integer.valueOf(params.getFirst("active")));

			int ret = dealDao.insertDeal(deal);
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
	@Path("Update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateDeal(MultivaluedMap<String, String> params) {
		Deal deal;
		try {
			deal = new Deal();
			deal.setPrice(Double.valueOf(params.getFirst("price")));
			deal.setNotes(params.getFirst("notes"));
			deal.setCreateTime(params.getFirst("createTime"));
			deal.setSender(params.getFirst("sender"));
			deal.setRouteID(Integer.valueOf(params.getFirst("routeID")));
			deal.setGoodsID(Integer.valueOf(params.getFirst("goodsID")));
			deal.setDealStatusID(Integer.valueOf(params
					.getFirst("dealStatusID")));
			deal.setActive(Integer.valueOf(params.getFirst("active")));

			int ret = dealDao.updateDeal(deal);

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
}