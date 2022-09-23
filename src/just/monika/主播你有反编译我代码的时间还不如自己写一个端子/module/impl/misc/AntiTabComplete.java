package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.misc;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.network.PacketReceiveEvent;
import just.monika.反编译我代码油饼食不食.event.impl.network.PacketSendEvent;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.server.S3APacketTabComplete;

@SuppressWarnings("unused")
public final class AntiTabComplete extends Module {

    public AntiTabComplete() {
        super("AntiTabComplete", Category.MISC, "prevents you from tab completing");
    }

    private final EventListener<PacketSendEvent> packetSendEventEventListener = event -> {
        if (event.getPacket() instanceof C14PacketTabComplete) {
            event.cancel();
        }
    };
    private final EventListener<PacketReceiveEvent> packetReceiveEventEventListener = event -> {
        if (event.getPacket() instanceof S3APacketTabComplete) {
            event.cancel();
        }
    };

}
