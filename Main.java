package edu.unl.cc.ape.view;
import edu.unl.cc.ape.domain.Fraccion;
public class Main {

    public static void main(String[] args) {

        // Prueba 1: 1/2 + 1/3 = 5/6
        Fraccion a = new Fraccion(1, 2);
        Fraccion b = new Fraccion(1, 3);
        System.out.println("1/2 + 1/3 = " + a.sumar(b));

        // Prueba 2: 3/4 = 0.75
        Fraccion c = new Fraccion(3, 4);
        System.out.println(c + " = " + c.aDecimal());

        // Prueba 3: 2/0 -> mensaje de validación
        try {
            new Fraccion(2, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("2/0 -> " + e.getMessage());
        }

        // Prueba 4: 0/5 -> no admite inversa
        try {
            new Fraccion(0, 5).obtenerInversa();
        } catch (ArithmeticException e) {
            System.out.println("0/5 -> " + e.getMessage());
        }

        // Prueba 5: 8/12 simplificada = 2/3
        System.out.println("8/12 simplificada = " + new Fraccion(8, 12));

        // Pruebas extra
        System.out.println("3/4 - 1/2 = " + c.restar(a));
        System.out.println("1/2 * 1/3 = " + a.multiplicar(b));
        System.out.println("1/2 / 1/3 = " + a.dividir(b));
        System.out.println("Inversa de 3/4 = " + c.obtenerInversa());
    }
}
