package json;

public class OpenAccountRes extends AbstractRes{

    private int newAccountNum = 0;

    private int accountType = 0;

    public int getNewAccountNum() {
        return newAccountNum;
    }

    public void setNewAccountNum(int newAccountNum) {
        this.newAccountNum = newAccountNum;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
