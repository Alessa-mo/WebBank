//import DB.DBop;
//
//import java.io.IOException;
//
//public class ConcurrentTest2 {
//    public static void main(String[] args) {
//        //快速向数据库发起多次连续查询//没问题
//        new Thread(()->{
//            for(int i=0;i<100;i++){
//                DBop dBop = null;
//                try {
//                    dBop = DBop.getInstance();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                int accountNum = 1;
//                String truePassword = dBop.getPasswordById(accountNum);
//                System.out.println("i="+i+":"+truePassword);
//            }
//        }).start();
//
//        new Thread(()->{
//            for(int i=0;i<100;i++){
//                DBop dBop = null;
//                try {
//                    dBop = DBop.getInstance();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                int accountNum = 2;
//                String truePassword = dBop.getPasswordById(accountNum);
//                System.out.println("i="+i+":"+truePassword);
//            }
//        }).start();
//
//        new Thread(()->{
//            for(int i=0;i<100;i++){
//                DBop dBop = null;
//                try {
//                    dBop = DBop.getInstance();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                int accountNum = 3;
//                String truePassword = dBop.getPasswordById(accountNum);
//                System.out.println("i="+i+":"+truePassword);
//            }
//        }).start();
//
//        new Thread(()->{
//            for(int i=0;i<100;i++){
//                DBop dBop = null;
//                try {
//                    dBop = DBop.getInstance();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                int accountNum = 4;
//                String truePassword = dBop.getPasswordById(accountNum);
//                System.out.println("i="+i+":"+truePassword);
//            }
//        }).start();
//    }
//}
