package com.company.recurrentRelations;

/**
 * task 03
 *
 * Calculate N-th member of Fibonacci sequence mod 1 000 000 007
 */
public class FibonacciModulo {

    static long MOD = 1000000007;

    // computes A * B
    static long[][] mul(long A[][], long B[][]) {
        int i,j,k;
        long C[][] = new long[A.length][B[0].length];

        for (i=0;i<A.length;i++)
            for (j=0;j<B[0].length;j++)
                for (k=0;k<B.length;k++)
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
        return C;
    }

    // computes A ^ p
    static long[][] pow(long A[][], long p) {
        if (p == 1)
            return A;
        if (p % 2 == 1)
            return mul(A, pow(A, p-1));
        long X[][] = pow(A, p/2);
        return mul(X, X);
    }

    // returns the N-th term of Fibonacci sequence
    static long fib(long N) {
        int i;

        // create vector F1
        long F1[][] = new long[2][1];
        F1[0][0] = 1;
        F1[1][0] = 1;

        // create matrix T
        long T[][] = new long[2][2];
        T[0][0] = 0;
        T[0][1] = 1;
        T[1][0] = 1;
        T[1][1] = 1;

        // raise T to the (N-1)th power
        if (N == 1)
            return 1;

        T = pow(T, N-1);
        long FN[][] = mul(T, F1);

        return FN[0][0];
    }

    static long opt[] = new long[1000000];

    static long fibNaive(int N) {
        if (opt[N] != 0)
            return opt[N];
        if ((N == 1)||(N == 2))
            return 1;
        opt[N] = (fibNaive(N-1) + fibNaive(N-2)) % MOD;
        return opt[N];
    }

    public static void main(String[] args) throws Exception {
        System.out.println(fibNaive(100)+"\t"+fib(100));
    }

}

