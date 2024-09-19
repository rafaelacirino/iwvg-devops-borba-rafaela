package es.upm.miw.iwvg_devops.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FractionTest {

    @BeforeEach
    void setUp() {
        User.getAllUsers().clear();

        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(3, 4);
        Fraction f3 = new Fraction(5, 6);
        Fraction f4 = new Fraction(7, 8);
        Fraction f5 = new Fraction(-1, 2);
        Fraction f6 = new Fraction(-3, 4);
        Fraction f7 = new Fraction(5, 6);
        Fraction f8 = new Fraction(-7, 8);
        Fraction f10 = new Fraction(1, 4);

        User user1 = new User("1", "Juan", "Sanchez", new ArrayList<>(List.of(f1, f2)));
        User user2 = new User("2", "María", "Gimenez", new ArrayList<>(List.of(f3, f4)));
        User user3 = new User("3", "José", "Gonzalez", new ArrayList<>(List.of(f5, f6)));
        User user4 = new User("4", "Paula", "García", new ArrayList<>(List.of(f7, f8)));
        User user5 = new User("5", "Jane", "Silva", new ArrayList<>(List.of(f2, f10)));
        User user6 = new User("6", "Italo", "Macedo", new ArrayList<>(List.of(f1, f10)));

        User.addUser(user1);
        User.addUser(user2);
        User.addUser(user3);
        User.addUser(user4);
        User.addUser(user5);
        User.addUser(user6);
    }

    @Test
    public void testFractionConstructorAndGetters() {
        Fraction fraction = new Fraction(1, 2);

        assertEquals(1, fraction.getNumerator());
        assertEquals(2, fraction.getDenominator());
    }

    @Test
    public void setNumerator() {
        Fraction fraction = new Fraction();
        fraction.setNumerator(2);
        assertEquals(2, fraction.getNumerator());
    }

    @Test
    public void setDenominator() {
        Fraction fraction = new Fraction();
        fraction.setDenominator(3);
        assertEquals(3, fraction.getDenominator());
    }

    @Test
    public void testDecimal() {
        Fraction fraction = new Fraction(1, 2);
        assertEquals(0.5, fraction.decimal());
    }

    @Test
    public void testIsProper() {
        Fraction fraction = new Fraction(3, 4);
        assertTrue(fraction.isProper(), "Fraction 3/4 should be proper");

        Fraction improperFraction = new Fraction(5, 4);
        assertFalse(improperFraction.isProper(), "Fraction 5/4 should not be proper");
    }

    @Test
    public void testIsImproper() {
        Fraction fraction = new Fraction(5, 4);
        assertTrue(fraction.isImproper(), "Fraction 5/4 should be improper");

        Fraction properFraction = new Fraction(3, 7);
        assertFalse(properFraction.isImproper(), "Fraction 3/7 should not be improper");
    }

    @Test
    public void testIsEquivalent() {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(2, 4);

        assertTrue(fraction1.isEquivalent(fraction2), "Fraction 1/2 and 2/4 should be equivalent");

        Fraction fraction3 = new Fraction(3, 4);
        assertFalse(fraction1.isEquivalent(fraction3), "Fraction 1/2 and 3/4 should not be equivalent");
    }

    @Test
    public void testAdd() {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 3);
        Fraction result = fraction1.add(fraction2);

        assertEquals(5, result.getNumerator(), "Numerator of 1/2 + 1/3 should be 5");
        assertEquals(6, result.getDenominator(), "Denominator of 1/2 + 1/3 should be 6");
    }

    @Test
    public void testMultiply() {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(2, 3);
        Fraction result = fraction1.multiply(fraction2);

        assertEquals(2, result.getNumerator(), "Numerator of 1/2 * 2/3 should be 2");
        assertEquals(6, result.getDenominator(), "Denominator of 1/2 * 2/3 should be 6");
    }

    @Test
    public void testDivide() {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(2, 3);
        Fraction result = fraction1.divide(fraction2);

        assertEquals(3, result.getNumerator(), "Numerator of 1/2 divided by 2/3 should be 3");
        assertEquals(4, result.getDenominator(), "Denominator of 1/2 divided by 2/3 should be 4");
    }

    @Test
    public void testDivideWhenNumeratorIsZero() {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(0, 3);

        Exception exception = assertThrows(ArithmeticException.class, () -> {
            fraction1.divide(fraction2);
        });

        assertEquals("Cannot divide when numerator is zero", exception.getMessage());
    }

    @Test
    public void testToString() {
        Fraction fraction = new Fraction(1, 2);

        String expectedFraction = "Fraction{numerator=1, denominator=2}";
        assertEquals(expectedFraction, fraction.toString());
    }

    @Test
    void testFindFractionMultiplicationByUserFamily_validFamilyName() {
        Fraction result = new Fraction().findFractionMultiplicationByUserFamily("Sanchez");

        assertEquals(3, result.getNumerator());
        assertEquals(8, result.getDenominator());
    }

    @Test
    void testFindFractionMultiplicationByUserFamily_invalidFamilyName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Fraction().findFractionMultiplicationByUserFamily("NonExistentFamily");
        });

        String expectedMessage = "No fractions were found for the specified family name.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindUserFamilyNameByAllNegativeSignFractionDistinct_NoNegativeFractions() {
        User.getAllUsers().clear();

        Fraction f3 = new Fraction(5, 6);
        User user5 = new User("5", "Julio", "Soares", new ArrayList<>(List.of(f3)));
        User.addUser(user5);

        List<String> result = Fraction.findUserFamilyNameByAllNegativeSignFractionDistinct()
                                        .collect(Collectors.toList());

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindFractionSubtractionByUserName_noUserFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Fraction.findFractionSubtractionByUserName("NonExistentUser");
        });

        String expectedMessage = "No user found with the given name";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindFractionSubtractionByUserName_noFractionsForUser() {
        User user = new User("9", "Julia", "Alberto", new ArrayList<>());
        User.addUser(user);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Fraction.findFractionSubtractionByUserName("Julia");
        });

        String expectedMessage = "No fractions available for the given user";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindFractionSubtractionByUserName_validUser() {
        Fraction result = Fraction.findFractionSubtractionByUserName("Italo");

        assertEquals(2, result.getNumerator());
        assertEquals(8, result.getDenominator());
    }

    @Test
    void testFindFractionSubtractionByUserName_multipleFractions() {
        Fraction result = Fraction.findFractionSubtractionByUserName("Jane");

        assertEquals(8, result.getNumerator());
        assertEquals(16, result.getDenominator());
    }

    @Test
    void testFindUserFamilyNameInitialBySomeProperFraction() {
        List<String> result = Fraction.findUserFamilyNameInitialBySomeProperFraction()
                                    .collect(Collectors.toList());

        assertEquals(6, result.size());
        assertTrue(result.contains("S"));
        assertTrue(result.contains("G"));
    }

    @Test
    void testFindUserFamilyNameInitialBySomeProperFraction_noProperFractions() {
        User.getAllUsers().clear();

        Fraction improperFraction1 = new Fraction(5, 4);
        Fraction improperFraction2 = new Fraction(9, 8);

        User user1 = new User("4", "Marcos", "Costa",
                            new ArrayList<>(List.of(improperFraction1, improperFraction2)));
        User.addUser(user1);

        List<String> result = Fraction.findUserFamilyNameInitialBySomeProperFraction()
                                    .collect(Collectors.toList());

        assertEquals(0, result.size());
    }
}