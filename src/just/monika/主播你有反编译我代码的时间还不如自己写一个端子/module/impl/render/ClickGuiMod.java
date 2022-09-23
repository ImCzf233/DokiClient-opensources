package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.clickguis.dropdown.DropdownClickGui;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.RoundedUtil;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.render.ShaderUtil;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.ColorSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.ModeSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class ClickGuiMod extends Module {
    public static final ModeSetting cguiMode = new ModeSetting("ClickGui Mode","Dropdown","Dropdown","VapuLite");

    public static final ModeSetting colorMode = new ModeSetting("Color Mode", "Sync", "Sync", "Double Color", "Static", "Dynamic", "Dynamic Sync");
    public static final ColorSetting color = new ColorSetting("Color", new Color(41, 200, 224));
    public static final ColorSetting color2 = new ColorSetting("Color", new Color(136, 41, 224));
    public static final BooleanSetting accentedSettings = new BooleanSetting("Background Accent", true);
    public static final ModeSetting settingAccent = new ModeSetting("Setting Accent", "White", "White", "Color");
    public static final BooleanSetting walk = new BooleanSetting("Allow Movement", true);
    public static final ModeSetting scrollMode = new ModeSetting("Scroll Mode", "Screen Height", "Screen Height", "Value");
    public static final NumberSetting clickHeight = new NumberSetting("Tab Height", 250, 500, 100, 1);
    public static GuiScreen dropdownClickGui = new DropdownClickGui();

    public ClickGuiMod() {
        super("ClickGui", Category.RENDER, "Displays modules");
        clickHeight.addParent(scrollMode, selection -> selection.is("Value"));
        color.addParent(colorMode, selection -> !selection.is("Sync") || !selection.is("Dynamic Sync"));
        color2.addParent(colorMode, selection -> selection.is("Double Color") || selection.is("Dynamic"));
        this.addSettings(cguiMode,colorMode, color, color2, accentedSettings, settingAccent, walk, scrollMode, clickHeight);
        this.setKey(Keyboard.KEY_RSHIFT);
    }

    public void toggle() {
        this.onEnable();
    }

    public void onEnable() {
        mc.displayGuiScreen(dropdownClickGui);
        RoundedUtil.roundedShader = new ShaderUtil("roundedRect");
    }

}
