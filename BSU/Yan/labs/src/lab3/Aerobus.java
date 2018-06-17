package lab3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "aerobus")
@XmlAccessorType(XmlAccessType.FIELD)
public class Aerobus extends Plane{

    public Aerobus() {
        setCargo(120);
        setLift(8600);
        setConsumes(8);
    }

    public Aerobus(int flyLength) {
        this();
        setFlyLength(flyLength);
    }

    @Override
    public String toString() {
        return "Aerobus cargo: "+getCargo()+" lift: "+getLift()+" length: "+getFlyLength()+" consumes per mile: "+getConsumes();
    }
}
