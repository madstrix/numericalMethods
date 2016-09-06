package xyz.madstrix.numericalMethods;

import java.util.logging.Logger;
import static java.lang.Math.abs;

/**
 * TODO add a arrays names
 * Класс реализует решение систем линейных уравнений матодом прогонки и методом Гаусса.
 * На вход получает два массива. Один (___) с коэффициентами при x-ах, второй (___) со значениями уравнений
 * @author madstrix
 * @since 1.09.2016
 */
public class LinearEquation {

    /**
     * TODO add a description
     * @param M
     * @param D
     * @return
     */
    public double[] runMethod (double[][]M, double[] D) {

        /**
         * TODO add a description
         */
        int n = D.length;
        double[] A = new double[n];
        double[] B = new double[n];
        double[] X = new double[n];
        double a, b, c;

        //TODO add a description
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

        //TODO add a description
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

        //TODO add a description
        //second cycle
        X[n-1] = B[n-1];
        for (int i=n-2; i>=0; i--){
            X[i] = A[i]*X[i+1]+B[i];
        }

        return X;

    }

    //TODO add a description
    public double[] gaussMethod (double[][] M, double[] D){

        //TODO add a description
        int n = D.length;
        double[] X = new double[n];
        double c;

        for (int i=1; i<n; i++){

            for (int j=i; j<n; j++) {
                c = -(M[j][i - 1] / M[i - 1][i - 1]);
                M[j][i - 1] += (c*M[i-1][i-1]);

                for (int k = i; k < M[j].length; k++) {
                    M[j][k] = M[j][k] + (c*M[i-1][k]);
                }

                D[j] += c*D[i-1];
            }
        }

        //TODO add a description
        for (int i = n-1; i>=0; i--){

            X[i] = D[i];

            for (int j = i+1; j<M[i].length; j++){
                X[i] -= (M[i][j]*X[j]);
            }

            X[i] /= M[i][i];

        }

        return X;
    }

}
