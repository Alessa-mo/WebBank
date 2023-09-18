//import DB.DBop;
//
//import java.io.IOException;
//
//public class ConcurrentTest {
//    public static void main(String[] args) throws IOException {
//        //快速向数据库发起多次连续修改
//        new Thread(()->{
//            for(int i=0;i<50;i++){
//                DBop dBop = null;
//                try {
//                    dBop = DBop.getInstance();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                int srcAccount = 1;
//                int dstAccount = 2;
//                Double amount = 0.01;
//                dBop.transfer(srcAccount,dstAccount,amount);
//            }
//        }).start();
//
//        new Thread(()->{
//            for(int i=0;i<50;i++){
//                DBop dBop = null;
//                try {
//                    dBop = DBop.getInstance();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                int srcAccount = 1;
//                int dstAccount = 2;
//                Double amount = 0.01;
//                dBop.transfer(srcAccount,dstAccount,amount);
//            }
//        }).start();
//    }
//}
