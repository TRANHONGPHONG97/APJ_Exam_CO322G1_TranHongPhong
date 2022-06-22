package model;

import java.util.Comparator;

public class Student implements Comparator<Student> {
    private long studentID;
    private String name;
    private int age;
    private String gender;
    private String address;
    private double diemTrungBinh;

    public Student() {
    }

    public Student(String record) {
        String[] fields = record.split(",");
        studentID = Integer.parseInt(fields[0]);
        name = fields[1];
        age = Integer.parseInt(fields [2]);
        gender = fields[3];
        address = fields[4];
        diemTrungBinh = Double.parseDouble(fields[5]);
    }

    public Student(long studentID, String name, int age, String gender, String address, double diemTrungBinh) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.diemTrungBinh = diemTrungBinh;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    @Override
    public String toString() {
        return studentID +
                "," + name +
                "," + age +
                "," + gender +
                "," + address +
                "," + diemTrungBinh;
    }

    @Override
    public int compare(Student o1, Student o2) {
        return (int) (o1.getStudentID() - o2.getStudentID());
    }
}
