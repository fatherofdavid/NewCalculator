package com.annapolisWorks.calculator6;

import java.util.Scanner;

//this is alternate utility from CalculatorUIManager, that uses the command line instead of a GUI
//it was used in production but is not obsolete
//code is retained for learning/example
public class CalculatorUIManagerCmd {

    void publishToUser(boolean isError, String finalAnswer){
        if(!isError) System.out.println("The final answer is: "+ finalAnswer);
        else System.out.println("Error: " + finalAnswer);
    }

    String askUser(){
        System.out.println("Please type in an equation.");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        return str;
    }
}

