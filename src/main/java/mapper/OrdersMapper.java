package mapper;

import pojo.Orders;

public interface OrdersMapper {
    void createOrder(Orders orders);

    void deleteOrder(Integer orderID);

    Orders getOrderByID(Integer orderID);

    void updateOrder(Orders orders);

}
