package lab1;

public class PowerFunction implements MathFunction{
    private double n;

    @Override
    public double apply(double argument) {
        return Math.pow(argument, n);
    }

    public PowerFunction() {}

    public PowerFunction(double n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return (n==1.0) ? "x" : "x^"+n;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }
}
