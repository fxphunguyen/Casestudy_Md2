package vn.ntp.webCafe.views;

import java.util.Scanner;

public class UserViewLauncher {
    public static void launch() {
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();
        int option = -1;
        do {
            menuUser();
            try {
                do {
                    System.out.println("Chon chuc nang");
                    System.out.print("⭆ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 4 || option < 1)
                        System.out.println("Chon chuc nang khong dung! Vui long chon lai");
                } while (option > 4 || option < 1);
                switch (option) {
                    case 1:
                        userView.addUser();
                        break;
                    case 2:
                        userView.updateUser();
                        break;
                    case 3:
                        userView.showUsers(InputOption.SHOW);
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Chon chuc nang khong dung! Vui long chon lai");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhap sai! Vui long nhap lai");
            }
        } while (option != 4);
    }

    public static void menuUser() {
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪  USERS MANAGER  ⚪ ⚪ ⚪ ⚪ ⚪");
        System.out.println("⚪                                      ⚪");
        System.out.println("⚪     1. Thêm người dùng               ⚪");
        System.out.println("⚪     2. Sửa thông tin người dùng      ⚪");
        System.out.println("⚪     3. Hiện danh sách người dùng     ⚪");
        System.out.println("⚪     4. Quay lại                      ⚪");
        System.out.println("⚪                                      ⚪");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪");
    }


}
