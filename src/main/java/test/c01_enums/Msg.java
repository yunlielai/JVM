package test.c01_enums;

public enum  Msg {
    S000("S000", "交易成功"),
    F000("F000", "交易失败"),
    F001("F001", "未登录");

    private String code;
    private String des;

    private Msg(String code, String des) {
        this.code=code;
        this.des=des;
    }

    public String getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }
}
