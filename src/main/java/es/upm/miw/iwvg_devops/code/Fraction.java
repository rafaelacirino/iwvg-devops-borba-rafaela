package es.upm.miw.iwvg_devops.code;

import java.util.List;
import java.util.stream.Stream;

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

    public Fraction findFractionMultiplicationByUserFamily(String familyName){
        List<User> users = User.getAllUsers();

        Fraction result = null;
        for(User user : users){
            if(user.getFamilyName().equals(familyName)){
                for(Fraction fraction : user.getFractions()){
                    if(result == null){
                        result = new Fraction(fraction.getNumerator(), fraction.getDenominator());
                    } else{
                        result = result.multiply(fraction);
                    }
                }
            }
        }
        if(result == null){
            throw new IllegalArgumentException("No fractions were found for the specified family name.");
        }
        return result;
    }

    public static Stream<String> findUserFamilyNameByAllNegativeSignFractionDistinct() {
        return User.getAllUsers().stream()
                .filter(user -> !user.getFractions().isEmpty() &&
                        user.getFractions().stream().allMatch(fraction -> fraction.getNumerator() < 0))
                .map(User::getFamilyName)
                .distinct();
    }

    public static Fraction findFractionSubtractionByUserName(String name) {
        User user = User.getAllUsers().stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No user found with the given name"));

        List<Fraction> fractions = user.getFractions();

        if (fractions.isEmpty()) {
            throw new IllegalArgumentException("No fractions available for the given user");
        }

        Fraction result = fractions.get(0);

        for (int i = 1; i < fractions.size(); i++) {
            Fraction current = fractions.get(i);
            int newNumerator = result.getNumerator() * current.getDenominator() - current.getNumerator() * result.getDenominator();
            int newDenominator = result.getDenominator() * current.getDenominator();
            result = new Fraction(newNumerator, newDenominator);
        }

        return result;
    }

    public static Stream<String> findUserFamilyNameInitialBySomeProperFraction() {
        return User.getAllUsers().stream()
                .filter(user -> user.getFractions().stream().anyMatch(Fraction::isProper))
                .map(user -> user.getFamilyName().substring(0, 1).toUpperCase());
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}