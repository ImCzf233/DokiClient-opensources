package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.mainmenu;

import just.monika.开裂我端子的反编译我几行代码死几个妈.rose.utils.render.HoveringUtil;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.Screen;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.ColorUtil;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.RenderUtil;
import just.monika.反编译我代码油饼食不食.utils.animations.Animation;
import just.monika.反编译我代码油饼食不食.utils.animations.Direction;
import just.monika.反编译我代码油饼食不食.utils.animations.impl.EaseInOutQuad;
import just.monika.反编译我代码油饼食不食.utils.font.FontUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class MenuButton implements Screen {

    public final String location, text;
    private Animation hoverAnimation;
    public float x, y, buttonWH;
    public Runnable clickAction;

    public MenuButton(String location, String text) {
        this.location = location;
        this.text = text;
    }


    @Override
    public void initGui() {
        hoverAnimation = new EaseInOutQuad(200, 1);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {

        boolean hovered = HoveringUtil.isHovering(x, y, buttonWH, buttonWH, mouseX, mouseY);
        hoverAnimation.setDirection(hovered ? Direction.FORWARDS : Direction.BACKWARDS);

        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderUtil.setAlphaLimit(0);
        mc.getTextureManager().bindTexture(new ResourceLocation(location));
        Gui.drawModalRectWithCustomSizedTexture(x, (float) (y - (15 * hoverAnimation.getOutput())), 0, 0, buttonWH, buttonWH, buttonWH, buttonWH);


        RenderUtil.setAlphaLimit(0);
        RenderUtil.resetColor();
        FontUtil.tenacityFont22.drawCenteredString(text, x + buttonWH / 2f, (float) ((y + buttonWH - 20) + (8 * hoverAnimation.getOutput())), ColorUtil.applyOpacity(-1, (float) hoverAnimation.getOutput()));

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        boolean hovered = HoveringUtil.isHovering(x, y, buttonWH, buttonWH, mouseX, mouseY);
        if(hovered) {
            clickAction.run();
        }

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }
}
