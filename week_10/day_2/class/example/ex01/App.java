package ex01;

import java.util.List;

public class App {
    public static void main(String[] args){
        TeacherList teacherList = new TeacherList();
        List<Teacher> list = teacherList.getTeacherList();

        list.forEach(System.out::println);
    }
}
