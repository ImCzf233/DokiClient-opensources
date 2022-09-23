package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.combat;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.player.InventoryUtils;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import just.monika.反编译我代码油饼食不食.utils.network.PacketUtils;
import just.monika.反编译我代码油饼食不食.utils.time.TimerUtil;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.potion.Potion;

public class AutoHead extends Module {

    private final NumberSetting delay = new NumberSetting("Delay", 750, 3000, 0, 50);
    private final NumberSetting healPercent = new NumberSetting("Health %", 50, 99, 1, 1);
    private final TimerUtil timer = new TimerUtil();

    public AutoHead() {
        super("AutoHead", Category.COMBAT, "auto consume heads");
        this.addSettings(delay, healPercent);
    }

    private final EventListener<MotionEvent> onMotion = e -> {
        if (mc.thePlayer != null && mc.theWorld != null && e.isPre()
                && !(mc.thePlayer.isPotionActive(Potion.moveSpeed) && mc.thePlayer.isPotionActive(Potion.regeneration))
                && (mc.thePlayer.getHealth() / mc.thePlayer.getMaxHealth()) * 100 <= healPercent.getValue()
                && timer.hasTimeElapsed(delay.getValue().longValue())) {
            for (int i = 0; i < 45; i++) {
                ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (is != null && is.getItem() instanceof ItemSkull && is.getDisplayName().contains("Head")) {
                    int prevSlot = mc.thePlayer.inventory.currentItem;
                    if (i - 36 < 0) {
                        InventoryUtils.swap(i, 8);
                        PacketUtils.sendPacketNoEvent(new C09PacketHeldItemChange(8));
                    } else {
                        PacketUtils.sendPacketNoEvent(new C09PacketHeldItemChange(i - 36));
                    }

                    PacketUtils.sendPacketNoEvent(new C08PacketPlayerBlockPlacement(is));
                    mc.thePlayer.inventory.currentItem = prevSlot;
                    PacketUtils.sendPacketNoEvent(new C09PacketHeldItemChange(prevSlot));

                    timer.reset();
                }
            }
        }
    };

}
