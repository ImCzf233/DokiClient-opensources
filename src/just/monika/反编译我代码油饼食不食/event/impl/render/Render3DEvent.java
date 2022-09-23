package just.monika.反编译我代码油饼食不食.event.impl.render;

import just.monika.反编译我代码油饼食不食.event.Event;

public class Render3DEvent extends Event {

    private float ticks;

    public Render3DEvent(float ticks) {
        this.ticks = ticks;
    }

    public float getTicks() {
        return ticks;
    }

    public void setTicks(float ticks) {
        this.ticks = ticks;
    }

}
