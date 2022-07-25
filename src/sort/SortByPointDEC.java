package sort;

import model.Student;

import java.util.Comparator;

public class SortByPointDEC implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        double result = o2.getPointAverage() - o1.getPointAverage();
        return result == 0 ? 0 : (result > 0 ? 1 : -1);
    }
}
