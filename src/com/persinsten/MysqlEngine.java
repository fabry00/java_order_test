package com.persinsten;

import com.load.ILoad;

public class MysqlEngine implements IPersistentEngine{
	
	public void save(ILoad load) {
		throw new UnsupportedOperationException();
	}
	
	public void delete(ILoad load) {
		throw new UnsupportedOperationException();
	}
	
	public void modify(ILoad original, ILoad replacement){
		throw new UnsupportedOperationException();
	}
}
