package com.annapolisWorks.calculator6;

//do not set any priority less than 1 without updating the CalculatorEngine starting low priority
public enum Operator {
    ADD('+',1) {
        @Override public double doOperation(double n1, double n2){
        return n1 + n2;}
    },
    SUBTRACT('-',1){
        @Override public double doOperation(double n1, double n2){
            return n1 - n2;}
    },
    MULTIPLY('*',2){
        @Override public double doOperation(double n1, double n2){
            return n1 * n2;}
    },
    DIVIDE('/',2){
        @Override public double doOperation(double n1, double n2){
            return n1 / n2;}
    },
    EXPONENT('^',3){
        @Override public double doOperation(double n1, double n2){
            return Math.pow(n1,n2);}
    };

    public int priority;
    public char opChar;
    private Operator(char myChar, int myPriority){
        opChar = myChar;
        priority = myPriority;
    }

    public double doOperation(double n1, double n2){
        throw new RuntimeException("unsupported operator");
    }

    public static boolean operatorIsSupporter(char c){
        for (Operator op_k:Operator.values()) {
            if (c == op_k.opChar) return true;
        }
        return false;
    }

}
