import java.util.ArrayList;
import java.util.Scanner;

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
            ArrayList<MathSymbol> eqrpn = Interpreter.parseEquation(input);
            System.out.println(Solver.solve(eqrpn).toString());
            System.out.println();
        }
        sc.close();
    }

}
