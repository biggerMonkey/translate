package pers.biggermonkey.translate.file;

import pers.biggermonkey.translate.enums.FileTypeEnum;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 09:10
 * @description:
 */
public interface FileTranslateUtils {

    void translateFile(TranslateFileDto translateFileDto);

    FileTypeEnum supportType();
}
