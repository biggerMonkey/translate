package pers.biggermonkey.translate.translate.baidu;

/**
 * @author: huangwenjun16
 * @date: 2024/6/22 11:11
 * @description:
 */
public enum YoudaoResCodeEnum {
    ERROR_CODE_101(101, "缺少必填的参数", "确保必填参数齐全，并确认参数书写是否正确"),
    ERROR_CODE_102(102, "不支持的语言类型", "重新缩短评论"),
    ERROR_CODE_103(103, "翻译文本过长", "文本最长不超过1000"),
    ERROR_CODE_104(104, "不支持的API类型"),
    ERROR_CODE_105(105, "不支持的签名类型"),
    ERROR_CODE_106(106, "不支持的响应类型"),
    ERROR_CODE_107(107, "不支持的传输加密类型"),
    ERROR_CODE_108(108, "应用ID无效", "注册账号，登录后台创建应用并完成绑定，可获得应用ID和应用密钥等信息"),
    ERROR_CODE_109(109, "batchLog格式不正确"),
    ERROR_CODE_110(110, "无相关服务的有效应用", "检查应用没有绑定服务应用，可以新建服务应用。注：某些服务的翻译结果发音需要tts服务，需要在控制台创建语音合成服务绑定应用后方能使用。"),
    ERROR_CODE_111(111, "开发者账号无效", "检查开发者账号有效性"),
    ERROR_CODE_112(112, "请求服务无效"),
    ERROR_CODE_113(113, "q不能为空", "翻译内容不能为空"),
    ERROR_CODE_114(114, "不支持的图片传输方式", "不支持图片"),
    ERROR_CODE_116(116, "strict字段取值无效，请参考文档填写正确参数值"),
    ERROR_CODE_201(201, "解密失败，可能为DES,BASE64,URLDecode的错误"),
    ERROR_CODE_202(202, "签名检验失败"),
    ERROR_CODE_203(203, "访问IP地址不在可访问IP列表"),
    ERROR_CODE_205(205, "请求的接口与应用的平台类型不一致"),
    ERROR_CODE_206(206, "因为时间戳无效导致签名校验失败"),
    ERROR_CODE_207(207, "重放请求"),
    ERROR_CODE_301(301, "辞典查询失败"),
    ERROR_CODE_302(302, "翻译查询失败"),
    ERROR_CODE_303(303, "服务端的其它异常"),
    ERROR_CODE_304(304, "翻译失败，请联系技术同学"),
    ERROR_CODE_308(308, "rejectFallback参数错误"),
    ERROR_CODE_309(309, "domain参数错误"),
    ERROR_CODE_310(310, "未开通领域翻译服务", "先开通领域翻译服务"),
    ERROR_CODE_401(401, "账户已经欠费", "进行账户充值"),
    ERROR_CODE_402(402, "offlinesdk不可用"),
    ERROR_CODE_411(411, "访问频率受限", "稍后访问"),
    ERROR_CODE_412(412, "长请求过于频繁", "稍后访问"),
    ERROR_CODE_1001(1001, "无效的OCR类型"),
    ERROR_CODE_1002(1002, "不支持的OCR image类型"),
    ERROR_CODE_1003(1003, "不支持的OCR Language类型"),
    ERROR_CODE_1004(1004, "识别图片过大", "减小图片大小"),
    ERROR_CODE_1201(1201, "图片base64解密失败"),
    ERROR_CODE_1301(1301, "OCR段落识别失败"),
    ERROR_CODE_1411(1411, "访问频率受限"),
    ERROR_CODE_1412(1412, "超过最大识别字节数", "减少识别字节数"),
    ERROR_CODE_2003(2003, "不支持的语言识别Language类型", "更换语言类型"),
    ERROR_CODE_2004(2004, "合成字符过长", "减少字符"),
    ERROR_CODE_2005(2005, "不支持的音频文件类型", "不支持的音频文件类型"),
    ERROR_CODE_2006(2006, "不支持的发音类型", "不支持的发音类型"),
    ERROR_CODE_2201(2201, "解密失败", "解密失败"),
    ERROR_CODE_2301(2301, "服务的异常", "服务的异常"),
    ERROR_CODE_2411(2411, "访问频率受限,请稍后访问", "访问频率受限,请稍后访问"),
    ERROR_CODE_2412(2412, "超过最大请求字符数", "超过最大请求字符数"),
    ERROR_CODE_3001(3001, "不支持的语音格式", "不支持的语音格式"),
    ERROR_CODE_3002(3002, "不支持的语音采样率", "不支持的语音采样率"),
    ERROR_CODE_3003(3003, "不支持的语音声道", "不支持的语音声道"),
    ERROR_CODE_3004(3004, "不支持的语音上传类型", "不支持的语音上传类型"),
    ERROR_CODE_3005(3005, "不支持的语言类型", "不支持的语言类型"),
    ERROR_CODE_3006(3006, "不支持的识别类型", "不支持的识别类型"),
    ERROR_CODE_3007(3007, "识别音频文件过大", "识别音频文件过大"),
    ERROR_CODE_3008(3008, "识别音频时长过长", "识别音频时长过长"),
    ERROR_CODE_3009(3009, "不支持的音频文件类型", "不支持的音频文件类型"),
    ERROR_CODE_3010(3010, "不支持的发音类型", "不支持的发音类型"),
    ERROR_CODE_3201(3201, "解密失败", "解密失败"),
    ERROR_CODE_3301(3301, "语音识别失败", "语音识别失败"),
    ERROR_CODE_3302(3302, "语音翻译失败", "语音翻译失败"),
    ERROR_CODE_3303(3303, "服务的异常", "服务的异常"),
    ERROR_CODE_3411(3411, "访问频率受限,请稍后访问", "访问频率受限,请稍后访问"),
    ERROR_CODE_3412(3412, "超过最大请求字符数", "超过最大请求字符数"),
    ERROR_CODE_4001(4001, "不支持的语音识别格式", "不支持的语音识别格式"),
    ERROR_CODE_4002(4002, "不支持的语音识别采样率", "不支持的语音识别采样率"),
    ERROR_CODE_4003(4003, "不支持的语音识别声道", "不支持的语音识别声道"),
    ERROR_CODE_4004(4004, "不支持的语音上传类型", "不支持的语音上传类型"),
    ERROR_CODE_4005(4005, "不支持的语言类型", "不支持的语言类型"),
    ERROR_CODE_4006(4006, "识别音频文件过大", "识别音频文件过大"),
    ERROR_CODE_4007(4007, "识别音频时长过长", "识别音频时长过长"),
    ERROR_CODE_4201(4201, "解密失败", "解密失败"),
    ERROR_CODE_4301(4301, "语音识别失败", "语音识别失败"),
    ERROR_CODE_4303(4303, "服务的异常", "服务的异常"),
    ERROR_CODE_4411(4411, "访问频率受限,请稍后访问", "访问频率受限,请稍后访问"),
    ERROR_CODE_4412(4412, "超过最大请求时长", "超过最大请求时长"),
    ERROR_CODE_5001(5001, "无效的OCR类型", "无效的OCR类型"),
    ERROR_CODE_5002(5002, "不支持的OCR image类型", "不支持的OCR image类型"),
    ERROR_CODE_5003(5003, "不支持的语言类型", "不支持的语言类型"),
    ERROR_CODE_5004(5004, "识别图片过大", "识别图片过大"),
    ERROR_CODE_5005(5005, "不支持的图片类型", "不支持的图片类型"),
    ERROR_CODE_5006(5006, "文件为空", "文件为空"),
    ERROR_CODE_5201(5201, "解密错误，图片base64解密失败", "解密错误，图片base64解密失败"),
    ERROR_CODE_5301(5301, "OCR段落识别失败", "OCR段落识别失败"),
    ERROR_CODE_5411(5411, "访问频率受限", "访问频率受限"),
    ERROR_CODE_5412(5412, "超过最大识别流量", "超过最大识别流量"),
    ERROR_CODE_9001(9001, "不支持的语音格式", "不支持的语音格式"),
    ERROR_CODE_9002(9002, "不支持的语音采样率", "不支持的语音采样率"),
    ERROR_CODE_9003(9003, "不支持的语音声道", "不支持的语音声道"),
    ERROR_CODE_9004(9004, "不支持的语音上传类型", "不支持的语音上传类型"),
    ERROR_CODE_9005(9005, "不支持的语音识别 Language类型", "不支持的语音识别 Language类型"),
    ERROR_CODE_9301(9301, "ASR识别失败", "ASR识别失败"),
    ERROR_CODE_9303(9303, "服务器内部错误", "服务器内部错误"),
    ERROR_CODE_9411(9411, "访问频率受限（超过最大调用次数）", "访问频率受限（超过最大调用次数）"),
    ERROR_CODE_9412(9412, "超过最大处理语音长度", "超过最大处理语音长度"),
    ERROR_CODE_10001(10001, "无效的OCR类型", "无效的OCR类型"),
    ERROR_CODE_10002(10002, "不支持的OCR image类型", "不支持的OCR image类型"),
    ERROR_CODE_10004(10004, "识别图片过大", "识别图片过大"),
    ERROR_CODE_10201(10201, "图片base64解密失败", "图片base64解密失败"),
    ERROR_CODE_10301(10301, "OCR段落识别失败", "OCR段落识别失败"),
    ERROR_CODE_10411(10411, "访问频率受限", "访问频率受限"),
    ERROR_CODE_10412(10412, "超过最大识别流量", "超过最大识别流量"),
    ERROR_CODE_11001(11001, "不支持的语音识别格式", "不支持的语音识别格式"),
    ERROR_CODE_11002(11002, "不支持的语音识别采样率", "不支持的语音识别采样率"),
    ERROR_CODE_11003(11003, "不支持的语音识别声道", "不支持的语音识别声道"),
    ERROR_CODE_11004(11004, "不支持的语音上传类型", "不支持的语音上传类型"),
    ERROR_CODE_11005(11005, "不支持的语言类型", "不支持的语言类型"),
    ERROR_CODE_11006(11006, "识别音频文件过大", "识别音频文件过大"),
    ERROR_CODE_11007(11007, "识别音频时长过长，最大支持30s", "识别音频时长过长，最大支持30s"),
    ERROR_CODE_11201(11201, "解密失败", "解密失败"),
    ERROR_CODE_11301(11301, "语音识别失败", "语音识别失败"),
    ERROR_CODE_11303(11303, "服务的异常", "服务的异常"),
    ERROR_CODE_11411(11411, "访问频率受限,请稍后访问", "访问频率受限,请稍后访问"),
    ERROR_CODE_11412(11412, "超过最大请求时长", "超过最大请求时长"),
    ERROR_CODE_12001(12001, "图片尺寸过大", "图片尺寸过大"),
    ERROR_CODE_12002(12002, "图片base64解密失败", "图片base64解密失败"),
    ERROR_CODE_12003(12003, "引擎服务器返回错误", "引擎服务器返回错误"),
    ERROR_CODE_12004(12004, "图片为空", "图片为空"),
    ERROR_CODE_12005(12005, "不支持的识别图片类型", "不支持的识别图片类型"),
    ERROR_CODE_12006(12006, "图片无匹配结果", "图片无匹配结果"),
    ERROR_CODE_13001(13001, "不支持的角度类型", "不支持的角度类型"),
    ERROR_CODE_13002(13002, "不支持的文件类型", "不支持的文件类型"),
    ERROR_CODE_13003(13003, "表格识别图片过大", "表格识别图片过大"),
    ERROR_CODE_13004(13004, "文件为空", "文件为空"),
    ERROR_CODE_13301(13301, "表格识别失败", "表格识别失败"),
    ERROR_CODE_15001(15001, "需要图片", "需要图片"),
    ERROR_CODE_15002(15002, "图片过大（1M）", "图片过大（1M）"),
    ERROR_CODE_15003(15003, "服务调用失败", "服务调用失败"),
    ERROR_CODE_17001(17001, "需要图片", "需要图片"),
    ERROR_CODE_17002(17002, "图片过大（1M）", "图片过大（1M）"),
    ERROR_CODE_17003(17003, "识别类型未找到", "识别类型未找到"),
    ERROR_CODE_17004(17004, "不支持的识别类型", "不支持的识别类型"),
    ERROR_CODE_17005(17005, "服务调用失败", "服务调用失败"),
    ;

    YoudaoResCodeEnum(Integer code, String msg, String solve) {
        this.code = code;
        this.msg = msg;
        this.solve = solve;
    }

    YoudaoResCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.msg = "请重试";
    }

    public static YoudaoResCodeEnum getEnumByCode(Integer code) {
        for (YoudaoResCodeEnum codeEnum : values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        return null;
    }

    public String getErrorDesc() {
        return "遇到：" + this.getMsg() + ",解决方案：" + this.getSolve();
    }

    public Integer code;
    private String msg;

    private String solve;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getSolve() {
        return solve;
    }
}
