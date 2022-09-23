package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.altmanager;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.altmanager.helpers.AltManagerUtils;
import just.monika.反编译我代码油饼食不食.utils.animations.Animation;
import net.minecraft.client.Minecraft;

import java.awt.*;

public interface AltPanel {

    int rectColorInt = new Color(20, 20, 29, 120).getRGB();
    Minecraft mc = Minecraft.getMinecraft();

    void initGui();

    void keyTyped(char typedChar, int keyCode);

    void drawScreen(int mouseX, int mouseY, float partialTicks, Animation initAnimation);

    void mouseClicked(int mouseX, int mouseY, int button, AltManagerUtils altManagerUtils);

}
