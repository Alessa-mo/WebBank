package pojo;

// 数据库表条目对象
//mysql构建:
//create table store (
//        store_name varchar(20) not null primary key,
//        store_location int not null,
//        store_detail_location varchar(256) not null,
//        store_describe varchar(1024)
//        );

public class Store {
    protected String storeName;
    protected Integer storeLocation;
    protected String storeDetailLocation;
    protected String storeDescribe;

    public Store(String storeName, Integer storeLocation, String storeDetailLocation, String storeDescribe) {
        this.storeName = storeName;
        this.storeLocation = storeLocation;
        this.storeDetailLocation = storeDetailLocation;
        this.storeDescribe = storeDescribe;
    }

    public Store(String storeName, Integer storeLocation, String storeDetailLocation) {
        this.storeName = storeName;
        this.storeLocation = storeLocation;
        this.storeDetailLocation = storeDetailLocation;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(Integer storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getStoreDetailLocation() {
        return storeDetailLocation;
    }

    public void setStoreDetailLocation(String storeDetailLocation) {
        this.storeDetailLocation = storeDetailLocation;
    }

    public String getStoreDescribe() {
        return storeDescribe;
    }

    public void setStoreDescribe(String storeDescribe) {
        this.storeDescribe = storeDescribe;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                ", storeLocation=" + storeLocation +
                ", storeDetailLocation='" + storeDetailLocation + '\'' +
                ", storeDescribe='" + storeDescribe + '\'' +
                '}';
    }
}
