import java.io.File;

public class test {
    private static final String JSON_FILE_PATH = "/Users/sangwoong/Documents/nhnAcademy/week_6/day_4/class/student-step4/src/main/resources/student.json";

    public static void main(String[] args) {
        File file = new File(JSON_FILE_PATH);
        System.out.println(file.length());

    }
}
