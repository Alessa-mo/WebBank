//package web;
//
//import DB.DBop;
//import com.alibaba.fastjson.JSONObject;
//import json.*;
//import com.alibaba.fastjson.JSON;
//import org.apache.commons.io.IOUtils;
//import pojo.User;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//
//@WebServlet("/servlet")
//public class MySession extends HttpServlet {
//    public final static HashMap<HttpSession,Integer> sessionMap = new HashMap<>();
//    private final DBop dbop = DBop.getInstance();
//
//    public MySession() throws IOException {
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        System.out.println("servlet:"+this+" 已启用");
//        //统一编码格式
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        //response.setContentType("text/html;charset=UTF-8");//是下面一句的封装版
//        response.setHeader("Content-Type","text/html;charset=UTF-8");
//
//        //获取Session对象
//        HttpSession session = request.getSession();
//        System.out.println("HttpSession:"+session);
//
//        //获取返回writer
//        PrintWriter writer = response.getWriter();
//
//        //生成返回json结果的抽象父类AbstractRes
//        AbstractRes res = null;
//
//        //使用request对象获取负载的JSON
//        String payloadStr =  IOUtils.toString(request.getReader());
//        System.out.println("收到数据负载:"+payloadStr);
//        JSONObject jsonObject = JSON.parseObject(payloadStr);
//
//        if(jsonObject == null){
//            System.out.println("检测到jsonObject == null");
//            DefaultRes defaultRes = new DefaultRes();
//            defaultRes.setSuccess(DefaultRes.failCode);
//            defaultRes.setWrongMessage("未识别到jsonObject");
//            res = defaultRes;
//        }else {
//            //解析JSON
//            System.out.println("开始解析:");
//            String op = jsonObject.getString("op");
//            System.out.println("op:"+op);
//            //校验op
//            if(op == null){
//                System.out.println("未识别到操作码op");
//                DefaultRes defaultRes = new DefaultRes();
//                defaultRes.setSuccess(DefaultRes.failCode);
//                defaultRes.setWrongMessage("未识别到操作码op");
//                res = defaultRes;
//            }else {
//                //校验登录状态
//                System.out.println("校验登陆状态中:");
//                if( !op.equals("login") && !op.equals("checkLogin") && !sessionMap.containsKey(session) ){
//                    System.out.println("校验结果:op不符合。还未登录,op:"+op);
//                    DefaultRes defaultRes = new DefaultRes();
//                    defaultRes.setSuccess(DefaultRes.failCode);
//                    defaultRes.setWrongMessage("还未登录");
//                    res = defaultRes;
//                }
//                else {
//                    System.out.println("登录状态校验完成");
//                    switch (op){
//                        case "checkLogin":{
//                            CheckLoginRes checkLoginRes = new CheckLoginRes();
//                            if(sessionMap.containsKey(session)){
//                                int accountNum = sessionMap.get(session);
//                                User user = dbop.getUserById(accountNum);
//                                checkLoginRes.setAccountNum(accountNum);
//                                checkLoginRes.setUserName(user.getName());
//                                checkLoginRes.setBalance(user.getBalance());
//                                checkLoginRes.setAccountType(user.getAccountType());
//                                checkLoginRes.setSuccess(AbstractRes.successCode);
//                            }else {
//                                checkLoginRes.setSuccess(AbstractRes.failCode);
//                                checkLoginRes.setWrongMessage("checkLogin:未登录");
//                            }
//                            res = checkLoginRes;
//                            break;
//                        }
//                        case "login": {
//                            System.out.println("login:");
//                            //构造json返回对象
//                            LoginRes loginRes = new LoginRes();
//                            //获取用户输入
//                            System.out.println("accountNum:"+jsonObject.getString("accountNum"));
//                            if(jsonObject.getString("accountNum") == null || jsonObject.getString("accountNum").equals("")){
//                                loginRes.setSuccess(LoginRes.failCode);
//                                loginRes.setWrongMessage("账号未输入");
//                            }else {
//                                int accountNum = Integer.parseInt(jsonObject.getString("accountNum"));
//
//                                System.out.println("password:"+jsonObject.getString("password"));
//                                if(jsonObject.getString("password") == null || jsonObject.getString("password").equals("")){
//                                    loginRes.setSuccess(LoginRes.failCode);
//                                    loginRes.setWrongMessage("密码未输入");
//                                }else {
//                                    String password = jsonObject.getString("password");
//
//                                    //向数据库查询用户信息
//                                    User user = dbop.getUserById(accountNum);
//                                    if(user == null ){
//                                        loginRes.setSuccess(LoginRes.failCode);
//                                        loginRes.setWrongMessage("账户不存在");
//                                    }else {
//                                        System.out.println("查询到user:"+user);
//                                        //获取正确密码
//                                        String truePassword = user.getPassword();
//                                        System.out.println("truePassword:"+truePassword);
//                                        //密码校验
//                                        if (truePassword == null) {
//                                            //设置json对象结果
//                                            loginRes.setSuccess(LoginRes.failCode);
//                                            loginRes.setWrongMessage("账户不存在");
//                                        } else if (!truePassword.equals(password)) {
//                                            //设置json对象结果
//                                            loginRes.setSuccess(LoginRes.failCode);
//                                            loginRes.setWrongMessage("密码错误");
//                                            System.out.println("密码错误");
//                                        } else {
//                                            System.out.println("登陆成功");
//                                            //登陆成功,保存session登录信息
//                                            //置入map
//                                            sessionMap.put(session,accountNum);
//
//                                            //获取账户类型
//                                            int accountType = user.getAccountType();
//                                            System.out.println("准备返回账户类型:"+accountType);
//
//                                            //设置json对象结果
//                                            loginRes.setSuccess(LoginRes.successCode);
//                                            loginRes.setAccountType(accountType);
//                                            loginRes.setUserName(user.getName());
//                                            loginRes.setBalance(user.getBalance());
//                                        }
//                                    }
//                                }
//                            }
//                            res = loginRes;
//                            break;
//                        }
//                        case "openAccount": {
//                            System.out.println("openAccount:");
//                            //构造json对象
//                            OpenAccountRes openAccountRes = new OpenAccountRes();
//
//                            //校验用户名与密码
//                            String userName = jsonObject.getString("userName");
//                            System.out.println("userName:"+userName);
//                            if(userName == null){
//                                openAccountRes.setSuccess(LoginRes.failCode);
//                                openAccountRes.setWrongMessage("用户名未输入");
//                                System.out.println("用户名未输入");
//                            }else if(userName.length()>20){
//                                openAccountRes.setSuccess(LoginRes.failCode);
//                                openAccountRes.setWrongMessage("用户名过长:不可超过20个字符");
//                            }else {
//                                String userPassword = jsonObject.getString("userPassword");
//                                System.out.println("userPassword:"+userPassword);
//                                if(userPassword == null){
//                                    openAccountRes.setSuccess(LoginRes.failCode);
//                                    openAccountRes.setWrongMessage("密码未输入");
//                                    System.out.println("密码未输入");
//                                }else if(userPassword.length()>20){
//                                    openAccountRes.setSuccess(LoginRes.failCode);
//                                    openAccountRes.setWrongMessage("密码过长:不可超过20个字符");
//                                }else {
//                                    //校验可选值balance与accountType,若为null则填充默认值
//                                    double balance;
//                                    System.out.println("balance:"+jsonObject.getString("balance"));
//                                    if (jsonObject.getString("balance") == null) {
//                                        balance = 0.0;
//                                    } else {
//                                        balance = Double.parseDouble(jsonObject.getString("balance"));
//                                    }
//
//                                    int accountType;
//                                    System.out.println("accountType:"+jsonObject.getString("accountType"));
//                                    if (jsonObject.getString("accountType") == null) {
//                                        accountType = 0;
//                                    } else {
//                                        accountType = Integer.parseInt(jsonObject.getString("accountType"));
//                                    }
//
//                                    //向数据库进行注册
//                                    Integer newAccountNum = dbop.openAccount(userName, userPassword, balance, accountType);
//                                    //若成功注册
//                                    System.out.println("注册成功,newAccountNum:"+newAccountNum);
//                                    openAccountRes.setSuccess(OpenAccountRes.successCode);
//                                    openAccountRes.setNewAccountNum(newAccountNum);
//                                    openAccountRes.setAccountType(accountType);
//                                }
//                            }
//                            //写入json
//                            res = openAccountRes;
//                            break;
//                        }
//                        case "deleteAccount": {
//                            System.out.println("deleteAccount:");
//                            //构造json对象
//                            DeleteAccountRes deleteAccountRes = new DeleteAccountRes();
//                            //校验accountNum
//                            String accountNumStr = jsonObject.getString("accountNum");
//                            System.out.println("accountNum:"+accountNumStr);
//                            if(accountNumStr == null){
//                                deleteAccountRes.setSuccess(LoginRes.failCode);
//                                deleteAccountRes.setWrongMessage("请输入要注销的账号");
//                            }else if(accountNumStr.length() > 20){
//                                deleteAccountRes.setSuccess(LoginRes.failCode);
//                                deleteAccountRes.setWrongMessage("账号过长,请核对");
//                            }else if(!accountNumStr.matches("\\d+")){
//                                deleteAccountRes.setSuccess(LoginRes.failCode);
//                                deleteAccountRes.setWrongMessage("账号必须是正整数");
//                            }else {
//                                int accountNum = Integer.parseInt(jsonObject.getString("accountNum"));
//                                if(!dbop.hasAccount(accountNum)){
//                                    deleteAccountRes.setSuccess(DeleteAccountRes.failCode);
//                                    deleteAccountRes.setWrongMessage("该账户不存在");
//                                }else {
//                                    //进行删除
//                                    dbop.deleteAccount(accountNum);
//                                    //若成功删除
//                                    deleteAccountRes.setSuccess(DeleteAccountRes.successCode);
//                                }
//                            }
//                            //写入json
//                            res = deleteAccountRes;
//                            break;
//                        }
//                        case "balance": {
//                            System.out.println("balance:");
//                            //构造json对象
//                            BalanceRes balanceRes = new BalanceRes();
//                            if(jsonObject.getString("accountNum") == null){
//                                System.out.println("未检测到accountNum");
//                                balanceRes.setSuccess(BalanceRes.failCode);
//                                balanceRes.setWrongMessage("请输入账号");
//                            }else {
//                                int accountNum = Integer.parseInt(jsonObject.getString("accountNum"));
//                                System.out.println("accountNum:"+accountNum);
//
//                                if(!dbop.hasAccount(accountNum)){
//                                    System.out.println("该账户不存在");
//                                    balanceRes.setSuccess(BalanceRes.failCode);
//                                    balanceRes.setWrongMessage("该账户不存在");
//                                }else{
//                                    //进行查询
//                                    Double balance = dbop.balance(accountNum);
//                                    System.out.println("查询到balance:"+balance);
//                                    //若成功查询
//                                    balanceRes.setSuccess(BalanceRes.successCode);
//                                    balanceRes.setBalance(balance);
//                                }
//                            }
//                            //写入json
//                            res = balanceRes;
//                            break;
//                        }
//                        case "transfer": {
//                            //构造json对象
//                            TransferRes transferRes = new TransferRes();
//                            if(jsonObject.getString("srcAccount") == null){
//                                transferRes.setSuccess(TransferRes.failCode);
//                                transferRes.setWrongMessage("请输入转出账户");
//                                System.out.println("请输入转出账户");
//                            }else if(jsonObject.getString("dstAccount") == null){
//                                transferRes.setSuccess(TransferRes.failCode);
//                                transferRes.setWrongMessage("请输入转入账户");
//                                System.out.println("请输入转入账户");
//                            }else if(jsonObject.getString("amount") == null){
//                                transferRes.setSuccess(TransferRes.failCode);
//                                transferRes.setWrongMessage("请输入转帐金额");
//                                System.out.println("请输入转帐金额");
//                            }else {
//                                int srcAccount = Integer.parseInt(jsonObject.getString("srcAccount"));
//                                int dstAccount = Integer.parseInt(jsonObject.getString("dstAccount"));
//                                Double amount = Double.parseDouble(jsonObject.getString("amount"));
//
//                                if(!dbop.hasAccount(srcAccount)){
//                                    transferRes.setSuccess(TransferRes.failCode);
//                                    transferRes.setWrongMessage("源账户不存在");
//                                    System.out.println("原账户不存在");
//                                }else if(!dbop.hasAccount(dstAccount)){
//                                    transferRes.setSuccess(TransferRes.failCode);
//                                    transferRes.setWrongMessage("目的账户不存在");
//                                    System.out.println("目的账户不存在");
//                                }else if(amount <= 0){
//                                    transferRes.setSuccess(TransferRes.failCode);
//                                    transferRes.setWrongMessage("转账值必须为正值");
//                                    System.out.println("转账值必须为正值");
//                                } else {
//                                    //查询余额
//                                    Double balance = dbop.balance(srcAccount);
//                                    if (balance <= amount) {
//                                        transferRes.setSuccess(TransferRes.failCode);
//                                        transferRes.setWrongMessage("余额不足");
//                                        System.out.println("余额不足");
//                                    } else if (srcAccount == dstAccount) {
//                                        transferRes.setSuccess(TransferRes.failCode);
//                                        transferRes.setWrongMessage("转出账户与转入账户不可相同");
//                                        System.out.println("转出账户与转入账户不可相同");
//                                    } else {
//                                        //进行转账
//                                        dbop.transfer(srcAccount, dstAccount, amount);
//                                        transferRes.setSuccess(TransferRes.successCode);
//                                    }
//                                }
//                            }
//                            //实例化res
//                            res = transferRes;
//                            break;
//                        }
//                        case "logout": {
//                            System.out.println("logout:");
//                            //remove Map
//                            sessionMap.remove(session);
//
//                            LogoutRes logoutRes = new LogoutRes();
//                            logoutRes.setSuccess(LogoutRes.successCode);
//                            res = logoutRes;
//                            break;
//                        }
//                        default:{
//                            DefaultRes defaultRes = new DefaultRes();
//                            defaultRes.setSuccess(DefaultRes.failCode);
//                            defaultRes.setWrongMessage("未知操作");
//                            System.out.println("无法识别的op:"+op);
//                            res = defaultRes;
//                        }
//                    }
//                }
//            }
//        }
//        //写回结果
//        System.out.println("发回json:"+JSON.toJSONString(res));
//        writer.print(JSON.toJSONString(res));
//        writer.close();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        this.doGet(request,response);
//    }
//
//}
