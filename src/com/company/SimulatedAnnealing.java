package com.company;

import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {
    private double t;
    private double a;
    private int niter;
    public ArrayList<Double> hist;
    public ArrayList<Double> hista;
    public ArrayList<Double> histb;

    public SimulatedAnnealing(double t, double a, int niter) {
        this.t = t;
        this.a = a;
        this.niter = niter;
        hist = new ArrayList<>();
        hista = new ArrayList<>();
        histb = new ArrayList<>();
    }

    public double solve(Problem p, double lower, double upper) {
        Random r = new Random();
        double a0 = r.nextDouble() * (upper - lower) + lower;
        double b0 = r.nextDouble() * (upper - lower) + lower *28;
        double f0 = p.fit(a0,b0);
        hist.add(f0);
        hista.add(a0);
        histb.add(b0);

        for(int i=0; i<niter; i++) {    // REPEAT
            int kt = (int) Math.round(t * 10);
            for(int j=0; j<kt; j++) {
                double a1 = r.nextDouble() * (upper - lower) + lower;
                double b1 = r.nextDouble() * (upper - lower) + lower *28;
                double f1 = p.fit(a1,b1);
                if(p.isNeighborBetter(f0, f1)) {    // 이웃해가 더 나음
                    a0 = a1;
                    b0 = b1;
                    f0 = f1;
                    hist.add(f0);
                    hista.add(a0);
                    histb.add(b0);
                } else {    // 기존해가 더 나음
                    double d = f1 - f0;
                    double p0 = Math.exp(-d/t);
                    if(r.nextDouble() < p0) {
                        a0 = a1;
                        b0 = b1;
                        f0 = f1;
                        hist.add(f0);
                        hista.add(a0);
                        histb.add(b0);
                    }
                }
            }
            t *= a;
        }
        return a0;
    }
}