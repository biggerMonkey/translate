package pers.biggermonkey.translate.translate.google;


import pers.biggermonkey.translate.enums.TranslateSourceEnum;
import pers.biggermonkey.translate.translate.TranslateRequest;
import pers.biggermonkey.translate.translate.TranslateResponse;
import pers.biggermonkey.translate.translate.TranslateUtils;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 10:05
 * @description:
 */
public class GoogleTranslateUtils implements TranslateUtils {


    @Override
    public TranslateResponse translate(TranslateRequest request) {
        return null;
    }


    @Override
    public TranslateSourceEnum getSupportType() {
        return TranslateSourceEnum.GOOGLE;
    }
}
