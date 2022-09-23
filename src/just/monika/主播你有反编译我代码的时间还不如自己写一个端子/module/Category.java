package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module;

import just.monika.反编译我代码油饼食不食.utils.font.FontUtil;
import just.monika.反编译我代码油饼食不食.utils.objects.Drag;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.objects.Scroll;
import lombok.Getter;

public enum Category {

    COMBAT("Combat", FontUtil.BOMB),
    MOVEMENT("Movement", FontUtil.WHEELCHAIR),
    RENDER("Render", FontUtil.EYE),
    PLAYER("Player", FontUtil.PERSON),
    EXPLOIT("Exploit", FontUtil.BUG),
    MISC("Misc", FontUtil.LIST),
    SCRIPTS("Scripts", FontUtil.SCRIPT);

    public final String name;
    public final String icon;
    public final int posX;
    public final boolean expanded;

    @Getter
    private final Scroll scroll = new Scroll();

    @Getter
    private final Drag drag;
    public int posY = 20;

    Category(String name, String icon) {
        this.name = name;
        this.icon = icon;
        posX = 40 + (Module.categoryCount * 120);
        drag = new Drag(posX, posY);
        expanded = true;
        Module.categoryCount++;
    }

	public Drag getDrag() {
		return drag;
	}

	public Scroll getScroll() {
		return scroll;
	}
}
