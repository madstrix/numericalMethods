package xyz.madstrix.numericalMethods;

import static java.lang.Math.*;

/**
 * @author MaDStriX
 * @author madstrix@ya.ru
 */
public class FourierInterpolation {

    private final double a;     //начало интервала интерполяции
    private final double b;     //конец интервала интерполяции
    private double T;           //интервал интерполяции
    private double step;        //шаг
    private double My[];        //массив с узлами
    private double Fy[];        //масив с точками интерполированой функции
    private double Fx[];        //масив с точками интерполированой функции

    //конструктор с параметрами интерполирования
    public FourierInterpolation(double a, double b, double step, double My[]) {

        this.a = a;
        this.b = b;
        this.step = step;
        this.My = My;

        T = b - a;

    }

    //дискретное преобразование Фурье(коэф. a)
    private double a (int j){
        double a = 0;

        for (int k = 0; k < My.length; k++) {

            a += My[k]*cos(2*j*PI*k/My.length);

        }

        a = a * 2 / My.length;
        return a;
    }

    //дискретное преобразование Фурье(коэф. a)
    private double b (int j){
        double b = 0;

        for (int k = 0; k < My.length; k++) {

            b += My[k]*sin(2*j*PI*k/My.length);

        }

        b = b * 2 / My.length;
        return b;
    }

    //вычисление значения интерполированной функции в точке x
    public double getFourierInterpolationDot (double x){

        double f = 0;

        for (int j=1; j<= My.length/2; j++) {

            f += a(j) * cos(j * 2 * PI * x / T) + b(j) * sin(j * 2 * PI * x / T);

        }

        return f;

    }

    //создание массива с точками интерполированной функции на интервале [a, b]
    public void makeFourierInterpolationArray () {

        int n = (int) (T / step)+1;

        Fy = new double[n];
        Fx = new double[n];
        int i = 0;

        for (double x = a; x <= b; x += step) {
            Fy[i] = getFourierInterpolationDot(x);
            Fx[i] = x;
            i++;
        }
    }

    //геттер массива с y-точками
    public double[] getFourierInterpolationArrayOfY () { return Fy; }

    //геттер массива с x-точками
    public double[] getFourierInterpolationArrayOfX () { return Fx; }


}
