package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.player;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.network.PacketReceiveEvent;
import just.monika.反编译我代码油饼食不食.event.impl.player.BoundingBoxEvent;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.反编译我代码油饼食不食.event.impl.player.PushOutOfBlockEvent;
import net.minecraft.network.play.client.C03PacketPlayer;

public final class Freecam extends Module {

    private final EventListener<MotionEvent> motionEventEventListener = event -> {

    };

    private final EventListener<BoundingBoxEvent> boundingBoxEventEventListener = event -> {
        if(mc.thePlayer != null) {
            event.cancel();
        }
    };

    private final EventListener<PushOutOfBlockEvent> pushOutOfBlockEventEventListener = event -> {
        if(mc.thePlayer != null) {
            event.cancel();
        }
    };

    private final EventListener<PacketReceiveEvent> packetReceiveEventEventListener = event -> {
        if (event.getPacket() instanceof C03PacketPlayer) {
            event.cancel();
        }
    };

    @Override
    public void onEnable(){
        if(mc.thePlayer != null) {
            mc.thePlayer.capabilities.allowFlying = true;
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if(mc.thePlayer != null) {
            mc.thePlayer.capabilities.allowFlying = false;
            mc.thePlayer.capabilities.isFlying = false;
        }
        super.onDisable();
    }

    public Freecam() {
        super("Freecam", Category.PLAYER, "allows you to look around freely");
    }
}
