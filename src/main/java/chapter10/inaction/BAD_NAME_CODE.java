package chapter10.inaction;

public class BAD_NAME_CODE {
    enum colors {
        red, blue, green;
    }

    static final int _FORTY = 40;

    public static int NOT_A_CONSTANT = _FORTY;

    protected void BAD_NAME_CODE() {
        return ;
    }

    public void NOTacamelCASEmethodNAME() {
        return;
    }

    // 规则上是符合驼峰命名法的
    public void potEntialCaMelCasEmthoDnaMe() {
        return ;
    }
}
