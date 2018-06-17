package lab3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "tu132")
@XmlAccessorType(XmlAccessType.FIELD)
public class TU132 extends Plane{

    public TU132() {
        setCargo(80);
        setLift(8800);
        setConsumes(6);
    }

    public TU132(int flyLength) {
        this();
        setFlyLength(flyLength);
    }

    @Override
    public String toString() {
        return "TU132 cargo: "+getCargo()+" lift: "+getLift()+" length: "+getFlyLength()+" consumes per mile: "+getConsumes();
    }

}
