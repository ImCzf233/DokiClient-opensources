package just.monika.反编译我代码油饼食不食.event.impl.game;

import just.monika.反编译我代码油饼食不食.event.Event;

public class TickEvent extends Event {

    private final int ticks;

    public TickEvent(int ticks) {
        this.ticks = ticks;
    }

    public int getTicks() {
        return ticks;
    }

}
