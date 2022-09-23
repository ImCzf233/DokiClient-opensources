package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.checks;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.Detection;
import net.minecraft.entity.player.EntityPlayer;

public class ReachA extends Detection {

    public ReachA() {
        super("Reach A", Category.COMBAT);
    }

    @Override
    public boolean runCheck(EntityPlayer player) {
        return false;
    }
}
