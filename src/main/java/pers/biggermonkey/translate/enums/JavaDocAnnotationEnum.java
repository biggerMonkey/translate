package pers.biggermonkey.translate.enums;

/**
 * @author: huangwenjun16
 * @date: 2024/6/23 15:30
 * @description:
 */
public enum JavaDocAnnotationEnum {
    AUTHOR("@author", "@ author"),
    DEPRECATED("@deprecated", "@ deprecated"),
    DOCROOT("@docRoot", "@ docRoot"),
    EXCEPTION("@exception", "@ exception"),
    INHERITDOC("@inheritDoc", "@ inheritDoc"),
    LINK("@link", "@ link"),
    LINKPLAIN("@linkplain", "@ linkplain"),
    PARAM("@param", "@ param"),
    RETURN("@return", "@ return"),
    SEE("@see", "@ see"),
    SERIAL("@serial", "@ serial"),
    SERIALDATA("@serialData", "@ serialData"),
    SERIALFIELD("@serialField", "@ serialField"),
    SINCE("@since", "@ since"),
    THROWS("@throws", "@ throws"),
    VALUE("@value", "@ value"),
    VERSION("@version", "@ version"),
    ;
    private String sourceCode;

    private String resCode;

    JavaDocAnnotationEnum(String sourceCode, String resCode) {
        this.sourceCode = sourceCode;
        this.resCode = resCode;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getResCode() {
        return resCode;
    }
}
