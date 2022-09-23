package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;

public final class Brightness extends Module {
    private final EventListener<MotionEvent> motionEventEventListener = event -> {
        mc.gameSettings.gammaSetting = 100;
    };

    @Override
    public void onDisable(){
        mc.gameSettings.gammaSetting = 0;
        super.onDisable();
    }

    public Brightness() {
        super("Brightness", Category.RENDER, "changes the game brightness");
    }
}
