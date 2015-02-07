package vn.edu.fpt.fts.model;

import java.util.List;



/**
 * @author Huy
 *
 */
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Route implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7515339075887353188L;
	private int RouteID;
	private String startingAddress;
	private String destinationAddress;
	private String startTime;
	private String finishTime;
	private String notes;
	private int weight;
	private String createTime;
	private int active;
	private int driverID;
	private List<RouteMarker> routeMarker;

	public Route() {
		// TODO Auto-generated constructor stub
	}

	public Route(int routeID, String startingAddress,
			String destinationAddress, String startTime, String finishTime,
			String notes, int weight, String createTime, int active,
			int driverID, List<RouteMarker> routeMarker) {
		super();
		RouteID = routeID;
		this.startingAddress = startingAddress;
		this.destinationAddress = destinationAddress;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.notes = notes;
		this.weight = weight;
		this.createTime = createTime;
		this.active = active;
		this.driverID = driverID;
		this.routeMarker = routeMarker;
	}

	public Route(String startingAddress, String destinationAddress,
			String startTime, String finishTime, String notes, int weight,
			String createTime, int active, int driverID,
			List<RouteMarker> routeMarker) {
		super();
		this.startingAddress = startingAddress;
		this.destinationAddress = destinationAddress;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.notes = notes;
		this.weight = weight;
		this.createTime = createTime;
		this.active = active;
		this.driverID = driverID;
		this.routeMarker = routeMarker;
	}

	public int getRouteID() {
		return RouteID;
	}

	public void setRouteID(int routeID) {
		RouteID = routeID;
	}

	public String getStartingAddress() {
		return startingAddress;
	}

	public void setStartingAddress(String startingAddress) {
		this.startingAddress = startingAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getDriverID() {
		return driverID;
	}

	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}

	public List<RouteMarker> getRouteMarker() {
		return routeMarker;
	}

	public void setRouteMarker(List<RouteMarker> routeMarker) {
		this.routeMarker = routeMarker;
	}
	
}