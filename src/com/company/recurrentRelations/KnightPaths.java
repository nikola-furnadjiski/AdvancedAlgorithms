package com.company.recurrentRelations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Lab 01
 *
 * May be not correct, since I do not have the task text and do not know what is supposed to be calculated here.
 * So do not take this solution as granted for correct!
 */
public class KnightPaths {
    public static void main(String[] args) throws NumberFormatException{
        int N, K;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] tmp = br.readLine().split("\\s++");
            N = Integer.parseInt(tmp[0]);
            K = Integer.parseInt(tmp[1]);
            System.out.println(new KnightPaths().getNumberOfWays(K, N));
            br.close();
        } catch (IOException e) {}
    }

    public final long MOD = 1000000007;
    // computes A * B
    long[][] mul(long A[][], long B[][]) {

        int i,j,k;
        long C[][] = new long[A.length][B[0].length];

        for (i=0;i<A.length;i++)
            for (j=0;j<B[0].length;j++)
                for (k=0;k<B.length;k++)
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
        return C;
    }

    // computes A ^ p
    long[][] pow(long A[][], long p) {
        if (p == 1)
            return A;
        if (p % 2 == 1)
            return mul(A, pow(A, p-1));
        long X[][] = pow(A, p/2);
        return mul(X, X);
    }

    int getNumberOfWays(int C, int R) {
        int i;

        // create vector F1
        long F1[][] = new long[2*C][1];
        F1[0][0]=F1[1][0]=1;
        F1[C+2][0]=F1[C+3][0]=1;


        System.out.println("F1:");
        printVector(2*C, F1);

        // create transformation matrix T
        long T[][] = new long[2*C][2*C];

        for (i=0;i<C;i++)
        {
            T[i][C+i]=1;
            if(i+2 < C)
                T[i+C][i+C+2] = 1;
            if(i-1 > 0)
                T[i+C][i+C-2] = 1;
        }
        for(i=1; i<C; i++)
        {
            T[i+C-1][i] = 1;
            T[i+C][i-1] = 1;
        }


        System.out.println("T:");
        printMatrix(C, T);

        T = pow(T, R - 1);

        System.out.println("T^(N-1):");
        printMatrix(C, T);


        long FN[][] = mul(T, F1);

        long res = 0;
        res = (res + FN[C-1][0] + FN[C-2][0]) % MOD;

        return (int)res;
    }

    private void printMatrix(int K, long[][] t) {
        for(int r=0; r<K; r++) {
            for(int q=0; q<K; q++) {
                System.out.print(t[r][q]+"  ");
            }
            System.out.println();
        }
    }

    private void printVector(int K, long[][] F) {
        for(int r=0; r<K; r++) {
            System.out.print(F[r][0]+"  ");
        }
        System.out.println();
    }
}