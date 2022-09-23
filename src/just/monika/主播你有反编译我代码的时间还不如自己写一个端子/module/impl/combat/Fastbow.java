package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.combat;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.ModeSetting;
import just.monika.反编译我代码油饼食不食.utils.network.PacketUtils;
import net.minecraft.item.ItemBow;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Mouse;


public final class Fastbow extends Module {

    private final ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "Ghostly");

    private final EventListener<MotionEvent> motionEventEventListener = event -> {
        if(mc.thePlayer.getCurrentEquippedItem() == null) return;
        switch (mode.getMode()) {
            case "Vanilla":
            if (Mouse.isButtonDown(1) && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow) {
                for (int i = 0; i < 20; ++i) {
                    mc.rightClickDelayTimer = 0;
                    PacketUtils.sendPacketNoEvent(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.onGround));
                }
                mc.playerController.onStoppedUsingItem(mc.thePlayer);
            }
            break;
            case "Ghostly":
                if(Mouse.isButtonDown(1) && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow){
                    for(int i = 0; i < 20; i++){
                        mc.rightClickDelayTimer = 0;
                        PacketUtils.sendPacketNoEvent(new C03PacketPlayer(true));
                    }
                    mc.playerController.onStoppedUsingItem(mc.thePlayer);
                }
                break;
        }
    };

    @Override
    public void onDisable(){
        mc.rightClickDelayTimer = 4;
        super.onDisable();
    }

    public Fastbow() {
        super("Fastbow", Category.COMBAT, "shoot bows faster");
        this.addSettings(mode);
    }
}
