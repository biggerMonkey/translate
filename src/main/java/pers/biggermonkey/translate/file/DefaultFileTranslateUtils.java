package pers.biggermonkey.translate.file;

import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.enums.FileTypeEnum;

import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 09:11
 * @description:
 */
public class DefaultFileTranslateUtils extends AbsFileTranslateUtils implements FileTranslateUtils {

    @Override
    public void translateFile(TranslateFileDto translateFileDto, BufferedWriter tempBw, BufferedReader oldBr) throws Exception {
        String line;
        while ((line = oldBr.readLine()) != null) {
            tempBw.write(translateWord(line) + Constants.LINE_BREAKS);
        }
    }
    @Override
    public FileTypeEnum supportType() {
        return null;
    }
}
