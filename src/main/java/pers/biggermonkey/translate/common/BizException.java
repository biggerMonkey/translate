package pers.biggermonkey.translate.common;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 16:36
 * @description:
 */
public class BizException extends RuntimeException {
    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

}
