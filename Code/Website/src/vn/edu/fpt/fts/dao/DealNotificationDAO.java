/**
 * 
 */
package vn.edu.fpt.fts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import vn.edu.fpt.fts.common.DBAccess;
import vn.edu.fpt.fts.pojo.DealNotification;

/**
 * @author Huy
 *
 */
public class DealNotificationDAO {
	private final static String TAG = "DealNotificationDAO";

	public List<DealNotification> getDealNotificationByDealID(int dealID) {

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<DealNotification> listDealNoti = new ArrayList<DealNotification>();

		try {
			con = DBAccess.makeConnection();
			String sql = "SELECT * FROM DealNotification WHERE DealID=?";
			stm = con.prepareStatement(sql);

			stm.setInt(1, dealID);

			rs = stm.executeQuery();
			DealNotification dealNoti;
			while (rs.next()) {
				dealNoti = new DealNotification();

				dealNoti.setNotificationID(rs.getInt("NotificationID"));
				dealNoti.setMessage(rs.getString("Message"));
				dealNoti.setActive(rs.getInt("Active"));
				dealNoti.setCreateTime(rs.getString("CreateTime"));
				dealNoti.setDealID(rs.getInt("DealID"));

				listDealNoti.add(dealNoti);
			}
			return listDealNoti;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Can't load data from DealNotification table");
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Logger.getLogger(TAG).log(Level.SEVERE, null, e);
			}
		}
		return listDealNoti;
	}

	public int insertDealNotification(DealNotification bean) {
		Connection con = null;
		PreparedStatement stmt = null;
		int ret = 0;

		try {
			con = DBAccess.makeConnection();

			String sql = "INSERT INTO DealNotification ( " + "Message,"
					+ "Active," + "CreateTime," + "DealID" + ") VALUES ("
					+ "?, " + "?, " + "?, " + "?)";
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i = 1;

			stmt.setString(i++, bean.getMessage()); // Message
			stmt.setInt(i++, bean.getActive()); // Active
			stmt.setString(i++, bean.getCreateTime()); // CreateTime
			stmt.setInt(i++, bean.getDealID()); // DealID

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				ret = (int) rs.getLong(1);
			}
			return ret;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Can't insert to DealNotification table");
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);

		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Logger.getLogger(TAG).log(Level.SEVERE, null, e);
			}
		}
		return ret;
	}

	public int updateDealNotificationStatus(int dealNotiID, int dealNotiStatus) {
		Connection con = null;
		PreparedStatement stmt = null;
		int ret = 0;
		try {
			con = DBAccess.makeConnection();
			String sql = "UPDATE DealNotification SET " + " Active = ? "
					+ " WHERE DealNotificationID = '" + dealNotiID + "' ";
			stmt = con.prepareStatement(sql);
			int i = 1;

			stmt.setInt(i++, dealNotiStatus); // Active

			ret = stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Can't update Active to DealNotification table");
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Logger.getLogger(TAG).log(Level.SEVERE, null, e);
			}
		}
		return ret;
	}

	public List<DealNotification> getAllDealNotification() {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<DealNotification> listDealNoti = new ArrayList<DealNotification>();

		try {
			con = DBAccess.makeConnection();
			String sql = "SELECT * FROM DealNotification";
			stm = con.prepareStatement(sql);

			rs = stm.executeQuery();
			DealNotification dealNoti;
			while (rs.next()) {
				dealNoti = new DealNotification();

				dealNoti.setNotificationID(rs.getInt("NotificationID"));
				dealNoti.setMessage(rs.getString("Message"));
				dealNoti.setActive(rs.getInt("Active"));
				dealNoti.setCreateTime(rs.getString("CreateTime"));
				dealNoti.setDealID(rs.getInt("DealID"));

				listDealNoti.add(dealNoti);
			}
			return listDealNoti;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Can't load data from DealNotification table");
			Logger.getLogger(TAG).log(Level.SEVERE, null, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Logger.getLogger(TAG).log(Level.SEVERE, null, e);
			}
		}
		return listDealNoti;
	}
}
