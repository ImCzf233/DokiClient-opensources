package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.objects;

import just.monika.反编译我代码油饼食不食.utils.animations.Animation;
import just.monika.反编译我代码油饼食不食.utils.animations.Direction;
import just.monika.反编译我代码油饼食不食.utils.animations.impl.SmoothStepAnimation;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.input.Mouse;

/**
 * @author cedo
 * @author Foggy
 */
public class Scroll {

    @Getter
    @Setter
    private float maxScroll = Float.MAX_VALUE, minScroll = 0, rawScroll;
    private float scroll;
    private Animation scrollAnimation = new SmoothStepAnimation(0, 0, Direction.BACKWARDS);

    public void onScroll(int ms) {
        scroll = (float) (rawScroll - scrollAnimation.getOutput());
        rawScroll += Mouse.getDWheel() / 4f;
        rawScroll = Math.max(Math.min(minScroll, rawScroll), -maxScroll);
        scrollAnimation = new SmoothStepAnimation(ms, rawScroll - scroll, Direction.BACKWARDS);
    }

    public boolean isScrollAnimationDone() {
        return scrollAnimation.isDone();
    }

    public float getScroll() {
        scroll = (float) (rawScroll - scrollAnimation.getOutput());
        return scroll;
    }

	public void setMaxScroll(float f) {
		maxScroll = f;
	}

}
