package lab1;

import java.util.Scanner;

public class FunctionUtils {
    public static MathFunction createFunction(String message) {
        MathFunction function = (x) -> 0;
        System.out.println(message);
        System.out.println("Choose function type:\n\t" +
                "1: Linear (kX)\n\t" +
                "2: Power (X^n)\n\t" +
                "3: Primitive (n)");
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.next();
        switch (cmd){
            case "1":
                try {
                    System.out.println("Enter \"k\" value:");
                    double k = scanner.nextDouble();
                    function = new LinearFunction(k);
                    System.out.println("Linear function "+k+"x will be used.");
                } catch (Exception e){
                    System.out.println("Illegal argument. Primitive 0 will be used");
                }
                break;
            case "2":
                try {
                    System.out.println("Enter \"n\" value:");
                    double n = scanner.nextDouble();
                    function = new PowerFunction(n);
                    System.out.println("Power function X^"+n+" will be used.");
                } catch (Exception e){
                    System.out.println("Illegal argument. Primitive 0 will be used");
                }
                break;
            case "3":
                try {
                    System.out.println("Enter \"n\" value:");
                    double n = scanner.nextDouble();
                    function = new PrimitiveFunction(n);
                    System.out.println("Primitive function "+n+" will be used.");
                } catch (Exception e){
                    System.out.println("Illegal argument. Primitive 0 will be used");
                }
                break;
            default:
                System.out.println("ERROR: Invalid command. Primitive (0) will be used");
                break;
        }
        return function;
    }
}
