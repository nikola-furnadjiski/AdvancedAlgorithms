package com.company.solvingAlgorithmicProblems;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Task 03
 *
 * We enter number N
 * Then we enter N words
 * We enter number M
 * Then we enter M prefixes
 *
 * The result is for each prefix how many words contain it
 */
public class PrefixChecker {

    public static void main(String[] args) throws Exception {
        int i;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String words[] = new String[N];

        for (i = 0; i < N; i++)
            words[i] = br.readLine();

        int M = Integer.parseInt(br.readLine());
        String queries[] = new String[M];

        for (i = 0; i < M; i++)
            queries[i] = br.readLine();

        Trie t = new Trie();

        for (i=0;i<N;i++)
            t.insert(words[i]);

        System.out.println(t.containsPrefix("test"));

    }
}

class Trie {

    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insert(String s) {
        insertR(root, s);
    }

    void insertR(TrieNode tn, String s) {
        if (s.length() == 0) {
            return;
        }

        char c = s.charAt(0);
        int ci = (int) c - (int) 'a';

        if (tn.next[ci] == null) {
            tn.next[ci] = new TrieNode();
        }

         insertR(tn.next[ci], s.substring(1));
    }

    boolean containsPrefix(String s) {
        return containsPrefixR(root, s);
    }

    boolean containsPrefixR(TrieNode tn, String s) {
        if (s.length() == 0) {
            return true;
        }

        char c = s.charAt(0);
        int ci = (int) c - (int) 'a';

        if (tn.next[ci] == null) {
            return false;
        }

        return containsPrefixR(tn.next[ci], s.substring(1));
    }

}

class TrieNode {

    int value;
    TrieNode next[];

    TrieNode() {
        next = new TrieNode[26];
    }
}