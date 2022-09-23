package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.misc;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.Detection;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.DetectionManager;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.notifications.NotificationManager;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.notifications.NotificationType;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.game.TickEvent;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;
import just.monika.反编译我代码油饼食不食.settings.impl.MultipleBoolSetting;
import just.monika.反编译我代码油饼食不食.utils.time.TimerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class HackerDetector extends Module {

    private DetectionManager detectionManager = new DetectionManager();
    private TimerUtil timer = new TimerUtil();
    private final MultipleBoolSetting detections = new MultipleBoolSetting("Detections",
            new BooleanSetting("Flight A", true),
            new BooleanSetting("Flight B", true),
            new BooleanSetting("Reach A", true));

    public HackerDetector() {
        super("HackerDetector", Category.MISC, "Detects people using cheats inside your game");
        this.addSettings(detections);
    }

    private final EventListener<TickEvent> onTick = e -> {
        if(mc.theWorld == null || mc.thePlayer == null) return;
        for(Entity entity : mc.theWorld.getLoadedEntityList()) {
            if(entity instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer) entity;
                if(entityPlayer != mc.thePlayer) {
                    for(Detection d : detectionManager.getDetections()) {
                        if(detections.getSetting(d.getName()).isEnabled()) {
                            if(d.runCheck(entityPlayer) && System.currentTimeMillis() > d.getLastViolated() + 500) {
                                NotificationManager.post(NotificationType.WARNING, entityPlayer.getName(), "has flagged " + d.getName() + " | " + EnumChatFormatting.BOLD + entityPlayer.VL);
                                entityPlayer.VL++;
                                d.setLastViolated(System.currentTimeMillis());
                            }
                        }
                    }
                }
            }
        }
    };
}
