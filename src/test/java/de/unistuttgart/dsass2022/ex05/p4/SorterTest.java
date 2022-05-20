package de.unistuttgart.dsass2022.ex05.p4;

import static org.junit.Assert.*;

// START SOLUTION
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// END SOLUTION

import org.junit.Test;

public class SorterTest {

    @Test
    public void heapSorterTest() {
        ISimpleList<Integer> list = new SimpleList<>();
        list.append(99);
        list.append(7);
        list.append(354);
        list.append(37);
        list.append(2635);
        list.append(69);
        list.append(8);
        list.append(34);
        list.append(85);

        Sorter.heapSort(list);
    }

}
