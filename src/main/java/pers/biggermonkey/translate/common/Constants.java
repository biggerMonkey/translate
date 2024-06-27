package pers.biggermonkey.translate.common;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 14:33
 * @description:
 */
public class Constants {

    public static String DEFAULT_SEPARATOR = System.getProperty("line.separator");

    public static String LINE_BREAKS = "\n";

    public static String UTF_8 = "UTF-8";

    public static String JAVA_START_SIGNE_COMMENT = "//";
    public static String JAVA_START_NORMAL_MULTI_COMMENT = "/*";
    public static String JAVA_START_MULTI_COMMENT = "/**";

    public static String JAVA_END_MULTI_COMMENT = "*/";

    public static String EQUAL_SYMBOL = "=";

    public static String KEY_PREFIX = "translate";

    public static String XML_START_NORMAL_COMMENT = "<!--";
    public static String XML_START_DOC_COMMENT = "<![CDATA[";
    public static String XML_END_NORMAL_COMMENT = "-->";
    public static String XML_END_DOC_COMMENT = "]]>";

    public static String getConfig(String sourceCode, String field) {
        return KEY_PREFIX + "." + sourceCode + "." + field;
    }
}
