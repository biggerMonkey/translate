package pers.biggermonkey.translate.file;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.common.FileUtil;
import pers.biggermonkey.translate.common.StringLanguageUtils;
import pers.biggermonkey.translate.enums.FileTypeEnum;
import pers.biggermonkey.translate.enums.ResultDealTypeEnum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 09:11
 * @description:
 */
public class DefaultFileTranslateUtils extends AbsFileTranslateUtils implements FileTranslateUtils {

    @Override
    public void translateFile(TranslateFileDto translateFileDto, BufferedWriter tempBw, BufferedReader oldBr) throws Exception {
        FileTypeEnum fileTypeEnum = FileUtil.getFileType(translateFileDto.getOldFile().getName());
        String line;
        StringBuilder annSb = new StringBuilder();

        //找到注释 并进行翻译
        List<String> singleLines = fileTypeEnum.getSingleLine();
        List<String[]> multiLines = fileTypeEnum.getMultiLine();
        //多行注释下标，避免判断是出现问题
        int multiLineIndex = -1;
        while ((line = oldBr.readLine()) != null) {
            if (CollectionUtils.isNotEmpty(singleLines)) {
                //为空表示，没有单行注释
                boolean havaSingle = false;
                for (String singleLine : singleLines) {
                    if (line.contains(singleLine)) {
                        //单行注释
                        havaSingle = true;
                        String[] lineArr = line.split(singleLine);
                        if (lineArr.length == 0) {
                            tempBw.write(line + Constants.LINE_BREAKS);
                            break;
                        }
                        if (!isEmptyAndNoChar(lineArr[0])) {
                            //注释在有效内容之后
                            if (ResultDealTypeEnum.BOTH.equals(translateFileDto.getResultDealTypeEnum())) {
                                tempBw.write(singleLine + lineArr[1] + Constants.LINE_BREAKS);
                            }
                            String translateRes = translateWord(lineArr[1]);
                            if (StringUtils.isNotBlank(translateRes)) {
                                tempBw.write(singleLine + translateRes + Constants.LINE_BREAKS);
                            }
                            tempBw.write(lineArr[0] + Constants.LINE_BREAKS);
                            continue;
                        }

                        //单行注释，独立一行
                        if (ResultDealTypeEnum.BOTH.equals(translateFileDto.getResultDealTypeEnum())) {
                            tempBw.write(line + Constants.LINE_BREAKS);
                        }
                        if (lineArr.length < 2) {
                            //可能未空注解，不用翻译，直接保留
                            break;
                        }
                        String translateRes = translateWord(lineArr[1]);
                        if (StringUtils.isNotBlank(translateRes)) {
                            tempBw.write(lineArr[0] + singleLine + translateRes + Constants.LINE_BREAKS);
                        }
                        break;
                    }
                }
                if (havaSingle) {
                    continue;
                }
            }
            if (CollectionUtils.isNotEmpty(multiLines)) {
                boolean havaMulti = false;
                if (multiLineIndex != -1) {
                    String[] multiLine = multiLines.get(multiLineIndex);
                    //多行注释
                    annSb.append(line);
                    if (line.contains(multiLine[1])) {
                        dealMultiComment(annSb, multiLine[0], multiLine[1], tempBw, translateFileDto);
                        annSb = new StringBuilder();
                        multiLineIndex = -1;
                        continue;
                    }
                    annSb.append(Constants.LINE_BREAKS);
                    continue;
                }
                for (int i = 0; i < multiLines.size(); i++) {
                    String[] multiLine = multiLines.get(i);
                    if (line.contains(multiLine[0]) || annSb.length() > 0) {
                        //多行注释
                        multiLineIndex = i;
                        havaMulti = true;
                        annSb.append(line);
                        if (line.contains(multiLine[1])) {
                            dealMultiComment(annSb, multiLine[0], multiLine[1], tempBw, translateFileDto);
                            multiLineIndex = -1;
                            annSb = new StringBuilder();
                            continue;
                        }
                        annSb.append(Constants.LINE_BREAKS);
                        break;
                    }
                }
                if (havaMulti) {
                    continue;
                }
            }
            tempBw.write(line + Constants.LINE_BREAKS);
        }
    }

    public void dealMultiComment(StringBuilder annotationSb, String startComment, String endComment
            , BufferedWriter tempBw, TranslateFileDto translateFileDto) throws Exception {
        //遇到结束符号
        String annotationStr = annotationSb.toString().replace(endComment, "")
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
            if (StringLanguageUtils.validateStr(str, toLang)) {
                tempBw.write(str + Constants.LINE_BREAKS);
                continue;
            }
            if (StringUtils.isBlank(str)) {
                continue;
            }
            tempBw.write(translateWord(str) + Constants.LINE_BREAKS);
        }
        tempBw.write(endComment + Constants.LINE_BREAKS);
    }

    @Override
    public FileTypeEnum supportType() {
        return null;
    }
}
