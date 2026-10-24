package edu.unl.ec.ape.view.Cuenta;
import edu.unl.ec.ape.domain.PlanillaElectrica;

public class ExecutorPlanillaElectrica {
    public static void main(String[] args){
        //Crear objeto PlanillaElectrica
        try {
            PlanillaElectrica planilla = new PlanillaElectrica(1012352,"Camila Beltran", 1200, 0);
            System.out.println(planilla);
        }catch (IllegalArgumentException e){
            System. out.println("ERROR: "+  e.getMessage());
        }
    }
}
