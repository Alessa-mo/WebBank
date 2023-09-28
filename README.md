# WebBank
后台处理逻辑在processor类里，已完成登录和注册逻辑

## 数据库表
1.user
   ```
    create table user (
 user_name varchar(20) primary key,
 user_password varchar(20) not null,
 account_type int default 0);
   ```
