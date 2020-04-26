package com.company.geometricSearch;

import java.util.Random;

// ListPlot[{ {53,14}, {27,28}, {30,11}, {29,16}, {40,26}, {38,23}, {31,85}, {7,39}, {32,29}, {15,61}, {67,51}, {70,3}, {99,90}, {82,64}, {73,75}}]

public class KdTree<T extends Comparable<? super T>> {

    int dimension;

    @SuppressWarnings("unchecked")
    private class KdNode {
        T[] data;
        KdNode left;
        KdNode right;

        KdNode(T item[]) {
            int i;
            data = (T[]) new Comparable[dimension];

            for (i=0;i<dimension;i++)
                data[i] = item[i];

            left = right = null;
        }
    }
    private KdNode root;

    public KdTree(int dimension) {
        root = null;
        this.dimension = dimension;
    }

    public void insert(T[] x) {
        root = insert(x, root, 0);
    }

    private KdNode insert(T[] x, KdNode t, int level) {
        if (t == null) {
            t = new KdNode(x);
        } else {
            int m = level%dimension;

            if (x[m].compareTo(t.data[m]) < 0) {
                t.left = insert(x, t.left, level + 1);
            } else {
                t.right = insert(x, t.right, level + 1);
            }
        }
        return t;
    }

    /**
     * Print items satisfying
     * low[ 0 ] <= x[ 0 ] <= high[ 0 ] and
     * low[ 1 ] <= x[ 1 ] <= high[ 1 ].
     */
    public void printRange(T[] low, T[] high) {
        printRange(low, high, root, 0);
    }

    private void printRange(T[] low, T[] high, KdNode t, int level) {
        if (t != null) {
            boolean good = true;
            int i;

            for (i=0;i<dimension;i++) {
                if ((low[i].compareTo(t.data[i]) > 0)||(high[i].compareTo(t.data[i]) < 0)) {
                    good = false;
                    break;
                }
            }
            if (good)
                System.out.println("(" + t.data[0] + "," + t.data[1] + ")");

            int m = level%dimension;

            if (low[m].compareTo(t.data[m]) <= 0)
                printRange(low, high, t.left, level + 1);

            if (high[m].compareTo(t.data[m]) >= 0)
                printRange(low, high, t.right, level + 1);
        }
    }

    public static void main(String[] args) {
        int i,j,k;

        KdTree<Integer> t = new KdTree<Integer>(2);

        Random r = new Random(System.currentTimeMillis());

        for (i=0;i<1000000;i++) {
            Integer[] it = new Integer[2];
            it[0] = r.nextInt(1000000);
            it[1] = r.nextInt(1000000);
            t.insert(it);
        }

        System.out.println("FINISHED");

        Integer[] low = {250000, 400000};
        Integer[] high = {750000, 600000};

        t.printRange(low, high);
        System.out.println("FINISHED2");

    }
}

