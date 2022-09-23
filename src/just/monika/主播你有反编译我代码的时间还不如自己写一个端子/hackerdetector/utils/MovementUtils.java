package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.utils;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

public class MovementUtils {

    public static boolean isMoving(EntityPlayer player) {
        return player.moveForward != 0F || player.moveStrafing != 0F;
    }

    public static boolean isFalseFlaggable(EntityPlayer player) {
        return player.isInWater() || player.isInLava() || player.isOnLadder() || player.ticksExisted < 10;
    }

}
