package com.dto;

import java.util.List;

public class WebGroup {

	private int id;
	private String name;
	private int orderId;
	private List<WebItem> webItems;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<WebItem> getWebItems() {
		return webItems;
	}
	public void setWebItems(List<WebItem> webItems) {
		this.webItems = webItems;
	}
	
	
}
