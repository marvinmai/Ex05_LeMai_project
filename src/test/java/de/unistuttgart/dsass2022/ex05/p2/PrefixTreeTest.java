package de.unistuttgart.dsass2022.ex05.p2;

import org.junit.Test;

public class PrefixTreeTest {

    PrefixTree tree;

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
        tree.printTree();
    }

    @Test
    public void testInsertion2() {
        tree = new PrefixTree("Datenbank");
        tree.insert("Datenbanksystem");
        tree.insert("Datenbanksprache");

        tree.printTree();

        tree = new PrefixTree("Database");
        tree.insert("Datenbank");
        tree.insert("Datenbanksprache");
        tree.insert("Datenbankmodell");
        tree.insert("Datenbanksystem");
        tree.insert("Datum");

        tree.printTree();
    }

    @Test
    public void testInsertion3() {
        tree = new PrefixTree("Datum");
        tree.insert("Datenbanksystem");
        tree.insert("Datenbrei");
        tree.insert("Dadrüben");
        tree.insert("Datus");
        tree.insert("Datenbanksystem");
        tree.insert("Datenbanksystem");
        tree.insert("Datenbanksystem");
        tree.insert("xylophon");
        tree.insert("xylophonspieler*in");
        tree.insert("xylophonkonzert");
        tree.insert("xylophonspielende");
        tree.insert("xylophonspieler*innen");
        tree.insert("xanthan");

        tree.printTree();
    }
}
