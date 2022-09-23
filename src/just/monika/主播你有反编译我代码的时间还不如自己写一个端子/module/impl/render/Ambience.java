package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.game.TickEvent;
import just.monika.反编译我代码油饼食不食.event.impl.network.PacketReceiveEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

public class Ambience extends Module {

    private final NumberSetting time = new NumberSetting("Time", 12000, 24000, 0, 1000);

    public Ambience() {
        super("Ambience", Category.RENDER, "world time");
        this.addSettings(time);
    }

    private final EventListener<TickEvent> onTick = e -> {
        if (mc.theWorld != null) {
            mc.theWorld.setWorldTime(time.getValue().longValue());
        }
    };

    private final EventListener<PacketReceiveEvent> onPacketReceive = e -> {
        if (e.getPacket() instanceof S03PacketTimeUpdate) {
            e.cancel();
        }
    };

}
