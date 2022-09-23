package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.oldaltmanager;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.oldaltmanager.panels.AltListAltPanel;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.oldaltmanager.panels.KingAltsPanel;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.ui.oldaltmanager.panels.LoginAltPanel;

import java.util.ArrayList;
import java.util.Arrays;

public class AltPanels {
    private final ArrayList<AltPanel> altPanels = new ArrayList<>();

    public void addPanels() {
        altPanels.addAll(Arrays.asList(
                new LoginAltPanel(),
                new KingAltsPanel(),
                new AltListAltPanel()
//                new InformationAltPanel()
        ));
    }

    public ArrayList<AltPanel> getPanels() {
        return altPanels;
    }

    public AltPanel getPanel(Class<? extends AltPanel> panel) {
        return getPanels().stream().filter(pan -> panel == pan.getClass()).findFirst().orElse(null);
    }
}
