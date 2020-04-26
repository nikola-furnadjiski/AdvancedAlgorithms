package com.company.recurrentRelations;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *  task 01
 *
 *  In how many ways can we create sequence of bricks which can contain only -- (2 horizontal, 1 vertical spaces)
 *  and | (1 horizontal, 2 vertical spaces) bricks.
 *      |
 *
 *  If we think more deeply of the problem this is actually fibonacci's sequence
 *                  |                     | |   --                   | --  |||  --|
 *  1 space long:   |     2 spaces long:  | | , --   3 spaces long:  | --, |||, --|
 *
 *  I.E. sequence[n] = sequence [n-1]+sequence[n-2];
 *
 */
public class BrickWall {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        int opt[] = new int[51];
        opt[1] = 1;
        opt[2] = 2;

        for (i=3;i<=50;i++)
            opt[i] = opt[i-1]+opt[i-2];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            i = Integer.parseInt(br.readLine());
            if (i == 0)
                break;
            System.out.println(opt[i]);
        }

        br.close();

    }

}
