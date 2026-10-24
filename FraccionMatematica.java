package edu.unl.cc.ape.domain;

public class Fraccion {

    private int numerador;
    private int denominador;

    // Constructor
    public Fraccion(int numerador, int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException(
                    "El denominador no puede ser cero.");
        }

        this.numerador = numerador;
        this.denominador = denominador;

        simplificar();
    }

    // Representar la fraccion como texto
    @Override
    public String toString() {
        return numerador + "/" + denominador;
    }

    // Convertir la fraccion a decimal
    public double aDecimal() {
        return (double) numerador / denominador;
    }

    // Sumar dos fracciones
    public Fraccion sumar(Fraccion otra) {
        return new Fraccion(
                numerador * otra.denominador
                        + otra.numerador * denominador,
                denominador * otra.denominador);
    }

    // Restar dos fracciones
    public Fraccion restar(Fraccion otra) {
        return new Fraccion(
                numerador * otra.denominador
                        - otra.numerador * denominador,
                denominador * otra.denominador);
    }

    // Multiplicar dos fracciones
    public Fraccion multiplicar(Fraccion otra) {
        return new Fraccion(
                numerador * otra.numerador,
                denominador * otra.denominador);
    }

    // Dividir dos fracciones
    public Fraccion dividir(Fraccion otra) {
        if (otra.numerador == 0) {
            throw new ArithmeticException(
                    "No se puede dividir entre cero.");
        }

        return new Fraccion(
                numerador * otra.denominador,
                denominador * otra.numerador);
    }

    // Obtener la inversa
    public Fraccion obtenerInversa() {
        if (numerador == 0) {
            throw new ArithmeticException(
                    "No existe inversa para una fraccion con numerador cero.");
        }

        return new Fraccion(denominador, numerador);
    }

    // Simplificar la fraccion
    public void simplificar() {
        int mcd = calcularMcd(
                Math.abs(numerador),
                Math.abs(denominador));

        numerador /= mcd;
        denominador /= mcd;

        // Mantener el signo negativo en el numerador
        if (denominador < 0) {
            numerador = -numerador;
            denominador = -denominador;
        }
    }

    // Calcular el maximo comun divisor
    private int calcularMcd(int a, int b) {
        while (b != 0) {
            int resto = a % b;
            a = b;
            b = resto;
        }

        return a;
    }
}
