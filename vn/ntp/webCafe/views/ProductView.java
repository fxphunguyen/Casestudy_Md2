package vn.ntp.webCafe.views;


import vn.ntp.webCafe.model.Product;
import vn.ntp.webCafe.sevice.ProductService;

import java.util.Scanner;

public class ProductView {
    public static Scanner scanner = new Scanner(System.in);
    private static ProductService productService =  ProductService.getInstance();
    public static void run() {

        int option;
        do {
            show();
            System.out.println("\nChọn chức năng ");
            System.out.print(" ⭆ ");
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    productService.add(new Product());
                    break;
                case 2:
                    System.out.println("cap nhat sản phẩm thanh cong");
                    break;
                case 3:
                    productService.findAllOrderByPriceASC();
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
        System.out.println("⚪     1. Thêm sản phẩm               ⚪");
        System.out.println("⚪     2. Thay đổi thông tin sản phẩm ⚪");
        System.out.println("⚪     3. Hiện danh sách sản phẩm     ⚪");
        System.out.println("⚪                                    ⚪");
        System.out.println("Nhấn '8' để trở lại \t|\t '9' để thoát chương trình");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪");
    }
}
