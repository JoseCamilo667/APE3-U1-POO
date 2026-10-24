package edu.unl.cc.domain;

/**
 * @author Alexander Guaman
 */
public class EcuacionCuadratica {

    private final double a;
    private final double b;
    private final double c;

    // controlar que 'a' no sea cero
    public EcuacionCuadratica(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("Error: El coeficiente 'a' no puede ser cero.");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // calcula el discriminante: (b² - 4ac)
    public double calcularDiscriminante() {
        return (b * b) - (4 * a * c);
    }

    // muestra las raíces reales o complejas
    public void mostrarRaices() {
        double d = calcularDiscriminante();

        //raices reales
        if (d > 0) {
            // Caso 1: Dos raíces reales distintas
            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double x2 = (-b - Math.sqrt(d)) / (2 * a);
            System.out.println("Las raíces son reales y distintas:");
            System.out.printf("x1 = %.2f%n", x1);
            System.out.printf("x2 = %.2f%n", x2);

        } else if (d == 0) {
            double x = -b / (2 * a);
            System.out.println("Tiene una raíz doble:");
            System.out.printf("x = %.2f%n", x);

         //raices complejas
        } else {
            double parteReal = -b / (2 * a);
            double parteImaginaria = Math.sqrt(-d) / (2 * a);
            System.out.println("Las raíces son complejas conjugadas:");
            System.out.printf("x1 = %.2f + %.2fi%n", parteReal, Math.abs(parteImaginaria));
            System.out.printf("x2 = %.2f - %.2fi%n", parteReal, Math.abs(parteImaginaria));
        }
    }
}
