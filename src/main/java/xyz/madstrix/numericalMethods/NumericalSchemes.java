package xyz.madstrix.numericalMethods;

public abstract class NumericalSchemes {

    private final double h;
    private final int t_max;
    private double x;
    private double y;

    NumericalSchemes(int t_max, double h){

        this.h = h;
        this.t_max = t_max;

        x = 0.001;
        y = 0.001;

    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public double[][] calculateRK4 () {

        double[][] Result = new double[2][t_max];

        for (int t = 0; t < t_max; t++) {
            rk();
            Result[0][t] = x;
            Result[1][t] = y;
        }

        return Result;

    }

    public double[][] calculateEulier() {

        double[][] Result = new double[2][t_max];

        for (int t = 0; t < t_max; t++){
            eulier();
            Result[0][t] = x;
            Result[1][t] = y;

        }

        return Result;

    }

    private void rk (){

        double  k11, k12, k13, k14,
                k21, k22, k23, k24,
                dx, dy;

        k11 = h* firstPartFunction(x, y);
        k21 = h* secondPartFunction(x, y);

        k12 = h* firstPartFunction(x+k11/2, y+k21/2);
        k22 = h* secondPartFunction(x+k11/2, y+k21/2);

        k13 = h* firstPartFunction(x+k12/2, y+k22/2);
        k23 = h* secondPartFunction(x+k12/2, y+k22/2);

        k14 = h* firstPartFunction(x+k13/2, y+k23/2);
        k24 = h* secondPartFunction(x+k13/2, y+k23/2);

        dx = (k11+2*k12+2*k13+k14)/6;
        dy = (k21+2*k22+2*k23+k24)/6;

        x += dx;
        y += dy;

    }

    private void eulier () {

        double dx, dy;

        dx = h* firstPartFunction(x, y);
        dy = h* secondPartFunction(x, y);

        x = x + dx;
        y = y + dy;

    }
    public abstract double firstPartFunction(double x, double y);
    public abstract double secondPartFunction(double x, double y);


}
