package pojo;

// 数据库表条目对象
/**数据库表:
 * create table user (
 * user_id int not null primary key auto_increment,
 * user_name varchar(20) not null,
 * user_password varchar(20) not null,
 * balance double default 0,
 * account_type int default 0);
 */

public class User {
    private String name;
    private String password;
    private Integer accountType;//账户类型,0为customer,1为bank_clerks



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}
