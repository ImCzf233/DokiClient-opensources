package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;

public class ScoreboardMod extends Module {

    public static final BooleanSetting textShadow = new BooleanSetting("Text Shadow", true);
    public static final BooleanSetting redNumbers = new BooleanSetting("Red Numbers", false);

    public ScoreboardMod() {
        super("Scoreboard", Category.RENDER, "Scoreboard preferences");
        this.addSettings(textShadow, redNumbers);
        this.setToggled(true);
    }

}
