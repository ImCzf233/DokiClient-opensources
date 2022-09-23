package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.checks;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.Detection;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.utils.MovementUtils;
import net.minecraft.entity.player.EntityPlayer;

public class FlightB extends Detection {

    public FlightB() {
        super("Flight B", Category.MOVEMENT);
    }

    @Override
    public boolean runCheck(EntityPlayer player) {
        return player.airTicks > 20 && player.motionY == 0 && MovementUtils.isMoving(player);
    }
}
