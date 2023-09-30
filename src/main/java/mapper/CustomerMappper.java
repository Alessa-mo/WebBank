package mapper;

import pojo.Comment;
import pojo.Order;
import pojo.ShopItemFrequency;

import java.util.List;

public interface CustomerMappper
{
    //TODO 添加CustomerMapper.xml
    //查询客户的购买商品频次表
    List<ShopItemFrequency> QueryAllFrequency(String CustomerName);

    //查询客户的所有订单
    List<Order> QueryAllOrderByCustomer(String CustomerName);

    //查询客户所有评论
    List<Comment> QueryAllCommentByCustomer(String CustomerName);
}
