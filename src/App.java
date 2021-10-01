import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DivideByZeroException;
import exceptions.IllegalEquationException;
import exceptions.IllegalOperatorException;
import parser.Interpreter;
import solver.Solver;
import symbols.MathSymbol;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.toLowerCase().equals("quit")) {
                System.out.println("Quitting");
                break;
            }
            try {
                ArrayList<MathSymbol> eqrpn = Interpreter.parseEquation(input);
                System.out.println(Solver.solve(eqrpn).toString());
            } catch (IllegalEquationException e) {
                System.out.println("Error: Illegal Equation");
            } catch (IllegalOperatorException e) {
                System.out.println("Error: Illegal Operator");
            } catch (DivideByZeroException e) {
                System.out.println("Error: Divide by 0");
            }
            System.out.println();
        }
        sc.close();
    }

}
