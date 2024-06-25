package pers.biggermonkey.translate.common;

import com.intellij.openapi.application.ex.ApplicationUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.openapi.wm.IdeFrame;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author: huangwenjun16
 * @date: 2024/6/19 16:36
 * @description:
 */
public class ProjectUtils {
    public static Project findCurrentProject() {
        IdeFrame frame = IdeFocusManager.getGlobalInstance().getLastFocusedFrame();
        Project project = (frame != null) ? frame.getProject() : null;
        if (isValidProject(project))
            return project;
        for (Project p : ProjectManager.getInstance().getOpenProjects()) {
            if (isValidProject(p))
                return p;
        }
        return null;
    }

    @Nonnull
    public static Iterable<Project> findValidProjects() {
        return (Iterable<Project>) Arrays.<Project>stream(ProjectManager.getInstance().getOpenProjects())
                .filter(ProjectUtils::isValidProject)
                .collect(Collectors.toList());
    }

    private static boolean isValidProject(Project project) {
        return (project != null && !project.isDisposed() && !project.isDefault());
    }
}
