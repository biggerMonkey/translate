package pers.biggermonkey.translate;

import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.file.FileTranslateUtilsManager;
import pers.biggermonkey.translate.file.TranslateFileDto;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: huangwenjun16
 * @date: 2024/6/19 17:49
 * @description:
 */
public class TranslateFile {
    private static List<File> fileList;

    private FileTranslateUtilsManager fileTranslateUtilsManager;

    public TranslateFile() {
        this.fileTranslateUtilsManager = new FileTranslateUtilsManager();
        fileList = new ArrayList<>();
    }

    //循环找到执行目录下的所有文件，并挨个翻译
    public void translateFiles(String rootDir, TranslateFileDto translateFileDto
            , JTextArea showProcessing, JLabel progressBar) {
        getFileList(new File(rootDir));
        int totalFile = fileList.size();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < totalFile; i++) {
            File file = fileList.get(i);
            showProcessing.append(file.getPath() + Constants.LINE_BREAKS);
            // 滚动到最后一行
            showProcessing.setCaretPosition(showProcessing.getDocument().getLength());
            //更新文件进度
            progressBar.setText((i + 1) + "/" + totalFile + " 耗时：" + (System.currentTimeMillis() - startTime) / 1000 + "s");
            translateFileDto.setOldFile(file);
            fileTranslateUtilsManager.translateFile(translateFileDto);
        }
    }

    private void getFileList(File file) {
        if (file.isFile()) {
            fileList.add(file);
            return;
        }
        for (File listFile : file.listFiles()) {
            getFileList(listFile);
        }
    }
}
