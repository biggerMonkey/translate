package pers.biggermonkey.translate.file.xml;

import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.enums.FileTypeEnum;
import pers.biggermonkey.translate.file.AbsFileTranslateUtils;
import pers.biggermonkey.translate.file.FileTranslateUtils;
import pers.biggermonkey.translate.file.TranslateFileDto;

import java.io.*;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 09:11
 * @description:
 */
public class XmlFileTranslateUtils extends AbsFileTranslateUtils implements FileTranslateUtils {
    @Override
    public void translateFile(TranslateFileDto translateFileDto) {

    }

    @Override
    public boolean supportType(String fileName) {
        return FileTypeEnum.XML.equals(getFileType(fileName));
    }
}
