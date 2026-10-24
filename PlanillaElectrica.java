package edu.unl.ec.ape.domain;
/**
 * @author Camila Beltran R.
 * Panilla de consumo electrico
 */

public class PlanillaElectrica {
    //Declaracion de todos los atributos de la clasepackage edu.unl.ec.ape.view.Cuenta;
    //import edu.unl.ec.ape.domain.PlanillaElectrica;
    //
    //import java.util.Scanner;
    //
    //public class Executor {
    //    public static void main(String[] args){
    //        //Crear objeto PlanillaElectrica
    //        try {
    //            PlanillaElectrica planilla = new PlanillaElectrica(1012352,"Camila B", 1200, 50);
    //            System.out.println(planilla);
    //        }catch (IllegalArgumentException e){
    //            System. out.println();
    //        }
    //    }
    //}

    private int numeroMedidor;
    private String nombreTitular;
    private double lecturaAnterior;
    private double lecturaActual;

    // Constructor con validación
    public PlanillaElectrica(int numeroMedidor, String nombreTitular, double lecturaAnterior, double lecturaActual) {
        validarLectura(lecturaActual, lecturaAnterior);
        this.numeroMedidor = numeroMedidor;
        this.nombreTitular = nombreTitular;
        this.lecturaAnterior = lecturaAnterior;
        this.lecturaActual = lecturaActual;
    }

    public void validarLectura(double lecturaActual, double lecturaAnterior){
        if (lecturaActual < lecturaAnterior){
            throw new IllegalArgumentException("LA LECTURA ACTUAL TIENE QUE SER MAYOR O IGUAL QUE LA LECTURA ANTERIOR");
        }
    }
    //Calcular el consumo del periodo en Kwh
    public Double calcularConsumo(){
        return this.lecturaActual-this.lecturaAnterior;
    }
    //Calcular el valor a pagar por bloques
    public double calcularSubtotal(){
        double consumo = calcularConsumo();
        double subtotal = 0;
        if(consumo <= 0){
            return 0;
        }
        if(consumo <=100){
            subtotal = consumo * 0.10;
        }else if(consumo <=200){
            //Los primeros 100 a 0.10 + los otros hasta 200 a 0.15
            subtotal = (100*0.10) + ((consumo -100)*0.15);
        }else{
            //los primeros 100 a 0.10 + los otros 100 a 0.15 + los superiores a 200 a 0.20
            subtotal = (100*0.10)+(100*0.15)+((consumo -200)*0.20);
        }
        return subtotal;
    }
    //Aplicar el IVA del 15%
    public double aplciarIva(){
        double subtotal = calcularSubtotal();
        Double iva = subtotal * 0.15;
        return iva = Math.round(iva * 100.0)/100.0;
    }
    //Calcular el total a pagar
    public double calcularTotal(){
        double subtotal = calcularSubtotal();
        double iva = aplciarIva();
        return subtotal + iva;
    }

    //Imprimir
    @Override
    public String toString(){
        return "---PLANILLA DE CONSUMO ELECTRICO---"+
                "\n Numero de medidor: " + numeroMedidor +
                "\n Nombre del titular: "+nombreTitular+
                "\n Lectura Anterior: "+lecturaAnterior+" kWh"+
                "\n Lectura Actual: "+lecturaActual+" kWh"+
                "\n Consumo del Periodo: "+calcularConsumo() +" kWh"+
                "\n Subtotal: "+calcularSubtotal()+" $"+
                "\n Iva (15%): "+aplciarIva()+" $"+
                "\n TOTAL A PAGAR: "+calcularTotal()+" $";
    }
    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public int getNumeroMedidor() {
        return numeroMedidor;
    }

    public void setNumeroMedidor(int numeroMedidor) {
        this.numeroMedidor = numeroMedidor;
    }

    public double getLecturaAnterior() {
        return lecturaAnterior;
    }

    public void setLecturaAnterior(double lecturaAnterior) {
        validarLectura(lecturaActual, lecturaAnterior);
        this.lecturaAnterior = lecturaAnterior;
    }

    public double getLecturaActual() {
        return lecturaActual;
    }

    public void setLecturaActual(double lecturaActual) {
        this.lecturaActual = lecturaActual;
    }
}
