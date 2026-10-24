/*
 *@Author: Jose Camilo Merino Morocho
 *
 */
package edu.unl.cc.ape.domain;

import java.math.BigDecimal;

public class CuentaBancaria {

    // Datos de identificación de la cuenta y saldo disponible.
    private String numeroCuenta;
    private String nombreTitular;
    private double saldo;

    // El arreglo guarda hasta 100 movimientos; cantidad indica cuántos se han registrado.
    private Movimiento[] movimiento;
    private int cantidad;

    // Inicializa los datos de la cuenta y prepara el espacio para el historial.
    public CuentaBancaria(String numeroCuenta, String nombreTitular, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.nombreTitular = nombreTitular;
        this.saldo = saldo;
        movimiento = new Movimiento[100];
        cantidad = 0;
    }

    // Suma el monto al saldo y registra el depósito si el monto es válido
    // y todavía hay espacio en el historial.
    public boolean depositar(double monto) {
        if (Double.isFinite(monto) && monto > 0 && cantidad < movimiento.length) {
            saldo = saldo + monto;

            movimiento[cantidad] = new Movimiento(
                    "Depósito", monto, "Dinero depositado"
            );
            cantidad++;

            System.out.println("Depósito realizado correctamente.");
            return true;
        } else {
            if (cantidad >= movimiento.length) {
                System.out.println("No se puede realizar el depósito: el historial está lleno.");
            } else {
                System.out.println("El monto del depósito debe ser mayor que cero.");
            }
            return false;
        }
    }

    // Resta el monto si es positivo, hay saldo suficiente y queda espacio
    // para guardar el movimiento. Devuelve el monto retirado o cero si falla.
    public double retirar(double monto) {
        if (!Double.isFinite(monto) || monto <= 0) {
            System.out.println("El monto del retiro debe ser mayor que cero.");
            return 0;
        }
        if (monto > saldo) {
            System.out.println("Error: no se puede retirar más dinero del saldo disponible.");
            return 0;
        }
        if (cantidad >= movimiento.length) {
            System.out.println("No se puede realizar el retiro: el historial está lleno.");
            return 0;
        }

        saldo = saldo - monto;

        movimiento[cantidad] = new Movimiento(
                "Retiro", monto, "Dinero retirado"
        );
        cantidad++;

        System.out.println("Retiro realizado correctamente.");
        return monto;
    }

    // Devuelve el saldo actual sin modificar la cuenta.
    public double consultarSaldo() {
        return saldo;
    }

    public String consultarSaldoFormateado() {
        return formatearMonto(saldo);
    }

    // Prepara un texto con el número, el titular y el saldo de la cuenta.
    public String consultarDatos() {
        return "Número de cuenta: " + numeroCuenta
                + "\nTitular: " + nombreTitular
                + "\nSaldo: $" + consultarSaldoFormateado();
    }

    // Muestra los movimientos guardados, desde el primero hasta el último.
    public void consultarHistorial() {
        if (cantidad == 0) {
            System.out.println("No hay movimientos.");
        } else {
            BigDecimal totalDepositos = BigDecimal.ZERO;
            BigDecimal totalRetiros = BigDecimal.ZERO;
            System.out.println("Movimientos registrados:");
            for (int i = 0; i < cantidad; i++) {
                Movimiento actual = movimiento[i];
                System.out.println((i + 1) + ". " + actual);
                if ("Depósito".equals(actual.tipo)) {
                    totalDepositos = totalDepositos.add(BigDecimal.valueOf(actual.monto));
                } else {
                    totalRetiros = totalRetiros.add(BigDecimal.valueOf(actual.monto));
                }
            }
            System.out.println("----------------------------------------");
            System.out.println("Total depositado: +$" + formatearMonto(totalDepositos.doubleValue()));
            System.out.println("Total retirado: -$" + formatearMonto(totalRetiros.doubleValue()));
            System.out.println("Saldo disponible: $" + consultarSaldoFormateado());
        }
    }

    // Representa un movimiento individual del historial de la cuenta.
    private static class Movimiento {

        private String tipo;
        private double monto;
        private String descripcion;

        public Movimiento(String tipo, double monto, String descripcion) {
            this.tipo = tipo;
            this.monto = monto;
            this.descripcion = descripcion;
        }

        @Override
        public String toString() {
            String montoFormateado = formatearMonto(monto);
            return "Depósito".equals(tipo)
                    ? tipo + ": +" + montoFormateado
                    : tipo + ": " + montoFormateado;
        }
    }

    private static String formatearMonto(double monto) {
        return BigDecimal.valueOf(monto).stripTrailingZeros().toPlainString();
    }
}
