package lab3;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {
    @XmlAttribute(name = "name")
    private String name;
    @XmlElements({
            @XmlElement(name = "aerobus", type = Aerobus.class),
            @XmlElement(name = "tu132", type = TU132.class)
    })
    private List<Plane> planes = new ArrayList<>();

    public Company() {}

    public Company(String name, List<Plane> planes) {
        this.name = name;
        this.planes = planes;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company <"+name+">";
    }
}
