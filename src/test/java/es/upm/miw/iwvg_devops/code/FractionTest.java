package es.upm.miw.iwvg_devops.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void testToString(){
        Fraction fraction = new Fraction(1, 2);

        String expectedFraction = "Fraction{numerator=1, denominator=2}";
        assertEquals(expectedFraction, fraction.toString());
    }
}