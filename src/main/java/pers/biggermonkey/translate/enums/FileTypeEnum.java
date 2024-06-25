package pers.biggermonkey.translate.enums;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 09:14
 * @description:
 */
public enum FileTypeEnum {
    JAVA("java"),
    XML("xml"),
    ;

    private String fileSuffix;

    public static FileTypeEnum getEnumBySuffix(String fileSuffix) {
        for (FileTypeEnum fileTypeEnum : values()) {
            if (fileTypeEnum.getFileSuffix().equals(fileSuffix)) {
                return fileTypeEnum;
            }
        }
        return null;
    }

    FileTypeEnum(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }
}
