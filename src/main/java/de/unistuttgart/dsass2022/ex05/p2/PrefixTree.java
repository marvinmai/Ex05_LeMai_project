package de.unistuttgart.dsass2022.ex05.p2;

import java.util.Locale;
import java.util.Map;

public class PrefixTree implements IPrefixTree {

	private IPrefixTreeNode root;

	public PrefixTree(String word) {
		insert(word);
	}

	@Override
	public void insert(String word) {
		word = word.toLowerCase();
		if (root == null) root = new PrefixTreeNode(word);
		else {
			root = insert(root, word);
		}
	}

	/**
	 * Cases when inserting:
	 * 		1: no overlap - create new child of current currentNode
	 * 		2: overlap - shorten current currentNode in case overlap<currentNode.size, insert shortened part and new currentNode part into new children
	 * @param currentNode
	 * @param insertWord
	 */
	public IPrefixTreeNode insert(IPrefixTreeNode currentNode, String insertWord) {
		if(insertWord.equalsIgnoreCase(currentNode.getPrefix())) return currentNode;

		int overlapSize = getOverlapSize(currentNode, insertWord);

		if (overlapSize < currentNode.getPrefix().length() && overlapSize != 0) {
			// case: there's a partial overlap with the current prefix: algorithm, alles

			// store all children in temp if exists
			Map<String, IPrefixTreeNode> tempChildren = null;
			if (currentNode.hasChildren()) {
				tempChildren = currentNode.getChildren();
				currentNode.removeChildren();
			}
			// reduce current node prefix to first overlapping part of prefix
			String currentPrefix = currentNode.getPrefix();
			currentNode.setPrefix(currentNode.getPrefix().substring(0, overlapSize));
			// create a new node with part of the current node prefix that was reduced in previous step and store edge
			String newNodeEdge = String.valueOf(currentPrefix.charAt(overlapSize));
			PrefixTreeNode newNode = new PrefixTreeNode(currentPrefix.substring(overlapSize + 1));
			// create node with new part of the word that is inserted and store edge
			String insertNodeEdge = String.valueOf(insertWord.charAt(overlapSize));
			PrefixTreeNode insertNode = new PrefixTreeNode(insertWord.substring(overlapSize + 1));
			// add stored original children of current node as children of new node
			if (tempChildren != null) newNode.setChildren(tempChildren);
			// add insertNode and newNode as children of reduced current node
			currentNode.setNext(newNodeEdge, newNode);
			currentNode.setNext(insertNodeEdge, insertNode);

			return currentNode;

		} else if (overlapSize == currentNode.getPrefix().length()) {
			// case: there is a match/complete overlap with the current currentNode: algorithm, algorithmanalysis
			// don't change current currentNode and insert overlapping part of new word in to correct child, if exists
			// otherwise create a new child
			String edgeChar = insertWord.substring(currentNode.getPrefix().length(), currentNode.getPrefix().length() + 1);
			String newWordPart = insertWord.substring(currentNode.getPrefix().length() + 1);
			IPrefixTreeNode next = currentNode.getNode(edgeChar);
			if (next != null) {
				insert(next, newWordPart);
			} else {
				currentNode.setNext(edgeChar, new PrefixTreeNode(newWordPart));
			}
		} else if (overlapSize == 0) {
			// check if there's already an edge with the same beginning letter
			String edgeString = insertWord.substring(0, 1);
			IPrefixTreeNode nextNode = currentNode.getNode(edgeString);
			if (nextNode == null) {
				currentNode.setNext(edgeString, new PrefixTreeNode(insertWord.substring(1, insertWord.length() - 1)));
			} else {
				insert(nextNode, insertWord.substring(1, insertWord.length() - 1));
			}
		}
		return currentNode;
	}

	private int getOverlapSize(IPrefixTreeNode node, String newString) {
		char[] nodeChars = node.getPrefix().toCharArray();
		char[] newStringChars = newString.toCharArray();
		int overlapCount = 0;
		for (int i = 0; i < nodeChars.length; i++) {
			if (nodeChars[i] == newStringChars[i]) {
				overlapCount++;
			} else
				break;
		}
		return overlapCount;
	}

	@Override
	public int size() {
		return 0;
	}
}
