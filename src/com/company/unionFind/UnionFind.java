package com.company.unionFind;

/**
 * Task 00
 */
public class UnionFind {
    int parent[];
    int rank[];

    UnionFind(int N) {
        initialize(N);
    }

    private void initialize(int N) {
        parent = new int[N];
        rank = new int[N];

        int i;
        for (i = 0; i < N; i++) {
            makeSet(i);
        }
    }

    void makeSet(int x) {
        parent[x] = x;
        rank[x] = 0;
    }

    void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }

        // x and y are not already in the same set. Merge them
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }


    public static void main(String[] args) {
        int i;

        UnionFind uf = new UnionFind(10);

        uf.union(0, 5);
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(1, 4);
        uf.union(3, 5);
        uf.union(6, 7);
        uf.union(6, 8);
        uf.union(7, 9);

        for (i=0;i<10;i++)
            System.out.println(i+"\t"+uf.find(i));

    }
}
