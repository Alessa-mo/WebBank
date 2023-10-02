package mapper;

import pojo.Orders;

import java.util.List;

public interface OrdersMapper {
    void createOrder(Orders orders);

    void deleteOrder(Integer orderID);

    Orders getOrderByID(Integer orderID);

    void updateOrder(Orders orders);

    List<Orders> getAllOrdersToDeliver();

    List<Orders> getAllOrdersOfUser(String userName);

    List<Orders> getAllOrdersOfStore(String storeName);
}
