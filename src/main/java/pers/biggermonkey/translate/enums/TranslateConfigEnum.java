package pers.biggermonkey.translate.enums;

import com.google.common.collect.Lists;
import pers.biggermonkey.translate.translate.baidu.BaiduTranslateUtils;
import pers.biggermonkey.translate.translate.baidu.ai.BaiduAiTranslateUtils;
import pers.biggermonkey.translate.translate.youdao.YoudaoTranslateUtils;

import java.util.List;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 18:28
 * @description:
 */
public enum TranslateConfigEnum {
    BAIDU(TranslateSourceEnum.BAIDU, "baidu", Lists.newArrayList(BaiduTranslateUtils.APP_ID, BaiduTranslateUtils.SECRET_KEY)),
    YOUDAO(TranslateSourceEnum.YOUDAO, "youdao", Lists.newArrayList(YoudaoTranslateUtils.APP_KEY, YoudaoTranslateUtils.APP_SECRET)),
    BAIDU_AI(TranslateSourceEnum.BAIDU_AI, "baiduAi", Lists.newArrayList(BaiduAiTranslateUtils.CLIENT_ID, BaiduAiTranslateUtils.CLIENT_SECRET, BaiduAiTranslateUtils.TERM_IDS)),
    YOUDAO_AI(TranslateSourceEnum.YOUDAO_AI, "youdao", Lists.newArrayList(YoudaoTranslateUtils.APP_KEY, YoudaoTranslateUtils.APP_SECRET)),
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
