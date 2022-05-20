package de.unistuttgart.dsass2022.ex05.p4;

public class Sorter<T extends Comparable<T>> {

    public static <T extends Comparable<T>> void heapSort(final ISimpleList<T> heap) {
        for (int i = heap.size() / 2; i >= 0; i--) {
            sink(heap, i, heap.size());
        }

        for (int i = heap.size() - 1; i > 0; i--) {
          heap.swap(0, i);
          sink(heap, 0, i);
        }
        printHeap(heap);
    }

    private static <T extends Comparable<T>> void sink(ISimpleList<T> list, int index, int last) {
        int i = index + 1;
        int j;
        while (2 * i <= last) {
            j = 2 * i;
            if (j < last) {
                if (list.get(j - 1).compareTo(list.get(j)) > 0) {
                    j++;
                }
            }
            if (list.get(i - 1).compareTo(list.get(j - 1)) > 0) {
                list.swap(i - 1, j - 1);
                i = j;
            } else {
                break;
            }
        }
    }

    public static <T extends Comparable<T>> void printHeap(ISimpleList<T> heap) {
        int maxDepth = (int) (Math.log(heap.size()) / Math.log(2));  // log base 2 of n

        StringBuilder hs = new StringBuilder();  // heap string builder
        for(int d = maxDepth; d >= 0; d--) {  // number of layers, we build this backwards
            int layerLength = (int) Math.pow(2, d);  // numbers per layer

            StringBuilder line = new StringBuilder();  // line string builder
            for(int i = layerLength; i < (int) Math.pow(2, d + 1); i++) {
                // before spaces only on not-last layer
                if(d != maxDepth) {
                    line.append(" ".repeat((int) Math.pow(2, maxDepth - d)));
                }
                // extra spaces for long lines
                int loops = maxDepth - d;
                if(loops >= 2) {
                    loops -= 2;
                    while(loops >= 0) {
                        line.append(" ".repeat((int) Math.pow(2, loops)));
                        loops--;
                    }
                }

                // add in the number
                if(i <= heap.size()) {
                    line.append(String.format("%-2s", heap.get(i-1)));  // add leading zeros
                } else {
                    line.append("--");
                }

                line.append(" ".repeat((int) Math.pow(2, maxDepth - d)));  // after spaces
                // extra spaces for long lines
                loops = maxDepth - d;
                if(loops >= 2) {
                    loops -= 2;
                    while(loops >= 0) {
                        line.append(" ".repeat((int) Math.pow(2, loops)));
                        loops--;
                    }
                }
            }
            hs.insert(0, line.toString() + "\n");  // prepend line
        }
        System.out.println(hs.toString());
    }

}
