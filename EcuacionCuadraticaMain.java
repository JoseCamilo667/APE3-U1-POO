package edu.unl.cc.domain;

import edu.unl.cc.domain.EcuacionCuadratica;
import java.util.Scanner;

public class MainEcuacionInteractivo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EcuacionCuadratica ecuacion = null;
        int opcion = 0;

        do {
            System.out.println("\n==================================");
            System.out.println("   MENÚ DE ECUACIÓN CUADRÁTICA");
            System.out.println("==================================");
            System.out.println("1. Ingresar coeficientes (a, b, c)");
            System.out.println("2. Calcular y mostrar las raíces");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            } else {
                System.out.println("Error: Por favor, ingrese un número entero válido.");
                scanner.next(); // Limpiar buffer
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el coeficiente a: ");
                    double a = scanner.nextDouble();

                    System.out.print("Ingrese el coeficiente b: ");
                    double b = scanner.nextDouble();

                    System.out.print("Ingrese el coeficiente c: ");
                    double c = scanner.nextDouble();

                    try {
                        ecuacion = new EcuacionCuadratica(a, b, c);
                        System.out.println("\nEcuación guardada con éxito: (" + a + ")x² + (" + b + ")x + (" + c + ") = 0");
                    } catch (IllegalArgumentException e) {
                        System.out.println("\n" + e.getMessage());
                        ecuacion = null;
                    }
                    break;

                case 2:
                    if (ecuacion == null) {
                        System.out.println("\nError: Primero debe ingresar coeficientes válidos (Opción 1).");
                    } else {
                        System.out.println("\nDiscriminante: " + ecuacion.calcularDiscriminante());
                        ecuacion.mostrarRaices();
                    }
                    break;

                case 3:
                    System.out.println("\n¡Gracias por utilizar el programa! Saliendo...");
                    break;

                default:
                    System.out.println("\nOpción no válida. Por favor, seleccione entre 1 y 3.");
            }

        } while (opcion != 3);

        scanner.close();
    }
}
