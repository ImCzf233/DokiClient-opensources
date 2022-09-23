package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.combat;


import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.ModeSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import just.monika.反编译我代码油饼食不食.utils.network.PacketUtils;
import just.monika.反编译我代码油饼食不食.utils.time.TimerUtil;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C18PacketSpectate;

import java.util.UUID;

@SuppressWarnings("unused")
public final class Criticals extends Module {

    private final ModeSetting modeSetting = new ModeSetting("Mode", "Watchdog", "Watchdog", "Packet", "Dev");
    private final NumberSetting delay = new NumberSetting("Delay", 1,20, 0, 1);
    private final TimerUtil timer = new TimerUtil();

    public Criticals() {
        super("Criticals", Category.COMBAT, "Crit attacks");
        this.addSettings(modeSetting, delay);
    }

    private final EventListener<MotionEvent> onMotion = e -> {
        switch (modeSetting.getMode()) {
            case "Watchdog":
                if (KillAura.attacking && e.isOnGround()) {
                    if (KillAura.target.hurtTime > delay.getValue().intValue()) {
                        for (double offset : new double[]{0.06f, 0.01f}) {
                            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + offset + (Math.random() * 0.001), mc.thePlayer.posZ, false));
                        }
                    }
                }
                break;
            case "Packet":
                if (mc.objectMouseOver.entityHit != null && mc.thePlayer.onGround) {
                    if (mc.objectMouseOver.entityHit.hurtResistantTime > delay.getValue().intValue()) {
                        for (double offset : new double[]{0.006253453, 0.002253453, 0.001253453}) {
                            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + offset, mc.thePlayer.posZ, false));
                        }
                    }
                }
                break;
            case "Dev":
                if (mc.objectMouseOver.entityHit != null && mc.thePlayer.onGround) {
                    if (mc.objectMouseOver.entityHit.hurtResistantTime > delay.getValue().intValue()) {
                        for (double offset : new double[]{0.06253453, 0.02253453, 0.001253453, 0.0001135346}) {
                            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + offset, mc.thePlayer.posZ, false));
                            PacketUtils.sendPacketNoEvent(new C18PacketSpectate(UUID.randomUUID()));
                        }
                    }
                }
                break;
        }
    };

}
