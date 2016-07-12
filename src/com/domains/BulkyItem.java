package com.domains;

public class BulkyItem extends StandardItem {

	public BulkyItem(int weight, String name) {
		super(weight, name);
	}

	@Override
	public int getShippingCost(){
		return super.getShippingCost() * 2;
	}

}
