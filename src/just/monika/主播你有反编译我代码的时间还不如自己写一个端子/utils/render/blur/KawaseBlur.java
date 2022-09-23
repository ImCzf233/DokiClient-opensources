package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.blur;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.RenderUtil;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.ShaderUtil;
import just.monika.反编译我代码油饼食不食.utils.Utils;
import net.minecraft.client.shader.Framebuffer;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL20.glUniform1;

public class KawaseBlur implements Utils {

    public static ShaderUtil kawaseDown = new ShaderUtil("DokiClient/Shaders/kawaseDown.frag");
    public static ShaderUtil kawaseUp = new ShaderUtil("DokiClient/Shaders/kawaseUp.frag");

    public static Framebuffer framebuffer = new Framebuffer(1, 1, false);


    public static void setupUniforms(float offset) {
        kawaseDown.setUniformf("offset", offset, offset);
        kawaseUp.setUniformf("offset", offset, offset);
    }

    private static int currentIterations;

    private static final List<Framebuffer> framebufferList = new ArrayList<>();

    private static void initFramebuffers(float iterations) {
        for(Framebuffer framebuffer : framebufferList) {
            framebuffer.deleteFramebuffer();
        }
        framebufferList.clear();

        framebufferList.add(RenderUtil.createFrameBuffer(framebuffer));


        for(int i = 1; i <= iterations; i++) {
            Framebuffer framebuffer = new Framebuffer(mc.displayWidth, mc.displayHeight, false);
          //  framebuffer.setFramebufferFilter(GL11.GL_LINEAR);
            framebufferList.add(RenderUtil.createFrameBuffer(framebuffer));
        }
    }



    public static void renderBlur(int iterations, int offset) {
        if(currentIterations != iterations) {
            initFramebuffers(iterations);
            currentIterations = iterations;
        }

        renderFBO(framebufferList.get(1), mc.getFramebuffer().framebufferTexture, kawaseDown, offset);

        //Downsample
        for (int i = 1; i < iterations; i++) {
            renderFBO(framebufferList.get(i + 1), framebufferList.get(i).framebufferTexture, kawaseDown, offset);
        }

        //Upsample
        for (int i = iterations; i > 1; i--) {
            renderFBO(framebufferList.get(i - 1), framebufferList.get(i).framebufferTexture, kawaseUp, offset);
        }


        mc.getFramebuffer().bindFramebuffer(true);

        RenderUtil.bindTexture(framebufferList.get(1).framebufferTexture);
        kawaseUp.init();
        kawaseUp.setUniformf("offset", offset, offset);
        kawaseUp.setUniformf("halfpixel", 0.5f / mc.displayWidth, 0.5f / mc.displayHeight);
        kawaseUp.setUniformi("inTexture", 0);
        ShaderUtil.drawQuads();
        kawaseUp.unload();

    }

    private static void renderFBO(Framebuffer framebuffer, int framebufferTexture, ShaderUtil shader, float offset) {
        framebuffer.framebufferClear();
        framebuffer.bindFramebuffer(true);
        shader.init();
        RenderUtil.bindTexture(framebufferTexture);
        shader.setUniformf("offset", offset, offset);
        shader.setUniformi("inTexture", 0);
        shader.setUniformf("halfpixel", 0.5f / mc.displayWidth, 0.5f / mc.displayHeight);
        ShaderUtil.drawQuads();
        shader.unload();
        framebuffer.unbindFramebuffer();
    }


}
