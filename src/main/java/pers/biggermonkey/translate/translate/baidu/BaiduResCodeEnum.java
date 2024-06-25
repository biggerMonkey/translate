package pers.biggermonkey.translate.translate.baidu;

/**
 * @author: huangwenjun16
 * @date: 2024/6/22 11:11
 * @description:
 */
public enum BaiduResCodeEnum {
    SUCCESS(52000, "成功", ""),
    TIMEOUT(52001, "请求超时", "请重试"),
    SYSTEM_ERROR(52002, "系统错误", "请重试"),
    NO_PERMISSION(52003, "未授权用户", "请检查appid是否正确或者服务是否开通"),
    PARAM_ERROR(54000, "必填参数为空", "请检查是否少传参数"),
    SIGN_ERROR(54001, "签名错误", "请重试"),
    QPS_LIMIT_REACHED(54003, "访问频率受限", "请降低您的调用频率，或进行身份认证后切换为高级版/尊享版"),
    INSUFFICIENT_BALANCE(54004, "账户余额不足", "请前往管理控制台为账户充值"),
    LONG_REQ_FREQUENTLY(54005, "长query请求频繁", "请降低长query的发送频率，3s后再试"),
    CLIENT_IP_ILLEGAL(58000, "客户端IP非法", "检查个人资料里填写的IP地址是否正确，可前往开发者信息-基本信息修改"),
    UNKNOWN(58001, "译文语言方向不支持", "检查译文语言是否在语言列表里"),
    SERVER_CLOSE(58002, "服务当前已关闭", "请前往管理控制台开启服务"),
    CERTIFICATION_NO_PASS(90107, "认证未通过或未生效", "请前往我的认证查看认证进度"),
    REQ_CONTENT_RISK(20003, "请求内容存在安全风险", "请检查请求内容"),
    ;

    BaiduResCodeEnum(Integer code, String msg, String solve) {
        this.code = code;
        this.msg = msg;
        this.solve = solve;
    }

    public static BaiduResCodeEnum getEnumByCode(Integer code) {
        for (BaiduResCodeEnum codeEnum : values()) {
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
