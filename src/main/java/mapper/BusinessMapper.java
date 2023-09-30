package mapper;

import pojo.Comment;
import pojo.Order;
import pojo.ShopItem;
import pojo.ShopItemFrequency;

import java.util.List;

public interface BusinessMapper
{
    //TODO 添加BusinessMapper.xml
    //查询商店的所有商品
    List<ShopItem> QueryAllShopItem(String BusinessName);
    //查询商店所有评论
    List<Comment> QueryAllCommentByBusiness(String BusinessName);
    //查询商店的所有订单
    List<Order> QueryAllOrderByShop(String BusinessName);





}
