package lab2;

import java.util.*;

public class Main {
    private static List<Plane> planes = new ArrayList<>();

    static {
        planes.add(new Plane("AB202", new Wings(), new Engine(), new LandingGear(), new ArrayList<>()));
        planes.get(0).getPath().addAll(Arrays.asList("Москва", "Минск", "Москва"));
    }

    public static void exec(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while (running){
            System.out.println("COMMANDS:\n\t" +
                    "1: Add plane\n\t" +
                    "2: Print all planes\n\t" +
                    "3: Add path point to plane\n\t" +
                    "4: Schedule flight for plane" +
                    "5: Exit");
            String cmd = scanner.next();
            switch (cmd){
                case "1":
                    System.out.println("Enter new plane name:");
                    String name = new Scanner(System.in).nextLine();
                    planes.add(new Plane(name, new Wings(), new Engine(), new LandingGear(), new ArrayList<>()));
                    break;
                case "2":
                    planes.forEach(System.out::println);
                    break;
                case "3":
                    planes.forEach(System.out::println);
                    System.out.println("Enter plane name:");
                    String searchName = new Scanner(System.in).nextLine();
                    Optional<Plane> planeOptional = planes.stream().filter(plane1 -> plane1.getName().contentEquals(searchName)).findFirst();
                    if (!planeOptional.isPresent()){System.out.println("ERROR: no plane with such name"); break;}
                    System.out.println("Enter destination name");
                    String destName = new Scanner(System.in).nextLine();
                    planeOptional.get().getPath().add(destName);
                    break;
                case "4":
                    planes.forEach(System.out::println);
                    System.out.println("Enter plane name:");
                    String searchName2 = new Scanner(System.in).nextLine();
                    Optional<Plane> planeOptional2 = planes.stream().filter(plane1 -> plane1.getName().contentEquals(searchName2)).findFirst();
                    if (!planeOptional2.isPresent()){System.out.println("ERROR: no plane with such name"); break;}
                    planeOptional2.get().fly();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("ERROR: Invalid command");
                    break;
            }
        }
    }
}
