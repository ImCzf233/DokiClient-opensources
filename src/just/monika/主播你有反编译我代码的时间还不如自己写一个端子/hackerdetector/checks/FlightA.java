package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.checks;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.Detection;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.utils.MovementUtils;
import net.minecraft.entity.player.EntityPlayer;

public class FlightA extends Detection {

    public FlightA() {
        super("Flight A", Category.MOVEMENT);
    }

    @Override
    public boolean runCheck(EntityPlayer player) {
        return !player.onGround && player.motionY == 0 && MovementUtils.isMoving(player);
    }
}
