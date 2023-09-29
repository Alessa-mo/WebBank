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

public class User
{
    protected String Name;
    protected String Passward;
    protected Entity Type;

    public User(){}
    public User(String name,String passward,Entity type)
    {
        this.Name = name;
        this.Passward = passward;
        this.Type = type;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPassword() {
        return Passward;
    }

    public void setPassword(String password) {
        this.Passward = password;
    }

    public Entity getAccountType() {
        return this.Type;
    }

    public void setAccountType(Entity accountType) {
        this.Type = accountType;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + Name + '\'' +
                ", password='" + Passward + '\'' +
                ", accountType=" + Type +
                '}';
    }

}
