package json;

import pojo.Order;

import java.util.List;

public class LoadOrderRes extends AbstractRes
{
    //TODO 完善JSON格式问题
    List<Order> Orders;

    public List<Order> GetOrders() { return Orders;}

    public void FillOrdersByName(List<Order> orders) { this.Orders = orders; }
}
