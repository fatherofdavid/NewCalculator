package com.annapolisWorks.calculator6;

    //a node is an operator (e.g. '+') and the numbers on either side of it.
    public class OperatorNode {
        public Operator myOperator;
        public double num1;
        public double num2;
        //should I make these private and use getters and setters?

        public OperatorNode(Operator myOperator, double num1, double num2) {
            this.myOperator = myOperator;
            this.num1 = num1;
            this.num2 = num2;
        }

        double collapse(){
            return myOperator.doOperation(num1,num2);
        }
    }
