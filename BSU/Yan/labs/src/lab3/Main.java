package lab3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.*;

public class Main {
    private static List<Company> companies = new ArrayList<>();

    public static void exec(String[] args) {

        File xml = new File("data.xml");
        if (xml.exists()){getFromFile(xml);}

        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while (running){
            System.out.println("COMMANDS:\n\t" +
                    "1: Add company\n\t" +
                    "2: Add plane for company\n\t" +
                    "3: Count lift for company\n\t" +
                    "4: Count cargo place for company\n\t" +
                    "5: List all planes of company descending by length\n\t" +
                    "6: Filter by fuel consumption in range for company\n\t" +
                    "7: Exit");
            String cmd = scanner.next();
            switch (cmd){
                case "1":
                    System.out.println("Enter company name:");
                    Company newCompany = new Company(new Scanner(System.in).nextLine(), new ArrayList<>());
                    companies.add(newCompany);
                    break;
                case "2":
                    Company company = getCompByName();
                    if (company == null){ System.out.println("ERROR: No such company");break;}
                    System.out.println("Choose plane type:\n\t" +
                            "1: Aerobus\n\t" +
                            "2: TU123");
                    String choice = new Scanner(System.in).next();
                    switch (choice){
                        case "1":
                            company.getPlanes().add(new Aerobus(0));
                            break;
                        case "2":
                            company.getPlanes().add(new TU132(0));
                            break;
                        default:
                            System.out.println("ERROR: Invalid command");
                            break;
                    }
                    break;
                case "3":
                    Company company2 = getCompByName();
                    if (company2 == null){ System.out.println("ERROR: No such company");break;}
                    System.out.println("Total lift for company <"+company2.getName()+"> is: "+company2.getPlanes().stream().mapToInt(Plane::getLift).sum());
                    break;
                case "4":
                    Company company3 = getCompByName();
                    if (company3 == null){ System.out.println("ERROR: No such company");break;}
                    System.out.println("Total cargo place for company <"+company3.getName()+"> is: "+company3.getPlanes().stream().mapToInt(Plane::getCargo).sum());
                    break;
                case "5":
                    Company company4 = getCompByName();
                    if (company4 == null){ System.out.println("ERROR: No such company");break;}
                    company4.getPlanes().stream().sorted(Comparator.comparingInt(Plane::getFlyLength)).forEach(System.out::println);
                    break;
                case "6":
                    Company company5 = getCompByName();
                    if (company5 == null){ System.out.println("ERROR: No such company");break;}
                    System.out.println("Enter low border:");
                    int lowBorder = new Scanner(System.in).nextInt();
                    System.out.println("Enter high border:");
                    int highBorder = new Scanner(System.in).nextInt();
                    company5.getPlanes().stream().filter(plane -> plane.getConsumes()>=lowBorder&&plane.getConsumes()<=highBorder).forEach(System.out::println);
                    break;
                case "7":
                    save();
                    running = false;
                    break;
                default:
                    System.out.println("ERROR: Invalid command");
                    break;
            }
        }
    }

    private static void save() {
        CompaniesDTO dto = new CompaniesDTO();
        dto.setCompanies(companies);
        try {
            JAXBContext context = JAXBContext.newInstance(CompaniesDTO.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(dto, new File("data.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private static Company getCompByName() {
        companies.forEach(System.out::println);
        System.out.println("Enter company name:");
        String compName = new Scanner(System.in).nextLine();
        Optional<Company> optional = companies.stream().filter(company -> company.getName().contentEquals(compName)).findFirst();
        return optional.orElse(null);
    }

    private static void getFromFile(File xml) {
        CompaniesDTO dto = null;
        try {
            dto = (CompaniesDTO) JAXBContext.newInstance(CompaniesDTO.class).createUnmarshaller().unmarshal(xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        companies.addAll(Objects.requireNonNull(dto).getCompanies());
    }
}
