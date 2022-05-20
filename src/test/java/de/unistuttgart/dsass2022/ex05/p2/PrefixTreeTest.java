package de.unistuttgart.dsass2022.ex05.p2;

import org.junit.Test;

public class PrefixTreeTest {

    IPrefixTree tree;

    @Test
    public void testInsertion() {
        tree = new PrefixTree("Alles");

        tree.insert("Algorithmen");

        tree.insert("Allerwertester");

        tree.insert("Algorithmikanalyse");

        tree.insert("Algorithmikentwicklung");

        tree.insert("Algorithmikanalyseentwicklung");

        tree.insert("Ende");

        tree.insert("allesamt");

        tree.insert("allesanders");

        System.out.println(tree.toString());
    }

    @Test
    public void testInsertion2() {
        // does not work correctly
        tree = new PrefixTree("Datenbank");
        tree.insert("Datenbanksystem");
        tree.insert("Datenbanksprache");

        System.out.println();

        // works correctly
        tree = new PrefixTree("Datenbanksystem");
        tree.insert("Datenbanksprache");

        System.out.println();
    }

    @Test
    public void testInsertion3() {
        tree = new PrefixTree("Datum");
        tree.insert("Datenbanksystem");
        tree.insert("Datenbrei");
        tree.insert("Dadrüben");
        tree.insert("Datus");

        System.out.println(tree.toString());
    }
}
