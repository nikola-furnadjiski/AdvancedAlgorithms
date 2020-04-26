package com.company.unionFind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class WatershedsMine {

    public static int encode(int i, int j, int W) {
        return i * W + j;
    }

    public static boolean isValid(int i, int j, int H, int W) {
        return i >= 0 && i < H && j >= 0 && j < W;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(tokenizer.nextToken());
        int W = Integer.parseInt(tokenizer.nextToken());

        int dx[] = {0, -1, 1, 0};
        int dy[] = {-1, 0, 0, 1};

        int[][] M = new int[H][W];

        for (int i = 0; i < H; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                M[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        UnionFind uf = new UnionFind(H * W);

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                int minHeight = Integer.MAX_VALUE;
                int minI = -1;
                int minJ = -1;
                for (int k = 0; k < 4; k++) {
                    int newI = i + dx[k];
                    int newJ = j + dy[k];
                    if (isValid(newI, newJ, H, W) && M[newI][newJ] < minHeight) {
                        minHeight = M[newI][newJ];
                        minI = newI;
                        minJ = newJ;

                    }
                }

                if(minI != -1) {
                    uf.union(encode(i, j, W), encode(minI, minJ, W));
                }

            }
        }

        HashMap<Integer, Character> hm = new HashMap<>();
        Character c = 'a';

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                int index = uf.find(encode(i,j,W));
                if(!hm.containsKey(index) ) {
                    hm.put(index,c);
                    c++;
                }
                System.out.print(hm.get(index));

                if(j != W-1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        br.close();
    }
}
