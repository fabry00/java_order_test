package com.domains;

public class LightItem extends StandardItem {

	public LightItem(int weight, String name) {
		super(weight, name);
	}
	
	@Override
	public int getShippingCost(){
		return super.getShippingCost() /2;
	}

}
