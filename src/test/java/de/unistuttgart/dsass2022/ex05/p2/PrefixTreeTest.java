package de.unistuttgart.dsass2022.ex05.p2;

import org.junit.Test;

public class PrefixTreeTest {

    IPrefixTree tree;

    @Test
    public void testInsertion() {
        tree = new PrefixTree("Algorithmik");

        tree.insert("Algorithmen");

        tree.insert("Allerwertester");

        tree.insert("Algorithmikanalyse");

        tree.insert("Algorithmikentwicklung");

        tree.insert("Algorithmikanalyseentwicklung");

        tree.insert("alles");

        tree.insert("allesamt");

        tree.insert("Ende");

        System.out.println(tree.toString());
    }
}
