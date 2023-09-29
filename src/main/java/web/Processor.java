package web;

import com.alibaba.fastjson.JSONObject;
import json.*;
import com.alibaba.fastjson.JSON;
import DB.DBop;
import pojo.Entity;
import pojo.ShopItem;
import pojo.Util;

import java.io.IOException;

//后端业务逻辑
public class Processor {
    private JSONObject message;
    private final DBop dbop = DBop.getInstance();

    public Processor() throws IOException {
    }

    void setMessage(JSONObject message){
        this.message = message;
    }

    public String parseMessage()
    {
        String op=message.getString("op");
        AbstractRes res;
        switch (op)
        {
            case "register":
            {
                DefaultRes defaultRes = new DefaultRes();
                if(dbop.CreateUser(message.getString("username"),message.getString("pwd"),Integer.parseInt(message.getString("type")))){
                    defaultRes.setSuccess(DefaultRes.successCode);
                }else{
                    defaultRes.setSuccess(DefaultRes.failCode);
                    defaultRes.setWrongMessage("用户名已存在");
                }
                res = defaultRes;
                break;
            }
            case "login":
            {
                LoginRes loginRes = new LoginRes();
                String username = message.getString("username");
                if(dbop.getPasswordById(username).equals(message.getString("pwd"))){
                    loginRes.setSuccess(LoginRes.successCode);
                    loginRes.setAccountType(dbop.getAccountTypeById(username));
                }else{
                    loginRes.setSuccess(LoginRes.failCode);
                    loginRes.setWrongMessage("用户名或密码错误");
                }
                res = loginRes;
                break;
            }

            case "LoadShopItems":
            {
                //TODO 加载商店商品 不知道是否正确，需检查
                LoadShopItemRes LsiRes = new LoadShopItemRes();
                String BusinessName = message.getString("username");
                if(dbop.hasAccount(BusinessName))
                {
                    LsiRes.setSuccess(LoadShopItemRes.successCode);
                    LsiRes.FillShopItems(dbop.getShopItemsById(BusinessName));
                }
                else {
                    LsiRes.setSuccess(LoadShopItemRes.failCode);
                    LsiRes.setWrongMessage("不存在当前商家");
                }
                res = LsiRes;
                break;
            }

            case "LoadOrders":
            {
                LoadOrderRes LoRes = new LoadOrderRes();
                String name = message.getString("username");
                if(dbop.hasAccount(name))
                {
                    LoRes.setSuccess(LoadOrderRes.successCode);
                    LoRes.FillOrdersByName(dbop.getOrdersById(name));
                }
                else
                {
                    LoRes.setSuccess(LoadOrderRes.failCode);
                    LoRes.setWrongMessage("不存在当前商家");
                }
                res = LoRes;
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
}
