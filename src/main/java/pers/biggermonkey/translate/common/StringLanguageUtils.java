package pers.biggermonkey.translate.common;


import pers.biggermonkey.translate.enums.LanguageTypeEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: huangwenjun16
 * @date: 2024/6/18 11:46
 * @description: 字符串语言判断
 */
public class StringLanguageUtils {


    /**
     * 校验字符串中是否包含某种语言的字符
     *
     * @param str
     * @param languageTypeEnum
     * @return
     */
    public static boolean validateStr(String str, LanguageTypeEnum languageTypeEnum) {
        if (languageTypeEnum.getMatchPattern() == null) {
            return false;
        }
        Pattern p = Pattern.compile(languageTypeEnum.getMatchPattern());
        return p.matcher(cleanStr(str)).find();
    }

    /**
     * 清理不需要翻译的字符、数字
     *
     * @param str
     * @return
     */
    public static String cleanStr(String str) {
        return str.replaceAll("[0-9\\n\\p{Punct}]", "");
    }

    public static void main(String[] args) {
        String str = "huangwenjun16中文";
        System.out.println(str);
        System.out.println("结果：" + validateStr(str,LanguageTypeEnum.ZH));
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        System.out.println("结果2：" + m.find());
    }
}
