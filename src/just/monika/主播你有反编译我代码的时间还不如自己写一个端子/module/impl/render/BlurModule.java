package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.DokiClient;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.RenderUtil;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.blur.BloomUtil;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.blur.GaussianBlur;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.blur.KawaseBlur;
import just.monika.反编译我代码油饼食不食.event.impl.render.ShaderEvent;
import just.monika.反编译我代码油饼食不食.settings.ParentAttribute;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.ModeSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import just.monika.反编译我代码油饼食不食.utils.render.StencilUtil;
import net.minecraft.client.shader.Framebuffer;

public class BlurModule extends Module {

    private final BooleanSetting blur = new BooleanSetting("Blur", true);
    private final ModeSetting blurMode = new ModeSetting("Blur Mode", "Kawase", "Gaussian", "Kawase");
    private final NumberSetting radius = new NumberSetting("Blur Radius", 10, 50, 1, 1);
    private final NumberSetting iterations = new NumberSetting("Blur Iterations", 4, 15, 1, 1);
    private final NumberSetting offset = new NumberSetting("Blur Offset", 3, 20, 1, 1);
    private final BooleanSetting shadow = new BooleanSetting("Shadow", true);
    private final NumberSetting shadowRadius = new NumberSetting("Shadow Radius", 6, 20, 1,1);
    private final NumberSetting shadowOffset = new NumberSetting("Shadow Offset", 2, 15, 1,1);

    public BlurModule() {
        super("Blur", Category.RENDER, "blurs shit");
        blurMode.addParent(blur, ParentAttribute.BOOLEAN_CONDITION);
        iterations.addParent(blurMode, modeSetting -> modeSetting.is("Kawase"));
        offset.addParent(blurMode, modeSetting -> modeSetting.is("Kawase"));
        radius.addParent(blurMode, modeSetting -> !modeSetting.is("Kawase"));
        shadowRadius.addParent(shadow, ParentAttribute.BOOLEAN_CONDITION);
        shadowOffset.addParent(shadow, ParentAttribute.BOOLEAN_CONDITION);
        addSettings(blur, blurMode, radius, iterations, offset, shadow, shadowRadius, shadowOffset);
    }

    private String currentMode;

    @Override
    public void onEnable() {
        currentMode = blurMode.getMode();
      //  RoundedUtil.roundedOutlineShader = new ShaderUtil("DokiClient/Shaders/roundRectOutline.frag");
        super.onEnable();
    }

    public void stuffToBlur(boolean bloom) {

       // Gui.drawRect2(40, 40, 400, 40, -1);

    }


    private Framebuffer bloomFramebuffer = new Framebuffer(1, 1, false);

    public void blurScreen() {
        if (!toggled) return;
        if (!currentMode.equals(blurMode.getMode())) {
            currentMode = blurMode.getMode();
        }
        if (blur.isEnabled()) {
            StencilUtil.initStencilToWrite();
            DokiClient.INSTANCE.getEventProtocol().dispatch(new ShaderEvent(false));
            stuffToBlur(false);
            StencilUtil.readStencilBuffer(1);

            switch (currentMode) {
                case "Gaussian":
                    GaussianBlur.renderBlur(radius.getValue().floatValue());
                    break;
                case "Kawase":
                    KawaseBlur.renderBlur(iterations.getValue().intValue(), offset.getValue().intValue());
                    break;
            }
            StencilUtil.uninitStencilBuffer();
        }


        if (shadow.isEnabled()) {
            bloomFramebuffer = RenderUtil.createFrameBuffer(bloomFramebuffer);

            bloomFramebuffer.framebufferClear();
            bloomFramebuffer.bindFramebuffer(true);
            DokiClient.INSTANCE.getEventProtocol().dispatch(new ShaderEvent(true));
            stuffToBlur(true);
            bloomFramebuffer.unbindFramebuffer();

            BloomUtil.renderBlur(bloomFramebuffer.framebufferTexture, shadowRadius.getValue().intValue(), shadowOffset.getValue().intValue());
        }


    }


}
