package com.domains;


public class StandardItem implements IOrderItem {

	private static final int m_shippingCost = 10;
	private int m_weight;
	private String m_name;
	
	public StandardItem(int weight, String name) {
		m_weight = weight;
		m_name = name;
	}
	
	public int getWeight() {
		return m_weight;
	}
	
	public String getName() {
		return m_name;
	}
	
	public int getShippingCost() {
		return m_shippingCost;
	}
	
	@Override
	public int hashCode() {		
		return m_name.hashCode();
	}
	
	@Override
	public String toString() {
		return m_name+": "+m_weight;
	}
}
