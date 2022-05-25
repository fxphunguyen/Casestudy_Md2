package vn.ntp.webCafe.views;

import vn.ntp.webCafe.sevice.IUserService;
import vn.ntp.webCafe.sevice.UserService;
import vn.ntp.webCafe.utils.AppUtils;

import java.util.Scanner;

public class AdminView {
    private final IUserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public AdminView() {
        userService = UserService.getInstance();
    }

    public void adminLogin() {
        boolean isRetry;
        System.out.println("✽ ✽ ✽ ✽ ✽ ✽ ✽ ✽ ĐĂNG NHẬP HỆ THỐNG ✽ ✽ ✽ ✽ ✽ ✽ ✽ ✽ ");
        do {
            System.out.println("Username");
            String username = AppUtils.retryString("Username");
            System.out.println("Password");
            String password = AppUtils.retryString("Password");
            if (userService.adminLogin(username, password) == null) {
                System.out.println("Tài khoản không hợp lệ!");
                isRetry = isRetry();
            }else {
                System.out.println("CHÀO MỪNG ĐẾN VỚI WEB CAFE VIET \n");
                isRetry = false;
            }
        }while (isRetry);
    }

    private boolean isRetry() {
        do {
            try {
                System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ CHỌN ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
                System.out.println("✿                                      ✿");
                System.out.println("✿   1.Nhấn 'y' để đăng nhập lại        ✿");
                System.out.println("✿   2.Nhấn 'n' để thoát chương trình   ✿");
                System.out.println("✿                                      ✿");
                System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
                System.out.print(" ⭆ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "n":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Sai chức năng! Vui lòng chọn lại");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai! Vui lòng  chọn lại");
                ex.printStackTrace();
            }
        } while (true);
    }
}
