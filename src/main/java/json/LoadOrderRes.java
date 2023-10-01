package json;

import pojo.Orders;

import java.util.List;

public class LoadOrderRes extends AbstractRes
{
    //TODO 完善JSON格式问题
    List<Orders> OrdersList;

    public List<Orders> GetOrders() { return OrdersList;}

    public void FillOrdersByName(List<Orders> ordersList) { this.OrdersList = ordersList; }
}
