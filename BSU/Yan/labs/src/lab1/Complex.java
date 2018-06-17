package lab1;

public class Complex {
    private RationalFraction real;
    private RationalFraction img;

    public double evaluateReal(double argument){
        return real.evaluate(argument);
    }

    public double evaluateImg(double argument){
        return img.evaluate(argument);
    }

    public Complex() {}

    public Complex(RationalFraction real, RationalFraction img) {
        this.real = real;
        this.img = img;
    }

    public RationalFraction getReal() {
        return real;
    }

    public void setReal(RationalFraction real) {
        this.real = real;
    }

    public RationalFraction getImg() {
        return img;
    }

    public void setImg(RationalFraction img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Complex number{ real: "+real.toString()+" imaginary: "+img.toString()+" }";
    }
}

