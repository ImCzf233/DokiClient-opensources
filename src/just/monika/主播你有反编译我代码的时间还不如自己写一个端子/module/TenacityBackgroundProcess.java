package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.DokiClient;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.config.ConfigManager;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.config.DragManager;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.render.SessionStats;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.mainmenu.TenacityMainMenu;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.game.KeyPressEvent;
import just.monika.反编译我代码油饼食不食.event.impl.game.GameCloseEvent;
import just.monika.反编译我代码油饼食不食.event.impl.game.TickEvent;
import just.monika.反编译我代码油饼食不食.event.impl.game.WorldEvent;
import just.monika.反编译我代码油饼食不食.event.impl.player.ChatReceivedEvent;
import just.monika.反编译我代码油饼食不食.utils.Utils;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.util.StringUtils;

import java.io.File;
import java.util.Arrays;

public class TenacityBackgroundProcess implements Utils {

    private final EventListener<KeyPressEvent> onKeyPress = e ->
            DokiClient.INSTANCE.getModuleCollection().getModules().stream()
                    .filter(m -> m.getKeybind().getCode() == e.getKey()).forEach(Module::toggle);

    private final File dragData = new File(DokiClient.DIRECTORY, "Drag.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
//.setLenient()

    private final EventListener<WorldEvent.Load> onLoad = e ->{
        ConfigManager.loadConfig();
    };
    
    private final EventListener<GameCloseEvent> onGameClose = e -> {
        DokiClient.INSTANCE.getConfigManager().saveDefaultConfig();
        ConfigManager.saveConfig();
        DragManager.saveDragData();
    };

    private final EventListener<ChatReceivedEvent> onChatReceived = e -> {
        if (mc.thePlayer == null) return;
        String message = StringUtils.stripControlCodes(e.message.getUnformattedText());
        if (!message.contains(":") && Arrays.stream(SessionStats.KILL_TRIGGERS).anyMatch(message.replace(mc.thePlayer.getName(), "*")::contains)) {
            SessionStats.killCount++;
        } else if (e.message.toString().contains("ClickEvent{action=RUN_COMMAND, value='/play ")) {
            SessionStats.gamesPlayed++;
        }
    };

    private final EventListener<TickEvent> onTick = e -> {
        if (SessionStats.endTime == -1 && ((!mc.isSingleplayer() && mc.getCurrentServerData() == null) || mc.currentScreen instanceof TenacityMainMenu || mc.currentScreen instanceof GuiMultiplayer || mc.currentScreen instanceof GuiDisconnected)) {
            SessionStats.endTime = System.currentTimeMillis();
        } else if (SessionStats.endTime != -1 && (mc.isSingleplayer() || mc.getCurrentServerData() != null)) {
            SessionStats.reset();
        }
    };

}
