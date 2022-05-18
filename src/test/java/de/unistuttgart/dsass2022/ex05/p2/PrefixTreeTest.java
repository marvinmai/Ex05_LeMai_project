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

        tree.insert("kanalisation");

//        tree.insert("Ende");

        System.out.println(tree.toString());
    }

    @Test
    public void testInsertion2() {
        tree = new PrefixTree("Database");

        tree.insert("Datum");

        tree.insert("Datenbank");

        tree.insert("Datenbankmodell");

        tree.insert("Datenbanksystem");


        // does not work: inserted as Datenbanksystem-prach
        tree.insert("Datenbanksprache");

        System.out.println(tree.toString());
    }
}
