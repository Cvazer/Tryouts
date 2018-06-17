package lab1;

public class PrimitiveFunction implements MathFunction{
    private double n;

    public PrimitiveFunction() {}

    public PrimitiveFunction(double n) {
        this.n = n;
    }

    @Override
    public double apply(double argument) {
        return n;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return n+"";
    }
}
