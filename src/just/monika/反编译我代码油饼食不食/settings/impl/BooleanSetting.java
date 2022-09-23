package just.monika.反编译我代码油饼食不食.settings.impl;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import just.monika.反编译我代码油饼食不食.settings.Setting;

public class BooleanSetting extends Setting {

    @Expose
    @SerializedName("name")
    private boolean state;

    public BooleanSetting(String name, boolean state) {
        this.name = name;
        this.state = state;
    }

    public boolean isEnabled() {
        return state;
    }

    public void toggle() {
        setState(!isEnabled());
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public Boolean getConfigValue() {
        return isEnabled();
    }

}
