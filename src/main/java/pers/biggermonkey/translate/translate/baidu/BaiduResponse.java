package pers.biggermonkey.translate.translate.baidu;

import java.util.List;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 11:32
 * @description:
 */
public class BaiduResponse {
    private String from;
    private String to;

    private List<BaiduTranslateRes> trans_result;

    private Integer error_code;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<BaiduTranslateRes> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<BaiduTranslateRes> trans_result) {
        this.trans_result = trans_result;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }
}
