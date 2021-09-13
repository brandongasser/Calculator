package parsers;

import java.util.ArrayList;

import exceptions.IllegalEquationException;
import exceptions.IllegalSymbolException;

public class Interpreter {

    /**
     * Takes a string and converts it into a parsed equation
     * @param source String to be parsed
     * @return Returns an ArrayList<String> containing the separated parts of the equation
     * @throws IllegalEquationException
     * @throws IllegalSymbolException
     */
    public static ArrayList<String> parseEquation(String source) throws IllegalEquationException, IllegalSymbolException {
        source = source.replaceAll(" ", "").toLowerCase();
        ArrayList<String> parsedEq = new ArrayList<String>();
        String currentNum = "";
        while (source.length() > 0) {
            if (isOperator(Character.toString(source.charAt(0))) || isParentheses(Character.toString(source.charAt(0)))
                    || source.charAt(0) == '-') {
                if (source.charAt(0) == '-') {
                    if (!currentNum.equals("")) {
                        parsedEq.add(currentNum);
                        parsedEq.add("+");
                        currentNum = "-";
                    } else if (parsedEq.get(parsedEq.size() - 1).equals(")")) {
                        parsedEq.add("+");
                        currentNum += "-";
                    } else {
                        currentNum += "-";
                    }
                } else {
                    parsedEq.add(Character.toString(source.charAt(0)));
                }
            } else if (isNumber(source.charAt(0))) {
                currentNum += source.charAt(0);
                try {
                    if (!isNumber(source.charAt(1))) {
                        parsedEq.add(currentNum);
                        currentNum = "";
                    }
                } catch (Exception e) {
                    parsedEq.add(currentNum);
                }
            } else {
                throw new IllegalEquationException("Error: Illegal Equation");
            }
            source = source.substring(1);
        }
        if (!isLegal(parsedEq)) {
            throw new IllegalEquationException("Error: Illegal Equation");
        }
        return parsedEq;
    }

    private static boolean isLegal(ArrayList<String> eq) {
        if (!(eq.get(0).equals("(") || legalNumber(eq.get(0)))) {
            return false;
        }
        if (!(eq.get(eq.size() - 1).equals(")") || legalNumber(eq.get(eq.size() - 1))
                || eq.get(eq.size() - 1).equals("!"))) {
            return false;
        }
        int parenCount = 0;
        for (int i = 0; i < eq.size(); i++) {
            if (isOperator(eq.get(i))) {
                if (eq.get(i).equals("!")) {
                    if (!legalNumber(eq.get(i - 1)) && !eq.get(i - 1).equals(")")) {
                        return false;
                    }
                } else if (!(legalNumber(eq.get(i - 1)) || eq.get(i - 1).equals(")"))
                        || !(legalNumber(eq.get(i + 1)) || eq.get(i + 1).equals("("))) {
                    return false;
                }
            } else if (eq.get(i).equals("(")) {
                parenCount++;
            } else if (eq.get(i).equals(")")) {
                parenCount--;
            } else if (isNumber(eq.get(i).charAt(0))) {
                if (!legalNumber(eq.get(i))) {
                    System.out.println("entered here");
                    return false;
                }
            }
            if (parenCount < 0) {
                return false;
            }
        }
        return (parenCount == 0) ? true : false;
    }

    private static boolean legalNumber(String str) {
        byte numDot = 0, numInt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                numDot++;
            } else if (str.charAt(i) == '-' && i != 0) {
                return false;
            } else {
                numInt++;
            }
        }
        return (numDot <= 1 && numInt > 0);
    }

    private static boolean isNumber(char a) {
        return (a >= 48 && a <= 57 || a == '.' || a == '-');
    }

    private static boolean isOperator(String a) {
        String operators = "+/*^!";
        return (operators.contains(a));
    }

    private static boolean isParentheses(String a) {
        return (a.equals("(") || a.equals(")"));
    }

}
