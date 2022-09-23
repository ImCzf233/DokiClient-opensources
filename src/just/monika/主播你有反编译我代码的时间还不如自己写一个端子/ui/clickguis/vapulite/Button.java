package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.clickguis.vapulite;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;

public class Button {

    private Panel panel;
    private Module module;

    public Button(Panel panel, Module module) {
        this.panel = panel;
        this.module = module;
    }

    public void click() {
        module.toggle();
    }

    public Module getModule() {
        return module;
    }

    public boolean isHover(final int x, final int y, final int widht, final int height, final int mouseX, final int mouseY) {
        return mouseX >= x && mouseX <= x + widht && mouseY >= y && mouseY <= y + height;
    }
}