package parser;

import java.util.ArrayList;
import java.util.Stack;

import exceptions.IllegalOperatorException;
import exceptions.IllegalParenthesesException;
import symbols.MathSymbol;
import symbols.OperandSymbol;
import symbols.Operator;
import symbols.OperatorSymbol;

public class Interpreter {

    /**
     * Takes a string and parses it into an equation in reverse polish notation
     * 
     * @param source String to be parsed
     * @return Returns an ArrayList<MathSymbol> which contains the equation in
     *         reverse polish notation
     * @throws IllegalParenthesesException
     * @throws IllegalOperatorException
     */
    public static ArrayList<MathSymbol> parseEquation(String source)
            throws IllegalParenthesesException, IllegalOperatorException {
        ArrayList<MathSymbol> eqrpn = new ArrayList<MathSymbol>();
        Stack<OperatorSymbol> operatorStack = new Stack<OperatorSymbol>();
        String currentNum = "";
        for (int i = 0; i < source.length(); i++) {
            if ((currentNum.length() == 0 && source.charAt(i) == '-') || isNumChar(source.charAt(i))) {
                currentNum += source.charAt(i);
            } else {
                try {
                    eqrpn.add(new OperandSymbol(currentNum));
                } catch (NumberFormatException e) {
                }
                currentNum = "";
                OperatorSymbol currentSymbol;
                try {
                    currentSymbol = new OperatorSymbol(Character.toString(source.charAt(i)));
                } catch (IllegalOperatorException e) {
                    throw e;
                }
                if (currentSymbol.getOperator() == Operator.OPEN_PAREN) {
                    operatorStack.push(currentSymbol);
                } else if (currentSymbol.getOperator() == Operator.CLOSE_PAREN) {
                    while (true) {
                        if (operatorStack.empty()) {
                            throw new IllegalParenthesesException();
                        }
                        if (operatorStack.peek().getOperator() != Operator.OPEN_PAREN) {
                            eqrpn.add(operatorStack.pop());
                        } else {
                            operatorStack.pop();
                            eqrpn.add(operatorStack.pop());
                            currentNum = " ";
                            break;
                        }
                    }
                } else {
                    while (!operatorStack.empty()) {
                        if (operatorStack.peek().getOperator().getPrecedence() < currentSymbol.getOperator()
                                .getPrecedence()) {
                            break;
                        } else {
                            eqrpn.add(operatorStack.pop());
                        }
                    }
                    operatorStack.push(currentSymbol);
                }
            }
        }
        if (currentNum.replace(" ", "").length() > 0) {
            eqrpn.add(new OperandSymbol(currentNum));
        }
        while (!operatorStack.empty()) {
            eqrpn.add(operatorStack.pop());
        }
        return eqrpn;
    }

    private static boolean isNumChar(char a) {
        String numChars = "1234567890.";
        return numChars.contains(Character.toString(a));
    }

}
