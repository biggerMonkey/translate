package pers.biggermonkey.translate.translate;


import org.apache.commons.collections.CollectionUtils;
import pers.biggermonkey.translate.common.BizException;
import pers.biggermonkey.translate.enums.TranslateSourceEnum;
import pers.biggermonkey.translate.enums.TranslateTypeEnum;
import pers.biggermonkey.translate.translate.baidu.BaiduTranslateUtils;
import pers.biggermonkey.translate.translate.baidu.ai.BaiduAiTranslateUtils;
import pers.biggermonkey.translate.translate.google.GoogleTranslateUtils;
import pers.biggermonkey.translate.translate.youdao.YoudaoTranslateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:00
 * @description:
 */
public class TranslateUtilsManager {

    private static TranslateUtilsManager translateUtilsManager = new TranslateUtilsManager();

    public static TranslateUtilsManager getInstance(){
        return translateUtilsManager;
    }

    private Map<TranslateSourceEnum, TranslateUtils> translateUtilsMap;

    public TranslateUtilsManager() {
        //初始化
        translateUtilsMap = new HashMap<>();
        translateUtilsMap.put(TranslateSourceEnum.BAIDU, new BaiduTranslateUtils());
        translateUtilsMap.put(TranslateSourceEnum.BAIDU_AI, new BaiduAiTranslateUtils());
        translateUtilsMap.put(TranslateSourceEnum.YOUDAO, new YoudaoTranslateUtils());
        translateUtilsMap.put(TranslateSourceEnum.GOOGLE, new GoogleTranslateUtils());

    }

    public Map<TranslateSourceEnum, TranslateResponse> translate(TranslateRequest request) {
        Map<TranslateSourceEnum, TranslateResponse> response = new HashMap<>();
        if (CollectionUtils.isNotEmpty(request.getTranslateSourceEnumList())) {
            for (TranslateSourceEnum translateSourceEnum : request.getTranslateSourceEnumList()) {
                TranslateUtils translateUtils = translateUtilsMap.get(translateSourceEnum);
                if (translateUtils == null) {
                    continue;
                }
                try {
                    TranslateResponse tempResponse = translateUtils.translate(request);
                    if (tempResponse == null) {
                        continue;
                    }
                    response.put(translateUtils.getSupportType(), tempResponse);
                } catch (BizException be) {
                    if (TranslateTypeEnum.FILE.equals(request.getTranslateTypeEnum())) {
                        throw be;
                    }
                    response.put(translateUtils.getSupportType(), convert(request, be.getMessage()));
                } catch (Exception e) {
                    e.printStackTrace();
                    if (TranslateTypeEnum.FILE.equals(request.getTranslateTypeEnum())) {
                        throw new BizException("翻译异常！");
                    }
                    response.put(translateUtils.getSupportType(), convert(request, "翻译异常！"));
                }
            }
            return response;
        }
        for (TranslateUtils translateUtils : translateUtilsMap.values()) {
            try {
                TranslateResponse tempResponse = translateUtils.translate(request);
                if (tempResponse == null) {
                    continue;
                }
                response.put(translateUtils.getSupportType(), tempResponse);
            } catch (BizException be) {
                response.put(translateUtils.getSupportType(), convert(request, be.getMessage()));
            } catch (Exception e) {
                response.put(translateUtils.getSupportType(), convert(request, "翻译异常！"));
            }
        }
        return response;
    }

    public TranslateResponse convert(TranslateRequest request, String resContent) {
        TranslateResponse response = new TranslateResponse();
        response.setFrom(response.getFrom());
        response.setTo(response.getTo());
        response.setSource(request.getContent());
        response.setTarget(resContent);
        return response;
    }
}
