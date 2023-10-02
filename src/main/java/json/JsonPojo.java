package json;

import com.alibaba.fastjson.JSONObject;
import org.joda.time.DateTime;
import pojo.Comment;
import pojo.Goods;
import pojo.Orders;
import pojo.User;

public class JsonPojo
{

    public static User JsonToUser(JSONObject jsonObject)
    {
        User user = new User();
        user.setUserName(jsonObject.getString("username"));
        user.setUserPassword(jsonObject.getString("pwd"));
        user.setUserType(jsonObject.getInteger("type"));
        return user;
    }
    public static Orders JsonToOrders(JSONObject jsonObject)
    {
        String Customer = jsonObject.getString("Customer");
        String Store = jsonObject.getString("Store");
        String orderList = jsonObject.getString("OrderList");
        String TotalPrice =jsonObject.getString("TotalPrice");
        String OrderType = jsonObject.getString("OrderType");
        Integer orderStatus = jsonObject.getInteger("OrderStatus");
        String time = jsonObject.getString("OrderTime");

        return new Orders(Customer,Store,orderList,TotalPrice,OrderType,time,orderStatus);
    }

    public static Comment JsonToComment(JSONObject jsonObject)
    {
        String Name = jsonObject.getString("Origin");
        Integer GoodID = jsonObject.getInteger("Good");
        String CommentTag = jsonObject.getString("Tag");
        String CommentText = jsonObject.getString("Text");

        return new Comment(Name,GoodID,CommentTag,CommentText);
    }

    public static Goods JsonToGood(JSONObject jsonObject)
    {
        String store = jsonObject.getString("Store");
        String url = jsonObject.getString("URL");
        String Describe = jsonObject.getString("Describe");
        String Price = jsonObject.getString("Price");
        Integer Amount = jsonObject.getInteger("Amount");

        return new Goods(store,url,Describe,Price,Amount);
    }

}
