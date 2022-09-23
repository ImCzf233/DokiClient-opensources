package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.player.EntityPlayer;

@Getter
@Setter
public abstract class Detection {

    private String name;
    private Category type;
    private long lastViolated;

    public Detection(String name, Category type) {
        this.name = name;
        this.type = type;
    }

    public abstract boolean runCheck(EntityPlayer player);

	public String getName() {
		return name;
	}
	
	public long getLastViolated() {
		return lastViolated;
	}

	public void setLastViolated(long lastViolated) {
		this.lastViolated = lastViolated;
	}
}
