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
        String ten = scanner.nextLine();
        String namecheck;
        namecheck = ConvertUtils.removeAccent(ten);
        while (!ValidationUtils.isNameValid(namecheck)) {
            System.out.println("Tên " + namecheck + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu)");
            System.out.println("Nhập tên: (Ví dụ: Tran Hong Phong) ");
            System.out.print(" ➨ ");
            ten = scanner.nextLine();
        }

        System.out.println("Nhập tuổi của sinh viên: ");
        int tuoi;
        do {
            tuoi = Integer.parseInt(scanner.nextLine());
            if (!(tuoi >= 0)) {
                System.out.println("! ERROR ! \n" +
                        " \t Xin vui lòng nhập lại! \n ➨ \t ");
                System.out.print("➨ \t ");
            }
        } while (!(tuoi >= 0));
        System.out.printf("➨ \t ");


        System.out.println("Nhập giới tính của sinh viên");
        System.out.print("➨ ");
        String gioiTinh = scanner.nextLine();
        while (!ValidationUtils.isGioitinh(gioiTinh)) {
            System.out.println("Giới tính " + gioiTinh + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và có dấu)");
            System.out.println("Nhập giới tính (ví dụ: Nam hoặc Nữ");
            System.out.print("➨ ");
            gioiTinh = scanner.nextLine();
        }


        System.out.print("Nhập địa chỉ: \n ➨ \t");
        String diaChi = scanner.nextLine();
        while (!ValidationUtils.isAddressValid(diaChi)) {
            System.out.println("Địa chỉ " + diaChi + " chưa hợp lệ. Mời nhập lại (Địa chỉ bắt đầu bằng số) \n " +
                    "\t (vd: 30, Dong Da, Hue)");
            diaChi = scanner.nextLine();
        }

        System.out.print("Nhập điểm trung bình ");
        double diemTrungBinh;
        do {
            diemTrungBinh = Double.parseDouble(scanner.nextLine());
            if (!(diemTrungBinh > 0)) {
                System.out.print("Nhập sai. Xin vui lòng nhập lại!");
                diemTrungBinh = Double.parseDouble(scanner.nextLine());
            }
        } while (!(diemTrungBinh > 0));

        Student student = new Student(id, ten, tuoi, gioiTinh, diaChi, diemTrungBinh);
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
//        show(productService.getItem());
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
                    System.out.print("Nhấn 'c' để tiếp tục cập nhật \nNhấn 'b' để quay lại \nNhấn 'e' để thoát... \n=> \t");
                    String chon = scanner.nextLine();
                    switch (chon) {
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
            System.out.printf("%-20s %-30s %-10s %-10s %-20s %-15s\n", student.getStudentID(), student.getName(), student.getAge(), student.getGender(), student.getAddress(), student.getDiemTrungBinh());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------\n");
    }
    public static void showAll() {
        List<Student> studentList = studentService.getItem();
        System.out.println("menu");

            System.out.println("---------------------------------- DANH SÁCH SINH VIÊN ------------------------------------------------------");
            System.out.printf("%-20s %-30s %-10s %-10s %-20s %-15s\n", "Mã sinh viên", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ", "Điểm TB");
            for (Student student : studentList) {
                System.out.printf("%-20s %-30s %-10s %-10s %-20s %-15s\n", student.getStudentID(), student.getName(), student.getAge(), student.getGender(), student.getAddress(), student.getDiemTrungBinh());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
            do {
                System.out.print("Nhấn 'b' để quay lại || Nhấn 'e' để thoát chương trình");
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

    public void showStudent() {
        show(studentService.getItem());

        do {
            System.out.println("Nhấn 'c' để trở lại     ||  Nhấn 'e' để thoát chương trình");
            System.out.printf(" ➨ \t");
            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "c":
                        Menu.mainMenu();
                        break;
                    case "e":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Vui lòng nhập lại!");

                }
            } catch (Exception e) {
                System.out.println("Chưa hợp lệ! Xin vui lòng nhập lại!");
            }
        } while (true);
    }

    public static void inputTen(int id) {
        Student student = studentService.getStudentByID(id);
        System.out.printf("Cập nhật tên sinh viên: \n➨ \t");
        String ten = scanner.nextLine().trim();
        student.setName(ten);
        studentService.update(student);
        show(studentService.getItem());
        System.out.println("Cập nhật thành công!");
    }

    public static void inputTuoi(int id) {
        Student student = studentService.getStudentByID(id);
        System.out.printf("Cập nhật tuổi sinh viên: \n➨ \t");
        int tuoi = Integer.parseInt(scanner.nextLine().trim());
        student.setAge(tuoi);
        studentService.update(student);
        show(studentService.getItem());
        System.out.println("Cập nhật thành công!");
    }

    public static void inputGioiTinh(int id) {
        Student student = studentService.getStudentByID(id);
        System.out.printf("Cập nhật giới tính sinh viên: \n➨ \t");
        String gioiTinh = scanner.nextLine();
        student.setGender(gioiTinh);
        studentService.update(student);
        show(studentService.getItem());
        System.out.println("Cập nhật thành công!");
    }

    public static void inputDiachi(int id) {
        Student student = studentService.getStudentByID(id);
        System.out.printf("Cập nhật địa chỉ của sinh viên: \n➨ \t");
        String diaChi = scanner.nextLine();
        student.setAddress(diaChi);
        studentService.update(student);
        show(studentService.getItem());
        System.out.println("Cập nhật thành công!");
    }

    public static void inputDiemTrungBinh(int id) {
        Student student = studentService.getStudentByID(id);
        System.out.printf("Cập nhật điểm trung bình của sinh viên: \n➨ \t");
        double diemTrungBinh = Double.parseDouble(scanner.nextLine().trim());
        student.setDiemTrungBinh(diemTrungBinh);
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
            boolean check = true;
            Menu.removeConfirm();
            String number = scanner.nextLine();
            try {
                switch (number) {
                    case "y":
                        studentService.remove(student.getStudentID());
                        System.out.println("Xóa thành công sinh viên ra khỏi danh sách!");
                        show(studentService.getItem());
                        do {
                            System.out.println("Nhấn '1' để quay lại ||  Nhấn '2' để thoát");
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
                                    System.out.println("\t Nhập lại!!! ");
                                    check = false;
                            }
                        } while (!check);
                        break;
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
