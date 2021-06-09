package com.company;

public class main {

    public static void main(String[] args) {

        SimulatedAnnealing sa = new SimulatedAnnealing(1, 0.97, 100);
        sa.solve(new Problem() {
            @Override
            public double fit(double a,double b) {

                return 10000*a*a + 2000*a*b + b*b - 14000*a - 1400*b + 400 ;
            }

            @Override
            public boolean isNeighborBetter(double f0, double f1) {
                return f1 > f0;
            }
        }, 1, 20);

        System.out.println(sa.hist);
        System.out.println(sa.hista);
        System.out.println(sa.histb);

    }
}