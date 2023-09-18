import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Pictest {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;
        try {
            Base64.Encoder encoder = Base64.getEncoder();

            inputStream = new FileInputStream("C:\\Users\\MHS\\Desktop\\1.PNG");

            int available = inputStream.available();
            byte[] bytes = new byte[available];
            inputStream.read(bytes);

            String base64Str = encoder.encodeToString(bytes);
            System.out.println(base64Str);
            System.out.println(base64Str.length());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }

    }
//    void base2pic(){
//
//        FileOutputStream outputStream = null;
//        try {
//            Base64.Decoder decoder = Base64.getDecoder();
//
//            byte[] bytes = decoder.decode("/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAPABEDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD3e+mkgtt0W0OZEQFxkDc4XOMj19awf+EgvNiuIJWVuhWzzn8pq3NQSSS1AijMjLLG+0EAkK6k9SB0BrhNG8D3uj5Ed/qrKTkquxAfr+95rSm4L4lczqc+nKb/APb99/z63H/gAf8A47RS/wBn3/8Ae1D84v8A45RWvNR/l/Ei1Xuf/9k=");
//
//            outputStream = new FileOutputStream("e://temp.jpg");
//
//            outputStream.write(bytes);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            outputStream.close();
//        }
//    }
}
