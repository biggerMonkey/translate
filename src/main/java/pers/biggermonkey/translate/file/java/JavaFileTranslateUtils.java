package pers.biggermonkey.translate.file.java;

import org.apache.commons.lang3.StringUtils;
import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.common.StringLanguageUtils;
import pers.biggermonkey.translate.enums.FileTypeEnum;
import pers.biggermonkey.translate.enums.ResultDealTypeEnum;
import pers.biggermonkey.translate.file.AbsFileTranslateUtils;
import pers.biggermonkey.translate.file.FileTranslateUtils;
import pers.biggermonkey.translate.file.TranslateFileDto;

import java.io.*;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 09:11
 * @description:
 */
public class JavaFileTranslateUtils extends AbsFileTranslateUtils implements FileTranslateUtils {
    @Override
    public void translateFile(TranslateFileDto translateFileDto) {
        //写入新的临时文件
        FileOutputStream tempFos = null;
        OutputStreamWriter tempOsw = null;
        BufferedWriter tempBw = null;
        //读取旧文件内容
        FileInputStream oldFis = null;
        InputStreamReader oldIsr = null;
        BufferedReader oldBr = null;
        try {
            initParam(translateFileDto);
            File oldFile = translateFileDto.getOldFile();
            String oldFilePath = oldFile.getAbsolutePath();
            String tempFilePath = oldFilePath + "temp";
            File tempFile = new File(tempFilePath);
            //写入新的临时文件
            tempFos = new FileOutputStream(tempFile);
            tempOsw = new OutputStreamWriter(tempFos, Constants.UTF_8);
            tempBw = new BufferedWriter(tempOsw);

            //读取旧文件内容
            oldFis = new FileInputStream(oldFile);
            oldIsr = new InputStreamReader(oldFis, Constants.UTF_8);
            oldBr = new BufferedReader(oldIsr);
            String line;
            StringBuilder annSbOne = new StringBuilder();
            StringBuilder annSbTwo = new StringBuilder();
            //找到注释 并进行翻译
            while ((line = oldBr.readLine()) != null) {
                if (line.contains(Constants.JAVA_START_SIGNE_COMMENT)) {
                    //单行注释
                    String[] lineArr = line.split(Constants.JAVA_START_SIGNE_COMMENT);
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
                tempBw.write(line + Constants.LINE_BREAKS);
            }
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
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            if (StringLanguageUtils.validateStr(str, toLang)) {
                tempBw.write(str + Constants.LINE_BREAKS);
                continue;
            }
            tempBw.write(translateWord(str) + Constants.LINE_BREAKS);
        }
        tempBw.write(Constants.JAVA_END_MULTI_COMMENT + Constants.LINE_BREAKS);
    }

    @Override
    public boolean supportType(String fileName) {
        return FileTypeEnum.JAVA.equals(getFileType(fileName));
    }
}
