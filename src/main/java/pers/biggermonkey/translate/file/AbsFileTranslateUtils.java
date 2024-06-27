package pers.biggermonkey.translate.file;

import com.google.common.collect.Lists;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import pers.biggermonkey.translate.common.BizException;
import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.common.StringLanguageUtils;
import pers.biggermonkey.translate.enums.LanguageTypeEnum;
import pers.biggermonkey.translate.enums.TranslateSourceEnum;
import pers.biggermonkey.translate.enums.TranslateTypeEnum;
import pers.biggermonkey.translate.translate.TranslateRequest;
import pers.biggermonkey.translate.translate.TranslateResponse;
import pers.biggermonkey.translate.translate.TranslateUtilsManager;

import java.io.*;
import java.util.Map;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 09:11
 * @description:
 */
public abstract class AbsFileTranslateUtils implements FileTranslateUtils {
    protected LanguageTypeEnum fromLang;
    protected LanguageTypeEnum toLang;
    protected TranslateSourceEnum translateSourceEnum;

    @Override
    public void translateFile(TranslateFileDto translateFileDto) {
        //写入新的临时文件
        FileOutputStream tempFos = null;
        OutputStreamWriter tempOsw = null;
        BufferedWriter tempBw = null;
        File tempFile = null;
        //读取旧文件内容
        FileInputStream oldFis = null;
        InputStreamReader oldIsr = null;
        BufferedReader oldBr = null;
        try {
            initParam(translateFileDto);
            File oldFile = translateFileDto.getOldFile();
            String oldFilePath = oldFile.getAbsolutePath();
            String tempFilePath = oldFilePath + "temp";
            tempFile = new File(tempFilePath);
            //写入新的临时文件
            tempFos = new FileOutputStream(tempFile);
            tempOsw = new OutputStreamWriter(tempFos, Constants.UTF_8);
            tempBw = new BufferedWriter(tempOsw);

            //读取旧文件内容
            oldFis = new FileInputStream(oldFile);
            oldIsr = new InputStreamReader(oldFis, Constants.UTF_8);
            oldBr = new BufferedReader(oldIsr);
            translateFile(translateFileDto, tempBw, oldBr);
            //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
            oldBr.close();
            oldIsr.close();
            oldFis.close();
            tempBw.close();
            tempOsw.close();
            tempFos.close();
            //重写文件
            oldFile.delete();
            tempFile.renameTo(new File(oldFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
            try {
                if (oldBr != null) {
                    oldBr.close();
                }
                if (oldIsr != null) {
                    oldIsr.close();
                }
                if (oldIsr != null) {
                    oldFis.close();
                }

                if (oldIsr != null) {
                    tempBw.close();
                }
                if (oldIsr != null) {
                    tempOsw.close();
                }
                if (oldIsr != null) {
                    tempFos.close();
                }
                if (tempFile != null) {
                    tempFile.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public abstract void translateFile(TranslateFileDto translateFileDto, BufferedWriter tempBw, BufferedReader oldBr) throws Exception;

    public String translateWord(String inputStr) {
        if (StringLanguageUtils.validateStr(inputStr, toLang)) {
            return "";
        }
        TranslateUtilsManager translateUtilsManager = TranslateUtilsManager.getInstance();
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
