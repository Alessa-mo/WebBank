package web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import json.*;
import com.alibaba.fastjson.JSON;
import DB.DBop;
import json.Comment.LoadCommentRes;
import json.Comment.PushCommentRes;
import json.Order.LoadOrderRes;
import json.Order.PushOrderRes;
import pojo.*;

import java.util.*;
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

                User newUser = JSON.parseObject(message.getJSONObject("User").toJSONString(),User.class);

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

                User currUser = JSON.parseObject(message.getJSONObject("User").toJSONString(),User.class);

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
            { //加载所有商店
                LoadStoreRes LsRes = new LoadStoreRes();
                LsRes.setOperationCode(0);
                LsRes.FillStoreList(dbop.storeOp.getAllStores());
                LsRes.setSuccess(LoadStoreRes.successCode);
                return JSON.toJSONString(LsRes);
            }

            case 3:
            {
                //TODO 加载商店商品
                LoadGoodRes LgRes = new LoadGoodRes();
                String store = message.getString("storeName");
                LgRes.setSuccess(LoadGoodRes.successCode);
                LgRes.setOperationCode(0);
                LgRes.FillGoodList(dbop.goodsOp.getAllGoodsOfStore(store));
                return JSON.toJSONString(LgRes);
            }

            case 4:
            {
                //TODO 获取来自客户下的订单 并插入数据库
                DefaultRes dres = new DefaultRes();
                dres.setOperationCode(0);
                dres.setSuccess(DefaultRes.successCode);

                Orders orders = JSON.parseObject(message.getJSONObject("Orders").toJSONString(),Orders.class);
                dbop.ordersOp.createOrder(orders);


                //插入或者更新一条频次信息  待测试
                UpdateFrequencyByOrders(orders);

                //推送订单给商店  待测试
                PushOrderRes PoRes = new PushOrderRes();
                String store = orders.getOrderStore();
                PoRes.SetOrder(orders);
                PushMessage(WebSocket.U2W.get(store),JSON.toJSONString(PoRes));

                res = dres;
                break;
            }

            case 5:
            {
                //TODO 查询订单
                LoadOrderRes LoRes = new LoadOrderRes();
                String name = message.getString("userName");
                Integer usertype = dbop.userOp.getAccountTypeByName(name);

                List<Orders> ordersList;
                LoRes.setOperationCode(0);
                LoRes.setSuccess(LoadOrderRes.successCode);
                switch (usertype)
                {
                    case 0:
                        ordersList= dbop.ordersOp.getAllOrdersOfUser(name);
                        for(int i =0;i<ordersList.size();++i)
                        {
                            Orders o = ordersList.get(i);
                            JSONArray t = (JSONArray) JSONArray.parse(o.getOrderList());
                            if(!t.isEmpty())
                            {
                                for(int j =0;j<t.size();++j)
                                {

                                    JSONObject job = t.getJSONObject(i);
                                    Goods good = dbop.goodsOp.getGoodsByID(job.getInteger("goodsID"));
                                    String url = "url";
                                    String goodsName = "goodsName";
                                    String uk = good.getGoodsPhotoURL();
                                    String gk = good.getGoodsName();
                                    job.put(url,uk);
                                    job.put(goodsName,gk);
                                }
                            }

                        }
                        break;
                    case 1:
                        ordersList = dbop.ordersOp.getAllOrdersOfStore(name);
                        break;
                    case 2:
                        ordersList = dbop.ordersOp.getAllOrdersToDeliver();

                        break;
                    default:
                        LoRes.setSuccess(LoadOrderRes.failCode);
                        LoRes.setWrongMessage("用户类型错误");
                        break;
                }
                return JSON.toJSONString(LoRes);
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
                Comment comment = JSON.parseObject(message.getJSONObject("Comment").toJSONString(),Comment.class);
                dbop.commentOp.createMapper(comment);

                //推送评论
                PushCommentRes PcRes = new PushCommentRes();
                PcRes.SetComment(comment);
                String storeName = dbop.goodsOp.getGoodsByID(comment.getCommentGoods()).getGoodsStore();
                PushMessage(WebSocket.U2W.get(storeName),JSON.toJSONString(PcRes));


                res = res7;
                break;
            }

            case 8:
            {
                //TODO 查询评论
                LoadCommentRes LcRes = new LoadCommentRes();
                LcRes.setOperationCode(0);
                LcRes.setSuccess(LoadCommentRes.successCode);
                String name = message.getString("userName");
                Integer goodID = message.getInteger("goodsID");
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
                //TODO 加载某用户的购买频次表 待测试
                LoadFrequencyRes LfRes = new LoadFrequencyRes();
                LfRes.setOperationCode(0);
                String Customer = message.getString("userName");

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
                return JSON.toJSONString(LfRes);
            }

            case 10:
            {
                //TODO 创建商店
                DefaultRes res10 = new DefaultRes();
                String userName = message.getString("userName");
                Store newStore = JSON.parseObject( message.getJSONObject("Store").toJSONString(),Store.class);
                if(dbop.userOp.getAccountTypeByName(newStore.getStoreName())==2&& newStore.getStoreName().equals(userName))
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
                String userName = message.getString("userName");
                Goods goods = JSON.parseObject( message.getJSONObject("Good").toJSONString(),Goods.class);

                if(dbop.userOp.getAccountTypeByName(goods.getGoodsStore())==2 && goods.getGoodsStore().equals(userName))
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
                Goods goods = JSON.parseObject( message.getJSONObject("Good").toJSONString(),Goods.class);
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
                Orders orders = JSON.parseObject(message.getJSONObject("Orders").toJSONString(),Orders.class);
                dbop.ordersOp.updateOrder(orders);

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


    void PushMessage(WebSocket webSocket,String msg)
    {
        try
        {
            webSocket.PushMessage(msg);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage()+msg);
        }
    }


    void UpdateFrequencyByOrders(Orders orders)
    {
        JSONObject orderList = JSON.parseObject(orders.getOrderList());
        String username = orders.getOrderOrderer();
        Set<String> keys = orderList.keySet();
        for(String GoodID:keys)
        {
            Integer ID = Integer.getInteger(GoodID);
            Integer count = orderList.getInteger(GoodID);
            Frequency f = dbop.frequencyOp.getFrequency(username,ID);
            if(f==null)
            {
                f= new Frequency(username,ID,count);
                dbop.frequencyOp.createFrequency(f);
            }
            else
            {
                f.setFrequencyNum(f.getFrequencyNum()+count);
                dbop.frequencyOp.updateFrequency(f);
            }
        }
    }
}
