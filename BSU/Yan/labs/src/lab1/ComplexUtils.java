package lab1;

public class ComplexUtils {
    public static String add(Complex complex1, Complex complex2, double argument){
        double real = complex1.evaluateReal(argument)+complex2.evaluateReal(argument);
        double img = complex1.evaluateImg(argument)+complex2.evaluateImg(argument);
        return real+((img>0) ? " + " : " - ")+img+"i";
    }

    public static String mul(Complex complex1, Complex complex2, double argument){
        double c1real = complex1.evaluateReal(argument);
        double c2real = complex2.evaluateReal(argument);
        double c1img = complex2.evaluateImg(argument);
        double c2img = complex2.evaluateImg(argument);

        double real = ((c1real*c2real) - (c1img*c2img));
        double img = ((c1real*c2img) + (c1img*c2real));

        return real+((img>0) ? " + " : " - ")+img+"i";
    }
}
