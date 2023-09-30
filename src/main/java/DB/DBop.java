package DB;

import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionManager;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;

/// 数据库操作对象
public class DBop {
    private static boolean flag = true;//单例
    SqlSessionManager sqlSessionManager;

    public UserOp userOp = new UserOp();
    public StoreOp storeOp = new StoreOp();
    public OrdersOp ordersOp = new OrdersOp();
    public GoodsOp goodsOp = new GoodsOp();
    public FrequencyOp frequencyOp = new FrequencyOp();
    public CommentOp commentOp = new CommentOp();


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
            return res == null || res.isEmpty();
        }

        //注册
        public boolean logOn(String userName, String userPassword, Integer userType) {
            if(hasAccount(userName)){
                System.out.println("DBop:该账户已存在");
                return false;
            } else {
                User user = new User(userName,userPassword,userType);
                userMapper.createUser(user);
                return true;
            }
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
    }

    public class StoreOp {

    }

    public class OrdersOp {

    }

    public class GoodsOp {

    }

    public class FrequencyOp {

    }

    public class CommentOp {

    }
}
