package pers.biggermonkey.translate.file.xml;

import pers.biggermonkey.translate.enums.FileTypeEnum;
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
public class XmlFileTranslateUtils extends AbsFileTranslateUtils implements FileTranslateUtils {

    @Override
    public void translateFile(TranslateFileDto translateFileDto, BufferedWriter tempBw, BufferedReader oldBr) throws Exception {

    }

    @Override
    public FileTypeEnum supportType() {
        return FileTypeEnum.XML;
    }
}
