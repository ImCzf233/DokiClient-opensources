package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.command.impl

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.DokiClient
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.command.Command
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.notifications.NotificationManager
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.notifications.NotificationType
import org.lwjgl.input.Keyboard

object Bind : Command("bind") {
    override fun onTyped(args: Array<String>) {
        try {
            DokiClient.INSTANCE.moduleCollection.getModuleByName(args[0]).setKey(Keyboard.getKeyIndex(args[1]))
            NotificationManager.post(NotificationType.SUCCESS, "Bind ${args[0]} to ${args[1]} success.", "LoL")
        }catch (_:Throwable){}
    }

}