package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.oldaltmanager.panels.components.impl;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.oldaltmanager.backend.AltManagerUtils;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.oldaltmanager.panels.components.Component;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.objects.PasswordField;
import just.monika.反编译我代码油饼食不食.utils.animations.Animation;
import just.monika.反编译我代码油饼食不食.utils.font.FontUtil;

public class StringField extends Component {
    private final boolean password;
    public String placeHolderText;
    public float width;
    public float rectWidth;
    public int placeHolderTextXOffset = 0;
    public PasswordField stringField;

    public StringField(String placeHolderText, float width, float rectWidth, boolean password) {
        this.placeHolderText = placeHolderText;
        this.width = width;
        this.rectWidth = rectWidth;
        this.password = password;
    }

    @Override
    public void initGui() {
        stringField = new PasswordField(this.placeHolderText, 0, 0, 0, (int) this.width, 20, FontUtil.tenacityFont20, -1);
        stringField.setMaxStringLength(128);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        this.stringField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks, Animation initAnimation) {
        int xVal = (int) ((x - rectWidth) + (rectWidth * initAnimation.getOutput()));
        stringField.xPosition = (int) ((x - rectWidth) + (rectWidth * initAnimation.getOutput()));
        stringField.yPosition = (int) y;
        stringField.placeHolderTextX = ((xVal + rectWidth) / 2f) - placeHolderTextXOffset;
        if (password) {
            stringField.drawPasswordBox();
        } else {
            stringField.drawTextBox();
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button, AltManagerUtils altManagerUtils) {
        this.stringField.mouseClicked(mouseX, mouseY, button);
    }

    public String getText() {
        return stringField.getText();
    }

    public PasswordField getPasswordField() {
        return stringField;
    }

}
