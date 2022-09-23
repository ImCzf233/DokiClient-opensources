package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui;

import just.monika.反编译我代码油饼食不食.utils.Utils;

public interface Screen extends Utils {

    void initGui();

    void keyTyped(char typedChar, int keyCode);

    void drawScreen(int mouseX, int mouseY);

    void mouseClicked(int mouseX, int mouseY, int button);

    void mouseReleased(int mouseX, int mouseY, int state);

}
