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
	 * 		1: no overlap - create new child of currentNode
	 * 		2: overlap - shorten currentNode in case overlap<currentNode.size, insert shortened part and new currentNode part into new children
	 * @param currentNode
	 * @param insertWord
	 */
	public IPrefixTreeNode insert(IPrefixTreeNode currentNode, String insertWord) {
		if(insertWord.equalsIgnoreCase(currentNode.getPrefix())) return currentNode;

		int overlapSize = getOverlapSize(currentNode, insertWord);

		if (currentNode.getPrefix().isEmpty()) {
			// case: current node has no prefix
			// if theres a child with a matching edge, insert there
			// otherwise create a new children

			IPrefixTreeNode child = currentNode.getNode(insertWord.substring(0, 1));
			if (child == null) {
				currentNode.setNext(insertWord.substring(0, 1), new PrefixTreeNode(insertWord.substring(1)));
			} else {
				insert(child, insertWord.substring(1));
			}
		} else if (overlapSize < currentNode.getPrefix().length() || overlapSize == 0) {
			// case: there's a partial or no overlap with the current prefix: algorithm, alles or alles, ende
			insertWithPartialOverlap(currentNode, insertWord, overlapSize);
		} else if (overlapSize == currentNode.getPrefix().length()) {
			// case: there is a match/complete overlap with the current currentNode: algorithm, algorithmanalysis
			insertWithCompleteOverlap(currentNode, insertWord);
		}
		return currentNode;
	}

	private void insertWithCompleteOverlap(IPrefixTreeNode currentNode, String insertWord) {
		// don't change current currentNode and insert overlapping part of new word into correct child, if exists
		// otherwise create a new child
		String insertNodeEdge = insertWord.substring(currentNode.getPrefix().length(), currentNode.getPrefix().length() + 1);
		String insertWordPart = insertWord.substring(currentNode.getPrefix().length() + 1);
		IPrefixTreeNode next = currentNode.getNode(insertNodeEdge);
		if (next != null) {
			insert(next, insertWordPart);
		} else {
			currentNode.setNext(insertNodeEdge, new PrefixTreeNode(insertWordPart));
		}
	}

	private void insertWithPartialOverlap(IPrefixTreeNode currentNode, String insertWord, int overlapSize) {
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
