package pers.biggermonkey.translate.enums;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:02
 * @description: 翻译类型
 */
public enum TranslateTypeEnum {
    FILE(1,"文件翻译"),
    WORD(2,"句子翻译"),
    ;

    private Integer code;

    private String msg;

    TranslateTypeEnum(Integer code, String msg) {
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
