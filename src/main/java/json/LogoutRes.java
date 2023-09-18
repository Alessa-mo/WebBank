package json;

public class LogoutRes extends AbstractRes{
    public static final int successCode = 1;
    public static final int failCode = 0;
    private int success = 0;
    private String wrongMessage;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
