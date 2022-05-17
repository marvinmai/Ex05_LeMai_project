package de.unistuttgart.dsass2022.ex05.p2;

import java.util.Locale;

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
			insert(root, word);
		}
	}

	/**
	 * Cases when inserting:
	 * 		1: no overlap - create new child of current node
	 * 		2: overlap - shorten current node in case overlap<node.size, insert shortened part and new node part into new children
	 * @param node
	 * @param wordPart
	 */
	public void insert(IPrefixTreeNode node, String wordPart) {
		if(wordPart.equalsIgnoreCase(node.getPrefix())) return;

		int overlapSize = getOverlapSize(node, wordPart);

		if (overlapSize < node.getPrefix().length() && overlapSize != 0) {
			// reduce current node prefix and insert reduced part of this node
			int currentNodeReductionSize = node.getPrefix().length() - overlapSize;
			String reducedWordPart = node.getPrefix().substring(currentNodeReductionSize - 1, node.getPrefix().length()-1);
			String newWordPart = wordPart.substring(currentNodeReductionSize - 1, wordPart.length() - 1);
			node.setPrefix(node.getPrefix().substring(0, currentNodeReductionSize - 1));
			insert(node, reducedWordPart);
			insert(node, newWordPart);
		} else if (overlapSize == node.getPrefix().length()) {
			// don't change current node and insert overlapping part of new word in to correct child, if exists
			// otherwise create a new child
			String edgeChar = wordPart.substring(node.getPrefix().length(), node.getPrefix().length() + 1);
			String newWordPart = wordPart.substring(node.getPrefix().length() + 1);
			IPrefixTreeNode next = node.getNode(edgeChar);
			if (next != null) {
				insert(next, newWordPart);
			} else {
				node.setNext(edgeChar, new PrefixTreeNode(newWordPart));
			}
		} else if (overlapSize == 0) {
			// check if there's already an edge with the same beginning letter
			String edgeString = wordPart.substring(0, 1);
			IPrefixTreeNode nextNode = node.getNode(edgeString);
			if (nextNode == null) {
				node.setNext(edgeString, new PrefixTreeNode(wordPart.substring(1, wordPart.length() - 1)));
			} else {
				insert(nextNode, wordPart.substring(1, wordPart.length() - 1));
			}
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

	@Override
	public int size() {
		return 0;
	}
}
