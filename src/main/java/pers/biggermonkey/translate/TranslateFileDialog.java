package pers.biggermonkey.translate;

import com.intellij.openapi.module.Module;
import org.apache.commons.lang3.StringUtils;
import pers.biggermonkey.translate.common.BizException;
import pers.biggermonkey.translate.common.ModuleUtils;
import pers.biggermonkey.translate.enums.LanguageTypeEnum;
import pers.biggermonkey.translate.enums.ResultDealTypeEnum;
import pers.biggermonkey.translate.enums.TranslateSourceEnum;
import pers.biggermonkey.translate.file.TranslateFileDto;
import pers.biggermonkey.translate.model.ModuleInfoModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: huangwenjun16
 * @date: 2024/6/19 16:17
 * @description:
 */
public class TranslateFileDialog {

    private JPanel jpanel;
    private JComboBox modules;
    private JTextField dirPath;
    private JButton cancelBtn;
    private JButton translateBtn;
    private JTextArea showProcessing;
    private JScrollPane showProcessingPane;
    private JComboBox translateSource;
    private JComboBox fromLanguage;
    private JComboBox toLanguage;
    private JComboBox resultDealType;
    private JLabel progressBar;

    public TranslateFileDialog() {
        Module[] allModule = ModuleUtils.getAllModule();
        for (Module module : allModule) {
            modules.addItem(new ModuleInfoModel(module.getName(), module.getModuleFilePath()));
        }
        ModuleInfoModel defaultSelected = new ModuleInfoModel();
        modules.addItem(defaultSelected);
        modules.setSelectedItem(defaultSelected);
        for (TranslateSourceEnum translateSourceEnum : TranslateSourceEnum.values()) {
            translateSource.addItem(translateSourceEnum);
        }
        translateSource.setSelectedItem(TranslateSourceEnum.BAIDU);

        fromLanguage.addItem(LanguageTypeEnum.ZH);

        for (LanguageTypeEnum languageTypeEnum : LanguageTypeEnum.values()) {
            toLanguage.addItem(languageTypeEnum);
        }

        toLanguage.setSelectedItem(LanguageTypeEnum.EN);
        for (ResultDealTypeEnum resultDealEnum : ResultDealTypeEnum.values()) {
            resultDealType.addItem(resultDealEnum);
        }
        resultDealType.setSelectedItem(ResultDealTypeEnum.ONLY_TARGET);

        this.modules.addActionListener(e -> {
            ModuleInfoModel selectedItem = (ModuleInfoModel) modules.getSelectedItem();
            if (selectedItem != null) {
                System.out.println("Selected Key: " + selectedItem.getName() + " " + selectedItem.getRootPath());
            }
        });
        translateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                translateBtn.setEnabled(false);
                String dirFullPath = dirPath.getText();
                if (StringUtils.isBlank(dirFullPath)) {
                    ModuleInfoModel selectedItem = (ModuleInfoModel) modules.getSelectedItem();
                    if (selectedItem != null) {
                        dirFullPath = selectedItem.getRootPath();
                    }
                }
                if (StringUtils.isBlank(dirFullPath)) {
                    JOptionPane.showMessageDialog(jpanel, "请选择模块或者输入路径");
                    return;
                }
                LanguageTypeEnum fromLang = (LanguageTypeEnum) fromLanguage.getSelectedItem();
                LanguageTypeEnum toLang = (LanguageTypeEnum) toLanguage.getSelectedItem();
                TranslateSourceEnum translateSourceEnum = (TranslateSourceEnum) translateSource.getSelectedItem();
                ResultDealTypeEnum resultDealTypeEnum = (ResultDealTypeEnum) resultDealType.getSelectedItem();
                if (StringUtils.isNotBlank(dirFullPath)) {
                    String finalDirFullPath = dirFullPath;
                    new Thread(() -> {
                        try {
                            new TranslateFile().translateFiles(finalDirFullPath
                                    , new TranslateFileDto(fromLang, toLang, translateSourceEnum, resultDealTypeEnum)
                                    , showProcessing, progressBar);
                            JOptionPane.showMessageDialog(jpanel, "翻译完成，请重新格式化代码");
                            translateBtn.setEnabled(true);
                        } catch (BizException bizException) {
                            translateBtn.setEnabled(true);
                            JOptionPane.showMessageDialog(jpanel, bizException.getMessage());
                        } catch (Exception exception) {
                            translateBtn.setEnabled(true);
                            exception.printStackTrace();
                            JOptionPane.showMessageDialog(jpanel, "未知错误，请重试");
                        }
                    }).start();
                }
            }
        });
    }

    public JComboBox getModules() {
        return modules;
    }

    public void setModules(JComboBox modules) {
        this.modules = modules;
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    public void setJpanel(JPanel jpanel) {
        this.jpanel = jpanel;
    }

    public JTextField getDirPath() {
        return dirPath;
    }

    public void setDirPath(JTextField dirPath) {
        this.dirPath = dirPath;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(JButton cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public JButton getTranslateBtn() {
        return translateBtn;
    }

    public void setTranslateBtn(JButton translateBtn) {
        this.translateBtn = translateBtn;
    }

    public JTextArea getShowProcessing() {
        return showProcessing;
    }

    public void setShowProcessing(JTextArea showProcessing) {
        this.showProcessing = showProcessing;
    }

    public JScrollPane getShowProcessingPane() {
        return showProcessingPane;
    }

    public void setShowProcessingPane(JScrollPane showProcessingPane) {
        this.showProcessingPane = showProcessingPane;
    }

    public JComboBox getTranslateSource() {
        return translateSource;
    }

    public void setTranslateSource(JComboBox translateSource) {
        this.translateSource = translateSource;
    }

    public JComboBox getFromLanguage() {
        return fromLanguage;
    }

    public void setFromLanguage(JComboBox fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    public JComboBox getToLanguage() {
        return toLanguage;
    }

    public void setToLanguage(JComboBox toLanguage) {
        this.toLanguage = toLanguage;
    }

    public JComboBox getResultDealType() {
        return resultDealType;
    }

    public void setResultDealType(JComboBox resultDealType) {
        this.resultDealType = resultDealType;
    }

    public JLabel getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JLabel progressBar) {
        this.progressBar = progressBar;
    }
}
