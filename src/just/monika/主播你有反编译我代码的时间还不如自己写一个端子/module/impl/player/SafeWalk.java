package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.player;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.SafeWalkEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;
import net.minecraft.item.ItemBlock;

public final class SafeWalk extends Module {

    private final BooleanSetting blocksOnly = new BooleanSetting("Blocks only", true);

    private final EventListener<SafeWalkEvent> onSafeWalk = e -> {
        if (canSafeWalk()) {
            e.setSafe(true);
        }
    };

    private boolean canSafeWalk() {
        if (!blocksOnly.isEnabled()) return true;
        return mc.thePlayer != null && mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock;
    }

    public SafeWalk() {
        super("SafeWalk", Category.PLAYER, "prevents walking off blocks");
        this.addSettings(blocksOnly);
    }

}
