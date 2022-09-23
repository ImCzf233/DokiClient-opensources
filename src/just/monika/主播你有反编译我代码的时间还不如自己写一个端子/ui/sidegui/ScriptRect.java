package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.sidegui;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render.ClickGuiMod;
import just.monika.反编译我代码油饼食不食.utils.animations.Animation;
import just.monika.反编译我代码油饼食不食.utils.animations.Direction;
import just.monika.反编译我代码油饼食不食.utils.animations.impl.DecelerateAnimation;
import just.monika.反编译我代码油饼食不食.utils.animations.impl.EaseBackIn;
import just.monika.反编译我代码油饼食不食.utils.font.FontUtil;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.misc.HoveringUtil;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.ColorUtil;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.RoundedUtil;

import java.awt.*;

public class ScriptRect extends GuiPanel {

    private final Module module;
    public boolean reinit;
    float x, y, width, height;
    private Animation hoverAnimation;
    private Animation toggleAnimation;

    public ScriptRect(Module module) {
        this.module = module;
    }


    @Override
    public void initGui() {
        hoverAnimation = new DecelerateAnimation(250, 1);
        toggleAnimation = new EaseBackIn(250, 1, 2);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks, int alpha) {

        int textColor = ColorUtil.applyOpacity(-1, alpha / 255f);
        Color backgroundColor = new Color(45, 45, 45);
        Color rectColor = ColorUtil.interpolateColorC(backgroundColor, ClickGuiMod.color.getColor(), (float) (toggleAnimation.getOutput() * .1f));
        Color outlineColor = ColorUtil.interpolateColorC(ColorUtil.brighter(backgroundColor, .6f),
                ClickGuiMod.color.getColor(), (float) toggleAnimation.getOutput());

        boolean hovered = HoveringUtil.isHovering(x, y, width, height, mouseX, mouseY);
        hoverAnimation.setDirection(hovered ? Direction.FORWARDS : Direction.BACKWARDS);
        toggleAnimation.setDirection(module.isToggled() ? Direction.FORWARDS : Direction.BACKWARDS);

        RoundedUtil.drawRoundOutline(x, y, width, height, 6, (float) (.5 + (1.5 * toggleAnimation.getOutput())),
                ColorUtil.interpolateColorC(rectColor, ColorUtil.brighter(rectColor, .8f), (float) hoverAnimation.getOutput()), outlineColor);

        FontUtil.tenacityBoldFont26.drawString(module.getName(), x + 5, y + 5, textColor);

        FontUtil.tenacityFont18.wrapText(module.getDescription(), x + 5, y + 23,
                ColorUtil.applyOpacity(new Color(145, 145, 145), alpha / 255f).getRGB(), width, 4);

        //  FontUtil.tenacityFont18.drawString("§fAuthor: §r" + module.author, x + 5,
          //      y + height - (FontUtil.tenacityFont18.getHeight() + 5), clickGUIMod.color.getColor().getRGB());


    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        boolean hovered = HoveringUtil.isHovering(x, y, width, height, mouseX, mouseY);
        if (hovered && button == 0) module.toggleSilent();
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int button) {

    }
}
