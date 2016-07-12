package com.persinsten;

import com.load.ILoad;

public interface IPersistentEngine {

	public void save(ILoad load);
	
	public void delete(ILoad load);
	
	public void modify(ILoad original, ILoad replacement);
}
