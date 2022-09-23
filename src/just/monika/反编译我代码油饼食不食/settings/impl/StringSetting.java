package just.monika.反编译我代码油饼食不食.settings.impl;

import just.monika.反编译我代码油饼食不食.settings.Setting;

public class StringSetting extends Setting {

    private String string = "";

    public StringSetting(String name) {
        this.name = name;
    }

    public StringSetting(String name, String defaultValue) {
        this.name = name;
        this.string = defaultValue;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String getConfigValue() {
        return string;
    }

}
