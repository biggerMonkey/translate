package pers.biggermonkey.translate.enums;


import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author: huangwenjun16
 * @date: 2024/6/27 11:11
 * @description:
 */
public enum FileTypeEnum {
    JAVA("java", Lists.newArrayList("//"), Lists.newArrayList(new String[]{"/**", "*/"}, new String[]{"/*", "*/"})),
    XML("xml", null, Lists.newArrayList(new String[]{"<!--", "-->"}, new String[]{"<![CDATA[", "]]>"})),
    ;

    private String suffix;

    private List<String> singleLine;

    private List<String[]> multiLine;

    FileTypeEnum(String suffix, List<String> singleLine, List<String[]> multiLine) {
        this.suffix = suffix;
        this.singleLine = singleLine;
        this.multiLine = multiLine;
    }

    public static FileTypeEnum getEnumBySuffix(String fileSuffix) {
        for (FileTypeEnum fileTypeEnum : values()) {
            if (fileTypeEnum.getSuffix().equals(fileSuffix)) {
                return fileTypeEnum;
            }
        }
        return null;
    }

    public String getSuffix() {
        return suffix;
    }

    public List<String> getSingleLine() {
        return singleLine;
    }

    public List<String[]> getMultiLine() {
        return multiLine;
    }
}
