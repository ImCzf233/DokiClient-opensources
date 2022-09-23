package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.utils.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Mode {

    String name();

}
