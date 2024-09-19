package es.upm.miw.iwvg_devops.code;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction() {
        this(1, 1);
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public double decimal() {
        return (double) numerator / denominator;
    }

    public boolean isProper(){
        return Math.abs(numerator) < Math.abs(denominator);
    }

    public boolean isImproper(){
        return Math.abs(numerator) >= Math.abs(denominator);
    }

    public boolean isEquivalent(Fraction fraction){
        return this.numerator * fraction.denominator == this.denominator * fraction.numerator;
    }

    public Fraction add(Fraction fraction){
        int num1 = this.numerator * fraction.denominator + fraction.numerator * this.denominator;
        int den1 = this.denominator * fraction.denominator;
        return new Fraction(num1, den1);
    }

    public Fraction multiply(Fraction fraction){
        int num = this.numerator * fraction.numerator;
        int den = this.denominator * fraction.denominator;
        int mdc = mdc(num, den);

        num = num / mdc;
        den = den / mdc;

        return new Fraction(num, den);
    }

    private int mdc(int a, int b) {
        if (b == 0) {
            return Math.abs(a);
        }
        return mdc(b, a % b);
    }

    public Fraction divide(Fraction fraction){
        if(fraction.numerator == 0){
            throw new ArithmeticException("Cannot divide when numerator is zero");
        }
        int num = this.numerator * fraction.denominator;
        int den = this.denominator * fraction.numerator;
        return new Fraction(num, den);
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}