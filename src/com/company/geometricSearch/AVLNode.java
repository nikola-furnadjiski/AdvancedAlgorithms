package com.company.geometricSearch;

public class AVLNode<E extends Comparable<E>> {

    public E info;
    public AVLNode<E> left;
    public AVLNode<E> right;
    public int height;


    // Constructors
    AVLNode(E theElement) {
        this(theElement, null, null);
    }

    AVLNode(E info, AVLNode<E> left, AVLNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
        height = 0;
    }

    //added code
    public int total;

    public void update() {
        total = 1;
        if (left != null)
            total += left.total;
        if (right != null)
            total += right.total;
    }

}
