package json.Order;

import json.AbstractRes;
import pojo.Orders;

public class PushOrderRes extends AbstractRes
{
    Orders orders;
    public void SetOrder(Orders o) { this.orders = o ; }
}
