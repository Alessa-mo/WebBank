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

import com.alibaba.fastjson.JSON;
import org.joda.time.DateTime;

public class Orders {
    protected Integer orderID;
    protected String orderOrderer;
    protected String orderStore;
    protected JSON orderList;
    protected String orderType;
    protected DateTime orderTime;
    protected Integer orderStatus;

    public Orders(){}

    public Orders(Integer orderID, String orderOrderer, String orderStore, JSON orderList, String orderType, DateTime orderTime, Integer orderStatus) {
        this.orderID = orderID;
        this.orderOrderer = orderOrderer;
        this.orderStore = orderStore;
        this.orderList = orderList;
        this.orderType = orderType;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }

    public Orders(String orderOrderer, String orderStore, JSON orderList, String orderType, DateTime orderTime, Integer orderStatus) {
        this.orderOrderer = orderOrderer;
        this.orderStore = orderStore;
        this.orderList = orderList;
        this.orderType = orderType;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }

    public Orders(String orderOrderer, String orderStore, JSON orderList, String orderType, DateTime orderTime) {
        this.orderOrderer = orderOrderer;
        this.orderStore = orderStore;
        this.orderList = orderList;
        this.orderType = orderType;
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderID=" + orderID +
                ", orderOrderer='" + orderOrderer + '\'' +
                ", orderStore='" + orderStore + '\'' +
                ", orderList=" + orderList.toString() +
                ", orderType='" + orderType + '\'' +
                ", orderTime=" + orderTime +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
