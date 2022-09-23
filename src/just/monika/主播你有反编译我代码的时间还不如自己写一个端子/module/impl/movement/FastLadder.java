package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.movement;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.ModeSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FastLadder extends Module {

    private final ModeSetting mode = new ModeSetting("Mode", "Motion", "Motion", "Timer", "Position");
    private final NumberSetting speed = new NumberSetting("Speed", 1.5, 5, 0.1, 0.01);

    public FastLadder() {
        super("FastLadder", Category.MOVEMENT, "Climbs up ladders faster than normal");
        this.addSettings(mode, speed);
    }

    private final EventListener<MotionEvent> onMotion = e -> {
        if(mc.thePlayer.isOnLadder()) {
            switch(mode.getMode()) {
                case "Timer":
                    mc.timer.timerSpeed = speed.getValue().floatValue();
                    break;
                case "Motion":
                    mc.thePlayer.motionY = speed.getValue();
                    break;
                case "Position":
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY + speed.getValue(), mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, false));
                    mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + speed.getValue(), mc.thePlayer.posZ);
                    break;
            }
        }else{
            mc.timer.timerSpeed = 1;
        }
    };

    @Override
    public void onDisable() {
        mc.timer.timerSpeed = 1;
        super.onDisable();
    }
}
