import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DivideByZeroException;
import exceptions.IllegalEquationException;
import exceptions.IllegalFactorialException;
import parsers.Interpreter;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Interpreter inter = new Interpreter();
        Solver solver = new Solver();
        while (true) {
            String input = sc.nextLine();
            if (input.toLowerCase().equals("quit")) {
                System.out.println("Quitting");
                break;
            }
            try {
                ArrayList<String> parsedEq = inter.parseEquation(input);
                try {
                    System.out.println(solver.solve(parsedEq, 0, parsedEq.size()));
                } catch (DivideByZeroException e) {
                    System.out.println(e.getMessage());
                } catch (IllegalFactorialException e) {
                    System.out.println(e.getMessage());
                }
            } catch (IllegalEquationException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
        sc.close();
    }

}
