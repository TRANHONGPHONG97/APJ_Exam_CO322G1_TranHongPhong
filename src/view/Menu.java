package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    static Scanner scanner = new Scanner(System.in);

    public static void startMenu() {
        try {
            int choice;
            do {
                mainMenu();
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        StudentView.showAll();
                        break;
                    case 2:
                        StudentView.add();
                        break;
                    case 3:
                        StudentView.update();
                        break;
                    case 4:
                        StudentView.remove();
                        break;
                    case 5:
                        MenuSort.option();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Không hợp lệ. Vui lòng nhập lại");
                }
            } while (true);
        } catch (InputMismatchException io) {
            io.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void mainMenu() {
        System.out.println("===============================================");
        System.out.println("=               Quản lý sinh viên             =");
        System.out.println("===============================================");
        System.out.println("=           1. Xem danh sách                  =");
        System.out.println("=           2. Thêm mới                       =");
        System.out.println("=           3. Cập nhật                       =");
        System.out.println("=           4. Xóa                            =");
        System.out.println("=           5. Sắp Xếp                        =");
        System.out.println("=           0. Thoát                          =");
        System.out.println("===============================================");
        System.out.println("Chọn chức năng");
        System.out.printf("➨ \t");
    }

    public static void inputUpdate() {
        System.out.println("-------------   Cập nhật   ---------------");
        System.out.println("-    1. Cập nhật họ tên sinh viên        -");
        System.out.println("-    2. Cập nhật tuổi của sinh viên      -");
        System.out.println("-    3. Cập nhật giới tính sinh viên     -");
        System.out.println("-    4. Cập nhật địa chỉ                 -");
        System.out.println("-    5. Cập nhật điểm trung bình         -");
        System.out.println("-    0. Quay lại                         -");
        System.out.println("------------------------------------------");
        System.out.println("Chọn chức năng");
        System.out.printf("➨ \t");
    }

    public static void removeConfirm() {
        System.out.println("1. Nhấn y để xác nhận xóa || 2. Nhấn c để quay lại\n");
        System.out.printf("➨ \t");
    }
}
