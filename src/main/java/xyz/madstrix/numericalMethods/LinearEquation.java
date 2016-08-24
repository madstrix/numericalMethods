package xyz.madstrix.numericalMethods;

import java.util.logging.Logger;
import static java.lang.Math.abs;

public class LinearEquation {

    public double[] runMethod (double[][]M, double[] D) {

        int n = D.length;
        double[] A = new double[n];
        double[] B = new double[n];
        double[] X = new double[n];
        double a, b, c;

        //check diagonal superiority
        boolean f = false;
        for (int i = 0; i<n; i++) {
            a = abs((i==0) ? 0 : M[i][i-1]) ;
            c = abs(M[i][i]);
            b = abs((i==n-1) ? 0 : -M[i][i + 1]);
            if (c<=(a+b)) {
                f = true;
            }
        }
        if (f) {
            Logger.getLogger(LinearEquation.class.getName()).warning("Matrix don't have diagonal superiority. The calculation results may not be accurate");
        }

        //first cycle
        c = M[0][0];
        b = -M[0][0+1];
        A[0] = b/c;
        B[0] = D[0]/c;
        for (int i = 1; i < n; i++) {

            a = -M[i][i-1];
            c = M[i][i];
            b = (i==n-1) ? 0 : -M[i][i + 1];
            A[i] = b/(c-A[i-1]*a);
            B[i] = (D[i]+B[i-1]*a)/(c-A[i-1]*a);
        }

        //second cycle
        X[n-1] = B[n-1];
        for (int i=n-2; i>=0; i--){
            X[i] = A[i]*X[i+1]+B[i];
        }

        return X;

    }

}
