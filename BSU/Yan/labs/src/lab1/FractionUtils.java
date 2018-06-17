package lab1;

import java.util.ArrayList;
import java.util.Scanner;

public class FractionUtils {
    public static RationalFraction createFunction(String message) {
        RationalFraction fraction = new RationalFraction(new ArrayList<>(), new ArrayList<>());
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running){
            System.out.println("Chose action:\n\t" +
                    "1: Add function to numerator\n\t" +
                    "2: Add function to denominator\n\t" +
                    "3: Cancel\n\t" +
                    "4: Confirm");
            String cmd = scanner.next();
            switch (cmd){
                case "1":
                    fraction.getNumerator().add(FunctionUtils.createFunction("Create function for numerator\n"));
                    break;
                case "2":
                    fraction.getDenominator().add(FunctionUtils.createFunction("Create function for denominator\n"));
                    break;
                case "3":
                    fraction.setNumerator(new ArrayList<>());
                    fraction.setDenominator(new ArrayList<>());
                    running = false;
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("ERROR: Invalid command");
                    break;
            }
        }
        return (fraction.getDenominator().size()==0) ? null : (fraction.getNumerator().size()==0) ? null : fraction;
    }
}
