package pers.biggermonkey.translate.common;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;

/**
 * @author: huangwenjun16
 * @date: 2024/6/19 16:37
 * @description:
 */
public class ModuleUtils {

    public static Module[] getAllModule() {
        Project project = ProjectUtils.findCurrentProject();
        if (project == null) {
            return null;
        }
        return ModuleManager.getInstance(project).getModules();
    }
}
