package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.player;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.network.PacketSendEvent;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.ModeSetting;
import just.monika.反编译我代码油饼食不食.utils.network.PacketUtils;
import net.minecraft.network.play.client.C03PacketPlayer;

@SuppressWarnings("unused")
public final class NoFall extends Module {

    private final ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "Packet", "Edit");

    private final EventListener<MotionEvent> motionEventEventListener = event -> {
        if (event.isPre()) {
            if (mc.thePlayer.fallDistance > 3.0) {
                switch (mode.getMode()) {
                    case "Vanilla":
                        event.setOnGround(true);
                        break;
                    case "Packet":
                        PacketUtils.sendPacket(new C03PacketPlayer(true));
                        break;
                }
                mc.thePlayer.fallDistance = 0;
            }
        }
    };

    private final EventListener<PacketSendEvent> packetSendEventEventListener = event -> {
    };

    public NoFall() {
        super("NoFall", Category.PLAYER, "pervents fall damage");
        this.addSettings(mode);
    }
}
