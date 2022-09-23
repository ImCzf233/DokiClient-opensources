package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.player;

import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.NumberSetting;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.player.InventoryUtils;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.player.MovementUtils;
import just.monika.反编译我代码油饼食不食.utils.time.TimerUtil;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class AutoArmor extends Module {

    private final NumberSetting delay = new NumberSetting("Delay", 150, 300, 0, 10);
    private final BooleanSetting onlyWhileNotMoving = new BooleanSetting("Only while not moving", true);
    private final BooleanSetting invOnly = new BooleanSetting("Inventory only", true);
    private final TimerUtil timer = new TimerUtil();

    public AutoArmor() {
        super("AutoArmor", Category.PLAYER, "Automatically equips armor");
        this.addSettings(delay, onlyWhileNotMoving, invOnly);
    }

    private final EventListener<MotionEvent> onMotion = e -> {
        if (e.isPost()) return;
        if ((invOnly.isEnabled() && !(mc.currentScreen instanceof GuiInventory)) || (onlyWhileNotMoving.isEnabled() && MovementUtils.isMoving())) {
            return;
        }
        if (mc.thePlayer.openContainer instanceof ContainerChest) {
            // so it doesn't put on armor immediately after closing a chest
            timer.reset();
        }
        if (timer.hasTimeElapsed(delay.getValue().longValue())) {
            for (int armorSlot = 5; armorSlot < 9; armorSlot++) {
                if (equipBest(armorSlot)) {
                    timer.reset();
                    break;
                }
            }
        }
    };

    private boolean equipBest(int armorSlot) {
        int equipSlot = -1, currProt = -1;
        ItemArmor currItem = null;
        ItemStack slotStack = mc.thePlayer.inventoryContainer.getSlot(armorSlot).getStack();
        if (slotStack != null && slotStack.getItem() instanceof ItemArmor) {
            currItem = (ItemArmor) slotStack.getItem();
            currProt = currItem.damageReduceAmount
                    + EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, mc.thePlayer.inventoryContainer.getSlot(armorSlot).getStack());
        }
        // find best piece
        for (int i = 9; i < 45; i++) {
            ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (is != null && is.getItem() instanceof ItemArmor) {
                int prot = ((ItemArmor) is.getItem()).damageReduceAmount + EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, is);
                if ((currItem == null || currProt < prot) && isValidPiece(armorSlot, (ItemArmor) is.getItem())) {
                    currItem = (ItemArmor) is.getItem();
                    equipSlot = i;
                    currProt = prot;
                }
            }
        }
        // equip best piece (if there is a better one)
        if (equipSlot != -1) {
            if (slotStack != null) {
                InventoryUtils.drop(armorSlot);
            } else {
                InventoryUtils.click(equipSlot, 0, true);
            }
            return true;
        }
        return false;
    }

    private boolean isValidPiece(int armorSlot, ItemArmor item) {
        String unlocalizedName = item.getUnlocalizedName();
        return armorSlot == 5 && unlocalizedName.startsWith("item.helmet")
                || armorSlot == 6 && unlocalizedName.startsWith("item.chestplate")
                || armorSlot == 7 && unlocalizedName.startsWith("item.leggings")
                || armorSlot == 8 && unlocalizedName.startsWith("item.boots");
    }

}
