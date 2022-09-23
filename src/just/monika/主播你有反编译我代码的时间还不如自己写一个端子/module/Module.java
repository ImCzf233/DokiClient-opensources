package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.DokiClient;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.config.ConfigSetting;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render.GlowESP;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render.NotificationsMod;
import just.monika.反编译我代码油饼食不食.settings.Setting;
import just.monika.反编译我代码油饼食不食.settings.impl.KeybindSetting;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.notifications.NotificationManager;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.notifications.NotificationType;
import just.monika.反编译我代码油饼食不食.utils.Utils;
import just.monika.反编译我代码油饼食不食.utils.animations.Animation;
import just.monika.反编译我代码油饼食不食.utils.animations.Direction;
import just.monika.反编译我代码油饼食不食.utils.animations.impl.DecelerateAnimation;
import just.monika.反编译我代码油饼食不食.utils.misc.Multithreading;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class Module implements Utils {
    public static Module INSTANCE=null;

    private final String description;
    private final Category category;
    private final CopyOnWriteArrayList<Setting> settingsList = new CopyOnWriteArrayList<>();

    private String suffix;

    @Expose
    @SerializedName("toggled")
    protected boolean toggled;
    @Expose
    @SerializedName("settings")
    public ConfigSetting[] cfgSettings;
    @Expose
    @SerializedName("name")
    private final String name;

    public boolean expanded;
    public final Animation animation = new DecelerateAnimation(250, 1);
    public static int categoryCount;
    public static float allowedClickGuiHeight = 300;

    private final KeybindSetting keybind = new KeybindSetting(Keyboard.KEY_NONE);

    public Module(String name, Category category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
        addSettings(keybind);
        if(INSTANCE==null){
            INSTANCE=this;
        }
    }

    public boolean isInGame() {
        return mc.theWorld != null && mc.thePlayer != null;
    }

    public void addSettings(Setting... settings) {
        settingsList.addAll(Arrays.asList(settings));
    }

    public String getName() {
        return this.name;
    }

    public List<Setting> getSettingsList() {
        return settingsList;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean hasMode() {
        return suffix != null;
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public void toggleSilent() {
        this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public void toggle() {
        toggleSilent();
        if (NotificationsMod.toggleNotifications.isEnabled()) {
            if (toggled) {
                NotificationManager.post(NotificationType.SUCCESS, "Module toggled", this.getName() + " was " + "\u00A7aenabled\r");
            } else {
                NotificationManager.post(NotificationType.DISABLE, "Module toggled", this.getName() + " was " + "\u00A7cdisabled\r");
            }
        }
    }

    public void onEnable() {
        DokiClient.INSTANCE.getEventProtocol().register(this);
    }

    public void onDisable() {
        if (this instanceof GlowESP) {
            GlowESP.fadeIn.setDirection(Direction.BACKWARDS);
            Multithreading.schedule(() -> DokiClient.INSTANCE.getEventProtocol().unregister(this), 250, TimeUnit.MILLISECONDS);
        } else {
            DokiClient.INSTANCE.getEventProtocol().unregister(this);
        }
    }

    public KeybindSetting getKeybind() {
        return keybind;
    }

    public void setKey(int code) {
        this.keybind.setCode(code);
    }

}
