package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.combat;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;


public final class Reach extends Module {
    private final NumberSetting reach = new NumberSetting("Reach", 4, 6, 1, 0.1);
    public static double dbReach;
    
    public Reach() {
        super("Reach", Category.COMBAT, "reach");
    }
    
    private final EventListener<MotionEvent> motionEventEventListener = event -> {
    	dbReach = this.reach.getValue();
    };
}
