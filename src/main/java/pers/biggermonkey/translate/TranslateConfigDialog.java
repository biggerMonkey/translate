package pers.biggermonkey.translate;

import com.intellij.ide.util.PropertiesComponent;
import pers.biggermonkey.translate.common.Constants;
import pers.biggermonkey.translate.enums.TranslateConfigEnum;
import pers.biggermonkey.translate.enums.TranslateSourceEnum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: huangwenjun16
 * @date: 2024/6/20 15:59
 * @description:
 */
public class TranslateConfigDialog {
    private JPanel jpanel;
    private JComboBox translateSource;
    private JButton save;
    private JTextArea config;

    public TranslateConfigDialog() {
        for (TranslateSourceEnum translateSourceEnum : TranslateSourceEnum.values()) {
            translateSource.addItem(translateSourceEnum);
        }
        translateSource.setSelectedItem(TranslateSourceEnum.BAIDU);
        fillContent();
        translateSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillContent();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TranslateSourceEnum translateSourceEnum = (TranslateSourceEnum) translateSource.getSelectedItem();
                if (translateSourceEnum == null) {
                    return;
                }
                TranslateConfigEnum translateConfigEnum = TranslateConfigEnum.getEnumBySource(translateSourceEnum);
                if (translateConfigEnum == null) {
                    return;
                }
                String content = config.getText();
                String[] lines = content.split("\\R"); // "\\R"是Java 8引入的，匹配任何行终止符
                // 将分割后的行数据显示在JList中
                for (String line : lines) {
                    if (!line.contains(Constants.EQUAL_SYMBOL)) {
                        continue;
                    }
                    String[] keyV = line.split(Constants.EQUAL_SYMBOL);
                    if (!translateConfigEnum.getFields().contains(keyV[0])) {
                        continue;
                    }
                    PropertiesComponent.getInstance().setValue(Constants.getConfig(translateConfigEnum.getCode(), keyV[0]), keyV[1]);
                }
                JOptionPane.showMessageDialog(jpanel, "保存成功");
            }
        });
    }

    public void fillContent(){
        TranslateSourceEnum translateSourceEnum = (TranslateSourceEnum) translateSource.getSelectedItem();
        if (translateSourceEnum == null) {
            config.setText("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        TranslateConfigEnum translateConfigEnum = TranslateConfigEnum.getEnumBySource(translateSourceEnum);
        if (translateConfigEnum == null) {
            config.setText("");
            return;
        }
        for (String field : translateConfigEnum.getFields()) {
            String value = PropertiesComponent.getInstance().getValue(Constants.getConfig(translateConfigEnum.getCode(), field));
            sb.append(field).append(Constants.EQUAL_SYMBOL).append(value).append(Constants.LINE_BREAKS);
        }
        config.setText(sb.toString());
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    public void setJpanel(JPanel jpanel) {
        this.jpanel = jpanel;
    }

    public JComboBox getTranslateSource() {
        return translateSource;
    }

    public void setTranslateSource(JComboBox translateSource) {
        this.translateSource = translateSource;
    }

    public JButton getSave() {
        return save;
    }

    public void setSave(JButton save) {
        this.save = save;
    }

    public JTextArea getConfig() {
        return config;
    }

    public void setConfig(JTextArea config) {
        this.config = config;
    }
}
