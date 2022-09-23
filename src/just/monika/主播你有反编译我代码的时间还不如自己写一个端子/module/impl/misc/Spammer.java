package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.misc;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.MultipleBoolSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.StringSetting;
import just.monika.反编译我代码油饼食不食.utils.misc.MathUtils;
import just.monika.反编译我代码油饼食不食.utils.time.TimerUtil;

public final class Spammer extends Module {
    private final StringSetting text = new StringSetting("Text");
    private final NumberSetting delay = new NumberSetting("Delay", 100, 1000, 10, 1);
    private final MultipleBoolSetting settings = new MultipleBoolSetting("Settings",
            new BooleanSetting("AntiSpam", false),
            new BooleanSetting("Bypass", false));
    private final TimerUtil delayTimer = new TimerUtil();
    private final EventListener<MotionEvent> motionEventEventListener = event -> {
        String spammerText = text.getString();

        if (spammerText != null && delayTimer.hasTimeElapsed(settings.getSetting("Bypass").isEnabled() ? 2000 : delay.getValue().longValue())) {

            if (settings.getSetting("AntiSpam").isEnabled()) {
                spammerText += " " + MathUtils.getRandomInRange(10, 100000);
            }

            mc.thePlayer.sendChatMessage(spammerText);
            delayTimer.reset();
        }
    };

    public Spammer() {
        super("Spammer", Category.MISC, "Spam the chat with a custom message");
        this.addSettings(text, delay, settings);
    }
}
