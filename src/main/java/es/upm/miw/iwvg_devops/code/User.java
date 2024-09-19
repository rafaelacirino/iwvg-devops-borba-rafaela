package es.upm.miw.iwvg_devops.code;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String familyName;
    private List<Fraction> fractions;
    private static List<User> allUsers = new ArrayList<>();

    public User() {
        this.fractions = new ArrayList<>();
    }

    public User(String id, String name, String familyName, List<Fraction> fractions) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.fractions = fractions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public List<Fraction> getFractions() {
        return fractions;
    }

    public void setFractions(List<Fraction> fractions) {
        this.fractions = fractions;
    }

    public void addFraction(Fraction fraction) {
        this.fractions.add(fraction);
    }

    public String fullName() {
        return this.name + " " + this.familyName;
    }

    public String initials() {
        return this.name.substring(0, 1) + ".";
    }

    public static List<User> getAllUsers(){
        return allUsers;
    }

    public static void addUser(User user) {
        allUsers.add(user);
    }

        @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", fractions=" + fractions +
                '}';
    }
}