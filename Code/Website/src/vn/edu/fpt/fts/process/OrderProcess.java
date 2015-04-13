/**
 * 
 */
package vn.edu.fpt.fts.process;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import vn.edu.fpt.fts.common.Common;
import vn.edu.fpt.fts.dao.OrderDAO;
import vn.edu.fpt.fts.pojo.Order;

/**
 * @author Huy
 *
 */
public class OrderProcess {
	private final static String TAG = "OrderProcess";

	OrderDAO orderDao = new OrderDAO();
	NotificationProcess notificationProcess = new NotificationProcess();

	@SuppressWarnings("deprecation")
	public void checkDelivery() {
		List<Order> l_order = orderDao.getAllOrder();
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date today = new Date();

			Logger logger = Logger.getLogger("CHECK ORDER STATUS AFTER "
					+ Common.periodDay + " DAY(s)" + " --- TIME: "
					+ new Date().toString());

			System.out.println("CHECK ORDER STATUS AFTER " + Common.periodDay
					+ " DAY(s)" + " --- TIME: " + new Date().toString());
			for (int i = 0; i < l_order.size(); i++) {
				Date deliveryDate = new Date();

				int orderStatusID = l_order.get(i).getOrderStatusID();

				if (orderStatusID != Common.order_accept
						&& orderStatusID != Common.order_lost) {
					if (l_order.get(i).getDeal() != null) {
						if (l_order.get(i).getDeal().getGoods() != null) {
							deliveryDate = dateFormat.parse(l_order.get(i)
									.getDeal().getGoods().getDeliveryTime());
							if ((today.getDate() - Common.periodDay) == deliveryDate
									.getDate()) {
								int orderID = l_order.get(i).getOrderID();
								int ret = orderDao.updateOrderStatusID(orderID,
										Common.order_accept);
								logger.warning("AUTO ACCEPT ORDER: " + orderID
										+ " --- Time: " + new Date().toString());
								if (ret == 1) {
									notificationProcess.insertAcceptOrderNotification(l_order.get(i));
								}
							}
						}
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		}
	}
}
