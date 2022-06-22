package service;

import model.Student;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentService implements IStudentService {
    List<Student> students = new ArrayList<>();
    private static String path = "data/student.csv";

    @Override
    public List<Student> getItem() {
        List<Student> newStudent = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newStudent.add(new Student(record));
        }
        return students = newStudent;
    }

    @Override
    public void addItem(Student newStudent) {
        List<Student> students = getItem();
        students.add(newStudent);
        CSVUtils.write(path, students);
    }

    @Override
    public void update(Student newStudent) {
        List<Student> students = getItem();
        for (Student student : students) {
            if (student.getStudentID() == newStudent.getStudentID()) {
                student.setName(newStudent.getName());
                student.setAge(newStudent.getAge());
                student.setGender(newStudent.getGender());
                student.setAddress(newStudent.getAddress());
                student.setDiemTrungBinh(newStudent.getDiemTrungBinh());
            }
        }
        CSVUtils.write(path, students);
    }

    @Override
    public void remove(long id) {
        List<Student> students = getItem();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID() == id) {
                students.remove(i);
                break;
            }
        }
        CSVUtils.write(path, students);
    }

    @Override
    public boolean exists(int id) {
        return getStudentByID(id) != null;
    }

    @Override
    public Student getStudentByID(int id) {
        List<Student> students = getItem();
        for (Student student : students) {
            if (student.getStudentID() == id) {
                return student;
            }
        }
        return null;
    }
}
