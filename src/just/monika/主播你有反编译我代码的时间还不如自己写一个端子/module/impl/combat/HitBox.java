package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.combat;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;

public class HitBox extends Module {
    public final NumberSetting box=new NumberSetting("Box", 10, 100, 0, 10);
    public HitBox() {
        super("HitBox",Category.COMBAT,"Make HitBox Bigger");
        addSettings(box);
    }
}
