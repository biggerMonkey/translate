package pers.biggermonkey.translate.translate.baidu.ai;

/**
 * @author: huangwenjun16
 * @date: 2024/6/22 11:11
 * @description:
 */
public enum BaiduAiResCodeEnum {
    UNKNOWN(1, "未知错误", "请重试"),
    TIMEOUT(2, "服务处理超时", "请重试"),
    REQUEST_LIMIT_REACHED(4, "集群超限额", "请重试"),
    NO_PERMISSION(6, "没有接口权限", "请确认您调用的接口已经被赋权。企业认证生效时间为1小时左右，使用需要企业认证的服务，请等待生效后重试"),
    QPS_LIMIT_REACHED(18, "QPS超限额", "请降低您的调用频率"),
    TOTAL_REQ_LIMIT_REACHED(19, "请求总量超限额", "请检查当前可用字符/次数包额度"),
    INVALID_PARAM(100, "请求参数不合法", "请检查请求参数是否正确"),
    token_invalid(110, "token失效", "请重试"),
    token_expire(111, "token过期", "请重试"),
    ;

    BaiduAiResCodeEnum(Integer code, String msg, String solve) {
        this.code = code;
        this.msg = msg;
        this.solve = solve;
    }

    public static BaiduAiResCodeEnum getEnumByCode(Integer code) {
        for (BaiduAiResCodeEnum codeEnum : values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        return UNKNOWN;
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
