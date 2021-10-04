import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DivideByZeroException;
import exceptions.IllegalEquationException;
import exceptions.IllegalOperatorException;
import exceptions.IllegalParenthesesException;
import parser.Interpreter;
import solver.Solver;
import symbols.MathSymbol;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine().replace(" ", "");
            if (input.toLowerCase().equals("quit")) {
                System.out.println("Quitting");
                break;
            }
            try {
                ArrayList<MathSymbol> eqrpn = Interpreter.parseEquation(input);
                for (MathSymbol symbol : eqrpn) {
                    System.out.print(symbol.toString() + ", ");
                }
                System.out.println();
                DecimalFormat outputFormat = new DecimalFormat("#0.##########");
                System.out.println(outputFormat.format(Solver.solve(eqrpn).doubleValue()));
            } catch (IllegalEquationException e) {
                System.out.println("Error: Illegal Equation");
            } catch (IllegalOperatorException e) {
                System.out.println("Error: Illegal Operator");
            } catch (DivideByZeroException e) {
                System.out.println("Error: Divide by 0");
            } catch (IllegalParenthesesException e) {
                System.out.println("Error: Illegal Parentheses");
            }
            System.out.println();
        }
        sc.close();
    }

}
