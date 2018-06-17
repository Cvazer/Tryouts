package lab1;

import java.util.List;

public class RationalFraction {
    private List<MathFunction> numerator;
    private List<MathFunction> denominator;

    public RationalFraction() {}

    public RationalFraction(List<MathFunction> numerator, List<MathFunction> denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public List<MathFunction> getNumerator() {
        return numerator;
    }

    public void setNumerator(List<MathFunction> numerator) {
        this.numerator = numerator;
    }

    public List<MathFunction> getDenominator() {
        return denominator;
    }

    public void setDenominator(List<MathFunction> denominator) {
        this.denominator = denominator;
    }

    public double evaluate(double x){
        final double[] numeratorResult = {0};
        numerator.forEach(function -> numeratorResult[0] +=function.apply(x));

        final double[] denominatorResult = {0};
        denominator.forEach(function -> denominatorResult[0] +=function.apply(x));

        return numeratorResult[0] / denominatorResult[0];
    }

    @Override
    public String toString() {
        String text = "";
        for (MathFunction function: numerator){
            text+=function.toString()+" + ";
        }
        text = text.substring(0, text.length()-3);
        text += " \u00F7 ";
        for (MathFunction function: denominator){
            text+=function.toString()+" + ";
        }
        text = text.substring(0, text.length()-3);
        return text;
    }
}
