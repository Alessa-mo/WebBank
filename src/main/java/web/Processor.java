package web;

import com.alibaba.fastjson.JSONObject;
import json.*;
import com.alibaba.fastjson.JSON;
import DB.DBop;
import pojo.*;

import java.io.IOException;

//后端业务逻辑
public class Processor {
    private JSONObject message;
    private final DBop dbop = DBop.getInstance();

    public Processor() throws IOException
    {
    }

    void setMessage(JSONObject message){
        this.message = message;
    }

    public String parseMessage()
    {
        Integer op=message.getInteger("op");
        AbstractRes res;
        switch (op)
        {
            case 0: {
                DefaultRes defaultRes = new DefaultRes();
                if (dbop.CreateUser(message.getString("username"), message.getString("pwd"), Integer.parseInt(message.getString("type")))) {
                    defaultRes.setSuccess(DefaultRes.successCode);
                } else {
                    defaultRes.setSuccess(DefaultRes.failCode);
                    defaultRes.setWrongMessage("用户名已存在");
                }
                res = defaultRes;
                break;
            }
            case 1: {
                LoginRes loginRes = new LoginRes();
                String username = message.getString("username");
                if (dbop.getPasswordById(username).equals(message.getString("pwd"))) {
                    loginRes.setSuccess(LoginRes.successCode);
                    loginRes.setAccountType(dbop.getAccountTypeById(username));
                } else {
                    loginRes.setSuccess(LoginRes.failCode);
                    loginRes.setWrongMessage("用户名或密码错误");
                }
                res = loginRes;
                break;
            }

            case 2:
            {
                //TODO 加载所有商家
                LoadShopRes LsRes = new LoadShopRes();

                res = LsRes;
                break;
            }

            case 3:
            {
                //TODO 加载商店商品
                LoadShopItemRes LsiRes = new LoadShopItemRes();
                String Business = message.getString("Business");
                res = LsiRes;
                break;
            }

            case 4:
            {
                //TODO 获取来自客户下的订单 并插入数据库
                DefaultRes dres = new DefaultRes();
                String Customer = message.getString("Customer");
                String Business = message.getString("Business");
                Order o = new Order();

                //收到订单后发送给商家和用户
                SendOrder(o, Business);
                SendOrder(o, Customer);

                res = dres;
                break;
            }

            case 5: {
                //TODO 查询订单
                LoadOrderRes LoRes = new LoadOrderRes();
                res = LoRes;
                break;
            }

            case 6:
            {
                //TODO 推送订单状态
                DefaultRes res6 = new DefaultRes();
                res = res6;
                break;
            }


            case 7:
            {
                //TODO 获取来自客户的评论
                DefaultRes res7 = new DefaultRes();
                String Business = message.getString("Business");
                Comment comment = new Comment();
                //推送给商家
                SendComment(comment, Business);
                res = res7;
                break;
            }

            case 8:
            {
                //TODO 查询评论
                LoadCommentRes LcRes = new LoadCommentRes();
                res = LcRes;
                break;
            }

            case 9:
            {
                //TODO 加载某用户的购买频次表
                LoadFrequency LfRes = new LoadFrequency();
                String Customer = message.getString("Customer");
                res = LfRes;
                break;
            }


            default:
            {
                DefaultRes defaultRes = new DefaultRes();
                defaultRes.setSuccess(DefaultRes.failCode);
                defaultRes.setWrongMessage("操作码错误");
                res = defaultRes;
            }
        }
        return JSON.toJSONString(res);
    }


    //推送订单给指定用户
    public void SendOrder(Order order,String Name)
    {
        //TODO
    }
    //推送评论给商家
    public void SendComment(Comment comment,String Name)
    {
        //TODO
    }
}
