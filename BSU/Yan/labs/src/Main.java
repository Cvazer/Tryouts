import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Франковский Ян константинович\nЛабораторная работа № 1-2-3");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Lab1\n2: Lab2\n3: Lab3\n");
        String lab = new Scanner(System.in).nextLine();
        switch (lab){
            case "1":
                lab1.Main.exec(args);
                break;
            case "2":
                lab2.Main.exec(args);
                break;
            case "3":
                lab3.Main.exec(args);
                break;
            default:
                break;
        }
    }
}
