package vn.ntp.webCafe.views;

import vn.ntp.webCafe.model.Order;
import vn.ntp.webCafe.model.OrderItem;
import vn.ntp.webCafe.model.Product;
import vn.ntp.webCafe.sevice.*;
import vn.ntp.webCafe.utils.AppUtils;
import vn.ntp.webCafe.utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class OderView {
    private final IOrderService orderService;
    private final IProductService productService;
    private final IOrderItemService orderItemService;
    private final Scanner scanner = new Scanner(System.in);

    public OderView() {
        productService = ProductService.getInstance();
        orderService = OrderService.getInstance();
        orderItemService = OrderItemSevice.getInstance();
    }

    public OrderItem addOrderItem(long orderId) {
        orderItemService.findAll();
        ProductView productView = new ProductView();
        productView.showProducts(InputOption.ADD);
        long id = System.currentTimeMillis() / 1000;
        System.out.println("Nhap id san pham");
        System.out.print("⭆ ");
        int producId = scanner.nextInt();
        scanner.nextInt();
        while (!productService.existsById(producId)) {
            System.out.println("Id sản phẩm không tìm thấy");
            System.out.println("Nhập id sản phẩm");
            System.out.print("⭆ ");
            producId = scanner.nextInt();
        }
        Product product = productService.findById(producId);
        double price = product.getPrice();
        int oldQuantity = product.getQuantity();
        System.out.println("Nhập số lượng");
        System.out.println("⭆ ");
        int quantity = scanner.nextInt();
        scanner.nextInt();
        while (!checkQualityBakery(product, quantity)) {
            System.out.println("Vượt quá số lượng! Vui lòng nhập lại");
            System.out.println("Nhập số lượng");
            System.out.print("⭆");
            quantity = scanner.nextInt();
        }
        String cafeName = product.getTitle();
        double total = quantity * price;
        int currentQuantity = oldQuantity - quantity;
        product.setQuantity(currentQuantity);
        OrderItem orderItem = new OrderItem(id, price, quantity, orderId, cafeName, total);
        return orderItem;
    }

    public boolean checkQualityBakery(Product product, int quantity) {
        if (quantity <= product.getQuantity())
            return true;
        else
            return false;
    }

    public void addOrder() {
        try {
            orderService.findAll();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Nhập họ tên ");
            System.out.print("⭆");
            String name = scanner.nextLine();
            while (!ValidateUtils.isNameValid(name)) {
                System.out.println("Tên \" + name + \" không đúng.\" + \" Vui lòng nhập lại!\" + \" (Tên phải viết hoa chữ cái đầu và không dấu)");
                System.out.println("Nhập tên");
                System.out.println("⭆");
                name = scanner.nextLine();
            }
            System.out.println("Nhập số điện thoại");
            System.out.print("⭆ ");
            String phone = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số \" + phone + \" của bạn không đúng. Vui lòng nhập lại! \" + \"(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                System.out.println("Nhập số điện thoại");
                System.out.print("⭆ ");
                phone = scanner.nextLine();
            }
            System.out.println("Nhập địa chỉ");
            System.out.print("⭆ ");
            String address = scanner.nextLine();
            OrderItem orderItem = addOrderItem(orderId);
            Order order = new Order(orderId, name, phone, address);
            orderItemService.add(orderItem);
            orderService.add(order);
            System.out.println("Tạo đơn hàng thành công");
            do {
                System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
                System.out.println("㋡                                         ㋡");
                System.out.println("㋡     1.Nhấn 'y' để tạo tiếp đơn hàng     ㋡");
                System.out.println("㋡     2. Nhấn 'q' để trở lại              ㋡");
                System.out.println("㋡     3. nhấp 'p' để in hoá đơn           ㋡");
                System.out.println("㋡     4. Nhấn 't' để thoát                ㋡");
                System.out.println("㋡                                         ㋡");
                System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "y":
                        addOrder();
                        break;
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "p":
                        showPaymentInfo(orderItem, order);
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Sai cú pháp! Vui lòng nhập lại");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Sai cú pháp! Vui lòng nhập lại");
        }
    }

    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("----------------------------------------------------------HOÁ ĐƠN----------------------------------------------------------------");
            System.out.printf("|%-15s %-20s %-15s %-15s %-15s %-15s %-15s\n|", "   Id", "Tên khách hàng", "   SĐT", "Địa chỉ", "Tên sản phẩm", "Số lượng", "Giá");
            System.out.printf("%-15d %-20s %-15s %-15s %-15s %-15d %-15f \n|", order.getId(), order.getFullName(), order.getMobile(),
                    order.getAddress(), orderItem.getProductName(), orderItem.getQuantity(), orderItem.getPrice());
            System.out.println(" ------------------------------------------------------------------------------------------------- Tổng tiền:" + order.getGrandTotal());
            System.out.println("---------------------------------------------CAFE VIET-----------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.println("Nhấn ");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Sai cú pháp! Vui lòng nhập lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showAllOrder() {
        List<Order> orders = orderService.findAll();
        List<OrderItem> orderItems = orderItemService.findAll();
        OrderItem newOrderItem = new OrderItem();
        try {
            System.out.println("----------------------------------------------------------LIST ORDER--------------------------------------------------------------------");
            System.out.println("|                                                                                                                                      |");
            System.out.printf("|%-15s %-20s %-12s %-23s %-10s %-10s %-15s %-21s\n|", "   Id", "Tên khách hàng", "  SĐT", "Địa chỉ", "Tên sản phẩm", "Số lượng", "   Giá", "   Tổng" + "               |");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        break;
                    }
                }
                System.out.printf("%-15d %-20s %-12s %-23s %-10s %-10d %-15f %-21f %-7s\n|",
                        order.getId(),
                        order.getFullName(),
                        order.getMobile(),
                        order.getAddress(),
                        newOrderItem.getProductName(),
                        newOrderItem.getQuantity(),
                        newOrderItem.getPrice(),
                        newOrderItem.getPrice() * newOrderItem.getQuantity(),
                        "|");
            }
            System.out.println("                                                                                                                                      |");
            System.out.println("---------------------------------------------CAFE VIET----------------------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
