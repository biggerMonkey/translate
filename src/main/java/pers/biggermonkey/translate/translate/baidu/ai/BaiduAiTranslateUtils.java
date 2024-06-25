package pers.biggermonkey.translate.translate.baidu.ai;

import com.google.gson.Gson;
import com.intellij.ide.util.PropertiesComponent;
import org.apache.commons.lang3.StringUtils;
import pers.biggermonkey.translate.common.BizException;
import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.common.HttpUtils;
import pers.biggermonkey.translate.enums.TranslateConfigEnum;
import pers.biggermonkey.translate.enums.TranslateSourceEnum;
import pers.biggermonkey.translate.translate.TranslateRequest;
import pers.biggermonkey.translate.translate.TranslateResponse;
import pers.biggermonkey.translate.translate.baidu.BaiduTranslateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:05
 * @description:
 */
public class BaiduAiTranslateUtils extends BaiduTranslateUtils {

    public static String CLIENT_ID = "clientId";
    public static String CLIENT_SECRET = "clientSecret";
    public static String ACCESS_TOKEN = "accessToken";
    public static String TOKEN_EXPIRE_TIME = "tokenExpireTime";
    public static String TERM_IDS = "termIds";


    private String clientId;

    private String clientSecret;

    private String accessToken;

    private String termIds;

    @Override
    public TranslateResponse translate(TranslateRequest request) {
        try {
            validateReq(request);
            String url = "https://aip.baidubce.com/rpc/2.0/mt/texttrans/v1";
            Map<String, Object> map = new HashMap<>();
            map.put("from", "auto");
            map.put("to", request.getTo().getCode());
            map.put("q", request.getContent());
            if (StringUtils.isNotBlank(termIds)) {
                map.put("termIds", termIds);
            }
            String param = new Gson().toJson(map);

            String result = HttpUtils.post(url, accessToken, "application/json", param);
            System.out.println("百度AI响应：" + result);

            BaiduAiResponse baiduResponse = new Gson().fromJson(result, BaiduAiResponse.class);
            validateRes(baiduResponse);
            return buildTranslateResponse(baiduResponse.getResult());
        } catch (BizException be) {
            throw be;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BaiduAiTranslateUtils() {
        TranslateConfigEnum translateConfigEnum = TranslateConfigEnum.getEnumBySource(getSupportType());
        if (translateConfigEnum == null) {
            return;
        }
        termIds = PropertiesComponent.getInstance().getValue(Constants.getConfig(translateConfigEnum.getCode(), TERM_IDS));
        clientId = PropertiesComponent.getInstance().getValue(Constants.getConfig(translateConfigEnum.getCode(), CLIENT_ID));
        clientSecret = PropertiesComponent.getInstance().getValue(Constants.getConfig(translateConfigEnum.getCode(), CLIENT_SECRET));
        if (StringUtils.isBlank(clientId) || StringUtils.isBlank(clientId)) {
            return;
        }
        accessToken = PropertiesComponent.getInstance().getValue(Constants.getConfig(translateConfigEnum.getCode(), ACCESS_TOKEN));
        String tokenExpireTime = PropertiesComponent.getInstance().getValue(Constants.getConfig(translateConfigEnum.getCode(), TOKEN_EXPIRE_TIME));
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(tokenExpireTime)
                || Long.parseLong(tokenExpireTime) < System.currentTimeMillis() - 86400) {
            //提前一天刷新token
            //token不存在或者 token已过期，刷新token
            refreshAccessToken();
        }
    }

    public void refreshAccessToken() {
        try {
            String getTokenUrl = "https://aip.baidubce.com/oauth/2.0/token";
            Map<String, String> uriParam = new HashMap<>();
            uriParam.put("client_id", clientId);
            uriParam.put("client_secret", clientSecret);
            uriParam.put("grant_type", "client_credentials");
            String result = HttpUtils.postJson(getTokenUrl, uriParam, new HashMap<>());
            System.out.println("百度AI获取accessToken响应" + result);
            if (StringUtils.isBlank(result)) {
                return;
            }
            BaiduAiTokenResponse tokenResponse = new Gson().fromJson(result, BaiduAiTokenResponse.class);
            accessToken = tokenResponse.getAccess_token();
            TranslateConfigEnum translateConfigEnum = TranslateConfigEnum.getEnumBySource(getSupportType());
            if (translateConfigEnum == null) {
                return;
            }
            PropertiesComponent.getInstance().setValue(Constants.getConfig(translateConfigEnum.getCode(), ACCESS_TOKEN), accessToken);
            PropertiesComponent.getInstance().setValue(Constants.getConfig(translateConfigEnum.getCode(), TOKEN_EXPIRE_TIME), String.valueOf(System.currentTimeMillis() + tokenResponse.getExpires_in()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateReq(TranslateRequest request) {
        if (request.getContent().length() > 6000) {
            throw new BizException("翻译字符长度不能超过6000");
        }
        if (StringUtils.isBlank(accessToken)) {
            refreshAccessToken();
        }
        if (StringUtils.isBlank(accessToken)) {
            throw new BizException("百度AI翻译获取token异常");
        }
    }

    public void validateRes(BaiduAiResponse baiduResponse) {
        if (baiduResponse.getError_code() == null) {
            return;
        }
        throw new BizException(BaiduAiResCodeEnum.getEnumByCode(baiduResponse.getError_code()).getErrorDesc());
    }

    @Override
    public TranslateSourceEnum getSupportType() {
        return TranslateSourceEnum.BAIDU_AI;
    }
}
