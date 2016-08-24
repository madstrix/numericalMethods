package xyz.madstrix.numericalMethods;

import static java.lang.Math.*;

/**
 * Получает на вход массив значений некой функции (inputYArray) и расчитывает интерполяционную кривую
 * на промежутке [a, b] с шагом step.
 * @author madstrix
 * @since 25.08.2016
 */
public class FourierInterpolation{

    /**
     * начало интервала интерполяции
     */
    private final double a;
    /**
     * конец интервала интерполяции
     */
    private final double b;
    /**
     * интервал интерполяции
     */
    private double T;
    /**
     * шаг итератора
     */
    private double step;
    /**
     * массив с узлами для интерполяции
     */
    private double[] inputYArray;
    /**
     * аргумент интерполяционной функции
     */
    private double outputX;
    /**
     * значение интерполяционной функции
     */
    private double outputY;
    /**
     * массив с аргументами интерполяционной функции
     */
    private double[] outputXArray;
    /**
     * массив со значениями интерполяционной функции
     */
    private double[] outputYArray;


    /**
     * @param a начало интервала интеграции
     * @param b конец игтервала интеграции
     * @param step шаг игтеграции
     * @param inputYArray массив с входящими данными
     */
    public FourierInterpolation(double a, double b, double step, double inputYArray[]) {

        this.a = a;
        this.b = b;
        this.step = step;
        this.inputYArray = inputYArray;
        T = b - a;
        int n = (int) (T / step)+1;
        outputX = 0;
        outputY = 0;
        outputXArray = new double[n];
        outputYArray = new double[n];

    }

    /**
     * дискретное преобразование Фурье(коэф. a)
     * @param j
     * @return a
     */
    private double fourierA(int j){

        double a = 0;

        for (int k = 0; k < inputYArray.length; k++) {
            a += inputYArray[k]*cos(2*j*PI*k/ inputYArray.length);
        }

        a = a * 2 / inputYArray.length;
        return a;
    }

    /**
     * дискретное преобразование Фурье(коэф. b)
     * @param j
     * @return b
     */
    private double fourierB(int j){

        double b = 0;

        for (int k = 0; k < inputYArray.length; k++) {
            b += inputYArray[k]*sin(2*j*PI*k/ inputYArray.length);
        }

        b = b * 2 / inputYArray.length;
        return b;
    }

    /**
     * вычисление значения интерполированной функции в точке outputX и помещает значение в outputY
     */
    public void calculateDot (){

        outputY = 0;

        for (int j = 1; j<= inputYArray.length/2; j++) {
            outputY += fourierA(j) * cos(j * 2 * PI * outputX / T) + fourierB(j) * sin(j * 2 * PI * outputX / T);
        }

    }

    /**
     * создание массива с точками интерполированной функции на интервале [a, b]
     */
    public void calculateArray () {

        int i = 0;

        for (outputX = a; outputX <= b; outputX += step) {
            calculateDot();
            outputXArray[i] = outputX;
            outputYArray[i] = outputY;
            i++;
        }
    }

    public double getOutputX() {
        return outputX;
    }

    public double getOutputY() {
        return outputY;
    }

    public double[] getOutputXArray() {
        return outputXArray;
    }

    public double[] getOutputYArray() {
        return outputYArray;
    }

}
