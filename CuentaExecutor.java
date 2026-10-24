package edu.unl.cc.ape.view;

import edu.unl.cc.ape.domain.CuentaBancaria;
import java.math.BigDecimal;
import java.util.Scanner;

// Controla la interacción entre el usuario y las operaciones de la cuenta.
public class CuentaExecutor {

    private CuentaBancaria cuenta;
    private final Scanner teclado = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenuPrincipal();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    crearCuenta();
                    break;

                case 2:
                    gestionarDeposito();
                    break;

                case 3:
                    gestionarRetiro();
                    break;

                case 4:
                    consultarCuenta();
                    break;

                case 5:
                    consultarHistorial();
                    break;

                case 0:
                    salir();
                    break;
                default:
                    System.out.println("\nOpción no válida. Seleccione un número del 0 al 5.");
            }
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n==================================================");
        System.out.println("              BANCA - MENU PRINCIPAL");
        System.out.println("==================================================");
        if (cuenta == null) {
            System.out.println("  Estado: No hay una cuenta creada");
        } else {
            System.out.println("  Saldo disponible: $" + cuenta.consultarSaldoFormateado());
        }
        System.out.println("--------------------------------------------------");
        System.out.println("  1  Crear cuenta");
        System.out.println("  2  Depositar dinero");
        System.out.println("  3  Retirar dinero");
        System.out.println("  4  Consultar cuenta");
        System.out.println("  5  Ver historial");
        System.out.println("  0  Salir");
        System.out.println("--------------------------------------------------");
    }

    private int leerOpcion() {
        while (true) {
            System.out.print("  Seleccione una opción: ");
            try {
                int opcion = Integer.parseInt(teclado.nextLine().trim());
                if (opcion >= 0 && opcion <= 5) {
                    return opcion;
                }
            } catch (NumberFormatException ignored) {
                // Se muestra una indicación clara y se vuelve a solicitar la opción.
            }
            System.out.println("  Entrada no válida. Ingrese un número del 0 al 5.");
        }
    }

    public void crearCuenta() {
        mostrarTitulo("CREAR CUENTA");
        String numero = leerTextoObligatorio("Número de cuenta: ");
        String titular = leerTextoObligatorio("Nombre del titular: ");
        double saldo = leerMonto("Saldo inicial: $", true);

        cuenta = new CuentaBancaria(numero, titular, saldo);
        System.out.println("\n  Cuenta creada correctamente.");
        System.out.println("  Saldo inicial: $" + cuenta.consultarSaldoFormateado());
    }

    public void gestionarDeposito() {
        if (cuentaNoDisponible()) {
            return;
        }
        mostrarTitulo("DEPOSITAR DINERO");
        double monto = leerMonto("Monto a depositar: $", false);
        if (cuenta.depositar(monto)) {
            System.out.println("  Monto depositado: +$" + formatearMonto(monto));
        }
        mostrarSaldoActual();
    }

    public void gestionarRetiro() {
        if (cuentaNoDisponible()) {
            return;
        }
        mostrarTitulo("RETIRAR DINERO");
        double monto = leerMonto("Monto a retirar: $", false);
        if (cuenta.retirar(monto) > 0) {
            System.out.println("  Monto retirado: -$" + formatearMonto(monto));
        }
        mostrarSaldoActual();
    }

    public void consultarCuenta() {
        if (cuentaNoDisponible()) {
            return;
        }
        mostrarTitulo("DATOS DE LA CUENTA");
        System.out.println(cuenta.consultarDatos());
    }

    public void consultarHistorial() {
        if (cuentaNoDisponible()) {
            return;
        }
        mostrarTitulo("HISTORIAL DE MOVIMIENTOS");
        cuenta.consultarHistorial();
    }

    public void salir() {
        System.out.println("\n==================================================");
        System.out.println("  Gracias por utilizar Banca.");
        System.out.println("  ¡Que tenga un buen día!");
        System.out.println("==================================================");
    }

    private void mostrarTitulo(String titulo) {
        System.out.println("\n---------------- " + titulo + " ----------------");
    }

    private boolean cuentaNoDisponible() {
        if (cuenta == null) {
            System.out.println("\n  No hay una cuenta creada. Seleccione «Crear cuenta» primero.");
            return true;
        }
        return false;
    }

    private void mostrarSaldoActual() {
        System.out.println("  Saldo disponible: $" + cuenta.consultarSaldoFormateado());
    }

    private String formatearMonto(double monto) {
        return BigDecimal.valueOf(monto).stripTrailingZeros().toPlainString();
    }

    private String leerTextoObligatorio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = teclado.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("  Este dato no puede quedar vacío.");
        }
    }

    private double leerMonto(String mensaje, boolean permitirCero) {
        while (true) {
            System.out.print(mensaje);
            try {
                double monto = Double.parseDouble(teclado.nextLine().trim());
                if (Double.isFinite(monto) && (permitirCero ? monto >= 0 : monto > 0)) {
                    return monto;
                }
            } catch (NumberFormatException ignored) {
                // Se solicita nuevamente el monto si la entrada no es numérica.
            }
            System.out.println(permitirCero
                    ? "  Ingrese un monto válido (cero o mayor)."
                    : "  Ingrese un monto válido mayor que cero.");
        }
    }

    public static void main(String[] args) {
        CuentaExecutor programa = new CuentaExecutor();
        programa.mostrarMenu();
    }
}
