package pers.biggermonkey.translate.translate;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:01
 * @description:
 */
public class TranslateResponse {

    private String source;

    private String target;

    private String from;

    private String to;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

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
}
