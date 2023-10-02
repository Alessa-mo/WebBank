package DB;

import mapper.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionManager;
import pojo.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/// 数据库操作对象
public class DBop {
    private static boolean flag = true;//单例
    SqlSessionManager sqlSessionManager;

    public UserOp userOp;
    public StoreOp storeOp;
    public OrdersOp ordersOp;
    public GoodsOp goodsOp;
    public FrequencyOp frequencyOp;
    public CommentOp commentOp;


    private DBop() throws IOException {
        synchronized (DBop.class){
            if(flag){
                flag = false;
            }else {
                throw new RuntimeException();
            }
        }
        //加载MyBatis的核心配置文件，获取SqlSessionManager
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionManager = SqlSessionManager.newInstance(inputStream);
        System.out.println("DBop:数据库连接成功");

        userOp = new UserOp();
        storeOp = new StoreOp();
        ordersOp = new OrdersOp();
        goodsOp = new GoodsOp();
        frequencyOp = new FrequencyOp();
        commentOp = new CommentOp();
    }

    public volatile static DBop DBOP;
    public static DBop getInstance() throws IOException {
        if(DBOP == null){
            synchronized (DBop.class){
                if(DBOP == null){
                    DBOP = new DBop();
                }
            }
        }
        return DBOP;
    }

    public class UserOp {
        private final UserMapper userMapper = sqlSessionManager.getMapper(UserMapper.class);

        public boolean hasAccount(String name) {
            System.out.println("DBop:检验账户存在性");
            String res = userMapper.hasAccount(name);
            return res != null && !res.isEmpty();
        }

        //注册
        public void logOn(String userName, String userPassword, Integer userType) {
            User user = new User(userName,userPassword,userType);
            userMapper.createUser(user);
        }

        public void deleteAccount(String name) {
            userMapper.deleteUser(name);
        }

        public String getPasswordByName(String name) {
            return userMapper.getPasswordByName(name);
        }

        public Integer getAccountTypeByName(String name) {
            return userMapper.getAccountTypeByName(name);
        }

        public User getUserByName(String name) {
            return userMapper.getUserByName(name);
        }

        public void updateUser(User user) {
            userMapper.updateUser(user);
        }
    }

    public class StoreOp {
        private final StoreMapper storeMapper = sqlSessionManager.getMapper(StoreMapper.class);

        public void createStore(Store store) {
            storeMapper.createStore(store);
        }

        public void deleteStore(String name) {
            storeMapper.deleteStore(name);
        }

        public Integer getLocationMemByName(String name) {
            return storeMapper.getLocationMemByName(name);
        }

        public String getDetailLocationByName(String name) {
            return storeMapper.getDetailLocationByName(name);
        }

        public String getDescribeByName(String name) {
            return storeMapper.getDescribeByName(name);
        }

        public Store getStoreByName(String name) {
            return storeMapper.getStoreByName(name);
        }

        public void updateStore(Store store) {
            storeMapper.updateStore(store);
        }

        public List<Store> getAllStores() {return storeMapper.getAllStores();}
    }

    public class OrdersOp {
        private final OrdersMapper ordersMapper = sqlSessionManager.getMapper(OrdersMapper.class);

        public void createOrder(Orders orders) {
            ordersMapper.createOrder(orders);
        }

        public void deleteOrder(Integer orderID) {
            ordersMapper.deleteOrder(orderID);
        }

        public Orders getOrderByID(Integer orderID) {
            return ordersMapper.getOrderByID(orderID);
        }

        public void updateOrder(Orders orders) {
            ordersMapper.updateOrder(orders);
        }

        public List<Orders> getAllOrdersToDeliver() {
            return ordersMapper.getAllOrdersToDeliver();
        }

        public List<Orders> getAllOrdersOfUser(String userName) {
            return ordersMapper.getAllOrdersOfUser(userName);
        }

        public List<Orders> getAllOrdersOfStore(String storeName) {
            return ordersMapper.getAllOrdersOfStore(storeName);
        }
    }

    public class GoodsOp {
        private final GoodsMapper goodsMapper = sqlSessionManager.getMapper(GoodsMapper.class);

        public void createGoods(Goods goods) {
            goodsMapper.createGoods(goods);
        }

        public void deleteGoods(Integer goodsID) {
            goodsMapper.deleteGoods(goodsID);
        }

        public Goods getGoodsByID(Integer goodsID) {
            return goodsMapper.getGoodsByID(goodsID);
        }

        public void updateGoods(Goods goods) {
            goodsMapper.updateGoods(goods);
        }

        public List<Goods> getAllGoodsOfStore(String storeName) {
            return goodsMapper.getAllGoodsOfStore(storeName);
        }
    }

    public class FrequencyOp {
        private final FrequencyMapper frequencyMapper = sqlSessionManager.getMapper(FrequencyMapper.class);

        public void createFrequency(Frequency frequency) {
            frequencyMapper.createFrequency(frequency);
        }

        public void deleteFrequency(String userName, Integer goodsID) {
            frequencyMapper.deleteFrequency(userName, goodsID);
        }

        public Frequency getFrequency(String userName, Integer goodsID) {
            return frequencyMapper.getFrequency(userName, goodsID);
        }

        public List<Frequency> getFrequencyOfUser(String userName) {
            return frequencyMapper.getFrequencyOfUser(userName);
        }

        public void updateFrequency(Frequency frequency) {
            frequencyMapper.updateFrequency(frequency);
        }
    }

    public class CommentOp {
        private final CommentMapper commentMapper = sqlSessionManager.getMapper(CommentMapper.class);

        public void createMapper(Comment comment) {
            commentMapper.createMapper(comment);
        }

        public void deleteComment(Integer commentID) {
            commentMapper.deleteComment(commentID);
        }

        public Comment getCommentByID(Integer commentID) {
            return commentMapper.getCommentByID(commentID);
        }

        public void updateComment(Comment comment) {
            commentMapper.updateComment(comment);
        }

        public List<Comment> getCommentsOfGoods(Integer goodsID) {
            return commentMapper.getCommentsOfGoods(goodsID);
        }

        public List<Comment> getCommentsOfUser(String userName) {
            return commentMapper.getCommentsOfUser(userName);
        }
    }
}
