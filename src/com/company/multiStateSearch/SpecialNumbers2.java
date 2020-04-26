package com.company.multiStateSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * Task 02
 *
 * Same as Task 01 only we need to print the resulting numbers in ascending order
 */
public class SpecialNumbers2 {

    static boolean taken[];
    static int ordered[];

    static void enumerate(int k) {

        if (k == N) {
            process();
            return;
        }

        int i;

        for (i=0;i<N;i++) {
            if (taken[i] == false) {
                taken[i] = true;
                ordered[k] = a[i];
                enumerate(k+1);
                taken[i] = false;
            }
        }

    }

    static void process() {
        int i;
        long res = 0;

        for (i=0;i<N;i++) {
            res *= 10;
            res += ordered[i];
        }

        boolean good = true;

        for (i=0;i<T;i++) {
            if (res%divisors[i] != 0) {
                good = false;
                break;
            }
        }

        if (good) {
            for (i=0;i<N;i++)
                System.out.print(ordered[i]);
            System.out.println();
        }

    }

    static int T;
    static int divisors[];

    static int N;
    static int[] a; // bits (0 or 1)

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
        ordered = new int[N];
        taken = new boolean[N];

        enumerate(0);

        br.close();

    }
}

