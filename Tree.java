/*
File: Tree.java
Author: Ram Longman
Date: 8/3/2023
Description: A class for a binary tree
*/
public class Tree<T extends Comparable<T>> {
	T data; // The data stored in the node
	Tree<T> left; // The left child node
	Tree<T> right; // The right child node

	/**
	 * Constructs a tree node with the specified data value.
	 *
	 * @param d The data to be stored in the node.
	 */
	public Tree(T d) {
		data = d;
		left = null;
		right = null;
	}
}
