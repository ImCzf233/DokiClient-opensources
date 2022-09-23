package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.player;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.DokiClient;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.movement.Flight;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.ModeSetting;
import just.monika.反编译我代码油饼食不食.utils.misc.MathUtils;
import just.monika.反编译我代码油饼食不食.utils.network.PacketUtils;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AntiVoid extends Module {

    private final ModeSetting mode = new ModeSetting("Mode", "Watchdog", "Watchdog");

    public AntiVoid() {
        super("AntiVoid", Category.PLAYER, "saves you from the void");
        this.addSettings(mode);
    }

    private final EventListener<MotionEvent> onMotion = e -> {
        this.setSuffix(mode.getMode());
        if (DokiClient.INSTANCE.isToggled(Flight.class) || mc.thePlayer.isDead) return;
        if (e.isPre()) {
            switch (mode.getMode()) {
                case "Watchdog":
                    PacketUtils.sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + MathUtils.getRandomInRange(10, 12), mc.thePlayer.posZ, false));
                    break;
            }
        }
    };

}
