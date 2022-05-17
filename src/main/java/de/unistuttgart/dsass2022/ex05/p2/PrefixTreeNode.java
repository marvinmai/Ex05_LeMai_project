package de.unistuttgart.dsass2022.ex05.p2;

import java.util.HashMap;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrefixTreeNode implements IPrefixTreeNode{

	private String prefix;

	private HashMap<String, IPrefixTreeNode> children;
	
	public PrefixTreeNode() {
		children = new HashMap<>();
	}

	public PrefixTreeNode(String string, IPrefixTreeNode newNode) {
		children = new HashMap<>();
		setNext(string, newNode);
	}
	

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
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
		children.put(string, node);
	}


	@Override
	public IPrefixTreeNode getNode(String string) {
		return children.get(string);
	}


	@Override
	public void removeChildren() {
		this.children.clear();
	}
}
