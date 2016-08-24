package xyz.madstrix.numericalMethods;

/**
 * @author MaDStriX
 * @author madstrix@ya.ru
 */
public abstract class Integration {

    private double a;           //начало интервала интегрирования
    private double b;           //конец интервала интегрирования
    private double step;        //шаг интегрирования (точность интегрирования)

    //конструктор для параметров интегрирования
    public Integration(double a, double b, double step) {

        this.a = a;
        this.b = b;
        this.step = step;
    }

    //функция для интегрирования
    public abstract double function(double x);

    //метод левосторонних прямоугольников
    public double rectangle () {

        double I = 0;
        double x = this.a;
        //вычисление кол-ва шагов
        double n = (int) Math.round((this.b - this.a) / this.step);
        //переопределение шага
        this.step = (this.b - this.a) / n;

        //вычисление интеграла
        while (x <= b - step) {
            I += step * function(x);
            x += this.step;
        }

        return I;

        }

    //метод трапеции (описание см. метод прямоугольников)
    public double trapeze (){

        double I = 0;
        double x = a;
        double n = (int) Math.round((this.b - this.a) / this.step);
        this.step = (this.b - this.a) / n;

        while (x<b) {
            I += step*(function(x)+function(x+step))/2;
            x += this.step;
        }

        return I;

    }

}


