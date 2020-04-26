package com.company.unionFind;

public class UnionFindNaive {
    int[] parent;

    public UnionFindNaive(int N) {
        parent = new int[N];
        for(int i=0; i<N; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if(x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        parent[xRoot] = yRoot;
    }

    public static void main(String[] args) {
        int N = 10;
        UnionFindNaive ufn = new UnionFindNaive(N);

        ufn.union(0, 5);
        ufn.union(1, 2);
        ufn.union(2, 3);
        ufn.union(1, 4);
        ufn.union(3, 5);
        ufn.union(6, 7);
        ufn.union(6, 8);
        ufn.union(7, 9);

        for(int i=0; i<N; i++) {
            System.out.println(i+": "+ufn.find(i));
        }
    }
}
