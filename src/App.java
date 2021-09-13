import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DivideByZeroException;
import exceptions.IllegalEquationException;
import exceptions.IllegalFactorialException;
import exceptions.IllegalSymbolException;
import parsers.Interpreter;

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
                ArrayList<String> parsedEq = Interpreter.parseEquation(input);
                try {
                    System.out.println(Solver.solve(parsedEq, 0, parsedEq.size()));
                } catch (DivideByZeroException e) {
                    System.out.println(e.getMessage());
                } catch (IllegalFactorialException e) {
                    System.out.println(e.getMessage());
                }
            } catch (IllegalEquationException e) {
                System.out.println(e.getMessage());
            } catch (IllegalSymbolException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
        sc.close();
    }

}
