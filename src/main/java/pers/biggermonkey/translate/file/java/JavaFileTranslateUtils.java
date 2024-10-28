package pers.biggermonkey.translate.file.java;

import org.apache.commons.lang3.StringUtils;
import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.common.StringLanguageUtils;
import pers.biggermonkey.translate.enums.FileTypeEnum;
import pers.biggermonkey.translate.enums.ResultDealTypeEnum;
import pers.biggermonkey.translate.file.AbsFileTranslateUtils;
import pers.biggermonkey.translate.file.FileTranslateUtils;
import pers.biggermonkey.translate.file.TranslateFileDto;

import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 09:11
 * @description:
 */
public class JavaFileTranslateUtils extends AbsFileTranslateUtils implements FileTranslateUtils {
    @Override
    public void translateFile(TranslateFileDto translateFileDto, BufferedWriter tempBw, BufferedReader oldBr) throws Exception {
        String line;
        StringBuilder annSbOne = new StringBuilder();
        StringBuilder annSbTwo = new StringBuilder();
        //找到注释 并进行翻译
        while ((line = oldBr.readLine()) != null) {
            if (line.contains(Constants.JAVA_START_SIGNE_COMMENT)) {
                //单行注释
                String[] lineArr = line.split(Constants.JAVA_START_SIGNE_COMMENT);
                if (lineArr.length < 2) {
                    tempBw.write(line + Constants.LINE_BREAKS);
                    continue;
                }
                if (!isEmptyAndNoChar(lineArr[0])) {
                    //注释在有效内容之后
                    if (ResultDealTypeEnum.BOTH.equals(translateFileDto.getResultDealTypeEnum())) {
                        tempBw.write(Constants.JAVA_START_SIGNE_COMMENT + lineArr[1] + Constants.LINE_BREAKS);
                    }
                    String translateRes = translateWord(lineArr[1]);
                    if (StringUtils.isNotBlank(translateRes)) {
                        tempBw.write(Constants.JAVA_START_SIGNE_COMMENT + translateRes + Constants.LINE_BREAKS);
                    }
                    tempBw.write(lineArr[0] + Constants.LINE_BREAKS);
                    continue;
                }
                //单行注释，独立一行
                if (ResultDealTypeEnum.BOTH.equals(translateFileDto.getResultDealTypeEnum())) {
                    tempBw.write(line + Constants.LINE_BREAKS);
                }
                String translateRes = translateWord(lineArr[1]);
                if (StringUtils.isNotBlank(translateRes)) {
                    tempBw.write(lineArr[0] + Constants.JAVA_START_SIGNE_COMMENT + translateRes + Constants.LINE_BREAKS);
                }
                continue;
            }
            if (line.contains(Constants.JAVA_START_MULTI_COMMENT) || annSbOne.length() > 0) {
                //多行注释
                annSbOne.append(line);
                if (line.contains(Constants.JAVA_END_MULTI_COMMENT)) {
                    dealMultiComment(annSbOne, Constants.JAVA_START_MULTI_COMMENT, tempBw, translateFileDto);
                    annSbOne = new StringBuilder();
                    continue;
                }
                annSbOne.append(Constants.LINE_BREAKS);
                continue;
            }
            if (line.contains(Constants.JAVA_START_NORMAL_MULTI_COMMENT) || annSbTwo.length() > 0) {
                //多行注释
                annSbTwo.append(line);
                if (line.contains(Constants.JAVA_END_MULTI_COMMENT)) {
                    dealMultiComment(annSbTwo, Constants.JAVA_START_NORMAL_MULTI_COMMENT, tempBw, translateFileDto);
                    annSbTwo = new StringBuilder();
                    continue;
                }
                annSbTwo.append(Constants.LINE_BREAKS);
                continue;
            }
            //字符串中不包含当前语言则保留原串
            //eg:china 中文->英文  china 中只包含英文，则直接保留原串 china
            if (!StringLanguageUtils.validateStr(line, fromLang)) {
                tempBw.write(line + Constants.LINE_BREAKS);
                continue;
            }
            if (StringUtils.isBlank(line)) {
                tempBw.write(line + Constants.LINE_BREAKS);
                continue;
            }
            tempBw.write(translateWord(line) + Constants.LINE_BREAKS);
        }
    }

    public void dealMultiComment(StringBuilder annotationSb, String startComment
            , BufferedWriter tempBw, TranslateFileDto translateFileDto) throws Exception {
        //遇到结束符号
        String annotationStr = annotationSb.toString().replace(Constants.JAVA_END_MULTI_COMMENT, "")
                .replace(startComment, "").trim();
        //一行一行翻译，避免
        String[] strArr = annotationStr.split("\\R");
        tempBw.write(startComment + Constants.LINE_BREAKS);
        for (String str : strArr) {
            if (ResultDealTypeEnum.BOTH.equals(translateFileDto.getResultDealTypeEnum())) {
                tempBw.write(str + Constants.LINE_BREAKS);
            }
            //字符串中不包含当前语言则保留原串
            //eg:china 中文->英文  china 中只包含英文，则直接保留原串 china
            if (!StringLanguageUtils.validateStr(str, fromLang)) {
                tempBw.write(str + Constants.LINE_BREAKS);
                continue;
            }
            tempBw.write(translateWord(str) + Constants.LINE_BREAKS);
        }
        tempBw.write(Constants.JAVA_END_MULTI_COMMENT + Constants.LINE_BREAKS);
    }

    @Override
    public FileTypeEnum supportType() {
        return FileTypeEnum.JAVA;
    }
}
