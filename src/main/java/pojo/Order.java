package pojo;

import java.sql.Time;
import java.util.List;

public class Order
{
    //TODO 补充相关函数
    int ID;

    String Customer;
    String Business;

    List<ShopItem> Lists;
    String TotalPrice;

    int Type;
    int Status;
    Time StartTime;


}
