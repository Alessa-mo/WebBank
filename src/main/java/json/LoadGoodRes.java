package json;

import java.util.List;

public class LoadGoodRes extends AbstractRes
{
    //TODO 完善JSON格式问题
    List<ShopItem> ShopItems;

    public void FillShopItems(List<ShopItem> sis) { this.ShopItems = sis;}
    public List<ShopItem> GetShopItems() { return this.ShopItems;}

}
