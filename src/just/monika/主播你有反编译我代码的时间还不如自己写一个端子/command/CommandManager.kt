package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.command

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.command.impl.*

object CommandManager {
    val commands = mutableSetOf<Command>()
    fun getCommandByName(name : String): Command? {
        commands.forEach{
            if("."+it.name==name) return it
        }
        return null
    }
    init {
        commands.let {
            it.add(Toggle)
            it.add(Bind)
        }
    }
}