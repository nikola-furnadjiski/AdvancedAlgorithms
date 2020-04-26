// problem Watersheds
// https://code.google.com/codejam/contest/dashboard?c=90101#s=p1&a=1

package com.company.unionFind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


/**
 * Task 02
 */
public class Watersheds {

    static int encode(int i, int j, int W) {
        return i * W + j;
    }

    static boolean isValid(int i, int j, int H, int W) {
        if ((i < 0) || (i >= H) || (j < 0) || (j >= W)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;
        int dx[] = new int[]{0, -1, 1, 0};
        int dy[] = new int[]{-1, 0, 0, 1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int m[][] = new int[H][W];

        UnionFind uf = new UnionFind(H * W);

        for (i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (j = 0; j < W; j++) {
                m[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (i = 0; i < H; i++) {
            for (j = 0; j < W; j++) {

                int lowest = m[i][j];
                int besti = -1;
                int bestj = -1;

                for (k = 0; k < 4; k++) {
                    int ni = i + dy[k];
                    int nj = j + dx[k];

                    if ((isValid(ni, nj, H, W) == true) && (m[ni][nj] < lowest)) {
                        lowest = m[ni][nj];
                        besti = ni;
                        bestj = nj;
                    }

                }

                if (besti != -1) {
                    uf.union(encode(i, j, W), encode(besti, bestj, W));
                }

            }
        }

        char taken = 'a';
        HashMap<Integer, Character> hm = new HashMap<>();

        for (i = 0; i < H; i++) {
            for (j = 0; j < W; j++) {
                int root = uf.find(encode(i, j, W));
                if (!hm.containsKey(root)) {
                    hm.put(root, taken);
                    taken++;
                }
                System.out.print(hm.get(root));
                if (j < W-1)
                    System.out.print(" ");
            }
            System.out.println();
        }

        br.close();

    }
}
