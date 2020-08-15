package com.challenge;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class ExploderTest {
	
	Exploder exploder;
	
	@Before
	public void beforeStart() {
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
		exploder.printGraph();
		assertEquals(2, node.getCountExplotions());
	}

}
