package xyz.madstrix.numericalMethods;

import java.util.Random;

public class Test {


    public static void main (String[] argv){

        int n = 5;
        double[][] M = new double[n][n];
        double[] D = new double[n];
        double[] DD = new double[n];

        Random rnd = new Random();


        M[0][0] =  rnd.nextInt(9) + 1;
        M[0][0+1] =  rnd.nextInt(9) + 1;
        for (int i = 1; i<n-1; i++){
            M[i][i-1] =  rnd.nextInt(9) + 1;
            M[i][i] =  rnd.nextInt(9) + 1;
            M[i][i+1] =  rnd.nextInt(9) + 1;
        }
        M[n-1][n-2] =  rnd.nextInt(9) + 1;
        M[n-1][n-1] =  rnd.nextInt(9) + 1;

        for (int i= 0; i<n; i++){
            D[i] = rnd.nextInt(9)+1;
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j< n; j++){
                System.out.print(M[i][j] + " ");
            }
            System.out.print("| " + D[i]);
            System.out.println();
        }

        LinearEquation linearEquation = new LinearEquation();
        double[] X = linearEquation.runMethod(M, D);


        System.out.println();

        double a;
        double c = M[0][0];
        double b = -M[0][0+1];
        DD[0] = c*X[0] - b*X[0+1];
        System.out.println(X[0] + " | " + DD[0]);
        for (int i = 1; i < n; i++) {

            a = -M[i][i-1];
            c = M[i][i];
            b = (i==n-1) ? 0 : -M[i][i + 1];
            D[i] = -a*X[i-1] + c*X[i] - ((i==n-1) ? 0 : b*X[i+1]);
            System.out.println(X[i] + " | " + D[i]);
        }


    }

}
