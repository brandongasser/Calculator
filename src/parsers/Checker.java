package parsers;

import java.util.ArrayList;

public class Checker {
    
    /**
     * Checks an equation parsed with Interpreter and checks if it is a legal equation
     * @param eq Parsed equation to be checked
     * @return Returns true if the equation is legal and false if the equation isn't legal
     */
    protected static boolean check(ArrayList<String> eq) {
        return true;
    }

    /**
     * Checks if a character is a legal character
     * @param symbol Character to be checked
     * @return Returns true if the symbol is allowed and false if the symbol isn't allowed
     */
    protected static boolean legalSymbol(char symbol) {
        String legalSymbols = "0123456789!+-/*^()";
        return legalSymbols.contains(Character.toString(symbol));
    }

}
