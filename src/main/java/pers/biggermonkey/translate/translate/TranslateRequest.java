package pers.biggermonkey.translate.translate;


import pers.biggermonkey.translate.enums.LanguageTypeEnum;
import pers.biggermonkey.translate.enums.TranslateSourceEnum;
import pers.biggermonkey.translate.enums.TranslateTypeEnum;

import java.util.List;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:01
 * @description:
 */
public class TranslateRequest {
    private LanguageTypeEnum from;
    private LanguageTypeEnum to;
    private String content;
    private List<TranslateSourceEnum> translateSourceEnumList;

    private TranslateTypeEnum translateTypeEnum;
    public LanguageTypeEnum getFrom() {
        return from;
    }

    public void setFrom(LanguageTypeEnum from) {
        this.from = from;
    }

    public LanguageTypeEnum getTo() {
        return to;
    }

    public void setTo(LanguageTypeEnum to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<TranslateSourceEnum> getTranslateSourceEnumList() {
        return translateSourceEnumList;
    }

    public void setTranslateSourceEnumList(List<TranslateSourceEnum> translateSourceEnumList) {
        this.translateSourceEnumList = translateSourceEnumList;
    }

    public TranslateTypeEnum getTranslateTypeEnum() {
        return translateTypeEnum;
    }

    public void setTranslateTypeEnum(TranslateTypeEnum translateTypeEnum) {
        this.translateTypeEnum = translateTypeEnum;
    }
}
