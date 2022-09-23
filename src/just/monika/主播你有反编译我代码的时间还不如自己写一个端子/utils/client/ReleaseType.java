package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.client;

public enum ReleaseType {

    PUBLIC("Public"),
    BETA("Beta"),
    DEV("Developer");

    private final String name;

    ReleaseType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
