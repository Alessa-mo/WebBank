package json.Order;

import json.AbstractRes;
import pojo.Orders;

import java.util.List;

public class LoadOrderRes extends AbstractRes
{
    //TODO 完善JSON格式问题
    List<Orders> OrdersList;

    public List<Orders> getOrdersList() { return OrdersList;}

    public void FillOrders(List<Orders> ordersList) { this.OrdersList = ordersList; }

}
