package com.scc.onlinedogshow.template;

import java.util.List;

public class ResponseObjectList<T> {

	private int itemsCount;
	
	private List<T> items;
	
	public ResponseObjectList(int itemsCount, List<T> items) {
		super();
		this.itemsCount = itemsCount;
		this.items = items;
	}
	
	public int getItemsCount() { return itemsCount; }
	public void setItemsCount(int itemsCount) { this.itemsCount = itemsCount; }

	public List<T> getItems() { return items; }
	public void setItems(List<T> items) { this.items = items; }

	public int size() { return this.items.size(); }
	
}
