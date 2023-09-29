package DB;

import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import pojo.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/// 数据库操作对象
public class DBop {
    private static boolean flag = true;//单例
    SqlSessionFactory sqlSessionFactory;
    SqlSessionManager sqlSessionManager;

    private DBop() throws IOException {
        synchronized (DBop.class){
            if(flag){
                flag = false;
            }else {
                throw new RuntimeException();
            }
        }

        //1.加载MyBatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //不使用线程不安全的DefaultSqlSession和SqlSessionFactory,改为使用封装的SqlSessionFactory。用法参照方法getPasswordById()
        //线程安全性的解决方案:将sqlSession置为局部变量
        inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionManager = SqlSessionManager.newInstance(inputStream);
        System.out.println("数据库连接成功");
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

    public String getPasswordById(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //4.执行方法
        String res = userMapper.getPasswordById(name);

        sqlSession.commit();
        sqlSession.close();
        return res;
    }


    public boolean CreateUser(String name, String passward, Integer type)
    {
        Entity tmp = Util.Int2Entity(type);
        //TODO 创建用户
        if(!hasAccount(name))
        {
            User user = new User(name,passward,tmp);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //4.执行方法
            userMapper.CreateUser(user);
            sqlSession.commit();
            sqlSession.close();
            return true;
        }
        else
        {
                return false;
        }

    }

    //TODO 销毁用户
    public boolean DeleteUser(String name)
    {
        return false;
    }


    public Integer getAccountTypeById(String name){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //4.执行方法
        Integer res = userMapper.getAccountTypeById(name);

        sqlSession.commit();
        sqlSession.close();
        return res;
    }

    public boolean hasAccount(String name)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("检测账户存在性:"+name);
        //4.执行方法
        String res = userMapper.getAccount(name);

        sqlSession.commit();
        sqlSession.close();
        return res != null;
    }

    public User getUserById(String name){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User res = userMapper.getUserById(name);
        sqlSession.commit();
        sqlSession.close();
        return res;
    }




    public List<ShopItem> getShopItemsById(String BusinessName)
    {
        //TODO 从数据库查找 商家的商品
        return null;
    }

    public List<Order> getOrdersById(String name)
    {
        //TODO 根据主体查找它的订单  只能是商家、客户、骑手
        return null;
    }





}
