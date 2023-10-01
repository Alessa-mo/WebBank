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
            case 0://注册
            {
                DefaultRes defaultRes = new DefaultRes();
                String username = message.getString("username");
                if (dbop.userOp.hasAccount(username))
                {
                    defaultRes.setSuccess(DefaultRes.successCode);

                    String pwd = message.getString("pwd");
                    Integer type = message.getInteger("type");
                    dbop.userOp.logOn(username,pwd,type);
                }
                else
                {
                    defaultRes.setSuccess(DefaultRes.failCode);
                    defaultRes.setWrongMessage("用户名已存在");
                }
                res = defaultRes;
                break;
            }
            case 1:  //登录
            {
                LoginRes loginRes = new LoginRes();
                String username = message.getString("username");
                if (dbop.userOp.getPasswordByName(username).equals(message.getString("pwd")))
                {
                    loginRes.setSuccess(LoginRes.successCode);
                    loginRes.setAccountType(dbop.userOp.getAccountTypeByName(username));
                }
                else
                {
                    loginRes.setSuccess(LoginRes.failCode);
                    loginRes.setWrongMessage("用户名或密码错误");
                }
                res = loginRes;
                break;
            }

            case 2:
            {
                //TODO 加载所有商店
                LoadStoreRes LsRes = new LoadStoreRes();



                res = LsRes;
                break;
            }

            case 3:
            {
                //TODO 加载商店商品
                LoadGoodRes LsiRes = new LoadGoodRes();
                String Store = message.getString("Store");
                res = LsiRes;
                break;
            }

            case 4:
            {
                //TODO 获取来自客户下的订单 并插入数据库
                DefaultRes dres = new DefaultRes();
                String Customer = message.getString("Customer");
                String Store = message.getString("Store");
                Orders o = new Orders();

                //收到订单后发送给商家和用户
                SendOrder(o, Store);
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
                String Store = message.getString("Store");
                Comment comment = new Comment();
                //推送给商家
                SendComment(comment, Store);
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
                LoadFrequencyRes LfRes = new LoadFrequencyRes();
                String Customer = message.getString("Customer");
                res = LfRes;
                break;
            }

            case 10:
            {
                //TODO 创建商店
                DefaultRes res10 = new DefaultRes();
                String name = message.getString("Name");//MainKey
                Integer area = message.getInteger("Area");
                String  detailLocation = message.getString("DetailLocation");
                String description = message.getString("Description");

                if(dbop.storeOp.getStoreByName(name)==null)
                {
                    res10.setSuccess(DefaultRes.successCode);
                    Store newStore = new Store(name,area,detailLocation,description);
                    dbop.storeOp.createStore(newStore);
                }
                else
                {

                    res10.setSuccess(DefaultRes.failCode);
                    res10.setWrongMessage("该商店名已存在");
                }
                res = res10;
                break;
            }

            case 11:
            {
                //TODO 商家给商店添加商品
                DefaultRes res11 = new DefaultRes();
                res = res11;
                break;
            }

            case 12:
            {
                //TODO 商家更新商品信息
                DefaultRes res12 = new DefaultRes();
                res = res12;
                break;
            }

            case 13:
            {
                //TODO 商家更新订单信息
                DefaultRes res13 = new DefaultRes();
                res = res13;
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
    public void SendOrder(Orders order,String Name)
    {
        //TODO
    }
    //推送评论给商家
    public void SendComment(Comment comment,String Name)
    {
        //TODO
    }
}
