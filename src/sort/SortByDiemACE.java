package sort;

import model.Student;

import java.util.Comparator;

public class SortByDiemACE implements Comparator<Student> {
    public int compare(Student o1, Student o2) {
        double result = o1.getPointAverage() - o2.getPointAverage();
        return result == 0 ? 0 : (result > 0 ? 1 : -1);
    }
}

