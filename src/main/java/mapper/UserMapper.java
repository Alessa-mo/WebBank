package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.User;

import java.util.List;

public interface UserMapper
{
    //void openAccount(User user);
    //void deleteAccount(String name);

    void CreateUser(User user);
    void DeleteUser(String name);
    //注销



//
//    int setBalanceById(@Param("accountNum")int accountNum,@Param("balance")double balance);
//    List<User> lockTransferAccount(@Param("accountNum1")int accountNum1, @Param("accountNum2")int accountNum2);
    //查询指定账户密码(登录验证)
    String getPasswordById(String name);

    //查询指定账户类型(登录后跳转)
    Integer getAccountTypeById(String name);

    //查询指定账户是否存在:回显
    String getAccount(String name);

    //查询指定账户
    User getUserById(String name);
}
