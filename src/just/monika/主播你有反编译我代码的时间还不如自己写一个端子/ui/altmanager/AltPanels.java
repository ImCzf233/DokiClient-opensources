package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.altmanager;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.altmanager.panels.AltListAltPanel;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.altmanager.panels.KingAltsPanel;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.altmanager.panels.LoginAltPanel;

import java.util.ArrayList;
import java.util.Arrays;

public class AltPanels {
    private final ArrayList<AltPanel> altPanels = new ArrayList<>();

    public void addPanels() {
        altPanels.addAll(Arrays.asList(
                new LoginAltPanel(),
                new KingAltsPanel(),
                new AltListAltPanel()
        ));
    }

    public ArrayList<AltPanel> getPanels() {
        return altPanels;
    }

    public AltPanel getPanel(Class<? extends AltPanel> panel) {
        return getPanels().stream().filter(pan -> panel == pan.getClass()).findFirst().orElse(null);
    }
}
