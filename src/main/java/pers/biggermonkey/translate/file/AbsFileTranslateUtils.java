package pers.biggermonkey.translate.file;

import com.google.common.collect.Lists;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import pers.biggermonkey.translate.common.BizException;
import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.common.StringLanguageUtils;
import pers.biggermonkey.translate.enums.FileTypeEnum;
import pers.biggermonkey.translate.enums.LanguageTypeEnum;
import pers.biggermonkey.translate.enums.TranslateSourceEnum;
import pers.biggermonkey.translate.enums.TranslateTypeEnum;
import pers.biggermonkey.translate.translate.TranslateRequest;
import pers.biggermonkey.translate.translate.TranslateResponse;
import pers.biggermonkey.translate.translate.TranslateUtilsManager;

import java.util.Map;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 09:11
 * @description:
 */
public class AbsFileTranslateUtils {
    protected LanguageTypeEnum fromLang;
    protected LanguageTypeEnum toLang;

    protected TranslateSourceEnum translateSourceEnum;

    public String translateWord(String inputStr) {
        if (StringLanguageUtils.validateStr(inputStr, toLang)) {
            return "";
        }
        TranslateUtilsManager translateUtilsManager = new TranslateUtilsManager();
        TranslateRequest request = new TranslateRequest();
        request.setFrom(fromLang);
        request.setTo(toLang);
        request.setContent(inputStr);
        request.setTranslateSourceEnumList(Lists.newArrayList(translateSourceEnum));
        request.setTranslateTypeEnum(TranslateTypeEnum.FILE);
        Map<TranslateSourceEnum, TranslateResponse> responseMap = translateUtilsManager.translate(request);
        if (MapUtils.isEmpty(responseMap)) {
            throw new BizException("翻译失败,请重试");
        }
        if (translateSourceEnum != null) {
            TranslateResponse translateResponse = responseMap.get(translateSourceEnum);
            return translateResponse.getTarget();
        }
        StringBuilder responseStr = new StringBuilder();
        for (Map.Entry<TranslateSourceEnum, TranslateResponse> responseEntry : responseMap.entrySet()) {
            responseStr.append(responseEntry.getKey().getMsg()).append(":").append(Constants.DEFAULT_SEPARATOR);
            responseStr.append(responseEntry.getValue().getTarget()).append(Constants.DEFAULT_SEPARATOR);
            return responseEntry.getValue().getTarget();
        }
        return "";
    }

    public boolean isEmptyAndNoChar(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        String temp = StringUtils.deleteWhitespace(str);
        if (temp.length() == 0) {
            return true;
        }
        return temp.replace("\t", "").length() == 0;
    }

    public FileTypeEnum getFileType(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        String[] fileNameArr = fileName.split("\\.");
        if (fileNameArr.length < 2) {
            return null;
        }
        return FileTypeEnum.getEnumBySuffix(fileNameArr[fileNameArr.length - 1]);
    }

    public void initParam(TranslateFileDto translateFileDto) {
        setFromLang(translateFileDto.getFromLanguage());
        setToLang(translateFileDto.getToLanguage());
        setTranslateSourceEnum(translateFileDto.getTranslateSourceEnum());
    }

    public LanguageTypeEnum getFromLang() {
        return fromLang;
    }

    public void setFromLang(LanguageTypeEnum fromLang) {
        this.fromLang = fromLang;
    }

    public LanguageTypeEnum getToLang() {
        return toLang;
    }

    public void setToLang(LanguageTypeEnum toLang) {
        this.toLang = toLang;
    }

    public TranslateSourceEnum getTranslateSourceEnum() {
        return translateSourceEnum;
    }

    public void setTranslateSourceEnum(TranslateSourceEnum translateSourceEnum) {
        this.translateSourceEnum = translateSourceEnum;
    }
}
