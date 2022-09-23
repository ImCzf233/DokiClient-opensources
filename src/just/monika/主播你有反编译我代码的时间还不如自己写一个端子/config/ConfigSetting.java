package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigSetting {

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("value")
    public Object value;

    public ConfigSetting(String name, Object value) {
        this.name = name;
        this.value = value;
    }

}
