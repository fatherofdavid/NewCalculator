package com.annapolisWorks.calculator6;

import java.util.LinkedList;
import java.util.ListIterator;

public class CalculatorEngine {
    private String equation;
    CalculatorEngine(String str){
        equation = str;
    }

    public double solve() throws ParserException {
        int highestPriority ;

        EquationParser eqnParser = new EquationParser(equation);
        LinkedList<OperatorNode> nodeChain;
        try {
            nodeChain = eqnParser.parse();
        }
        catch(ParserException e) {
            throw new ParserException(e.getMessage());
        }

        double calculatedValue = 0d;

        while(!nodeChain.isEmpty()) {
            //find the highest priority node in the chain
            highestPriority = 1;
            for (OperatorNode opNode : nodeChain) {
                if (opNode.myOperator.priority > highestPriority) {
                    highestPriority = opNode.myOperator.priority;
                }
            }

            //go left to right and collapse first node matching highest priority
            ListIterator i = nodeChain.listIterator();
            OperatorNode opNode;
            while (i.hasNext()) {
                opNode = (OperatorNode) i.next();
                if (opNode.myOperator.priority == highestPriority) {
                    calculatedValue = opNode.collapse();
                    //move new value into adjacent nodes
                    if(i.previousIndex() > 0){
                        nodeChain.get(i.previousIndex() - 1).num2 = calculatedValue;
                    }
                    if(i.hasNext()) {
                        nodeChain.get(i.nextIndex()).num1 = calculatedValue;
                    }
                    nodeChain.remove(opNode);
                    break;
                }
            }
        }
        return calculatedValue;
    }
}
