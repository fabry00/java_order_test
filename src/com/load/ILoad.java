package com.load;

import java.util.Set;

import com.domains.IOrderItem;

public interface ILoad {
	public Set<IOrderItem> getItems();
	
	public int getShippingCost();
	
	public String getID();
		
	public IOrderItem lightest();
	
	public IOrderItem haviest();
	
	public Load drop(IOrderItem item) ;
}
