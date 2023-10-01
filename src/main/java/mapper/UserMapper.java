package mapper;

import pojo.User;

public interface UserMapper
{
    void createUser(User user);
    //注销
    void deleteUser(String userName);

    //查询指定账户密码(登录验证)
    String getPasswordByName(String userName);

    //查询指定账户类型(登录后跳转)
    Integer getAccountTypeByName(String userName);

    //查询指定用户是否存在:回显
    String hasAccount(String userName);

    //查询指定User表的完整条目，结果绑定至User对象
    User getUserByName(String userName);

    void updateUser(User user);
}
