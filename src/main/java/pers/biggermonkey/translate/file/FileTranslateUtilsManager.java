package pers.biggermonkey.translate.file;

import pers.biggermonkey.translate.enums.LanguageTypeEnum;
import pers.biggermonkey.translate.file.java.JavaFileTranslateUtils;
import pers.biggermonkey.translate.file.xml.XmlFileTranslateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 09:19
 * @description:
 */
public class FileTranslateUtilsManager {
    private List<FileTranslateUtils> fileTranslateUtils;

    public FileTranslateUtilsManager() {
        //初始化
        this.fileTranslateUtils = new ArrayList<>();
        this.fileTranslateUtils.add(new JavaFileTranslateUtils());
        this.fileTranslateUtils.add(new XmlFileTranslateUtils());
    }

    public void translateFile(TranslateFileDto translateFileDto) {
        if (!validateParam(translateFileDto)) {
            return;
        }
        for (FileTranslateUtils fileTranslateUtil : fileTranslateUtils) {
            if (fileTranslateUtil.supportType(translateFileDto.getOldFile().getName())) {
                fileTranslateUtil.translateFile(translateFileDto);
                return;
            }
        }
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
