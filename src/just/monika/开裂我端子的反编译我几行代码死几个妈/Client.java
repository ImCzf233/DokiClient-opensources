package just.monika.开裂我端子的反编译我几行代码死几个妈;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.DokiClient;
import just.monika.反编译我代码油饼食不食.event.Event;

public class Client {

    public static ClientType client;

    public static void dispatchEvent(Event event) {
        if (client == null) return;
        DokiClient.INSTANCE.getEventProtocol().dispatch(event);
    }

}
