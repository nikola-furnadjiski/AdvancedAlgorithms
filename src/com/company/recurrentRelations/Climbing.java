package com.company.recurrentRelations;

/**
 * task 06
 */
public class Climbing {

    public static void main(String[] args) {
        Climbing c = new Climbing();
        int answer = c.getNumberOfWays(7, 8);
        //int answer2 = c.getNumberOfWaysDP(7, 8);
        System.out.println("answer: "+answer)
        ;
    }

    //my method with DP the lecture method is below
    private static int getNumberOfWaysDP(int K, int N) {
        int[][] mat = new int[N][K];
        for (int i = 0; i < K; i++) {
            mat[0][i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < K; j++) {
                if (j == 0) {
                    mat[i][j] = mat[i - 1][j] + mat[i - 1][j + 1];
                } else if (j == K-1) {
                    mat[i][j] = mat[i - 1][j - 1] + mat[i - 1][j];
                } else {
                    mat[i][j] = mat[i - 1][j - 1] + mat[i - 1][j] + mat[i - 1][j + 1];
                }
            }
        }
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < K; j++) {
//                System.out.print(mat[i][j] + "  ");
//            }
//            System.out.println();
//        }

        int answer = 0;
        for (int j = 0; j < K; j++) {
            answer+=mat[N-1][j];
        }

        return answer;
    }

    long MOD = 1000000007;

    // computes A * B
    long[][] mul(long A[][], long B[][]) {
        int i, j, k;
        long C[][] = new long[A.length][B[0].length];

        for (i = 0; i < A.length; i++)
            for (j = 0; j < B[0].length; j++)
                for (k = 0; k < B.length; k++)
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
        return C;
    }

    // computes A ^ p
    long[][] pow(long A[][], long p) {
        if (p == 1)
            return A;
        if (p % 2 == 1)
            return mul(A, pow(A, p - 1));
        long X[][] = pow(A, p / 2);
        return mul(X, X);
    }

    int getNumberOfWays(int K, int N) {
        int i;

        if (K == 1)
            return 1;

        // create vector F1
        long F1[][] = new long[K][1];
        for (i = 0; i < K; i++)
            F1[i][0] = 1;

        ////////////printing F1
        System.out.println("F1:");
        for(int r=0; r<K; r++) {
            System.out.print(F1[r][0]+"  ");
        }
        System.out.println();
        //////////////


        // create transformation matrix T
        long T[][] = new long[K][K];

        T[0][0] = 1;
        T[0][1] = 1;

        for (i = 1; i < (K - 1); i++) {
            T[i][i - 1] = 1;
            T[i][i] = 1;
            T[i][i + 1] = 1;
        }

        T[K - 1][K - 2] = 1;
        T[K - 1][K - 1] = 1;

        System.out.println("T:");
        printMatrix(K, T);

        T = pow(T, N - 1);

        System.out.println("T^(N-1):");
        printMatrix(K, T);

        long FN[][] = mul(T, F1);

        long res = 0;
        for (i = 0; i < FN.length; i++)
            res = (res + FN[i][0]) % MOD;

        return (int) res;
    }

    private void printMatrix(int K, long[][] t) {
        for(int r=0; r<K; r++) {
            for(int q=0; q<K; q++) {
                System.out.print(t[r][q]+"  ");
            }
            System.out.println();
        }
    }
}
