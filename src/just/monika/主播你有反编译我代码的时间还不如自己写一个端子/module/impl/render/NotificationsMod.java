package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;

public class NotificationsMod extends Module {

    public static final NumberSetting colorInterpolation = new NumberSetting("Color Value", .5, 1, 0, .05);
    public static final BooleanSetting toggleNotifications = new BooleanSetting("Toggle", true);

    public NotificationsMod() {
        super("Notifications", Category.RENDER, "Allows you to customize the client notifications");
        this.addSettings(colorInterpolation, toggleNotifications);
    }

}
