package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.misc;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.network.PacketReceiveEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.ModeSetting;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S2DPacketOpenWindow;

public final class AntiFreeze extends Module {

    private final ModeSetting mode = new ModeSetting("Mode", "Normal", "Normal", "Teleport");

    private final EventListener<PacketReceiveEvent> packetReceiveEventEventListener = event -> {
        if (event.getPacket() instanceof S2DPacketOpenWindow
                && ((S2DPacketOpenWindow) event.getPacket()).getWindowTitle().getUnformattedText().contains("frozen")) {
            event.cancel();
        } else if (event.getPacket() instanceof S02PacketChat
                && ((S02PacketChat) event.getPacket()).getChatComponent().getUnformattedText().contains("frozen")) {
            if (mode.is("Teleport")) {
                mc.thePlayer.posY = -999;
            }
            event.cancel();
        }
    };

    public AntiFreeze() {
        super("AntiFreeze", Category.MISC, "prevents server plugins from freezing you");
    }

}
