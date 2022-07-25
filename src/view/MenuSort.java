package view;

import model.Student;
import service.StudentService;
import sort.SortByPointACE;
import sort.SortByPointDEC;

import java.util.List;
import java.util.Scanner;

public class MenuSort {
    private static Scanner scanner = new Scanner(System.in);
    static StudentView studentView = new StudentView();
    static StudentService studentService = new StudentService();
    static List<Student> studentList;

    public MenuSort() {
        studentList = studentService.getItem();
    }
    public static void sortMenu() {
        System.out.println("-------------SẮP XẾP SINH VIÊN THEO ĐIỂM TRUNG BÌNH --------------");
        System.out.println("-            1. Hiển thị sắp xếp điểm trung bình                 -");
        System.out.println("-            0. Quay lại                                         -");
        System.out.println("------------------------------------------------------------------");
        System.out.print("➨ \t");
    }
    public static void option() {
        boolean flag = true;
        int choice;
        do {
            sortMenu();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showSortByDiemTrungBinh();
                    break;
                case 0:
                    Menu.mainMenu();
                    break;
                default:
                    System.out.println("Không hợp lệ, vui lòng nhập lại");
                    flag = false;
            }
        } while (!flag);
    }

    public static void showSortByDiemTrungBinh() {
        boolean flag = true;
        int choice;
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("-               SẮP XẾP ĐIỂM TRUNG BÌNH                 -");
            System.out.println("-          1. Sắp xếp điểm trung bình tăng dần          -");
            System.out.println("-          2. Sắp xếp điểm trung bình giảm dần          -");
            System.out.println("-          0. Quay lại                                  -");
            System.out.println("---------------------------------------------------------");
            System.out.println();
            System.out.print("Chọn chức năng:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        List<Student> studentList = studentService.getItem();
                        System.out.println("Sắp xếp điểm trung bình tăng dần");
                        SortByPointACE sortByDiemTrungBinhASC = new SortByPointACE();
                        studentList.sort(sortByDiemTrungBinhASC);
                        studentView.show(studentList);
                        option();
                        break;
                    case 2:
                        List<Student> studentsList = studentService.getItem();
                        System.out.println("Sắp xếp điểm trung bình giảm dần");
                        SortByPointDEC sortByPointDEC = new SortByPointDEC();
                        studentsList.sort(sortByPointDEC);
                        studentView.show(studentsList);
                        option();
                        break;
                    case 0:
                        Menu.mainMenu();
                        break;
                    default:
                        System.out.println("Chọn lại !");
                        flag = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!flag);

    }
}
