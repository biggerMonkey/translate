package pers.biggermonkey.translate.enums;

import com.google.common.collect.Lists;
import pers.biggermonkey.translate.translate.baidu.ai.BaiduAiTranslateUtils;

import java.util.List;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 18:28
 * @description:
 */
public enum TranslateConfigEnum {
    BAIDU(TranslateSourceEnum.BAIDU, "baidu", Lists.newArrayList("appId", "secretKey")),
    YOUDAO(TranslateSourceEnum.YOUDAO, "youdao", Lists.newArrayList("appKey", "appSecret")),
    BAIDU_AI(TranslateSourceEnum.BAIDU_AI, "baiduAi", Lists.newArrayList(BaiduAiTranslateUtils.CLIENT_ID, BaiduAiTranslateUtils.CLIENT_SECRET, BaiduAiTranslateUtils.TERM_IDS)),
    ;


    TranslateConfigEnum(TranslateSourceEnum source, String code, List<String> fields) {
        this.source = source;
        this.code = code;
        this.fields = fields;

    }

    public static TranslateConfigEnum getEnumBySource(TranslateSourceEnum sourceEnum) {
        for (TranslateConfigEnum configEnum : values()) {
            if (configEnum.getSource().equals(sourceEnum)) {
                return configEnum;
            }
        }
        return null;
    }

    private TranslateSourceEnum source;

    private String code;

    private List<String> fields;

    public TranslateSourceEnum getSource() {
        return source;
    }

    public String getCode() {
        return code;
    }

    public List<String> getFields() {
        return fields;
    }
}
