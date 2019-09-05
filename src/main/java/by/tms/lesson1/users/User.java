package by.tms.lesson1.users;

public class User {
    private int id;
    private String name;
    private int age;
    private static int usersCount;

    public User(String name, int age) {
        User.setUsersCount(User.getUsersCount() + 1);
        this.id = User.getUsersCount();
        this.name = name;
        this.age = age;
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

    public static int getUsersCount() {
        return usersCount;
    }

    public static void setUsersCount(int usersCount) {
        User.usersCount = usersCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
