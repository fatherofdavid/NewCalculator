package com.annapolisWorks.calculator6;

public class Calculator6Main {

    public static void main(String[] args){

        CalculatorUIManager UI_Mgr = new CalculatorUIManager();
    }

    public static void run(CalculatorUIManager UI_Mgr){
        String userEqn;
        userEqn = UI_Mgr.askUser();

        //instantiate and call the solver
        CalculatorEngine myCalc = new CalculatorEngine(userEqn);

        double solution;
        try{
            solution = myCalc.solve();

            //following block cleans integers to print without trailing decimal
            double checkInt = solution % 1;
            int intSolution;
            if(checkInt == 0) {
                Double dbl = new Double(solution);
                 intSolution = dbl.intValue();
                UI_Mgr.publishToUser(false, "" + intSolution);
            }
            else {
                UI_Mgr.publishToUser(false, "" + solution);
            }
        }
        catch(ParserException e){
            UI_Mgr.publishToUser(true, e.getMessage());
        }
    }
}
