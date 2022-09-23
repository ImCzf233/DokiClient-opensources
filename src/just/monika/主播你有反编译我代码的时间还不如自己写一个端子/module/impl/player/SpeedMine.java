package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.player;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.network.PacketSendEvent;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import just.monika.反编译我代码油饼食不食.utils.network.PacketUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class SpeedMine extends Module {

    private final NumberSetting speed = new NumberSetting("Speed", 1.5, 3, 1, 0.1);
    private EnumFacing facing;
    private BlockPos pos;
    private boolean boost;
    private float damage;

    public SpeedMine() {
        super("SpeedMine", Category.PLAYER, "mines blocks faster");
        this.addSettings(speed);
    }

    private final EventListener<MotionEvent> onMotion = e -> {
        if (e.isPre()) {
            mc.playerController.blockHitDelay = 0;
            if (pos != null && boost) {
                IBlockState blockState = mc.theWorld.getBlockState(pos);
                if (blockState == null) return;

                try {
                    damage += blockState.getBlock().getPlayerRelativeBlockHardness(mc.thePlayer, mc.theWorld, pos) * speed.getValue();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }

                if (damage >= 1) {
                    try {
                        mc.theWorld.setBlockState(pos, Blocks.air.getDefaultState(), 11);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                    PacketUtils.sendPacketNoEvent(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, facing));
                    damage = 0;
                    boost = false;
                }
            }
        }
    };

    private final EventListener<PacketSendEvent> onPacketSend = e -> {
        if (e.getPacket() instanceof C07PacketPlayerDigging) {
            C07PacketPlayerDigging packet = (C07PacketPlayerDigging) e.getPacket();
            if (packet.getStatus() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
                boost = true;
                pos = packet.getPosition();
                facing = packet.getFacing();
                damage = 0;
            } else if (packet.getStatus() == C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK | packet.getStatus() == C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
                boost = false;
                pos = null;
                facing = null;
            }
        }
    };

}
