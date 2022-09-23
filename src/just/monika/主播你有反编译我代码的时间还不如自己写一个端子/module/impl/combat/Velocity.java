package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.combat;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.player.MovementUtils;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.network.PacketReceiveEvent;
import just.monika.反编译我代码油饼食不食.settings.Setting;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.ModeSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import just.monika.反编译我代码油饼食不食.utils.misc.MathUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;

public class Velocity extends Module {

    private final ModeSetting mode = new ModeSetting("Mode", "Packet", "Packet", "Matrix");
    private final NumberSetting horizontal = new NumberSetting("Horizontal", 0, 100, 0, 1);
    private final NumberSetting vertical = new NumberSetting("Vertical", 0, 100, 0, 1);
    private final NumberSetting chance = new NumberSetting("Chance", 100, 100, 0, 1);
    private final BooleanSetting onlyWhileMoving = new BooleanSetting("Only while moving", false);

    public Velocity() {
        super("Velocity", Category.COMBAT, "Reduces your knockback");
        Setting.addParent(mode, m -> m.is("Packet"), horizontal, vertical);
        this.addSettings(mode, horizontal, vertical, chance, onlyWhileMoving);
    }

    private final EventListener<PacketReceiveEvent> onPacketReceive = e -> {
        if ((onlyWhileMoving.isEnabled() && !MovementUtils.isMoving()) || (chance.getValue() != 100 && MathUtils.getRandomInRange(0, 100) > chance.getValue()))
            return;
        Packet<?> packet = e.getPacket();
        switch (mode.getMode()) {
            case "Packet":
                if (packet instanceof S12PacketEntityVelocity) {
                    S12PacketEntityVelocity s12 = (S12PacketEntityVelocity) e.getPacket();
                    if (mc.thePlayer != null && s12.getEntityID() == mc.thePlayer.getEntityId()) {
                        if (cancel(e)) return;
                        s12.motionX *= horizontal.getValue() / 100.0;
                        s12.motionZ *= horizontal.getValue() / 100.0;
                        s12.motionY *= vertical.getValue() / 100.0;
                    }
                } else if (packet instanceof S27PacketExplosion) {
                    if (cancel(e)) return;
                    S27PacketExplosion s27 = (S27PacketExplosion) e.getPacket();
                    s27.motionX *= horizontal.getValue() / 100.0;
                    s27.motionZ *= horizontal.getValue() / 100.0;
                    s27.motionY *= vertical.getValue() / 100.0;
                }
                break;
            case "Matrix":
                if (packet instanceof S12PacketEntityVelocity) {
                    S12PacketEntityVelocity s12 = (S12PacketEntityVelocity) e.getPacket();
                    if (mc.thePlayer != null && s12.getEntityID() == mc.thePlayer.getEntityId()) {
                        s12.motionX *= 5 / 100.0;
                        s12.motionZ *= 5 / 100.0;
                        s12.motionY *= 100 / 100.0;
                    }
                }
                break;
        }
    };

    private boolean cancel(PacketReceiveEvent e) {
        if (horizontal.getValue() == 0 && vertical.getValue() == 0) {
            e.cancel();
            return true;
        }
        return false;
    }

}
