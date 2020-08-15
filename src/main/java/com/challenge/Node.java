package com.challenge;

import java.util.LinkedList;
import java.util.List;

/*
 * Class to store the information of a mine
 */
public class Node {
	private int id;
	private int x;
	private int y;
	private int r;
	private int countExplotions;
	private boolean exploded;
	private List<Node> adjacent = new LinkedList<Node>();
	
	public Node() {
		super();
	}

	Node(int id, int x, int y, int r) {
		this.id =id;
		this.x = x;
		this.y = y;
		this.r = r;
		this.countExplotions = 0;
		this.exploded = false;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getCountExplotions() {
		return this.countExplotions;
	}

	public void setCountExplotions(int countExplotions) {
		this.countExplotions = countExplotions;
	}

	public boolean isExploded() {
		return exploded;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}

	public List<Node> getAdjacent() {
		if (adjacent == null) adjacent = new LinkedList<Node>();
		return adjacent;
	}

	public void setAdjacent(List<Node> adjacent) {
		this.adjacent = adjacent;
	}

	@Override
	public String toString( ) {
		return String.format("(%s, %s, %s)", x, y, r);
	}
}
