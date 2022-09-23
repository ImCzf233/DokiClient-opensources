package just.monika.反编译我代码油饼食不食.event.impl.network;

import just.monika.反编译我代码油饼食不食.event.Event;
import net.minecraft.network.Packet;

public class PacketSendEvent extends Event {

    private Packet<?> packet;

    public PacketSendEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    public void setPacket(Packet<?> packet) {
        this.packet = packet;
    }

}
