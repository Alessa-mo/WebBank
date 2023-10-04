package pojo;

// 数据库表条目对象
//mysql构建:
//create table goods (
//        goods_id int not null primary key auto_increment,
//        goods_store varchar(20) not null,
//        goods_name varchar(50),
//        goods_photo_url text,
//        goods_describe varchar(1024),
//        goods_price varchar(7) not null,
//        goods_sale_amount int default 0
//        );

public class Goods {
    protected Integer goodsID;
    protected String goodsStore;
    protected String goodsName;
    protected String goodsPhotoURL;
    protected String goodsDescribe;
    protected String goodsPrice;
    protected Integer goodsSaleAmount;

    public Goods(Integer goodsID, String goodsStore, String goodsName, String goodsPhotoURL, String goodsDescribe, String goodsPrice, Integer goodsSaleAmount) {
        this.goodsID = goodsID;
        this.goodsStore = goodsStore;
        this.goodsName = goodsName;
        this.goodsPhotoURL = goodsPhotoURL;
        this.goodsDescribe = goodsDescribe;
        this.goodsPrice = goodsPrice;
        this.goodsSaleAmount = goodsSaleAmount;
    }

    public Goods(String goodsStore, String goodsName, String goodsPhotoURL, String goodsDescribe, String goodsPrice, Integer goodsSaleAmount) {
        this.goodsStore = goodsStore;
        this.goodsName = goodsName;
        this.goodsPhotoURL = goodsPhotoURL;
        this.goodsDescribe = goodsDescribe;
        this.goodsPrice = goodsPrice;
        this.goodsSaleAmount = goodsSaleAmount;
    }

    public Goods(String goodsStore, String goodsName, String goodsPhotoURL, String goodsDescribe, String goodsPrice) {
        this.goodsStore = goodsStore;
        this.goodsName = goodsName;
        this.goodsPhotoURL = goodsPhotoURL;
        this.goodsDescribe = goodsDescribe;
        this.goodsPrice = goodsPrice;
    }

    public Goods(String goodsStore, String goodsName, String goodsPrice, Integer goodsSaleAmount) {
        this.goodsStore = goodsStore;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsSaleAmount = goodsSaleAmount;
    }

    public Goods(String goodsStore, String goodsName, String goodsPrice) {
        this.goodsStore = goodsStore;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(Integer goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsStore() {
        return goodsStore;
    }

    public void setGoodsStore(String goodsStore) {
        this.goodsStore = goodsStore;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPhotoURL() {
        return goodsPhotoURL;
    }

    public void setGoodsPhotoURL(String goodsPhotoURL) {
        this.goodsPhotoURL = goodsPhotoURL;
    }

    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsSaleAmount() {
        return goodsSaleAmount;
    }

    public void setGoodsSaleAmount(Integer goodsSaleAmount) {
        this.goodsSaleAmount = goodsSaleAmount;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsID=" + goodsID +
                ", goodsStore='" + goodsStore + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPhotoURL='" + goodsPhotoURL + '\'' +
                ", goodsDescribe='" + goodsDescribe + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", goodsSaleAmount=" + goodsSaleAmount +
                '}';
    }
}
