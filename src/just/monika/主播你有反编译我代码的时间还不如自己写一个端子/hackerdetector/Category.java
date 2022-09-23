package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector;

public enum Category {

    COMBAT("Combat"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    MISC("Misc"),
    EXPLOIT("Exploit");

    private String name;

    Category(String name) {
        this.name = name;
    }

}
