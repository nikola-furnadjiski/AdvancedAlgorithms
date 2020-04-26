package com.company.multiStateSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * Task 01
 *
 * Given number 0<T<=10 and then T numbers, find all numbers that contain each digit from 0 to 9 exactly once and is divisible by all T numbers.
 */
public class SpecialNumbers {
    static int T;
    static int divisors[];

    static int[] a; // digits {1,2,3,4,5,6,7,8,9}
    static int N; //a.length -> always 9

    static void enumerate(int k) {
        if (k == N) {
            process();
            return;
        }
        for (int i = k; i < N; i++) {
            swapDigitsInA(k, i);
            enumerate(k + 1);
            swapDigitsInA(i, k);
        }
    }

    static void process() {
        int i;
        long res = 0;

        //construct the number
        for (i=0;i<N;i++) {
            res *= 10;
            res += a[i];
        }

        //check if number is divisible with all divisors
        boolean good = true;
        for (i=0;i<T;i++) {
            if (res%divisors[i] != 0) {
                good = false;
                break;
            }
        }

        //print the number
        if (good) {
            for (i=0;i<N;i++)
                System.out.print(a[i]);
            System.out.println();
        }
    }

    static void swapDigitsInA(int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static void main(String[] args) throws Exception {
        int i;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        divisors = new int[T];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (i=0;i<T;i++)
            divisors[i] = Integer.parseInt(st.nextToken());

        a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        N = a.length;

        enumerate(0);

        br.close();
    }
}
