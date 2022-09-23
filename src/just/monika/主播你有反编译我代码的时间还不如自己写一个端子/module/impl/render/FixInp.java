package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.game.TickEvent;
import just.monika.LInput;

public class FixInp extends Module {
    private final EventListener<TickEvent> tickEventEventListener = event -> {
        if(!mc.ingameGUI.getChatGUI().getChatOpen()){
            LInput.makeOff();
        }else{
            LInput.makeOn();
        }
    };
    public FixInp() {
        super("FixInput", Category.RENDER, "L");
    }
}
