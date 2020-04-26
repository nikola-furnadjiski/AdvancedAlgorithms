package com.company.recurrentRelations;

/**
 * task 5
 *
 *               indexes: {0,1,2,3,4}       indexes: {0,1}
 * We have array   T[n] = {x,y,x,x,y} and array P[k]={x,y} where k<=n,
 * and array costs c[n] = {2,2,7,1,1}
 *
 * We need to find maxCost which from array T[n] makes array P[k]
 *
 * eg. in our case we can make the array P[k] by taking T[0],T[1]. Cost is c[0] + c[1] = 2 + 2 = 4
 *     or we can make the P[k] array by taking          T[2],T[4]. Cost is c[2] + c[4] = 7 + 1 = 8
 *     or we can make the P[k] array by taking          T[3],T[4]. COst is c[3] + c[4] = 1 + 1 = 2
 *
 *     So the answer is T[2],T[4] with cost = 8.
 *
 *
 *
 *     To solve this problem we will use recurrent relation and define it as matrix M[n+1][k+1]
 *     where M[0][] = 0 and M[][0] = 0 (these indexes are fictional (written as '#' below and we will just use them
 *     so that we have values) for the first letter in T and first letter in P when we execute the algorithm.
 *
 *     Out matrix at the beginning will look like this:
 *
 *     P[j] :                    {  #,  X,  Y  }
 *     indexes :                 {  0 , 1,  2  } = i
 *
 *     so                              M =
 *     T[i]: indexes: { #, {0    [  0   0   0  ]
 *                      X   1    [  0   ?   ?  ]
 *                       Y, 2    [  0   ?   ?  ]
 *                       X, 3    [  0   ?   ?  ]
 *                       X, 4    [  0   ?   ?  ]
 *                       Y, 5}   [  0   ?   ?  ]
 *                          =
 *                          j
 *
 *    Now we iterate over the matrix and if:
 *    T[i] == P[j]   :   M[i,j] = max( M[i-1 , j-1] + C[i] , M[i-1 , j] )
 *    T[i] != P[j]   :   M[i,j] = M[i-1, j]
 *
 *    after the M has been filled it will look like this:
 *
 *   P[j] :                      {  #,  X,  Y  }
 *  indexes :                    {  0 , 1,  2  } = i
 *
 *     so                              M =
 *     T[i]: indexes: { #, {0    [  0   0   0  ]
 *                      X   1    [  0   2   0  ]
 *                       Y, 2    [  0   2   4  ]
 *                       X, 3    [  0   7   4  ]
 *                       X, 4    [  0   7   4  ]
 *                       Y, 5}   [  0   7   8  ]
 *                          =
 *                          j
 */
public class MaxCostSubsequence {
    private static void getMaxCostSubsequence(char[] T, char[] P, int[] Cost) {
        int[][] M = new int[T.length+1][P.length+1];

        for(int i=0; i<T.length; i++) {
            M[i][0] = 0;
        }
        for(int i=0; i<P.length; i++) {
            M[0][i] = 0;
        }

        System.out.println("Initial Matrix:");
        printMatrix(M);
        for(int i=1; i<T.length+1; i++) {
            for(int j=1; j<P.length+1; j++) {
                //this is because we are looking at T[i-1], P[j-1] for M[i][j] if still unclear why, see method docs.
                int Ti = i-1;
                int Pj = j-1;

                if(T[Ti] == P[Pj]) {
                    M[i][j] = Math.max(M[i-1][j-1] + Cost[Ti] , M[i-1][j] );
                } else {
                    M[i][j] = M[i-1][j];
                }
            }
        }

        System.out.println("Final Matrix:");
        printMatrix(M);
        System.out.println("RESULT IS: "+M[T.length][P.length]);
    }

    private static void printMatrix(int[][] M) {
        for(int i=0; i<M.length; i++) {
            for(int j=0; j<M[0].length; j++) {
                System.out.print(M[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


    public static void main(String[] args) {
        char[] T = {'X','Y','X','X','Y'};
        char[] P = {'X','Y'};

        int[] Cost = {2,2,7,1,1};

        getMaxCostSubsequence(T,P,Cost);
    }
}
