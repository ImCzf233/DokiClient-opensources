package just.monika.反编译我代码油饼食不食.event.impl.player;

import just.monika.反编译我代码油饼食不食.event.Event;


public class SafeWalkEvent extends Event {

    private boolean safe;

    public boolean isSafe() {
        return this.safe;
    }

     
    public void setSafe(boolean safe) {
        this.safe = safe;
    }

}
