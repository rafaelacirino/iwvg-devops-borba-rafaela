package es.upm.miw.iwvg_devops.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FractionTest {

    @Test
    public void testFractionConstructorAndGetters(){
        Fraction fraction = new Fraction(1, 2);

        assertEquals(1, fraction.getNumerator());
        assertEquals(2, fraction.getDenominator());
    }

    @Test
    public void setNumerator(){
        Fraction fraction = new Fraction();
        fraction.setNumerator(2);
        assertEquals(2, fraction.getNumerator());
    }

    @Test
    public void setDenominator(){
        Fraction fraction = new Fraction();
        fraction.setDenominator(3);
        assertEquals(3, fraction.getDenominator());
    }

    @Test
    public void testDecimal(){
        Fraction fraction = new Fraction(1, 2);
        assertEquals(0.5, fraction.decimal());
    }

    @Test
    public void testIsProper(){
        Fraction fraction = new Fraction(3, 4);
        assertTrue(fraction.isProper(), "Fraction 3/4 should be proper");

        Fraction improperFraction = new Fraction(5, 4);
        assertFalse(improperFraction.isProper(), "Fraction 5/4 should not be proper");
    }

    @Test
    public void testIsImproper(){
        Fraction fraction = new Fraction(5, 4);
        assertTrue(fraction.isImproper(), "Fraction 5/4 should be improper");

        Fraction properFraction = new Fraction(3, 7);
        assertFalse(properFraction.isImproper(), "Fraction 3/7 should not be improper");
    }

    @Test
    public void testIsEquivalent(){
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(2, 4);

        assertTrue(fraction1.isEquivalent(fraction2), "Fraction 1/2 and 2/4 should be equivalent");

        Fraction fraction3 = new Fraction(3, 4);
        assertFalse(fraction1.isEquivalent(fraction3), "Fraction 1/2 and 3/4 should not be equivalent");
    }

    @Test
    public void testAdd(){
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 3);
        Fraction result = fraction1.add(fraction2);

        assertEquals(5, result.getNumerator(), "Numerator of 1/2 + 1/3 should be 5");
        assertEquals(6, result.getDenominator(), "Denominator of 1/2 + 1/3 should be 6");
    }

    @Test
    public void testMultiply(){
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(2, 3);
        Fraction result = fraction1.multiply(fraction2);

        assertEquals(2, result.getNumerator(), "Numerator of 1/2 * 2/3 should be 2");
        assertEquals(6, result.getDenominator(), "Denominator of 1/2 * 2/3 should be 6");
    }

    @Test
    public void testDivide(){
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(2, 3);
        Fraction result = fraction1.divide(fraction2);

        assertEquals(3, result.getNumerator(), "Numerator of 1/2 divided by 2/3 should be 3");
        assertEquals(4, result.getDenominator(), "Denominator of 1/2 divided by 2/3 should be 4");
    }

    @Test
    public void testDividedWhenNumeratorIsZero(){
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(0, 3);

        Exception exception = assertThrows(ArithmeticException.class, () -> {
            fraction1.divide(fraction2);
        });

        assertEquals("Cannot divide when numerator is zero", exception.getMessage());
    }

    @Test
    public void testToString(){
        Fraction fraction = new Fraction(1, 2);

        String expectedFraction = "Fraction{numerator=1, denominator=2}";
        assertEquals(expectedFraction, fraction.toString());
    }
}