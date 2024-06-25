package pers.biggermonkey.translate.enums;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:28
 * @description:
 */
public enum LanguageTypeEnum {
    ZH("zh", "中文","^[\u4E00-\u9FA5]+$"),
    EN("en", "英语","^[\u0020-\u007E]+$"),
    SPA("spa", "西班牙语"),
    FRA("fra", "法语"),
    ARA("ara", "阿拉伯语"),
    RU("ru", "俄语"),
    PT("pt", "葡萄牙语"),
    DE("de", "德语"),
    VIE("vie", "越南语"),
    KOR("kor", "韩语"),
    TH("th", "泰语"),
    EL("el", "希腊语"),
    BUL("bul", "保加利亚语"),
    FIN("fin", "芬兰语"),
    SLO("slo", "斯洛文尼亚语"),
    NL("nl", "荷兰语"),
    EST("est", "爱沙尼亚语"),
    CS("cs", "捷克语"),
    SWE("swe", "瑞典语"),
    JP("jp", "日语"),
    IT("it", "意大利语"),
    PL("pl", "波兰语"),
    DAN("dan", "丹麦语"),
    ROM("rom", "罗马尼亚语"),
    HU("hu", "匈牙利语"),
    YUE("yue", "粤语"),
    CHT("cht", "繁体中文"),
    WYW("wyw", "文言文"),
    ;

    private String code;

    private String msg;
    /**
     * 匹配正则
     */
    private String matchPattern;

    LanguageTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    LanguageTypeEnum(String code, String msg, String matchPattern) {
        this.code = code;
        this.msg = msg;
        this.matchPattern = matchPattern;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getMatchPattern() {
        return matchPattern;
    }

    public static LanguageTypeEnum getEnumByCode(String code) {
        if (code == null) {
            return null;
        }
        for (LanguageTypeEnum typeEnum : values()) {
            if (code.equals(typeEnum.getCode())) {
                return typeEnum;
            }
        }
        return null;
    }

    public static LanguageTypeEnum getEnumByMsg(String msg) {
        if (msg == null) {
            return null;
        }
        for (LanguageTypeEnum typeEnum : values()) {
            if (msg.equals(typeEnum.getMsg())) {
                return typeEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return msg;
    }
}
