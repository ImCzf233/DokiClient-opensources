package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.module;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.DokiClient;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.DokiClient;
import net.minecraft.client.Minecraft;

public interface ModuleMode {

    Minecraft mc = Minecraft.getMinecraft();

    default void onEnable() {
        DokiClient.INSTANCE.getEventProtocol().register(this);
    }

    default void onDisable() {
        DokiClient.INSTANCE.getEventProtocol().unregister(this);
    }

}
