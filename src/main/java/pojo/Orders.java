package pojo;

// 数据库表条目对象
//mysql构建:
//create table orders (
//        order_id int not null primary key auto_increment,
//        order_orderer varchar(20) not null,
//        order_store varchar(20) not null,
//        order_list json not null,
//        order_total_price varchar(8) not null,
//        order_type int not null,
//        order_time datetime not null,
//        order_status int not null
//        );

public class Orders {
    protected Integer orderID;
    protected String orderOrderer;
    protected String orderStore;
    protected String orderList;
    protected String orderTotalPrice;
    protected String orderType;
    protected String orderTime;
    protected Integer orderStatus;

    public Orders(Integer orderID, String orderOrderer, String orderStore, String orderList, String orderTotalPrice, String orderType, String orderTime, Integer orderStatus) {
        this.orderID = orderID;
        this.orderOrderer = orderOrderer;
        this.orderStore = orderStore;
        this.orderList = orderList;
        this.orderTotalPrice = orderTotalPrice;
        this.orderType = orderType;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }

    public Orders(String orderOrderer, String orderStore, String orderList, String orderTotalPrice, String orderType, String orderTime, Integer orderStatus) {
        this.orderOrderer = orderOrderer;
        this.orderStore = orderStore;
        this.orderList = orderList;
        this.orderTotalPrice = orderTotalPrice;
        this.orderType = orderType;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }

    public Orders(String orderOrderer, String orderStore, String orderList, String orderTotalPrice, String orderType, String orderTime) {
        this.orderOrderer = orderOrderer;
        this.orderStore = orderStore;
        this.orderList = orderList;
        this.orderTotalPrice = orderTotalPrice;
        this.orderType = orderType;
        this.orderTime = orderTime;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getOrderOrderer() {
        return orderOrderer;
    }

    public void setOrderOrderer(String orderOrderer) {
        this.orderOrderer = orderOrderer;
    }

    public String getOrderStore() {
        return orderStore;
    }

    public void setOrderStore(String orderStore) {
        this.orderStore = orderStore;
    }

    public String getOrderList() {
        return orderList;
    }

    public void setOrderList(String orderList) {
        this.orderList = orderList;
    }

    public String getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(String orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderID=" + orderID +
                ", orderOrderer='" + orderOrderer + '\'' +
                ", orderStore='" + orderStore + '\'' +
                ", orderList=" + orderList +
                ", orderTotalPrice='" + orderTotalPrice + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderTime=" + orderTime +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
