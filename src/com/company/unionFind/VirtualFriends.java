// problem 11503 - Virtual Friends
// http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2498

package com.company.unionFind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Task 01
 *
 *
 */
public class VirtualFriends {
    static int parent[];
    static int rank[];
    static int total[];

    static void initialize(int N) {
        parent = new int[N];
        rank = new int[N];
        total = new int[N];

        int i;
        for (i = 0; i < N; i++) {
            makeSet(i);
        }
    }

    static void makeSet(int x) {
        parent[x] = x;
        rank[x] = 0;
        total[x] = 1;
    }

    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }

        // x and y are not already in the same set. Merge them
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
            total[yRoot] += total[xRoot];
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
            total[xRoot] += total[yRoot];
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
            total[xRoot] += total[yRoot];
        }
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }


    public static void main(String[] args) throws Exception {
        int i;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int F = Integer.parseInt(br.readLine());
        initialize(200000);

        HashMap<String, Integer> hm = new HashMap<>();
        int T = 0;

        for (i = 0; i < F; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name1 = st.nextToken();
            String name2 = st.nextToken();
            int index1, index2;

            if (hm.containsKey(name1)) {
                index1 = hm.get(name1);
            } else {
                index1 = T;
                hm.put(name1, index1);
                T++;
            }

            if (hm.containsKey(name2)) {
                index2 = hm.get(name2);
            } else {
                index2 = T;
                hm.put(name2, index2);
                T++;
            }

            union(index1, index2);
            System.out.println(total[find(index1)]);
        }

        br.close();
    }
}

