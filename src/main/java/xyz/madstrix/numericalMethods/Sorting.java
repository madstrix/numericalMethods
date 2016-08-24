package xyz.madstrix.numericalMethods;

public class Sorting {

    public double[] bubbleSort (double[] a) {

        for (int i = a.length - 1; i > 0; i--) {

            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    double tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }

        return a;

    }

    public int[] bubbleSort (int[] a) {

        for (int i = a.length - 1; i > 0; i--) {

            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }

        return a;

    }
}
