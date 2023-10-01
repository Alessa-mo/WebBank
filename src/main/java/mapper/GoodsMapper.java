package mapper;

import pojo.Goods;

import java.util.List;

public interface GoodsMapper {
    void createGoods(Goods goods);

    void deleteGoods(Integer goodsID);

    Goods getGoodsByID(Integer goodsID);

    void updateGoods(Goods goods);

    List<Goods> getAllGoodsOfStore(String storeName);

}
