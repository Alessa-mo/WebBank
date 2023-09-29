package pojo;

import java.util.HashMap;
import java.util.Map;

public class Util
{
    public static Entity Int2Entity(Integer num)
    {
        switch (num)
        {
            case 0:
                return Entity.Admin;
            case 1:
                return Entity.Customer;
            case 2:
                return Entity.Business;
            case 3:
                return Entity.Rider;
            case 4:
                return Entity.ShopItem;
            case 5:
                return Entity.Order;
        }
        return Entity.Customer;
    }

    public static Integer Entity2Int(Entity type)
    {
        switch (type)
        {
            case Admin:
                return 0;
            case Customer:
                return 1;
            case Business:
                return 2;
            case Rider:
                return 3;
            case ShopItem:
                return 4;
            case Order:
                return 5;
        }
        return 1;
    }
}
