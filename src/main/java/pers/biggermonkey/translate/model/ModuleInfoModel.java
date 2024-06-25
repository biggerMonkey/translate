package pers.biggermonkey.translate.model;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @author: huangwenjun16
 * @date: 2024/6/19 16:58
 * @description:
 */
public class ModuleInfoModel {

    private String name;

    private String rootPath;

    public ModuleInfoModel() {
    }

    public ModuleInfoModel(String name, String rootPath) {
        this.name = name;
        this.rootPath = new File(rootPath).getParentFile().getPath();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String toString() {
        return StringUtils.isBlank(name) ? "" : name;
    }
}
