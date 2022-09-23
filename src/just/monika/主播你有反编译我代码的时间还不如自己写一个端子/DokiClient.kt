package just.monika.主播你有反编译我代码的时间还不如自己写一个端子

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.config.ConfigManager
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.config.DragManager
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.Module
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.module.ModuleCollection
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.altmanager.AltPanels
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.altmanager.GuiAltManager
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.altmanager.helpers.KingGenApi
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.notifications.NotificationManager
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.sidegui.SideGui
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.client.ReleaseType
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.objects.Dragging
import just.monika.反编译我代码油饼食不食.event.Event
import just.monika.反编译我代码油饼食不食.event.EventProtocol
import just.monika.反编译我代码油饼食不食.utils.Utils
import org.apache.logging.log4j.LogManager
import java.awt.Color
import java.io.File
import java.util.concurrent.Executors

//import dev.client.tenacity.commands.CommandHandler;
enum class DokiClient : Utils {
    INSTANCE;

    val eventProtocol =
        EventProtocol<Event>()
    val notificationManager = NotificationManager()
    val executorService = Executors.newSingleThreadExecutor()
    val sideGui = SideGui()
    var moduleCollection = ModuleCollection()
    var configManager = ConfigManager()

    //    public CommandHandler getCommandHandler() { return commandHandler; }
    var altManager: GuiAltManager? = null

    @JvmField
    val altPanels = AltPanels()
    @JvmField
    var kingGenApi: KingGenApi? = null
    val version: String
        get() = VERSION + if (RELEASE != ReleaseType.PUBLIC) " (" + RELEASE.getName() + ")" else ""
    val clientColor: Color
        get() = Color(236, 133, 209)
    val alternateClientColor: Color
        get() = Color(28, 167, 222)

    fun isToggled(c: Class<out Module?>?): Boolean {
        val m = moduleCollection[c]
        return m != null && m.isToggled
    }

    fun createDrag(module: Module?, name: String?, x: Float, y: Float): Dragging? {
        DragManager.draggables[name] = Dragging(module, name, x, y)
        return DragManager.draggables[name]
    }

    companion object {
        const val NAME = "DokiClient"
        const val VERSION = "1.0"
        val RELEASE = ReleaseType.DEV
        @JvmField
        val LOGGER = LogManager.getLogger(NAME)
        @JvmField
        val DIRECTORY = File(Utils.mc.mcDataDir, "DokiClient")
    }
}