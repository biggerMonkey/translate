package pers.biggermonkey.translate.file;

import pers.biggermonkey.translate.common.FileUtil;
import pers.biggermonkey.translate.enums.FileTypeEnum;
import pers.biggermonkey.translate.enums.LanguageTypeEnum;
import pers.biggermonkey.translate.file.java.JavaFileTranslateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 09:19
 * @description:
 */
public class FileTranslateUtilsManager {
    private Map<FileTypeEnum, FileTranslateUtils> fileTranslateUtils;

    public FileTranslateUtilsManager() {
        //初始化
        this.fileTranslateUtils = new HashMap<>();
        this.fileTranslateUtils.put(FileTypeEnum.JAVA, new DefaultFileTranslateUtils());
        this.fileTranslateUtils.put(FileTypeEnum.XML, new DefaultFileTranslateUtils());
    }

    public void translateFile(TranslateFileDto translateFileDto) {
        if (!validateParam(translateFileDto)) {
            return;
        }
        FileTypeEnum fileTypeEnum = FileUtil.getFileType(translateFileDto.getOldFile().getName());
        if (fileTypeEnum == null) {
            return;
        }
        FileTranslateUtils fileTranslateUtil = fileTranslateUtils.get(fileTypeEnum);
        if (fileTranslateUtil == null) {
            fileTranslateUtil = new DefaultFileTranslateUtils();
        }
        fileTranslateUtil.translateFile(translateFileDto);
    }

    public boolean validateParam(TranslateFileDto translateFileDto) {
        if (translateFileDto == null || translateFileDto.getOldFile() == null) {
            return false;
        }
        if (translateFileDto.getFromLanguage() == null) {
            translateFileDto.setFromLanguage(LanguageTypeEnum.ZH);
        }
        if (translateFileDto.getToLanguage() == null) {
            translateFileDto.setToLanguage(LanguageTypeEnum.EN);
        }
        return true;
    }
}
