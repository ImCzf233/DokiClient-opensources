package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.combat;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.game.TickEvent;
import just.monika.反编译我代码油饼食不食.utils.time.TimerUtil;
import net.minecraft.client.Minecraft;

import java.util.Random;

public class LegitAura extends Module {
    public LegitAura(){
        super("LegitAura", Category.COMBAT,"L");
    }
    private EventListener<TickEvent> ms = event ->{
        TimerUtil t = new TimerUtil();
        boolean Bool;
        Bool = new Random().nextBoolean();
        if (mc.objectMouseOver.entityHit != null) {
            if (t.hasTimeElapsed(new Random(1).nextInt(51) + 50))
                mc.thePlayer.swingItem();
            mc.playerController.attackEntity(mc.thePlayer, Minecraft.getMinecraft().objectMouseOver.entityHit);
        }
    };
}
