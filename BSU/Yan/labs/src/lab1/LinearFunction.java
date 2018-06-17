package lab1;

public class LinearFunction implements MathFunction{
    private double k;

    @Override
    public double apply(double argument) {
        return k*argument;
    }

    public LinearFunction() {}

    public LinearFunction(double k) {
        this.k = k;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return (k==1.0) ? "x" : k+"x";
    }
}
