package pers.biggermonkey.translate.enums;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:02
 * @description: 翻译渠道
 */
public enum TranslateSourceEnum {
    GOOGLE(1,"谷歌翻译"),
    YOUDAO(2,"有道翻译"),

    YOUDAO_AI(4,"有道AI翻译"),
    BAIDU(3,"百度翻译"),
    BAIDU_AI(4,"百度AI翻译"),
    ;

    private Integer code;

    private String msg;

    TranslateSourceEnum(Integer code, String msg) {
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
