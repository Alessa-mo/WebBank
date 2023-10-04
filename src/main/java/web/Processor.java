package web;

import com.alibaba.fastjson.JSONObject;
import json.*;
import com.alibaba.fastjson.JSON;
import DB.DBop;
import json.Order.LoadOrderRes;
import json.Order.PushOrderRes;
import pojo.*;
import java.util.List;
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

    public String parseMessage(WebSocket webSocket)
    {

        Integer op=message.getInteger("op");
        AbstractRes res;
        switch (op)
        {
            case 0://注册
            {
                DefaultRes defaultRes = new DefaultRes();
                defaultRes.setOperationCode(0);
                User newUser = JsonPojo.JsonToUser(message.getJSONObject("User"));
                String username = newUser.getUserName();
                if (!dbop.userOp.hasAccount(username)) //检测是否已存在用户名
                {
                    defaultRes.setSuccess(DefaultRes.successCode);

                    String pwd =newUser.getUserPassword();
                    Integer type = newUser.getUserType();
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
                loginRes.setOperationCode(0);
                User currUser = JsonPojo.JsonToUser(message.getJSONObject("User"));

                if(WebSocket.U2W.containsKey(currUser.getUserName()))
                {
                    loginRes.setSuccess(LoginRes.failCode);
                    loginRes.setWrongMessage("用户已经登陆");
                }
                else if (dbop.userOp.hasAccount(currUser.getUserName()))
                {

                    User verifyUser = dbop.userOp.getUserByName(currUser.getUserName());
                    if(verifyUser.equal(currUser))
                    {
                        loginRes.setSuccess(LoginRes.successCode);
                        loginRes.setAccountType(currUser.getUserType());
                        WebSocket.U2W.put(currUser.getUserName(),webSocket);//已连接用户加入哈希表中便于推送消息
                    }
                    else
                    {
                        loginRes.setSuccess(LoginRes.failCode);
                        loginRes.setWrongMessage("用户名或密码错误");
                    }
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
                LsRes.setOperationCode(0);
                LsRes.FillStoreList(dbop.storeOp.getAllStores());
                LsRes.setSuccess(LoadStoreRes.successCode);
                return JSON.toJSONString(LsRes);
            }

            case 3:
            {
                //TODO 加载商店商品
                LoadGoodRes LsiRes = new LoadGoodRes();
                LsiRes.setOperationCode(0);
                res = LsiRes;
                break;
            }

            case 4:
            {
                //TODO 获取来自客户下的订单 并插入数据库
                DefaultRes dres = new DefaultRes();
                dres.setOperationCode(0);
                dres.setSuccess(DefaultRes.successCode);
                Orders orders = JsonPojo.JsonToOrders(message.getJSONObject("Orders"));
                dbop.ordersOp.createOrder(orders);

                //推送订单
                PushOrderRes PoRes = new PushOrderRes();

                res = dres;
                break;
            }

            case 5:
            {
                //TODO 查询订单
                LoadOrderRes LoRes = new LoadOrderRes();
                String name = message.getString("Name");
                Integer usertype = dbop.userOp.getAccountTypeByName(name);

                LoRes.setOperationCode(0);
                LoRes.setSuccess(LoadOrderRes.successCode);
                switch (usertype)
                {
                    case 1:
                        LoRes.FillOrders(dbop.ordersOp.getAllOrdersOfUser(name));
                        break;
                    case 2:
                        LoRes.FillOrders(dbop.ordersOp.getAllOrdersOfStore(name));
                        break;
                    case 3:
                        LoRes.FillOrders(dbop.ordersOp.getAllOrdersToDeliver());
                        break;
                    default:
                        LoRes.setSuccess(LoadOrderRes.failCode);
                        LoRes.setWrongMessage("用户类型错误");
                        break;
                }
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
                res7.setSuccess(DefaultRes.successCode);
                res7.setOperationCode(0);
                JSONObject Target = message.getJSONObject("Comment");
                Comment comment = JsonPojo.JsonToComment(Target);
                dbop.commentOp.createMapper(comment);
                //推送评论
                res = res7;
                break;
            }

            case 8:
            {
                //TODO 查询评论
                LoadCommentRes LcRes = new LoadCommentRes();
                LcRes.setOperationCode(0);
                LcRes.setSuccess(LoadCommentRes.successCode);
                String name = message.getString("Name");
                Integer goodID = message.getInteger("Good");
                List<Comment> list = null;
                if(name !=null)
                {
                     list= dbop.commentOp.getCommentsOfUser(name);
                }
                else
                {
                    list = dbop.commentOp.getCommentsOfGoods(goodID);
                }

                if(list==null)
                {
                    LcRes.setSuccess(LoadCommentRes.failCode);
                    LcRes.setWrongMessage("评论不存在");
                }
                else
                    LcRes.FillCommentsList(list);
                return JSON.toJSONString(LcRes);
            }

            case 9:
            {
                //TODO 加载某用户的购买频次表
                LoadFrequencyRes LfRes = new LoadFrequencyRes();
                LfRes.setOperationCode(0);
                String Customer = message.getString("Customer");

                if(dbop.userOp.hasAccount(Customer))
                {
                    LfRes.setSuccess(LoadFrequencyRes.successCode);
                    List<Frequency> frequencyList = dbop.frequencyOp.getFrequencyOfUser(Customer);
                    LfRes.FillFrequencyList(frequencyList);
                }
                else
                {
                    LfRes.setSuccess(LoadFrequencyRes.failCode);
                    LfRes.setWrongMessage("该用户不存在");
                }
                res = LfRes;
                break;
            }

            case 10:
            {
                //TODO 创建商店
                DefaultRes res10 = new DefaultRes();
                JSONObject Target = message.getJSONObject("Store");
                Store newStore = JsonPojo.JsonToStore(Target);
                if(dbop.userOp.getAccountTypeByName(newStore.getStoreName())==2)
                {
                    res10.setSuccess(DefaultRes.successCode);
                    dbop.storeOp.createStore(newStore);
                }
                else
                {
                    res10.setSuccess(DefaultRes.failCode);
                    res10.setWrongMessage("用户类型不是商家，无法创建商店");
                }
                res = res10;
                break;
            }

            case 11:
            {
                //TODO 商家给商店添加商品
                DefaultRes res11 = new DefaultRes();
                res11.setOperationCode(0);
                JSONObject Target = message.getJSONObject("Good");
                Goods goods = JsonPojo.JsonToGood(Target);

                if(dbop.userOp.getAccountTypeByName(goods.getGoodsStore())==2)
                {//检验用户是否是商店
                    dbop.goodsOp.createGoods(goods);
                    res11.setSuccess(DefaultRes.successCode);
                }
                else
                {
                    res11.setSuccess(DefaultRes.failCode);
                    res11.setWrongMessage("用户类型不是商家，无法添加商品");
                }
                res = res11;
                break;
            }

            case 12:
            {
                //TODO 商家更新商品信息
                DefaultRes res12 = new DefaultRes();
                res12.setOperationCode(0);

                JSONObject Target = message.getJSONObject("Good");
                Goods goods = JsonPojo.JsonToGood(Target);
                dbop.goodsOp.updateGoods(goods);
                res12.setSuccess(DefaultRes.successCode);
                res = res12;
                break;
            }

            case 13:
            {
                //TODO 更新订单信息
                DefaultRes res13 = new DefaultRes();
                res13.setOperationCode(0);
                JSONObject Target = message.getJSONObject("Order");

                Orders o =JsonPojo.JsonToOrders(Target);
                dbop.ordersOp.updateOrder(o);

                res13.setSuccess(DefaultRes.successCode);
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


    void PushMessage(WebSocket webSocket,AbstractRes res)
    {
        try
        {
            webSocket.PushMessage(JSON.toJSONString(res));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
