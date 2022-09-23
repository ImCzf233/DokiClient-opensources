package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.player;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.game.KeyPressEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import org.lwjgl.input.Keyboard;

import java.util.Random;

import static just.monika.主播你有反编译我代码的时间还不如自己写一个端子.主播你IQ行不行啊.MonikaIQBoost.LMessages;
import static org.lwjgl.input.Keyboard.KEY_P;

public final class IQBoost extends Module {

    private final EventListener<KeyPressEvent> motionEventEventListener = event -> {
        //this.setSuffix(this.getSettingsList().get(0).getConfigValue().toString());
        if (Keyboard.isKeyDown(KEY_P)) {
            Random r = new Random();
            mc.thePlayer.sendChatMessage(LMessages[r.nextInt(LMessages.length)]);
        }
    };

    public IQBoost() {
        super("IQBoost", Category.PLAYER, "press P to L");
        this.addSettings(new NumberSetting("IQ", 120, Integer.MAX_VALUE, 0, 10));
    }
}
