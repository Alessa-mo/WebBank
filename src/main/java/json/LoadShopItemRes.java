package json;

import pojo.ShopItem;

import java.util.List;

public class LoadShopItemRes extends AbstractRes
{
    //TODO 完善JSON格式问题
    List<ShopItem> ShopItems;

    public void FillShopItems(List<ShopItem> sis) { this.ShopItems = sis;}
    public List<ShopItem> GetShopItems() { return this.ShopItems;}

}
