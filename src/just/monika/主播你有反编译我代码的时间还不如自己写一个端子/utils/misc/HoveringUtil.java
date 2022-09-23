package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.misc;

public class HoveringUtil {

    public static boolean isHovering(float x, float y, float width, float height, int mouseX, int mouseY) {
        return mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
    }
    
}
