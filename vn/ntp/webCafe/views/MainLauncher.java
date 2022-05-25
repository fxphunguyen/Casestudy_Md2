package vn.ntp.webCafe.views;

import java.util.Scanner;

public class MainLauncher {

    public static void launch() {
        AdminView adminView = new AdminView();
        adminView.adminLogin();
        menuOption();
    }

    public static void menuOption() {
        do {
            mainMenu();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println(" \n Chọn chức năng");
                System.out.print("⭆");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        UserViewLauncher.launch();
                        break;
                    case 2:
                        ProductViewLauncher.run();
                        break;
                    case 3:
                        OrderViewLauncher.run();
                        break;
                    default:
                        System.out.println("Sai chức năng! Vui lòng chọn lại");
                        menuOption();
                }
            } catch (IndexOutOfBoundsException io) {
                System.out.println("Sai cú pháp! Vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void mainMenu() {
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬MAIN MENU ✬ ✬ ✬ ✬ ✬ ✬✬");
        System.out.println("\t ✬                                      ✬");
        System.out.println("\t ✬     1. Quản lí người dùng            ✬");
        System.out.println("\t ✬     2. Quản lí sản phẩm              ✬");
        System.out.println("\t ✬     3. Quản lí đơn đặt hàng          ✬");
        System.out.println("\t ✬                                      ✬");
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬✬");
    }

    public static void orderMenu() {
        System.out.println("✬ ✬ ✬ ✬ ✬ ✬ ✬ORDER MENU✬ ✬ ✬ ✬ ✬ ✬ ✬");
        System.out.println("✬                                       ✬");
        System.out.println("✬     1. Tạo đơn hàng                   ✬");
        System.out.println("✬     2. Xem danh sách đơn hàng         ✬");
        System.out.println("✬                                       ✬");
        System.out.println("✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
    }
}
