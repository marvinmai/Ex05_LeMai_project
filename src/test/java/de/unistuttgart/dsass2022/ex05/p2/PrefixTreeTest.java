package de.unistuttgart.dsass2022.ex05.p2;

import org.junit.Test;

public class PrefixTreeTest {

    IPrefixTree tree;

    @Test
    public void testInsertion() {
        tree = new PrefixTree("Algorithmik");

        tree.insert("Algorithmikanalyse");

        System.out.println(tree.toString());
    }
}
