package pers.biggermonkey.translate.translate.baidu;

import com.fasterxml.jackson.jr.ob.JSON;
import com.google.gson.Gson;
import com.intellij.ide.util.PropertiesComponent;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import pers.biggermonkey.translate.common.BizException;
import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.common.HttpUtils;
import pers.biggermonkey.translate.enums.JavaDocAnnotationEnum;
import pers.biggermonkey.translate.enums.TranslateConfigEnum;
import pers.biggermonkey.translate.enums.TranslateSourceEnum;
import pers.biggermonkey.translate.translate.TranslateRequest;
import pers.biggermonkey.translate.translate.TranslateResponse;
import pers.biggermonkey.translate.translate.TranslateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:05
 * @description:
 */
public class BaiduTranslateUtils implements TranslateUtils {

    public static String APP_ID="appId";
    public static String SECRET_KEY="secretKey";
    private String appId;
    private String secretKey;

    @Override
    public TranslateResponse translate(TranslateRequest request) {
        try {
            validateReq(request);
            String translateUrl = "https://fanyi-api.baidu.com/api/trans/vip/translate";
            long salt = new Date().getTime();
            Map<String, Object> param = new HashMap<>();
            param.put("q", request.getContent());
            param.put("from", "auto");
            param.put("to", request.getTo().getCode());
            param.put("appid", appId);
            param.put("salt", salt + "");
            param.put("sign", DigestUtils.md5Hex(appId + request.getContent() + salt + secretKey));
            param.put("needIntervene", 1);

            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
            System.out.println("百度翻译请求：" + request.getContent());
            String response = HttpUtils.get(translateUrl, header, param);
            System.out.println("百度翻译响应：" + response);
            BaiduResponse baiduResponse = new Gson().fromJson(response, BaiduResponse.class);
            validateRes(baiduResponse);
            return buildTranslateResponse(baiduResponse);
        } catch (BizException be) {
            throw be;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TranslateResponse buildTranslateResponse(BaiduResponse baiduResponse) {
        if (baiduResponse == null) {
            return null;
        }
        TranslateResponse translateResponse = new TranslateResponse();
        translateResponse.setFrom(baiduResponse.getFrom());
        translateResponse.setTo(baiduResponse.getTo());
        if (CollectionUtils.isEmpty(baiduResponse.getTrans_result())) {
            return translateResponse;
        }
        StringBuilder source = new StringBuilder();
        StringBuilder target = new StringBuilder();
        int size = baiduResponse.getTrans_result().size();
        for (int i = 0; i < size; i++) {
            BaiduTranslateRes baiduTranslateRes = baiduResponse.getTrans_result().get(i);
            source.append(baiduTranslateRes.getSrc());
            target.append(convertJavaDoc(baiduTranslateRes.getDst()));
            if (i < size - 1) {
                source.append(Constants.LINE_BREAKS);
                target.append(Constants.LINE_BREAKS);
            }
        }
        translateResponse.setSource(source.toString());
        translateResponse.setTarget(target.toString());
        return translateResponse;
    }

    @Override
    public TranslateSourceEnum getSupportType() {
        return TranslateSourceEnum.BAIDU;
    }

    public BaiduTranslateUtils() {
        TranslateConfigEnum translateConfigEnum = TranslateConfigEnum.getEnumBySource(getSupportType());
        if (translateConfigEnum == null) {
            return;
        }
        appId = PropertiesComponent.getInstance().getValue(Constants.getConfig(translateConfigEnum.getCode(), APP_ID));
        secretKey = PropertiesComponent.getInstance().getValue(Constants.getConfig(translateConfigEnum.getCode(), SECRET_KEY));
    }

    public void validateReq(TranslateRequest request) {
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(secretKey)) {
            throw new BizException("请先配置API调用相关秘钥");
        }
        if (request.getContent().length() > 6000) {
            throw new BizException("翻译字符长度不能超过6000");
        }
    }

    public void validateRes(BaiduResponse baiduResponse) {
        if (baiduResponse.getError_code() == null
                || BaiduResCodeEnum.SUCCESS.getCode().equals(baiduResponse.getError_code())) {
            return;
        }
        throw new BizException(BaiduResCodeEnum.getEnumByCode(baiduResponse.getError_code()).getErrorDesc());
    }

    /**
     * 由于百度接口响应会把Java doc注解分开，影响后续格式化，需要特殊处理
     * eg: * @param request 查询信息 --> *@ param request to query information
     */
    public String convertJavaDoc(String dst) {
        if (StringUtils.isBlank(dst)) {
            return dst;
        }
        for (JavaDocAnnotationEnum annotationEnum : JavaDocAnnotationEnum.values()) {
            if (dst.contains(annotationEnum.getResCode())) {
                dst = dst.replace(annotationEnum.getResCode(), annotationEnum.getSourceCode());
            }
        }
        return dst;
    }
}
