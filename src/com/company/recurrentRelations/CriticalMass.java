package com.company.recurrentRelations;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**  task 04.1 - shorter version
 *
 *  We have array of 'U's and 'L'd. If we have 3 successive 'U' the array is not valid (ex. UUULL, UUU, LLLUUUL, ULULUUULL)
 *  For a given 'n' we have to say how in how many ways can we arrange 'U's and 'V's so that the array is invalid
 *
 */
public class CriticalMass {

    // L is 0
    // U is 1
    static long comb[];

    static void fillComb(int N) {
        int i;

        comb = new long[N+1];

        long cv[][][] = new long[2][2][N+1];     // valid
        long cn[][][] = new long[2][2][N+1];     // not valid

        // we shouldn't add these lines of code
        // they are given just for better explanation

        //this means that with 2 elements we have only valid scenarios (that's why every line is 1)
        cv[0][0][2] = 1;
        cv[0][1][2] = 1;
        cv[1][0][2] = 1;
        cv[1][1][2] = 1;

        //this means that with 2 elements we can not have any non-valid scenario (that's why every line is 0)
        cn[0][0][2] = 0;
        cn[0][1][2] = 0;
        cn[1][0][2] = 0;
        cn[1][1][2] = 0;

        for (i=3;i<=N;i++) {

            // valid - expressing the recurrent relation (we need all 4 cases for computations in the 'for' below)
            cv[0][0][i] = cv[0][0][i-1]+cv[1][0][i-1];
            cv[1][0][i] = cv[0][1][i-1]+cv[1][1][i-1];
            cv[0][1][i] = cv[0][0][i-1]+cv[1][0][i-1];
            cv[1][1][i] = cv[0][1][i-1];

            // not valid - expressing the recurrent relation (we need all 4 cases for computations in the 'for' below)
            cn[0][0][i] = cn[0][0][i-1]+cn[1][0][i-1];
            cn[1][0][i] = cn[0][1][i-1]+cn[1][1][i-1];
            cn[0][1][i] = cn[0][0][i-1]+cn[1][0][i-1];
            cn[1][1][i] = cn[0][1][i-1]+cn[1][1][i-1]+cv[1][1][i-1];

            // we need only i-1, is it optimal to make array of length N?
        }

        comb[1] = 0;
        comb[2] = 0;
        for (i=3;i<=N;i++) {
            comb[i] = cn[0][0][i]+cn[1][0][i]+cn[0][1][i]+cn[1][1][i];
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
