
package com.company.solvingAlgorithmicProblems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * Task 04
 */
public class PrefixWeight {

    public static void main(String[] args) throws Exception {
        int i;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String words[] = new String[N];
        int weights[] = new int[N];

        for (i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken();
            weights[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        String queries[] = new String[M];

        for (i = 0; i < M; i++)
            queries[i] = br.readLine();

        TrieW tw = new TrieW();

        for (i=0;i<N;i++)
            tw.insert(words[i], weights[i]);

        //System.out.println(tw.getWeight("test"));

    }

}

class TrieW {

    TrieWNode root;

    TrieW() {
        root = new TrieWNode();
    }

    void insert(String s, int weight) {
        insertR(root, s, weight);
    }

    void insertR(TrieWNode tn, String s, int weight) {

        if (s.length() == 0) {
            return;
        }

        char c = s.charAt(0);
        int ci = (int) c - (int) 'a';

        if (tn.next[ci] == null) {
            tn.next[ci] = new TrieWNode();
        }

        tn.next[ci].sum += weight;
        insertR(tn.next[ci], s.substring(1), weight);
    }

    int getWeight(String s) {
        return getWeightR(root, s);
    }

    int getWeightR(TrieWNode tn, String s) {
        if (s.length() == 0) {
            return tn.sum;
        }

        char c = s.charAt(0);
        int ci = (int) c - (int) 'a';

        if (tn.next[ci] == null) {
            return 0;
        }

        return getWeightR(tn.next[ci], s.substring(1));
    }

}

class TrieWNode {

    int sum;
    int value;
    TrieWNode next[];

    TrieWNode() {
        next = new TrieWNode[26];
        sum = 0;
    }
}