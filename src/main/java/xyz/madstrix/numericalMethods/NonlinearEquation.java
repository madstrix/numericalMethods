package xyz.madstrix.numericalMethods;

import static java.lang.Math.*;

public abstract class NonlinearEquation {

    private double deviation;
    private static final double dx = 1e-6;

    public NonlinearEquation(double deviation){
        this.deviation = deviation;
    }

    public double halfDivision (double a, double b) throws Exception {

        if (function(a) * function(b)>0) {
            throw new Exception("Equation have more than one or none root. Use less interval or another metohod.");
        }

        while (abs(b-a)>deviation){
            double c = (a + b)/2;

            if (function(a) * function(c) <= 0){
                b = c;
            }else {
                a = c;
            }
        }

        return (a+b)/2;
    }

    public double newton (double x0) throws Exception {

        double x=x0;
        int i=0;

        do {
            x0 = x;

            x = x0 - (function(x0) / derivative(x0));

	    //exeption
            i++;
            if (i == 10000) {
                throw new Exception("The limit of operations exceeded. Perhaps the function has no solution. Use a another initial value, or another method.");
            }

        } while (abs(x-x0)>deviation);

        return x;
    }

    private double derivative(double x){
        return (function(x+dx)-function(x))/dx;
    }

    protected abstract double function(double x);

}
