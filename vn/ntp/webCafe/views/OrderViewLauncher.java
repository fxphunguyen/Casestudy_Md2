package vn.ntp.webCafe.views;

import java.util.Scanner;

public class OrderViewLauncher {
    public static void run() {
        MainLauncher.orderMenu();
        Scanner scanner = new Scanner(System.in);
        OderView oderView = new OderView();
        System.out.println("\n Chon chuc nang");
        System.out.print("⭆");
        int choices = scanner.nextInt();
        switch (choices) {
            case 1:
                oderView.addOrder();
                break;
            case 2:
                oderView.showAllOrder();
                break;
            default:
                System.out.println("Chon sai! Vui long nhap lai");
                run();
        }
    }
}
