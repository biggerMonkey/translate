package pers.biggermonkey.translate.translate.youdao;


import com.google.gson.Gson;
import com.intellij.ide.util.PropertiesComponent;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import pers.biggermonkey.translate.common.BizException;
import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.common.HttpUtils;
import pers.biggermonkey.translate.enums.LanguageTypeEnum;
import pers.biggermonkey.translate.enums.TranslateConfigEnum;
import pers.biggermonkey.translate.enums.TranslateSourceEnum;
import pers.biggermonkey.translate.enums.YoudaoLanguageTypeEnum;
import pers.biggermonkey.translate.translate.TranslateRequest;
import pers.biggermonkey.translate.translate.TranslateResponse;
import pers.biggermonkey.translate.translate.TranslateUtils;
import pers.biggermonkey.translate.translate.baidu.YoudaoResCodeEnum;
import pers.biggermonkey.translate.translate.youdao.utils.AuthV3Util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:05
 * @description:
 */
public class YoudaoTranslateUtils implements TranslateUtils {

    private String appKey;
    private String appSecret;

    public YoudaoTranslateUtils() {
        TranslateConfigEnum translateConfigEnum = TranslateConfigEnum.getEnumBySource(getSupportType());
        if (translateConfigEnum == null) {
            return;
        }
        this.appKey = PropertiesComponent.getInstance().getValue(Constants.getConfig(translateConfigEnum.getCode(), "appKey"));
        this.appSecret = PropertiesComponent.getInstance().getValue(Constants.getConfig(translateConfigEnum.getCode(), "appSecret"));
        this.appKey = "42f07ead7294ed25";
        this.appSecret = "22VbfXF5bT61lK0H4DWQNUWrMBkg1gC5";
    }

    @Override
    public TranslateResponse translate(TranslateRequest request) {
        try {
            validateReq(request);
            String translateUrl = "https://openapi.youdao.com/api";
            // 添加请求参数
            Map<String, String> params = new HashMap<>();
            // 添加鉴权相关参数
            String salt = UUID.randomUUID().toString();
            String currentTime = String.valueOf(System.currentTimeMillis() / 1000);
            String sign = AuthV3Util.calculateSign(appKey, appSecret, request.getContent(), salt, currentTime);
            params.put("q", request.getContent());
            params.put("from", YoudaoLanguageTypeEnum.getEnumByLanguageType(request.getFrom()).getCode());
            params.put("to", YoudaoLanguageTypeEnum.getEnumByLanguageType(request.getTo()).getCode());
            params.put("appKey", appKey);
            params.put("salt", salt);
            params.put("sign", sign);
            params.put("curtime", currentTime);
            params.put("signType", "v3");
//            params.put("vocabId", "");
            // 请求api服务
            Map<String, String> header = new HashMap<>();
//            header.put("Content-Type", "application/json");
            String result = HttpUtils.postForm(translateUrl, header, params);
            System.out.println("有道翻译：" + result);
            YoudaoResponse youdaoResponse = new Gson().fromJson(result, YoudaoResponse.class);
            validateRes(youdaoResponse);
            return buildTranslateResponse(youdaoResponse);
        } catch (BizException be) {
            throw be;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TranslateResponse buildTranslateResponse(YoudaoResponse youdaoResponse) {
        if (youdaoResponse == null) {
            return null;
        }
        TranslateResponse translateResponse = new TranslateResponse();
//        translateResponse.setFrom(youdaoResponse.getFrom());
//        translateResponse.setTo(youdaoResponse.getTo());
        if (CollectionUtils.isEmpty(youdaoResponse.getTranslation())) {
            return translateResponse;
        }
        StringBuilder target = new StringBuilder();
        int size = youdaoResponse.getTranslation().size();
        for (int i = 0; i < size; i++) {
            target.append(youdaoResponse.getTranslation().get(i));
            if (i < size - 1) {
                target.append(Constants.LINE_BREAKS);
            }
        }
        translateResponse.setTarget(target.toString());
        return translateResponse;
    }

    @Override
    public TranslateSourceEnum getSupportType() {
        return TranslateSourceEnum.YOUDAO;
    }


    public void validateReq(TranslateRequest request) {
        if (StringUtils.isBlank(appKey) || StringUtils.isBlank(appSecret)) {
            throw new BizException("请先配置API调用相关秘钥");
        }
        if (request.getContent().length() > 1000) {
            throw new BizException("翻译字符长度不能超过1000");
        }
    }

    public void validateRes(YoudaoResponse youdaoResponse) {
        YoudaoResCodeEnum codeEnum = YoudaoResCodeEnum.getEnumByCode(youdaoResponse.getErrorCode());
        if (codeEnum == null) {
            return;
        }
        throw new BizException(codeEnum.getErrorDesc());
    }

    public static void main(String[] args) {
        YoudaoTranslateUtils youdaoTranslateUtils = new YoudaoTranslateUtils();
        TranslateRequest request = new TranslateRequest();
        request.setFrom(LanguageTypeEnum.ZH);
        request.setTo(LanguageTypeEnum.EN);
        youdaoTranslateUtils.translate(request);
    }
}
