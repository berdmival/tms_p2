package by.tms.lesson1.entities.user;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private int age;
    private static int idIncrementer;
    private String password;

    public User() {
    }

    public User(String name, int age, String password) {
        this.id = idIncrementer++;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User with" +
                " id = " + id +
                " (name is '" + name + "\'" +
                ", age is " + age +
                " years)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
