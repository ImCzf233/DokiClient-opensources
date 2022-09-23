package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.misc;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

    public static String findLongestModuleName(List<Module> modules) {
        return Collections.max(modules, Comparator.comparing(module -> (module.getName() + (module.hasMode() ? " " + module.getSuffix() : "")).length())).getName();
    }

    public static List<Module> getToggledModules(List<Module> modules) {
        return modules.stream().filter(Module::isToggled).collect(Collectors.toList());
    }

}
