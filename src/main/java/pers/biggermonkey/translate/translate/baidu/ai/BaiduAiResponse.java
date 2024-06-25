package pers.biggermonkey.translate.translate.baidu.ai;

import pers.biggermonkey.translate.translate.baidu.BaiduResponse;

import java.util.List;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 11:32
 * @description:
 */
public class BaiduAiResponse {

    private String log_id;

    private BaiduResponse result;

    private String error_msg;
    private Integer error_code;

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public BaiduResponse getResult() {
        return result;
    }

    public void setResult(BaiduResponse result) {
        this.result = result;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }
}
