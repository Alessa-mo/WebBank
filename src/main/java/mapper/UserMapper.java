package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.User;

import java.util.List;

public interface UserMapper
{
    void CreateUser(User user);
    void DeleteUser(String name);

    String getPasswordById(String name);

    //查询指定账户类型(登录后跳转)
    Integer getAccountTypeById(String name);

    //查询指定账户是否存在:回显
    String getAccount(String name);

    //查询指定账户
    User getUserById(String name);

}
