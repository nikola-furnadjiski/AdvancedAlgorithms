package com.company.recurrentRelations;


/**
 * task 02
 *
 * Calculate f(n) if f(0) = 2, f(1) = 2, f(n) = sum(i=[0,n-1])(f(i)*f(i-1))
 */
public class NonLinearRecurrentRelation {
    private static void calculateInNSquaredTime(int n) {
        long[] result = new long[n+1];
        result[0] = result[1] = 2;

        for (int i = 2; i <= n; i++) {
            long tmp = 0;
            for (int j = i - 1; j > 0; j--) {
                tmp += result[j] * result[j - 1];
            }
            result[i] = tmp;
        }

        for(int i=0; i<result.length; i++) {
            //System.out.println("n["+i+"] = "+result[i]);
        }
    }

    /**
     * Recursive approach (worst time complexity)
     * @param N
     * @return
     */
    private static long T1(int N) {
        //System.out.println("T1 with: "+N);
        if ((N == 0)||(N == 1))
            return 2;
        int i;
        long res = 0;
        for (i=1;i<=N-1;i++) {
            res += T1(i)*T1(i-1);
        }
        return res;
    }

    private static void T1test() {
        int i;
        for (i=1;i<=25;i++) {
            long time1 = System.currentTimeMillis();
            long res = T1(i);
            long time2 = System.currentTimeMillis();
            System.out.println(res+"\t"+(time2-time1));
        }
    }

    static long UNDEFINED = -1;
    static long opt[];

    private static long T2(int N) {
        //System.out.println("T2 with: "+N);
        if (opt[N] != UNDEFINED)
            return opt[N];
        int i;
        long res = 0;
        for (i=1;i<=N-1;i++) {
            res += T2(i)*T2(i-1);
        }
        opt[N] = res;
        return opt[N];
    }

    private static void T2test() {
        int i;
        opt = new long[30001];

        opt[0] = 2;
        opt[1] = 2;
        for (i=2;i<=30000;i++)
            opt[i] = UNDEFINED;

        for (i=1;i<=30000;i+=300) {
            long time1 = System.currentTimeMillis();
            long res = T2(i);
            long time2 = System.currentTimeMillis();
            System.out.println(res+"\t"+(time2-time1));
        }
    }

    static long os[];

    private static long T3(int N) {
        //System.out.println("T3 with: "+N);
        if (os[N] != UNDEFINED)
            return os[N];
        os[N] = T3(N-1)*T3(N-2)+T3(N-1);
        return os[N];
    }

    private static void T3test() {
        int i;

        os = new long[269601];
        os[0] = 2;
        os[1] = 2;
        os[2] = 4;
        for (i=3;i<=269600;i++)
            os[i] = UNDEFINED;

        for (i=1;i<=269600;i+=2696) {
            long time1 = System.currentTimeMillis();
            long res = T3(i);
            long time2 = System.currentTimeMillis();
            System.out.println(res+"\t"+(time2-time1));
        }
    }

    private static void testCorrectness() {
        int i;

        opt = new long[30001];

        opt[0] = 2;
        opt[1] = 2;

        for (i=2;i<=30000;i++)
            opt[i] = UNDEFINED;

        os = new long[30001];
        os[0] = 2;
        os[1] = 2;
        os[2] = 4;

        for (i=3;i<=30000;i++)
            os[i] = UNDEFINED;

        for (i=0;i<=8;i++) {
            System.out.println(i+"\t"+T1(i)+"\t"+T2(i)+"\t"+T3(i));
        }
    }

    public static void main(String[] args) throws Exception {
        //calculateInNSquaredTime(10);
        //T1test();
        //T2test();
        //T3test();
        //testCorrectness();
    }

}
