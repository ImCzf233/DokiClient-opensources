package just.monika.反编译我代码油饼食不食.event.impl.game;

import just.monika.反编译我代码油饼食不食.event.Event;

public class KeyPressEvent extends Event {

    private final int key;

    public KeyPressEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

}
