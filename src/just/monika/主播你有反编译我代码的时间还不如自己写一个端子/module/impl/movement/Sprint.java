package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.movement;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.DokiClient;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.impl.player.NoSlow;
import just.monika.反编译我代码油饼食不食.event.EventListener;
import just.monika.反编译我代码油饼食不食.event.impl.player.MotionEvent;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Category;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module;
import just.monika.反编译我代码油饼食不食.settings.impl.BooleanSetting;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.player.MovementUtils;

public class Sprint extends Module {

    private final BooleanSetting omniSprint = new BooleanSetting("Omni Sprint", false);

    public Sprint() {
        super("Sprint", Category.MOVEMENT, "Sprints automatically");
        this.addSettings(omniSprint);
    }

    private final EventListener<MotionEvent> onMotion = e -> {
        if (DokiClient.INSTANCE.getModuleCollection().get(Scaffold.class).isToggled() && !Scaffold.sprint.isEnabled()) {
            mc.gameSettings.keyBindSprint.pressed = false;
            mc.thePlayer.setSprinting(false);
            return;
        }
        if (omniSprint.isEnabled()) {
            mc.thePlayer.setSprinting(MovementUtils.isMoving());
        } else {
            if(mc.thePlayer.isUsingItem()) {
                if (mc.thePlayer.moveForward > 0 && (DokiClient.INSTANCE.getModuleCollection().get(NoSlow.class).isToggled() || !mc.thePlayer.isUsingItem()) && !mc.thePlayer.isSneaking() && !mc.thePlayer.isCollidedHorizontally && mc.thePlayer.getFoodStats().getFoodLevel() > 6) {
                    mc.thePlayer.setSprinting(true);
                }
            }else{
                mc.gameSettings.keyBindSprint.pressed = true;
            }
        }
    };

    @Override
    public void onDisable() {
        mc.thePlayer.setSprinting(false);
        super.onDisable();
    }

}
