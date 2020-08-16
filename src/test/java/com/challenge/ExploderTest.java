package com.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class ExploderTest {
	
	static Exploder exploder;
	
	@BeforeClass
	public static void beforeStart() {
		exploder = new Exploder();
		exploder.loadFileData();
	}
	
	@Test
    public void testLoadFileData() {
		assertNotNull(exploder);
	}
	
	@Test
	public void testExploder() {
		Node node = exploder.findMaxMinesExplode();
		System.out.println("Maximun number of explode mines = " + node.getCountExplotions());
		System.out.println("Mine(s) to manually set off to explode = " + node.getAdjacent().toString());
		System.out.println("Grapth representation: ");
		exploder.printGraph();
		assertEquals(3, node.getCountExplotions());
	}

}
