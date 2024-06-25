package pers.biggermonkey.translate.enums;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:02
 * @description: 翻译类型
 */
public enum ResultDealTypeEnum {
    ONLY_TARGET(1,"只保留目标语言"),
    BOTH(2,"同时保留"),
    ;

    private Integer code;

    private String msg;

    ResultDealTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
