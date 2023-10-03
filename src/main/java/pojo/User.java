package pojo;

// 数据库表条目对象
//mysql构建:
//create table user (
//        user_name varchar(20) not null primary key,
//        user_password varchar(20) not null,
//        user_type int default 0
//        );


public class User
{
    protected String userName;
    protected String userPassword;
    protected Integer userType;

    public User(){}

    public User(String userName, String userPassword, Integer userType) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userType=" + userType +
                '}';
    }

    public boolean equal(User other)
    {
        return this.userName.equals(other.userName)&&this.userPassword.equals(other.userPassword)
                &&this.userType.equals(other.userType);
    }
}
