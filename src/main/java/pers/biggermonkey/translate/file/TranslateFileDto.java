package pers.biggermonkey.translate.file;

import pers.biggermonkey.translate.enums.LanguageTypeEnum;
import pers.biggermonkey.translate.enums.ResultDealTypeEnum;
import pers.biggermonkey.translate.enums.TranslateSourceEnum;

import java.io.File;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 16:13
 * @description:
 */
public class TranslateFileDto {
    /**
     * 源文件
     */
    private File oldFile;
    /**
     * 源语言
     */
    private LanguageTypeEnum fromLanguage;
    /**
     * 目标语言
     */
    private LanguageTypeEnum toLanguage;
    /**
     * 翻译渠道
     */
    private TranslateSourceEnum translateSourceEnum;
    /**
     * 结果处理方式
     */
    private ResultDealTypeEnum resultDealTypeEnum;

    public TranslateFileDto(File oldFile) {
        this.oldFile = oldFile;
    }

    public TranslateFileDto(File oldFile, LanguageTypeEnum fromLanguage, LanguageTypeEnum toLanguage) {
        this.oldFile = oldFile;
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
    }

    public TranslateFileDto(File oldFile, LanguageTypeEnum fromLanguage, LanguageTypeEnum toLanguage, TranslateSourceEnum translateSourceEnum) {
        this.oldFile = oldFile;
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.translateSourceEnum = translateSourceEnum;
        this.resultDealTypeEnum = ResultDealTypeEnum.BOTH;
    }

    public TranslateFileDto(LanguageTypeEnum fromLanguage, LanguageTypeEnum toLanguage, TranslateSourceEnum translateSourceEnum,ResultDealTypeEnum resultDealTypeEnum) {
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.translateSourceEnum = translateSourceEnum;
        this.resultDealTypeEnum = resultDealTypeEnum;
    }

    public TranslateSourceEnum getTranslateSourceEnum() {
        return translateSourceEnum;
    }

    public void setTranslateSourceEnum(TranslateSourceEnum translateSourceEnum) {
        this.translateSourceEnum = translateSourceEnum;
    }

    public File getOldFile() {
        return oldFile;
    }

    public void setOldFile(File oldFile) {
        this.oldFile = oldFile;
    }

    public LanguageTypeEnum getFromLanguage() {
        return fromLanguage;
    }

    public void setFromLanguage(LanguageTypeEnum fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    public LanguageTypeEnum getToLanguage() {
        return toLanguage;
    }

    public void setToLanguage(LanguageTypeEnum toLanguage) {
        this.toLanguage = toLanguage;
    }

    public ResultDealTypeEnum getResultDealTypeEnum() {
        return resultDealTypeEnum;
    }

    public void setResultDealTypeEnum(ResultDealTypeEnum resultDealTypeEnum) {
        this.resultDealTypeEnum = resultDealTypeEnum;
    }
}
