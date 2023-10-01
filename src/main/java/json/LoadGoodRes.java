package json;

import pojo.Goods;

import java.util.List;

public class LoadGoodRes extends AbstractRes
{
    //TODO 完善JSON格式问题
    List<Goods> GoodList;

    public void FillGoodList(List<Goods> sis) { this.GoodList = sis;}
    public List<Goods> GetShopItems() { return this.GoodList;}

}
