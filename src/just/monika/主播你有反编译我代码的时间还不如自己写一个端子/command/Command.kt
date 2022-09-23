package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.command

abstract class Command(var name : String) {
    abstract fun onTyped(args : Array<String>)
}