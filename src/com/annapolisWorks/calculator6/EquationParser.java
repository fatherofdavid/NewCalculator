package com.annapolisWorks.calculator6;

import java.util.LinkedList;


public class EquationParser {
    private String equation;
    public EquationParser(String equation){
        this.equation = equation;
    }

    private boolean decimalAlreadyFound;
    private double num1;
    private double num2;
    private StringBuilder sb;
    private LinkedList<OperatorNode> nodeChain;

    public LinkedList<OperatorNode> parse() throws ParserException {
        //creates a LinkedList of nodes and returns the list
        // first node to append will be '0 + ...', to ensure at least 1 node is created
        try{
            num2 = 0;
            char op = '+';
            equation = removeSpaces(equation);
            sb = new StringBuilder();
            nodeChain = new LinkedList<OperatorNode>();

            char c;
            decimalAlreadyFound = false;
            for(int i =0; i < equation.length(); i++){
                c = equation.charAt(i);

                //found new number
                if(Character.isDigit(c)) sb.append(c);
                else if(c == '.'){
                    if(!decimalAlreadyFound) {
                        sb.append(c);
                        decimalAlreadyFound = true;
                    }
                    else throw new ParserException("The eqn has an error with the decimal points.");
                }

                //found new parenthetical block
                else if(c == '('){
                    //convert a(b) into a*(b)
                    if(sb.length() != 0){
                        newOpFound(op, sb.toString());
                        op = '*';
                    }

                    i++;
                    int parenDepth = 1;
                    while(i < equation.length()){
                        c = equation.charAt(i);
                        if(c == ')') parenDepth--;
                        else if (c == '(') parenDepth++;
                        if(parenDepth == 0){
                            break;
                        }
                        else sb.append(c);
                        i++;
                    }
                    if(i == equation.length() && parenDepth != 0) {
                        throw new ParserException("The parentheses are unbalanced.");
                    }
                    if(sb.length() == 0) {
                        throw new ParserException("The eqn has a blank parenthetical block.");
                    }

                    //create a new engine to reduce the parenthetical block to a number
                    CalculatorEngine parenEngine = new CalculatorEngine(sb.toString());
                    sb = new StringBuilder(Double.toString(parenEngine.solve()));

                    //convert (a)b into (a)*b
                    if(i + 1 < equation.length()){
                        if(!Operator.operatorIsSupporter(equation.charAt(i + 1))) {
                            equation = equation.substring(0, i+1) + "*" + equation.substring(i + 1);
                        }
                    }
                }

                //found new Operator
                else if(Operator.operatorIsSupporter(c)){
                    //special case for '-' used to show a negative number
                    if(sb.length() == 0 && c == '-') {
                        sb.append(c);
                    }
                    else if(sb.length() > 0 && Character.isDigit(sb.charAt(0))){
                            newOpFound(op, sb.toString());
                            op = c; //pass in new operator for next iteration
                    }
                    //special provision for "-1"
                    else if(sb.length() > 1) {
                        newOpFound(op, sb.toString());
                        op = c; //pass in new operator for next iteration
                    }
                    else{
                        throw new ParserException("Unable to parse the eqn due to consecutive or leading operators.");
                    }
                }
                else {
                    throw new ParserException("Unable to parse the eqn due to character: '"+c+"'.");
                }
            }
            //add last node
            if(sb.length() != 0) newOpFound(op, sb.toString());
            else throw new ParserException("Unable to parse the eqn.");
            return nodeChain;
        }
        catch(ParserException e){
            throw new ParserException(e.getMessage());
        }
    }

    private void newOpFound(char op, String str){
        num1 = num2;
        num2 = Double.valueOf(str);
        addNode(op); //add last node
        decimalAlreadyFound = false;
        sb.setLength(0);
    }


    private String removeSpaces(String str){
        StringBuilder sb = new StringBuilder();
        for (char c: str.toCharArray()) {
            if (!Character.isSpaceChar(c)) sb.append(c);
        }
        return sb.toString();
        }

    private void addNode(char newOpChar) {
        Operator operator;
        OperatorNode newNode =  null;
        for (Operator op_k:Operator.values()) {
            if (newOpChar == op_k.opChar) {
                operator = op_k;
                newNode = new OperatorNode(operator, num1, num2);
            }
        }
        nodeChain.add(newNode);
    }
}

class ParserException extends Throwable{
    public ParserException(String message) {
        super(message);
    }
}