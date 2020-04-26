package com.company.solvingAlgorithmicProblems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 * Task 01
 * <p>
 * <p>
 * We enter N
 * Then we enter N integers
 * Then we enter decimal number d
 * then we enter 2 words z1 and z2
 * <p>
 * if first letter of z1 and z2 is same calculate sum: sum(i=[0,n))(abs)(word[i]-d)^2
 * otherwise calculate sum: sum(i=[0,n))(abs)(word[i]-d)
 */
public class CalculateSum {
    public static void main(String[] args) throws Exception {
        int i;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (i = 0; i < N; i++)
            a[i] = Integer.parseInt(st.nextToken());

        double d = Double.parseDouble(br.readLine());

        st = new StringTokenizer(br.readLine());

        String str1 = st.nextToken();
        String str2 = st.nextToken();

        if (str1.charAt(0) == str2.charAt(0)) {
            double res = 0;
            for (i = 0; i < N; i++) res += (Math.abs(a[i] - d)) * (Math.abs(a[i] - d));
            DecimalFormat df = new DecimalFormat("0.000000");
            System.out.println(df.format(res));
        } else {
            double res = 0;
            for (i = 0; i < N; i++) res += (Math.abs(a[i] - d));
            DecimalFormat df = new DecimalFormat("0.000000");
            System.out.println(df.format(res));
        }

        br.close();
    }
}
