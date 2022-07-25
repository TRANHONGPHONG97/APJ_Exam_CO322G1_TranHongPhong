package view;

import model.Student;
import service.StudentService;
import utils.ConvertUtils;
import utils.ValidationUtils;

import java.util.List;
import java.util.Scanner;

public class StudentView {

    private static StudentService studentService = new StudentService();
    private static Scanner scanner = new Scanner(System.in);

    public static void add() {
        int id;
        id = (int) (System.currentTimeMillis()/1000);
        System.out.println("Nhập họ tên sinh viên: (vd:Tran Hong Phong) ");
        System.out.print("➨ ");
        String name = scanner.nextLine();
        String nameCheck;
        nameCheck = ConvertUtils.removeAccent(name);
        while (!ValidationUtils.isNameValid(nameCheck)) {
            System.out.println("Tên " + nameCheck + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu)");
            System.out.println("Nhập tên: (Ví dụ: Tran Hong Phong) ");
            System.out.print(" ➨ ");
            name = scanner.nextLine();
        }

        System.out.println("Nhập tuổi của sinh viên: ");
        int age;
        do {
            age = Integer.parseInt(scanner.nextLine());
            if (!(age >= 0 && age <=100)) {
                System.out.println("! ERROR ! \n" +
                        " \t Xin vui lòng nhập lại! \n ➨ \t ");
                System.out.print("➨ \t ");
            }
        } while (!(age >= 0 && age <=100));
        System.out.println("Nhập giới tính của sinh viên");
        System.out.print("➨ ");
        String gender = scanner.nextLine();
        while (!ValidationUtils.isGioitinh(gender)) {
            System.out.println("Giới tính " + gender + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu)");
            System.out.println("Nhập giới tính (ví dụ: Nam/Nữ (nam/nữ))");
            System.out.print("➨ ");
            gender = scanner.nextLine();
        }

        System.out.print("Nhập địa chỉ: \n ➨ \t");
        String address = scanner.nextLine();
        while (!ValidationUtils.isAddressValid(address)) {
            System.out.println("Địa chỉ " + address + " chưa hợp lệ. Mời nhập lại (Địa chỉ bắt đầu bằng số) \n " +
                    "\t (vd: 28 Nguyen Tri Phuong)");
            address = scanner.nextLine();
        }

        System.out.print("Nhập điểm trung bình\n");
        System.out.print("➨ ");
        double pointAverage;
        do {
            pointAverage = Double.parseDouble(scanner.nextLine());
            if (!(pointAverage > 0 && pointAverage <=10)) {
                System.out.print("Nhập sai. Xin vui lòng nhập lại!");
                pointAverage = Double.parseDouble(scanner.nextLine());
            }
        } while (!(pointAverage > 0 && pointAverage <=10));

        Student student = new Student(id, name, age, gender, address, pointAverage);
        studentService.addItem(student);
        System.out.println("Đã thêm sinh viên thành công!");
        show(studentService.getItem());

        boolean flag = true;
        do {
            System.out.printf(" Nhấn 'a' để thêm sinh viên ||  Nhấn 'b' để quay lại ||  Nhấn 'e' để thoát \n");
            System.out.print("➨ \t ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "a":
                    add();
                    break;
                case "b":
                    Menu.mainMenu();
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Xin vui lòng nhập lại!");
                    flag = false;
            }
        } while (!flag);
    }

    public static void update() {
        show(studentService.getItem());
        System.out.print("Nhập ID cần sửa\n➨ \t ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (studentService.exists(id)) {
                Menu.inputUpdate();
                boolean is = true;
                do {
                    try {
                        int choice = Integer.parseInt(scanner.nextLine());
                        switch (choice) {
                            case 1:
                                inputTen(id);
                                break;
                            case 2:
                                inputTuoi(id);
                                break;
                            case 3:
                                inputGioiTinh(id);
                                break;
                            case 4:
                                inputDiachi(id);
                                break;
                            case 5:
                                inputDiemTrungBinh(id);
                                break;
                            case 0:
                                Menu.mainMenu();
                                break;
                            default:
                                System.out.print("Chưa hợp lệ!! Mời Nhập Lại\n");
                                is = false;
                        }
                    } catch (Exception e) {
                        update();
                    }
                } while (!is);
                boolean flag = true;
                do {
                    System.out.print("Nhấn 'c' để tiếp tục cập nhật || Nhấn 'b' để quay lại || Nhấn 'e' để thoát \n=> \t");
                    String number = scanner.nextLine();
                    switch (number) {
                        case "c":
                            update();
                            break;
                        case "b":
                            Menu.mainMenu();
                            break;
                        case "e":
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Mời Nhập Lại");
                            flag = false;
                    }
                } while (!flag);
            } else {
                System.out.println("Mời Nhập Lại");
                update();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void show(List<Student> studentList) {

        System.out.println("------------------------------------- DANH SÁCH SINH VIÊN ------------------------------------------------");
        System.out.printf("%-20s %-30s %-10s %-10s %-20s %-15s", "Mã sinh viên", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ", "Điểm TB");
        System.out.println(" ");
        for (Student student : studentList) {
            System.out.printf("%-20s %-30s %-10s %-10s %-20s %-15s\n",
                    student.getStudentID(),
                    student.getName(),
                    student.getAge(),
                    student.getGender(),
                    student.getAddress(),
                    student.getPointAverage());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------\n");
    }
    public static void showAll() {
        List<Student> studentList = studentService.getItem();
            System.out.println("---------------------------------- DANH SÁCH SINH VIÊN ------------------------------------------------------");
            System.out.printf("%-20s %-30s %-10s %-10s %-20s %-15s\n", "Mã sinh viên", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ", "Điểm TB");
            for (Student student : studentList) {
                System.out.printf("%-20s %-30s %-10s %-10s %-20s %-15s\n",
                        student.getStudentID(),
                        student.getName(),
                        student.getAge(),
                        student.getGender(),
                        student.getAddress(),
                        student.getPointAverage());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
            do {
                System.out.print("Nhấn 'b' để quay lại || Nhấn 'e' để thoát chương trình\n");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "b":
                        Menu.startMenu();
                        break;
                    case "e":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chưa hợp lệ, mời nhập lại");
                }
            } while (true);
    }

    public static void inputTen(int id) {
        Student student = studentService.getStudentByID(id);
        System.out.printf("Cập nhật tên sinh viên: \n➨ \t");
        String name = scanner.nextLine().trim();
        student.setName(name);
        studentService.update(student);
        show(studentService.getItem());
        System.out.println("Cập nhật thành công!");
    }

    public static void inputTuoi(int id) {
        Student student = studentService.getStudentByID(id);
        System.out.printf("Cập nhật tuổi sinh viên: \n➨ \t");
        int age = Integer.parseInt(scanner.nextLine().trim());
        student.setAge(age);
        studentService.update(student);
        show(studentService.getItem());
        System.out.println("Cập nhật thành công!");
    }

    public static void inputGioiTinh(int id) {
        Student student = studentService.getStudentByID(id);
        System.out.printf("Cập nhật giới tính sinh viên: \n➨ \t");
        String gender = scanner.nextLine();
        student.setGender(gender);
        studentService.update(student);
        show(studentService.getItem());
        System.out.println("Cập nhật thành công!");
    }

    public static void inputDiachi(int id) {
        Student student = studentService.getStudentByID(id);
        System.out.printf("Cập nhật địa chỉ của sinh viên: \n➨ \t");
        String address = scanner.nextLine();
        student.setAddress(address);
        studentService.update(student);
        show(studentService.getItem());
        System.out.println("Cập nhật thành công!");
    }

    public static void inputDiemTrungBinh(int id) {
        Student student = studentService.getStudentByID(id);
        System.out.printf("Cập nhật điểm trung bình của sinh viên: \n➨ \t");
        double pointAverage = Double.parseDouble(scanner.nextLine().trim());
        student.setPointAverage(pointAverage);
        studentService.update(student);
        show(studentService.getItem());
        System.out.println("Cập nhật thành công!");
    }

    public static void remove() {
        List<Student> studentList = studentService.getItem();
        show(studentList);
        System.out.printf("Nhập mã sinh viên \n➨ \t");
        int id = Integer.parseInt(scanner.nextLine());
        Student student = studentService.getStudentByID(id);
        if (student != null) {
            Menu.removeConfirm();
            String number = scanner.nextLine();
            try {
                switch (number) {
                    case "y":
                        studentService.remove(student.getStudentID());
                        System.out.println("Xóa thành công sinh viên ra khỏi danh sách!");
                        show(studentService.getItem());
                        do {
                            System.out.println("Nhấn '1' để quay lại ||  Nhấn '2' để thoát\n");
                            System.out.printf("➨ \t");
                            byte choice = Byte.parseByte(scanner.nextLine());
                            switch (choice) {
                                case 1:
                                    Menu.mainMenu();
                                    break;
                                case 2:
                                   System.exit(2);
                                    break;
                                default:
                                    System.out.println("Nhập lại!");
                            }
                        } while (true);
                    case "c":
                        Menu.mainMenu();
                        break;
                    default:
                        System.out.println("Vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Chưa hợp lệ! Xin vui lòng nhập lại!");
            }
        }
    }
}
