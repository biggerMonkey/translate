package pers.biggermonkey.translate;

import org.apache.commons.collections.MapUtils;
import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.enums.LanguageTypeEnum;
import pers.biggermonkey.translate.enums.TranslateSourceEnum;
import pers.biggermonkey.translate.enums.TranslateTypeEnum;
import pers.biggermonkey.translate.translate.TranslateRequest;
import pers.biggermonkey.translate.translate.TranslateResponse;
import pers.biggermonkey.translate.translate.TranslateUtilsManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: huangwenjun16
 * @date: 2023/9/7 18:12
 * @description:
 */
public class TranslateWordDialog {
    private JComboBox inputLanguage;
    private JComboBox outputLanguage;
    private JTextArea input;
    private JButton exchange;
    private JButton translate;
    private JPanel jpanel;

    private JTextPane output;
    private JCheckBox baiduSource;
    private JCheckBox baiduAISource;
    private JCheckBox youdaoSource;
    private JCheckBox googleSource;

    private TranslateUtilsManager translateUtilsManager;

    public TranslateWordDialog() {
        translateUtilsManager = TranslateUtilsManager.getInstance();
        for (LanguageTypeEnum typeEnum : LanguageTypeEnum.values()) {
            inputLanguage.addItem(typeEnum);
            outputLanguage.addItem(typeEnum);
        }
        inputLanguage.setSelectedItem(LanguageTypeEnum.ZH);
        outputLanguage.setSelectedItem(LanguageTypeEnum.EN);

        translate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                translate.setEnabled(false);
                String inputStr = input.getText();
                TranslateRequest request = new TranslateRequest();
                request.setFrom((LanguageTypeEnum) inputLanguage.getSelectedItem());
                request.setTo((LanguageTypeEnum) outputLanguage.getSelectedItem());
                request.setContent(inputStr);
                request.setTranslateSourceEnumList(getTranslateSourceList());
                request.setTranslateTypeEnum(TranslateTypeEnum.WORD);
                Map<TranslateSourceEnum, TranslateResponse> responseMap = translateUtilsManager.translate(request);
                if (MapUtils.isEmpty(responseMap)) {
                    output.setText("翻译失败,请重试");
                    translate.setEnabled(true);
                    return;
                }
                StringBuilder responseStr = new StringBuilder();
                for (Map.Entry<TranslateSourceEnum, TranslateResponse> responseEntry : responseMap.entrySet()) {
                    responseStr.append(responseEntry.getKey().getMsg()).append(":").append(Constants.DEFAULT_SEPARATOR);
                    responseStr.append(responseEntry.getValue().getTarget()).append(Constants.DEFAULT_SEPARATOR);
                }
                output.setText(responseStr.toString());
                translate.setEnabled(true);
            }
        });
        this.input.setRows(6);
        this.input.setColumns(35);
        this.input.setLineWrap(true);
//        input.setPreferredSize(new Dimension(jpanel.getWidth() - 100, jpanel.getHeight() - 100));
        exchange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LanguageTypeEnum input = (LanguageTypeEnum) inputLanguage.getSelectedItem();
                inputLanguage.setSelectedItem(outputLanguage.getSelectedItem());
                outputLanguage.setSelectedItem(input);
            }
        });
    }

    public List<TranslateSourceEnum> getTranslateSourceList() {
        List<TranslateSourceEnum> list = new ArrayList<>();
        if (baiduSource.isSelected()) {
            list.add(TranslateSourceEnum.BAIDU);
        }
        if (baiduAISource.isSelected()) {
            list.add(TranslateSourceEnum.BAIDU_AI);
        }
        if (googleSource.isSelected()) {
            list.add(TranslateSourceEnum.GOOGLE);
        }
        if (youdaoSource.isSelected()) {
            list.add(TranslateSourceEnum.YOUDAO);
        }
        return list;
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    public void setJpanel(JPanel jpanel) {
        this.jpanel = jpanel;
    }

    public JComboBox getInputLanguage() {
        return inputLanguage;
    }

    public void setInputLanguage(JComboBox inputLanguage) {
        this.inputLanguage = inputLanguage;
    }

    public JComboBox getOutputLanguage() {
        return outputLanguage;
    }

    public void setOutputLanguage(JComboBox outputLanguage) {
        this.outputLanguage = outputLanguage;
    }

    public JTextArea getInput() {
        return input;
    }

    public void setInput(JTextArea input) {
        this.input = input;
    }

    public JButton getExchange() {
        return exchange;
    }

    public void setExchange(JButton exchange) {
        this.exchange = exchange;
    }

    public JButton getTranslate() {
        return translate;
    }

    public void setTranslate(JButton translate) {
        this.translate = translate;
    }

    public JTextPane getOutput() {
        return output;
    }

    public void setOutput(JTextPane output) {
        this.output = output;
    }

    public JCheckBox getBaiduSource() {
        return baiduSource;
    }

    public void setBaiduSource(JCheckBox baiduSource) {
        this.baiduSource = baiduSource;
    }

    public JCheckBox getBaiduAISource() {
        return baiduAISource;
    }

    public void setBaiduAISource(JCheckBox baiduAISource) {
        this.baiduAISource = baiduAISource;
    }

    public JCheckBox getYoudaoSource() {
        return youdaoSource;
    }

    public void setYoudaoSource(JCheckBox youdaoSource) {
        this.youdaoSource = youdaoSource;
    }

    public JCheckBox getGoogleSource() {
        return googleSource;
    }

    public void setGoogleSource(JCheckBox googleSource) {
        this.googleSource = googleSource;
    }

    public TranslateUtilsManager getTranslateUtilsManager() {
        return translateUtilsManager;
    }

    public void setTranslateUtilsManager(TranslateUtilsManager translateUtilsManager) {
        this.translateUtilsManager = translateUtilsManager;
    }
}
