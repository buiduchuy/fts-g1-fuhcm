package vn.edu.fpt.fts.classes;

import java.io.Serializable;

public class Goods implements Serializable{
	private int GoodsID;
	private int Weight;
	private double Price;
	private String PickupTime;
	private String PickupAddress;
	private String DeliveryTime;
	private String DeliveryAddress;
	private float PickupMarkerLongtitude;
	private float PickupMarkerLatidute;
	private float DeliveryMarkerLongtitude;
	private float DeliveryMarkerLatidute;
	private String Notes;
	private String CreateTime;
	private int Active;
	private int OwnerID;
	private int GoodsCategoryID;
	private String GoodsCategory;
	
	public Goods() {
		// TODO Auto-generated constructor stub
	}
	
	public Goods(int goodsID, int weight, double price, String pickupTime,
			String pickupAddress, String deliveryTime, String deliveryAddress,
			float pickupMarkerLongtitude, float pickupMarkerLatidute,
			float deliveryMarkerLongtitude, float deliveryMarkerLatidute,
			String notes, String createTime, int active, int ownerID,
			int goodsCategoryID, String GoodsCategory) {
		super();
		GoodsID = goodsID;
		Weight = weight;
		Price = price;
		PickupTime = pickupTime;
		PickupAddress = pickupAddress;
		DeliveryTime = deliveryTime;
		DeliveryAddress = deliveryAddress;
		PickupMarkerLongtitude = pickupMarkerLongtitude;
		PickupMarkerLatidute = pickupMarkerLatidute;
		DeliveryMarkerLongtitude = deliveryMarkerLongtitude;
		DeliveryMarkerLatidute = deliveryMarkerLatidute;
		Notes = notes;
		CreateTime = createTime;
		Active = active;
		OwnerID = ownerID;
		GoodsCategoryID = goodsCategoryID;
		this.GoodsCategory = GoodsCategory;
	}

	public int getGoodsID() {
		return GoodsID;
	}

	public void setGoodsID(int goodsID) {
		GoodsID = goodsID;
	}

	public int getWeight() {
		return Weight;
	}

	public void setWeight(int weight) {
		Weight = weight;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getPickupTime() {
		return PickupTime;
	}

	public void setPickupTime(String pickupTime) {
		PickupTime = pickupTime;
	}

	public String getPickupAddress() {
		return PickupAddress;
	}

	public void setPickupAddress(String pickupAddress) {
		PickupAddress = pickupAddress;
	}

	public String getDeliveryTime() {
		return DeliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		DeliveryTime = deliveryTime;
	}

	public String getDeliveryAddress() {
		return DeliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		DeliveryAddress = deliveryAddress;
	}

	public float getPickupMarkerLongtitude() {
		return PickupMarkerLongtitude;
	}

	public void setPickupMarkerLongtitude(float pickupMarkerLongtitude) {
		PickupMarkerLongtitude = pickupMarkerLongtitude;
	}

	public float getPickupMarkerLatidute() {
		return PickupMarkerLatidute;
	}

	public void setPickupMarkerLatidute(float pickupMarkerLatidute) {
		PickupMarkerLatidute = pickupMarkerLatidute;
	}

	public float getDeliveryMarkerLongtitude() {
		return DeliveryMarkerLongtitude;
	}

	public void setDeliveryMarkerLongtitude(float deliveryMarkerLongtitude) {
		DeliveryMarkerLongtitude = deliveryMarkerLongtitude;
	}

	public float getDeliveryMarkerLatidute() {
		return DeliveryMarkerLatidute;
	}

	public void setDeliveryMarkerLatidute(float deliveryMarkerLatidute) {
		DeliveryMarkerLatidute = deliveryMarkerLatidute;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public int getActive() {
		return Active;
	}

	public void setActive(int active) {
		Active = active;
	}

	public int getOwnerID() {
		return OwnerID;
	}

	public void setOwnerID(int ownerID) {
		OwnerID = ownerID;
	}

	public int getGoodsCategoryID() {
		return GoodsCategoryID;
	}

	public void setGoodsCategoryID(int goodsCategoryID) {
		GoodsCategoryID = goodsCategoryID;
	}

	public String getGoodsCategory() {
		return GoodsCategory;
	}

	public void setGoodsCategory(String goodsCategory) {
		GoodsCategory = goodsCategory;
	}
	
	
}