package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.command.impl

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.DokiClient
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.command.Command

object Toggle : Command("t") {
    override fun onTyped(args: Array<String>) {
        try {
            DokiClient.INSTANCE.moduleCollection.getModuleByName(args[0]).toggle()
        }catch (_:Throwable){

        }

    }
}