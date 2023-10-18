package json;

import com.alibaba.fastjson.JSONObject;
import org.joda.time.DateTime;
import pojo.*;

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
        Integer ID = jsonObject.getInteger("ID");
        String Customer = jsonObject.getString("Customer");
        String Store = jsonObject.getString("Store");
        String orderList = jsonObject.getJSONObject("OrderList").toJSONString();
        String TotalPrice =jsonObject.getString("TotalPrice");
        String OrderType = jsonObject.getString("OrderType");
        Integer orderStatus = jsonObject.getInteger("OrderStatus");
        String time = jsonObject.getString("OrderTime");

        return ID==null?new Orders(Customer,Store,orderList,TotalPrice,OrderType,time,orderStatus):
                        new Orders(ID,Customer,Store,orderList,TotalPrice,OrderType,time,orderStatus);
    }

    public static Comment JsonToComment(JSONObject jsonObject)
    {
        Integer ID = jsonObject.getInteger("ID");
        String Name = jsonObject.getString("Name");
        Integer GoodID = jsonObject.getInteger("Good");
        String CommentTag = jsonObject.getString("Tag");
        String CommentText = jsonObject.getString("Text");

        return ID ==null ? new Comment(Name,GoodID,CommentTag,CommentText):
                new Comment(ID,Name,GoodID,CommentTag,CommentText);
    }



    public static Store JsonToStore(JSONObject jsonObject)
    {
        String Name = jsonObject.getString("Name");
        Integer Location = jsonObject.getInteger("Location");
        String DetailLocation = jsonObject.getString("DLocation");
        String Describe = jsonObject.getString("Describe");

        return new Store(Name,Location,DetailLocation,Describe);
    }

}
