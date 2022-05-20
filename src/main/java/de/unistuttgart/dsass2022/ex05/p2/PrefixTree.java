package de.unistuttgart.dsass2022.ex05.p2;

import java.util.Map;

public class PrefixTree implements IPrefixTree {

	private IPrefixTreeNode root;
	private int size = 0;

	public PrefixTree(String word) {
		size++;
		insert(word);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void insert(String word) {
		size++;
		word = word.toLowerCase();
		if (root == null) root = new PrefixTreeNode(word);
		else {
			root = insert(root, word);
		}
	}

	public IPrefixTreeNode insert(IPrefixTreeNode currentNode, String insertWord) {
		if(insertWord.equalsIgnoreCase(currentNode.getPrefix())) return currentNode;

		int overlapSize = getOverlapSize(currentNode, insertWord);

		 if (overlapSize < currentNode.getPrefix().length() || overlapSize == 0) {
			// case: there's a partial or no overlap with the current prefix: algorithm, alles or alles, ende
			insertWithPartialOverlap(currentNode, insertWord, overlapSize);
		} else if (overlapSize == currentNode.getPrefix().length()) {
			// case: there is a match/complete overlap with the current currentNode: algorithm, algorithmanalysis
			insertWithCompleteOverlap(currentNode, insertWord);
		} else if (currentNode.getPrefix().isEmpty()) {
			// case: current node has no prefix
			insertNodeInChildren(currentNode, insertWord.substring(0, 1), insertWord.substring(1));
		}
		return currentNode;
	}

	private void insertWithPartialOverlap(IPrefixTreeNode currentNode, String insertWord, int overlapSize) {
		// reduces current node to overlap size, moves reduced part into a new child of the reduced node,
		// adds original children into children of the new node,
		// adds inserted word into new child of reduced node

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

	private void insertWithCompleteOverlap(IPrefixTreeNode currentNode, String insertWord) {
		// don't change current currentNode and insert overlapping part of new word into correct child, if exists
		// otherwise create a new child
		String insertNodeEdge = insertWord.substring(currentNode.getPrefix().length(), currentNode.getPrefix().length() + 1);
		String insertWordPart = insertWord.substring(currentNode.getPrefix().length() + 1);
		insertNodeInChildren(currentNode, insertNodeEdge, insertWordPart);
	}

	private void insertNodeInChildren(IPrefixTreeNode parentNode, String insertWordEdge, String insertWord) {
		// if there's a child in parent node with a matching edge, insert there
		// otherwise create a new children in parent node
		IPrefixTreeNode next = parentNode.getNode(insertWordEdge);
		if (next == null) {
			parentNode.setNext(insertWordEdge, new PrefixTreeNode(insertWord));
		} else {
			insert(next, insertWord);
		}
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
}
