package de.unistuttgart.dsass2022.ex05.p2;

import java.util.HashMap;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrefixTreeNode implements IPrefixTreeNode{

	private String prefix;
	private int size;

	private Map<String, IPrefixTreeNode> children;

	public PrefixTreeNode(String word) {
		this.children = new HashMap<>();
		this.prefix = word;
		this.size = word.length();
	}

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
		this.size = prefix.length();
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public Set<String> getNext() {
		Set<String> allEdges = new HashSet<>();
		for (Map.Entry<String, IPrefixTreeNode> child: children.entrySet()) {
			allEdges.add(child.getKey());
		}
		return allEdges;
	}

	@Override
	public void setNext(String string ,IPrefixTreeNode node) {
		if (children == null) children = new HashMap<>();
		children.put(string, node);
	}


	@Override
	public IPrefixTreeNode getNode(String string) {
		return children.get(string);
	}

	@Override
	public void removeChildren() {
		this.children = null;
	}

	@Override
	public boolean hasChildren() {
		return children.size() > 0;
	}

	public Map<String, IPrefixTreeNode> getChildren() {
		return children;
	}

	public void setChildren(Map<String, IPrefixTreeNode> children) {
		this.children = children;
	}
}
