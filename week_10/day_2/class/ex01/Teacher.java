package ex01;

public class Teacher {
    private int id;
    private String name;
    private String create_at;

    public Teacher(int id, String name, String create_at) {
        this.id = id;
        this.name = name;
        this.create_at = create_at;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreate_at() {
        return create_at;
    }

    @Override
    public String toString() {
        return "teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", create_at=" + create_at +
                '}';
    }
}
