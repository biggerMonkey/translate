package pers.biggermonkey.translate.enums;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:28
 * @description:
 */
public enum GoogleLanguageTypeEnum {
    ZH("zh", "中文", LanguageTypeEnum.ZH);

    private String code;

    private String msg;

    private LanguageTypeEnum languageTypeEnum;

    GoogleLanguageTypeEnum(String code, String msg, LanguageTypeEnum languageTypeEnum) {
        this.code = code;
        this.msg = msg;
        this.languageTypeEnum = languageTypeEnum;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public LanguageTypeEnum getLanguageTypeEnum() {
        return languageTypeEnum;
    }
}
