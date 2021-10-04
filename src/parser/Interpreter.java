package parser;

import java.util.ArrayList;
import java.util.Stack;

import exceptions.IllegalEquationException;
import exceptions.IllegalOperatorException;
import symbols.MathSymbol;
import symbols.OperandSymbol;
import symbols.OperatorSymbol;

public class Interpreter {

    /**
     * Takes a string and parses it into an equation in reverse polish notation
     * @param source String to be parsed
     * @return Returns an ArrayList<MathSymbol> which contains the equation in reverse polish notation
     * @throws IllegalEquationException
     * @throws IllegalOperatorException
     */
    public static ArrayList<MathSymbol> parseEquation(String source) throws IllegalEquationException, IllegalOperatorException {
        ArrayList<MathSymbol> eqrpn = new ArrayList<MathSymbol>();
        Stack<OperatorSymbol> operatorStack = new Stack<OperatorSymbol>();
        String currentNum = "";
        for (int i = 0; i < source.length(); i++) {
            if ((currentNum.length() == 0 && source.charAt(i) == '-') || isNumChar(source.charAt(i))) {
                currentNum += source.charAt(i);
            } else {
                if (currentNum.length() == 0 || !containsDigit(currentNum)) {
                    throw new IllegalEquationException();
                }
                eqrpn.add(new OperandSymbol(currentNum));
                currentNum = "";
                OperatorSymbol currentSymbol;
                try {
                    currentSymbol = new OperatorSymbol(Character.toString(source.charAt(i)));
                } catch (IllegalOperatorException e) {
                    throw e;
                }
                while (!operatorStack.empty()) {
                    if (operatorStack.peek().getOperator().getPrecedence() < currentSymbol.getOperator().getPrecedence()) {
                        break;
                    } else {
                        eqrpn.add(operatorStack.pop());
                    }
                }
                operatorStack.push(currentSymbol);
            }
        }
        if (currentNum.length() > 0) {
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

    private static boolean containsDigit(String a) {
        String[] numChars = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        for (String numChar : numChars) {
            if (a.contains(numChar)) {
                return true;
            }
        }
        return false;
    }

}
