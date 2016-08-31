package xyz.madstrix.numericalMethods;

/**
 * Класс реализует механизм численного интегрирования.
 * Получает в конструктор интервал интегрирования и шаг интегрирования.
 * @author madstrix
 * @since 31.08.2016
 */
public abstract class Integration {

    /**
     * начало интервала интегрирования
     */
    private double a;
    /**
     * конец интервала интегрирования
     */
    private double b;
    /**
     * шаг интегрирования (точность интегрирования)
     */
    private double step;

    /**
     * @param a начало интервала интегрирования
     * @param b конец интервала интегрирования
     * @param step шаг интегрирования
     */
    public Integration(double a, double b, double step) {

        this.a = a;
        this.b = b;
        this.step = step;
    }

    /**
     * метод должен быть реализован при создании экземпляра класса и
     * возвращать значение функции (y), принимая на вход аргумент функции (x)
     * @param x аргумент функции
     * @return значение функции
     */
    public abstract double function(double x);

    /**
     * метод левосторонних прямоугольников.
     * Быстрый, но не особо точный метод.
     * @return значение интеграа на выбранном промежутке
     */
    public double rectangle () {

        /**
         * @param I интегралл
         * @param x значение интегралла на выбранном шаге
         * @param n колличество шагов, необходимо для цикла
         */
        double I = 0;
        double x = this.a;
        double n = (int) Math.round((this.b - this.a) / this.step);
        /**
         * переопределение шага для избежания лишних итераций интегрирования
         */
        this.step = (this.b - this.a) / n;

        while (x <= b - step) {
            I += step * function(x);
            x += this.step;
        }

        return I;

        }

    /**
     * метод трапеции
     * более точен, чем метод прямоугольников
     * @return значение интеграа на выбранном промежутке
     */
    public double trapeze (){

        /**
         * @param I интегралл
         * @param x значение интегралла на выбранном шаге
         * @param n колличество шагов, необходимо для цикла
         */
        double I = 0;
        double x = a;
        double n = (int) Math.round((this.b - this.a) / this.step);
        /**
         * переопределение шага для избежания лишних итераций интегрирования
         */
        this.step = (this.b - this.a) / n;

        while (x<b) {
            I += step*(function(x)+function(x+step))/2;
            x += this.step;
        }

        return I;

    }

}


