package json;

public abstract class AbstractRes {
    public static final int successCode = 1;
    public static final int failCode = 0;
    private int success = 0;
    private String wrongMessage;

    private Integer op;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getWrongMessage() {
        return wrongMessage;
    }

    public void setWrongMessage(String wrongMessage) {
        this.wrongMessage = wrongMessage;
    }

    public void setOperationCode(Integer code) { this.op = code;}
    public Integer getOperationCode(){return this.op;}
}
