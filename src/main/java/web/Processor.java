package web;

import com.alibaba.fastjson.JSONObject;
import json.*;
import com.alibaba.fastjson.JSON;
import DB.DBop;

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
                if(dbop.userOp.logOn(message.getString("username"),message.getString("pwd"),Integer.parseInt(message.getString("type")))){
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
                String name = message.getString("username");
                if(dbop.userOp.getPasswordByName(name).equals(message.getString("pwd"))){
                    loginRes.setSuccess(LoginRes.successCode);
                    loginRes.setAccountType(dbop.userOp.getAccountTypeByName(name));
                }else{
                    loginRes.setSuccess(LoginRes.failCode);
                    loginRes.setWrongMessage("用户名或密码错误");
                }
                res = loginRes;
                break;
            }

            default:{
                DefaultRes defaultRes = new DefaultRes();
                defaultRes.setSuccess(DefaultRes.failCode);
                defaultRes.setWrongMessage("操作码错误");
                res = defaultRes;
            }
        }
        return JSON.toJSONString(res);
    }
}
