package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.movement;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.DokiClient;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.player.BlockUtils;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.player.MovementUtils;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.反编译我代码油饼食不食.event.impl.player.StepConfirmEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.ModeSetting;
import just.monika.反编译我代码油饼食不食.utils.network.PacketUtils;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;

@SuppressWarnings("unused")
public final class Step extends Module {

    private final ModeSetting mode = new ModeSetting("Mode", "NCP", "NCP", "Smooth");
    private final double[] ncpOffsets = new double[]{0.42F, 0.75F}, smoothOffsets = new double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7};
    private boolean resetTimer;

    private final EventListener<MotionEvent> onUpdate = e -> {
        if (mc.thePlayer == null) return;
        this.setSuffix(mode.getMode());

        mc.thePlayer.stepHeight = BlockUtils.isInLiquid() || BlockUtils.isOnLiquid()
                || DokiClient.INSTANCE.getModuleCollection().get(Speed.class).isToggled()
                || DokiClient.INSTANCE.getModuleCollection().get(Scaffold.class).isToggled()
                || !mc.thePlayer.onGround ? 0.625F : mode.is("Watchdog") ? 1.5F : 1.0F;

        if (resetTimer && mc.timer.timerSpeed != 1) {
            mc.timer.timerSpeed = 1.0F;
            resetTimer = false;
        }
    };

    private final EventListener<StepConfirmEvent> onStepConfirm = e -> {
        if (mc.thePlayer == null
                || DokiClient.INSTANCE.getModuleCollection().get(Scaffold.class).isToggled()
                || DokiClient.INSTANCE.getModuleCollection().get(Speed.class).isToggled())
            return;
        double diffY = mc.thePlayer.getEntityBoundingBox().minY - mc.thePlayer.posY;
        double posX = mc.thePlayer.posX, posY = mc.thePlayer.posY, posZ = mc.thePlayer.posZ;

        if (MovementUtils.isMoving() && mc.thePlayer.onGround && diffY > 0.625 && diffY <= 1.5) {
            switch (mode.getMode()) {
                case "NCP":
                    if (diffY <= 1) {
                        for (double o : this.ncpOffsets) {
                            PacketUtils.sendPacketNoEvent(new C03PacketPlayer.C04PacketPlayerPosition(posX, posY + o, posZ, false));
                        }
                        for (int i = 0; i < 4; i++) {
                            PacketUtils.sendPacketNoEvent(new C03PacketPlayer(true));
                        }
                    }
                    PacketUtils.sendPacketNoEvent(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
                    break;
                case "Smooth":
                    PacketUtils.sendPacketNoEvent(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));
                    if (diffY <= 1) {
                        for (double o : this.smoothOffsets) {
                            PacketUtils.sendPacketNoEvent(new C03PacketPlayer.C04PacketPlayerPosition(posX, posY + o, posZ, false));
                        }
                        for (int i = 0; i < 4; i++) {
                            PacketUtils.sendPacketNoEvent(new C03PacketPlayer(true));
                        }
                    }
                    mc.timer.timerSpeed = 0.191f;
                    PacketUtils.sendPacketNoEvent(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
                    break;
            }
            mc.thePlayer.stepHeight = 0.625F;
            resetTimer = true;
        }
    };

    @Override
    public void onDisable() {
        mc.timer.timerSpeed = 1.0F;
        mc.thePlayer.stepHeight = 0.625F;
        super.onDisable();
    }

    public Step() {
        super("Step", Category.MOVEMENT, "step up blocks");
        this.addSettings(mode);
    }
}
