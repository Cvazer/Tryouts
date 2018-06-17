package lab3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Plane {
    @XmlAttribute(name = "consumes")
    private int consumes;
    @XmlAttribute(name = "cargo")
    private int cargo;
    @XmlAttribute(name = "lift")
    private int lift;
    @XmlAttribute(name = "length")
    private int flyLength;

    public Plane() {}

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public int getLift() {
        return lift;
    }

    public void setLift(int lift) {
        this.lift = lift;
    }

    public int getFlyLength() {
        return flyLength;
    }

    public void setFlyLength(int flyLength) {
        this.flyLength = flyLength;
    }

    public int getConsumes() {
        return consumes;
    }

    public void setConsumes(int consumes) {
        this.consumes = consumes;
    }
}
