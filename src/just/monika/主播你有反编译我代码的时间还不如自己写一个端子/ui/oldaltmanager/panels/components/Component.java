package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.oldaltmanager.panels.components;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.oldaltmanager.backend.AltManagerUtils;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.ColorUtil;
import just.monika.反编译我代码油饼食不食.utils.animations.Animation;
import net.minecraft.client.Minecraft;

import java.awt.*;

public abstract class Component {
    public float x;
    public float y;
    public boolean hovering;
    protected int rectColorInt = new Color(20, 20, 29, 120).getRGB();
    protected Color rectColor = new Color(20, 20, 29, 120);
    protected Minecraft mc = Minecraft.getMinecraft();

    public abstract void initGui();

    public abstract void keyTyped(char typedChar, int keyCode);

    public abstract void drawScreen(int mouseX, int mouseY, float partialTicks, Animation initAnimation);

    public abstract void mouseClicked(int mouseX, int mouseY, int button, AltManagerUtils altManagerUtils);

    public int interpolateRectColor(Animation animation) {
        return ColorUtil.interpolateColor(rectColor, new Color(255, 255, 255, 120), (float) animation.getOutput());
    }
}
