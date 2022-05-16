package vn.ntp.webCafe.views;

import java.util.Scanner;

public class UserView {

    public static Scanner scanner = new Scanner(System.in);

    public static void run() {
        int option;
        do {
            show();
            System.out.println("\nChọn chức năng ");
            System.out.print(" ⭆ ");
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    System.out.println("them nguoi dung thanh cong");
                    break;
                case 2:
                    System.out.println("cap nhat dung thanh cong");
                    break;
                case 3:
                    System.out.println("Hiện danh sách người dùng");
                    break;
                case 9:
                    System.exit(0);
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    break;
            }

        } while (option != 8);

    }

    public static void show() {
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪  USERS MANAGER  ⚪ ⚪ ⚪ ⚪ ⚪");
        System.out.println("⚪                                    ⚪");
        System.out.println("⚪     1. Thêm người dùng             ⚪");
        System.out.println("⚪     2. Sửa thông tin người dùng    ⚪");
        System.out.println("⚪     3. Hiện danh sách người dùng   ⚪");
        System.out.println("⚪                                    ⚪");
        System.out.println("Nhấn '8' để trở lại \t|\t '9' để thoát chương trình");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪");
    }

}

