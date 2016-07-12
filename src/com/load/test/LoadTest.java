package com.load.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.load.Load;

public class LoadTest {

	@Test
	public void testGetItems() {
		Load load = new Load.Builder()
								.withStandardItem(5, "Standard")
								.withBulkyItem(15, "Bulky")
								.withLightItem(2, "light")
								.build();
		
		assertEquals(3, load.getItems().size());
	}

	@Test
	public void testGetShippingCost() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetID() {
		fail("Not yet implemented");
	}

	@Test
	public void testLightest() {
		fail("Not yet implemented");
	}

	@Test
	public void testHaviest() {
		fail("Not yet implemented");
	}

	@Test
	public void testDrop() {
		fail("Not yet implemented");
	}

}
