package xyz.madstrix.numericalMethods;

/**
 * Реализует алгоритм решения обыкновенных дифференциальных уравнений методами Эйлера и Рунге-Кутты 4 порядка
 * Получает в конструкторе шаг (step).
 * Начальные x и y задаются через сеттеры или по умолчанию равны 0.001.
 * @author madstrix
 * @since 2.09.2016
 */
public abstract class NumericalSchemes {

    private final double step;
    private double x;
    private double y;

    /**
     * Конструктор класса
     * @param step шаг итераций
     */
    public NumericalSchemes(double step){

        this.step = step;

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

    /**
     * Рассчитывает массив размера tMax со значениями (x, y) от начальных (x, y) методом Рунге-Кутты 4 порядка
     * @param tMax максимальное колличество итерация от заданного x
     * @return
     */
    public double[][] calculateRK4Array (int tMax) {

        double[][] Result = new double[2][tMax];

        for (int t = 0; t < tMax; t++) {
            rk();
            Result[0][t] = x;
            Result[1][t] = y;
        }

        return Result;
    }

    /**
     * Рассчитывает массив размера tMax со значениями (x, y) от начальных (x, y) методом Эйлера
     * @param tMax максимальное колличество итерация от заданного x
     * @return
     */
    public double[][] calculateEulierArray (int tMax) {

        double[][] Result = new double[2][tMax];

        for (int t = 0; t < tMax; t++){
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

        k11 = step * firstPartFunction(x, y);
        k21 = step * secondPartFunction(x, y);

        k12 = step * firstPartFunction(x+k11/2, y+k21/2);
        k22 = step * secondPartFunction(x+k11/2, y+k21/2);

        k13 = step * firstPartFunction(x+k12/2, y+k22/2);
        k23 = step * secondPartFunction(x+k12/2, y+k22/2);

        k14 = step * firstPartFunction(x+k13/2, y+k23/2);
        k24 = step * secondPartFunction(x+k13/2, y+k23/2);

        dx = (k11+2*k12+2*k13+k14)/6;
        dy = (k21+2*k22+2*k23+k24)/6;

        x += dx;
        y += dy;

    }

    private void eulier () {

        double dx, dy;

        dx = step * firstPartFunction(x, y);
        dy = step * secondPartFunction(x, y);

        x += dx;
        y += dy;

    }

    /**
     * Абстрактные медоды, в которых нужно реализовать левую (firstPart) и правую (secondPart) части уравнения.
     * @param x
     * @param y
     * @return
     */
    public abstract double firstPartFunction(double x, double y);
    public abstract double secondPartFunction(double x, double y);


}
