package com.company.recurrentRelations;

import java.io.BufferedReader;
import java.io.InputStreamReader;



/** task 04.2 - a little bit longer code but maybe easier to understand
 *
 * We have array of 'U' and 'L' if we have 3 successive 'U' the array is not valid (ex. UUULL, UUU, LLLUUUL, ULULUUULL)
 * For a given 'n' we have to say how in how many ways can we arrange 'U's and 'V's so that the array is invalid
 */
public class CriticalMass2 {

    static long comb[];
    static final int L=0;
    static final int U=1;

    static void fillComb(int N) {
        int i;

        comb = new long[N+1];

        long cv[][] = new long[2][2];     // valid
        long cn[][] = new long[2][2];     // not valid

        long prevv[][] = new long[2][2];  // valid
        long prevn[][] = new long[2][2];  // not valid

        prevn[L][L] = 0;
        prevn[L][U] = 0;
        prevn[U][L] = 0;
        prevn[U][U] = 0;

        prevv[L][L] = 1;
        prevv[L][U] = 1;
        prevv[U][L] = 1;
        prevv[U][U] = 1;

        comb[1] = 0;
        comb[2] = 0;
        for (i=3;i<=N;i++) {
            // valid
            cv[L][L] = prevv[L][L]+prevv[U][L];
            cv[U][L] = prevv[L][U]+prevv[U][U];
            cv[L][U] = prevv[L][L]+prevv[U][L];
            cv[U][U] = prevv[L][U];

            // not valid
            cn[L][L] = prevn[L][L]+prevn[U][L];
            cn[U][L] = prevn[L][U]+prevn[U][U];
            cn[L][U] = prevn[L][L]+prevn[U][L];
            cn[U][U] = prevn[L][U]+prevn[U][U]+prevv[U][U];

            comb[i] = cn[L][L]+cn[U][L]+cn[L][U]+cn[U][U];

            prevv[L][L] = cv[L][L];
            prevv[U][L] = cv[U][L];
            prevv[L][U] = cv[L][U];
            prevv[U][U] = cv[U][U];

            prevn[L][L] = cn[L][L];
            prevn[U][L] = cn[U][L];
            prevn[L][U] = cn[L][U];
            prevn[U][U] = cn[U][U];
        }
    }

    public static void main(String[] args) throws Exception {
        int N;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        fillComb(64);

        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0)
                break;
            else
                System.out.println(comb[N]);
        }

        br.close();

    }

}

