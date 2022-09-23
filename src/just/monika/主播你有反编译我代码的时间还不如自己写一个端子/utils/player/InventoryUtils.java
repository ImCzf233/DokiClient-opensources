package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.player;

import just.monika.反编译我代码油饼食不食.utils.Utils;

public class InventoryUtils implements Utils {

    public static void click(int slot, int mouseButton, boolean shiftClick) {
        mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, slot, mouseButton, shiftClick ? 1 : 0, mc.thePlayer);
    }

    public static void drop(int slot) {
        mc.playerController.windowClick(0, slot, 1, 4, mc.thePlayer);
    }

    public static void swap(int slot, int hSlot) {
        mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, slot, hSlot, 2, mc.thePlayer);
    }

}
