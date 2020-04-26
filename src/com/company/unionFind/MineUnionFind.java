package com.company.unionFind;

public class MineUnionFind {
    //which is the parent of the node (in which set is the node, if top element is considered the set's name)
    int[] parent;
    //how deep is the tree under this node
    int[] rank;

    public MineUnionFind(int N) {
        parent = new int[N];
        rank = new int[N];

        for(int i=0; i<N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        //x and y are already in the same set
        if(rootX == rootY) {
            return;
        }

        //x and y are not in the same set so merge them!
        if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if(rank[rootY] > rank[rootX]) {
            parent[rootX] = rootY;
        } else {
            parent[rootX] = rootY;
            rank[rootY]++;
        }
    }


    public static void main(String[] args) {
        int i;

        MineUnionFind muf = new MineUnionFind(10);

        muf.union(0, 5);
        muf.union(1, 2);
        muf.union(2, 3);
        muf.union(1, 4);
        muf.union(3, 5);
        muf.union(6, 7);
        muf.union(6, 8);
        muf.union(7, 9);

        for (i=0;i<10;i++)
            System.out.println(i+"\t"+muf.find(i));

    }
}
