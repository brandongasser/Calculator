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
        source = source.replaceAll(" ", "").replaceAll(",", "").toLowerCase();
        ArrayList<String> parsedEq = new ArrayList<String>();
        while (source.length() > 0) {
            if (!Checker.legalSymbol(source.charAt(0))) {
                throw new IllegalSymbolException("Error: Illegal Symbol");
            }
            source = source.substring(1);
        }
        if (!Checker.check(parsedEq)) {
            throw new IllegalEquationException("Error: Illegal Equation");
        }
        return parsedEq;
    }

    private static boolean isNumber(char a) {
        String number = "0123456789.";
        return number.contains(Character.toString(a));
    }

}
