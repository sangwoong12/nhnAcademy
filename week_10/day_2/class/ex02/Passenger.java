package ex02;

public class Passenger {
    private int id;
    private String name;
    private int grade;
    private int age;

    public Passenger(int id, String name, int grade, int age) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", age=" + age +
                '}';
    }
}
