package com.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/*
EXERCISE:

There are bunch of mines in a mine field, and you are tasked with exploding as many of them as you can.  
The only caveat is you can only explode one manually.  The mine you manually explode will set off a chain 
reaction.  For any mine that explodes, all mines within the blast radius of that mine will be triggered to 
explode in one second.  The mine you manually explode blows up at time 0.

Your Task: 
Write a program which will read in a mines file and output the Maximum number of mines you can explode.  
Also output which mine you need to manually set off to explode this maximum number.  Since there may be 
multiple mines that blow up a maximal number of mines you should output all the mines that do that.
We'll provide you with:

Mines File (space separated) with values:
(x, y, r) - where x is x coordinate, y is y coordinate and r is blast radius

Example:
1 2 53
-32 40 100
10 15 25
-15 -15 200

Formula to determine if a mine explodes another mine:

 class Exploder {
   boolean mine_exploded?(Float x1, Float y1, Float r, Float x2, Float y2) {
     return r <= Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2))
   }
 }

Your code here
*/

class Exploder {
	
	boolean mine_exploded(Float x1, Float y1, Float r, Float x2, Float y2) {
		return r <= Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}
	  
	// variable to keep all existing mines in memory
	static Map<Integer, Node> nodeLookup = new HashMap<Integer, Node>();

	/*
	 * Get the maximum number of mines exploited as well as mine (s) to explode first.
	 */
	public Node findMaxMinesExplode() {
		LinkedList<Integer> exploited = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		int maxMineExploded = Integer.MIN_VALUE;
		int countExploded = 0;
		Node result = new Node();

		for (Node source : nodeLookup.values()) {
			exploited.clear();
			visited.clear();

			exploited.add(source.getId());
			visited.add(source.getId());

			int total = countMines(exploited, visited, countExploded);

			if (total > maxMineExploded) {
				maxMineExploded = total;
				result.setCountExplotions(total);
				result.getAdjacent().clear();
				result.getAdjacent().add(source);

			} else if (total == maxMineExploded) {
				result.getAdjacent().add(source);
			}
			//System.out.println(String.format("Max exploded = %s, Node = %s", total, source.toString()));
		}

		return result;
	}
	   
	/**
	 * Count the number of mines to explode for each mine that has exploded.
	 * @param exploited queue with the mines that have exploded
	 * @param visited contains a record of the mines that have been exploited
	 * @param countExploded counter of total mines that have exploded
	 * @return the total number of mines that exploded
	 */
	public int countMines(LinkedList<Integer> exploited, Set<Integer> visited, int countExploded) {

		while (!exploited.isEmpty()) {
			Node source = nodeLookup.get(exploited.poll());

			LinkedList<Integer> nodesToVisit = new LinkedList<Integer>(nodeLookup.keySet());
			nodesToVisit.removeAll(visited);

			for (Integer d : nodesToVisit) {
				Node destination = nodeLookup.get(d);

				if (source.getId() != destination.getId()) {

					boolean isExploded = mine_exploded((float) source.getX(), (float) source.getY(),
							(float) source.getR(), (float) destination.getX(), (float) destination.getY());

					if (isExploded) {
						source.setCountExplotions(source.getCountExplotions() + 1);
						destination.setExploded(true);
						source.getAdjacent().add(destination);

						nodeLookup.put(source.getId(), source);
						visited.add(destination.getId());
						exploited.add(destination.getId());
						countExploded++;
					}

				}

			}

		}
		return countExploded;
	}
	   
	/**
	 * Load the contents of the "mines.txt" file into a mine map.
	 * Note: the file is in "src/main/resources/mines.txt".
	 */
	public void loadFileData() {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("mines.txt");

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {

			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {
				count++;
				String[] arr = line.split("\\s");
				nodeLookup.put(count,
						new Node(count, Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	/*
	 * print the map in its graph representation
	 */
	public void printGraph() {
		for (int n : nodeLookup.keySet()) {
			System.out.println(String.format("%s %s: %s", n, nodeLookup.get(n).toString(),
					nodeLookup.get(n).getAdjacent().toString()));
		}
	}
}


public class Solution {
	
	public static void main(String[] args) {
		
		System.out.println("-------------------------[Solution]--------------------------------");
		Exploder exploder = new Exploder();
		exploder.loadFileData(); 
		
		Node maxNode = exploder.findMaxMinesExplode();
		System.out.println("Maximun number of explode mines = " + maxNode.getCountExplotions());
		System.out.println("Mine(s) to manually set off to explode = " + maxNode.getAdjacent().toString()); 
		//exploder.printGraph();
	}
	
}


