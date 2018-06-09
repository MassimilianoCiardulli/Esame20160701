package it.polito.tdp.formulaone.model;

import java.util.HashMap;
import java.util.Map;

public class DriverIdMap {
	private Map<Integer, Driver> map ;
	
	public DriverIdMap() {
		map = new HashMap<>();
	}
	public Driver get(int id) {
		return map.get(id);
	}
	
	public Driver get(Driver driver) {
		Driver old = map.get(driver.getDriverId());
		if(old == null) {
			map.put(driver.getDriverId(), driver);
			return driver ;
		} else {
			return old ;
		}
	}
	
	public void put(int id, Driver object) {
		map.put(id, object);
	}
}
