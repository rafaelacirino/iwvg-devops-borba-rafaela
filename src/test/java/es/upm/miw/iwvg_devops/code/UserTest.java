package es.upm.miw.iwvg_devops.code;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        List<Fraction> fractions = new ArrayList<>();
        User user = new User("1", "Juan", "Diaz", fractions);

        assertEquals("1", user.getId());
        assertEquals("Juan", user.getName());
        assertEquals("Diaz", user.getFamilyName());
        assertEquals(fractions, user.getFractions());
    }

    @Test
    public void testSetName() {
        User user = new User();
        user.setName("María");
        assertEquals("María", user.getName());
    }

    @Test
    public void testSetFamilyName() {
        User user = new User();
        user.setFamilyName("Sanches");
        assertEquals("Sanches", user.getFamilyName());
    }

    @Test
    public void testAddFraction() {
        User user = new User();
        Fraction fraction = new Fraction(1, 2);
        user.addFraction(fraction);

        assertTrue(user.getFractions().contains(fraction));
    }

    @Test
    public void testFullName() {
        User user = new User("1", "Juan", "Diaz", new ArrayList<>());
        assertEquals("Juan Diaz", user.fullName());
    }

    @Test
    public void testInitials() {
        User user = new User("1", "Juan", "Diaz", new ArrayList<>());
        assertEquals("J.", user.initials());
    }

    @Test
    public void testToString() {
        List<Fraction> fractions = new ArrayList<>();
        Fraction fraction = new Fraction(1, 2);
        fractions.add(fraction);
        User user = new User("1", "Juan", "Diaz", fractions);

        String expectedString = "User{id='1', name='Juan', familyName='Diaz', fractions=[Fraction{numerator=1, denominator=2}]}";
        assertEquals(expectedString, user.toString());
    }
}