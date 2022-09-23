package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render;


import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.ModeSetting;

public final class Animations extends Module {

    public static final ModeSetting modeSetting = new ModeSetting("Mode", "LoL",
            "LoL", "Stella", "Fathum", "1.7", "Exhi", "Exhi 2", "Shred", "Smooth", "Sigma");
    public static final BooleanSetting oldDamage = new BooleanSetting("Old Damage", false);

    public Animations() {
        super("Animations", Category.RENDER, "changes animations");
        this.addSettings(modeSetting, oldDamage);
    }

}
