package pers.biggermonkey.translate.translate.youdao;

import java.util.List;

/**
 * @author: huangwenjun16
 * @date: 2024/6/25 20:24
 * @description:
 */
public class YoudaoResponse {

    private Integer errorCode;

    private String query;

    private List<String> translation;

    private String l;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }
}
