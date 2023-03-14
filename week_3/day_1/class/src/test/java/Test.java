import org.example.exam.Lecture;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        Lecture lectures = new Lecture(5);
//        lectures.add(new Student(1,"Celine"));
//        lectures.add(new Student(2,"Vesper"));
//        lectures.add(new Student(3,"Paloma"));
//        lectures.add(new Student(4,"Clementine"));
//        lectures.add(new Student(5,"Stelve"));

        Iterator iterator = lectures.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
