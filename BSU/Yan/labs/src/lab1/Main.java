package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Complex> complexNumbers = new ArrayList<>();

    static {
        Complex complex = new Complex();

        RationalFraction real = new RationalFraction(new ArrayList<>(), new ArrayList<>());
        real.getNumerator().add(new LinearFunction(2));
        real.getNumerator().add(new LinearFunction(1));
        real.getDenominator().add(new LinearFunction(1));

        RationalFraction img = new RationalFraction(new ArrayList<>(), new ArrayList<>());
        img.getNumerator().add(new LinearFunction(1));
        img.getDenominator().add(new LinearFunction(1));

        complex.setReal(real);
        complex.setImg(img);

        complexNumbers.add(complex);

        Complex complex2 = new Complex();

        RationalFraction real2 = new RationalFraction(new ArrayList<>(), new ArrayList<>());
        real2.getNumerator().add(new PowerFunction(2));
        real2.getNumerator().add(new LinearFunction(1));
        real2.getDenominator().add(new LinearFunction(2));

        RationalFraction img2 = new RationalFraction(new ArrayList<>(), new ArrayList<>());
        img2.getNumerator().add(new LinearFunction(1));
        img2.getDenominator().add(new LinearFunction(1));

        complex2.setReal(real2);
        complex2.setImg(img2);

        complexNumbers.add(complex2);
    }

    public static void main(String[] args) {
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running){
            System.out.println("Choose Action (Enter number):\n\t" +
                    "1: Add complex number\n\t" +
                    "2: Remove complex number\n\t" +
                    "3: Print all complex numbers\n\t" +
                    "4: Evaluate complex number\n\t" +
                    "5: Operations with complex numbers\n\t" +
                    "6: Exit");
            String cmd = input.next();
            switch (cmd){
                case "1":
                    Complex complex = new Complex();
                    complex.setReal(FractionUtils.createFunction("Creating fraction for real part\n"));
                    complex.setImg(FractionUtils.createFunction("Creating fraction for imaginary part\n"));
                    if (complex.getReal()==null||complex.getImg()==null){continue;}
                    complexNumbers.add(complex);
                    break;
                case "2":
                    printAll();
                    System.out.println("Enter index of number to remove:");
                    complexNumbers.remove(input.nextInt());
                case "3":
                    printAll();
                    break;
                case "4":
                    printAll();
                    System.out.println("Enter index of number to evaluate:");
                    int index = input.nextInt();
                    System.out.println("Enter argument value:");
                    double arg = input.nextDouble();
                    double real = complexNumbers.get(index).evaluateReal(arg);
                    double img = complexNumbers.get(index).evaluateImg(arg);
                    System.out.println(real+((img>0) ? " + " : " - ")+img+"i");
                    break;
                case "5":
                    System.out.println("Enter first number index:");
                    int i1 = input.nextInt();
                    System.out.println("Enter second number index:");
                    int i2 = input.nextInt();
                    System.out.println("Enter argument value");
                    double argument = input.nextDouble();
                    System.out.println("Choose operation:\n\t" +
                            "1: Add\n\t" +
                            "2: Multiply\n\t");
                    String choice = input.next();
                    switch (choice){
                        case "1":
                            System.out.println("result is: "+ComplexUtils.add(complexNumbers.get(i1), complexNumbers.get(i2), argument));
                            break;
                        case "2":
                            System.out.println("result is: "+ComplexUtils.mul(complexNumbers.get(i1), complexNumbers.get(i2), argument));
                            break;
                        default:
                            System.out.println("ERROR: Invalid command");
                            break;
                    }
                    break;
                case "6":
                    running = false;
                    break;
                default:
                    System.out.println("ERROR: Invalid command\n");
                    break;
            }
        }
    }

    private static void printAll() {
        for (int i = 0; i < complexNumbers.size(); i++) {
            System.out.println(i+": "+complexNumbers.get(i));
        }
    }
}



