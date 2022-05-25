package vn.ntp.webCafe.sevice;

import vn.ntp.webCafe.model.OrderItem;
import vn.ntp.webCafe.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemSevice implements IOrderItemService {
    private final static String PATH = "data/order-item.csv";
    private static OrderItemSevice instance;

    private OrderItemSevice() {

    }

    public static OrderItemSevice getInstance() {
        if (instance == null)
            instance = new OrderItemSevice();
        return instance;
    }

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            orderItems.add(new OrderItem(record));
        }
        return orderItems;
    }

    @Override
    public void add(OrderItem newOrderItem) {
        List<OrderItem> orderItems = findAll();
        orderItems.add(newOrderItem);
        CSVUtils.write(PATH, orderItems);
    }

    @Override
    public void update(OrderItem newOrderItem) {
        List<OrderItem> orderItems = findAll();
        CSVUtils.write(PATH, orderItems);
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        List<OrderItem> orderItems = findAll();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getOrderId() == id)
                return orderItem;
        }
        return null;
    }
}
