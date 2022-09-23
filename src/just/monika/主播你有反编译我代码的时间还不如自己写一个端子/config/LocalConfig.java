package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.config;

import net.minecraft.client.Minecraft;

import java.io.File;

public class LocalConfig extends Configuration {

    private final File file;

    public LocalConfig(String name) {
        super(name);
        this.file = new File(Minecraft.getMinecraft().mcDataDir + "/DokiClient/Configs/" + name + ".json");
    }

    public File getFile() {
        return file;
    }

}
