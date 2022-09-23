package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.clickguis.dropdown.impl;

import net.minecraft.client.Minecraft;

public abstract class Component {

    public Minecraft mc = Minecraft.getMinecraft();

    abstract public void initGui();

    public abstract void keyTyped(char typedChar, int keyCode);

    abstract public void drawScreen(int mouseX, int mouseY);

    abstract public void mouseClicked(int mouseX, int mouseY, int button);

    abstract public void mouseReleased(int mouseX, int mouseY, int state);
}
