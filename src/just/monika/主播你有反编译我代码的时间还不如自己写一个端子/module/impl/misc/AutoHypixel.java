package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.misc;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.notifications.NotificationManager;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.notifications.NotificationType;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.player.ChatUtils;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.ChatReceivedEvent;
import just.monika.反编译我代码油饼食不食.settings.ParentAttribute;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.StringSetting;
import just.monika.反编译我代码油饼食不食.utils.misc.Multithreading;
import net.minecraft.util.StringUtils;

import java.util.concurrent.TimeUnit;

public class AutoHypixel extends Module {

    private final BooleanSetting autoGG = new BooleanSetting("AutoGG", true);
    private final StringSetting autoGGMessage = new StringSetting("AutoGG Message", "gg");
    private final BooleanSetting autoPlay = new BooleanSetting("AutoPlay", true);
    private final NumberSetting autoPlayDelay = new NumberSetting("AutoPlay Delay", 2.5, 8, 2, 0.5);
    private final BooleanSetting autoHubOnBan = new BooleanSetting("Auto /l on ban", false);

    public AutoHypixel() {
        super("AutoHypixel", Category.MISC, "stuff for hypixel");
        autoGGMessage.addParent(autoGG, ParentAttribute.BOOLEAN_CONDITION);
        autoPlayDelay.addParent(autoPlay, ParentAttribute.BOOLEAN_CONDITION);
        this.addSettings(autoGG, autoGGMessage, autoPlay, autoPlayDelay, autoHubOnBan);
    }

    private final EventListener<ChatReceivedEvent> onChatReceived = e -> {
        String message = e.message.getUnformattedText(), strippedMessage = StringUtils.stripControlCodes(message);
        if (autoHubOnBan.isEnabled() && strippedMessage.equals("A player has been removed from your game.")) {
            ChatUtils.send("/lobby");
            NotificationManager.post(NotificationType.WARNING, "AutoHypixel", "A player in your lobby got banned.");
        }
        String m = e.message.toString();
        if (m.contains("ClickEvent{action=RUN_COMMAND, value='/play ")) {
            if (autoGG.isEnabled() && !strippedMessage.startsWith("You died!")) {
                ChatUtils.send("/ac " + autoGGMessage.getString());
            }
            if (autoPlay.isEnabled()) {
                sendToGame(m.split("action=RUN_COMMAND, value='")[1].split("'}")[0]);
            }
        }
    };

    private void sendToGame(String mode) {
        float delay = autoPlayDelay.getValue().floatValue();
        NotificationManager.post(NotificationType.INFO, "AutoPlay",
                "Sending you to a new game" + (delay > 0 ? " in " + delay + "s" : "") + "!", delay);
        Multithreading.schedule(() -> ChatUtils.send(mode), (long) (delay * 1000), TimeUnit.MILLISECONDS);
    }

}
