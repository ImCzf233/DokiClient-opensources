package just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector;

import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.checks.FlightA;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.checks.FlightB;
import just.monika.主播你有反编译我代码的时间还不如自己写一个端子.hackerdetector.checks.ReachA;

import java.util.ArrayList;
import java.util.Arrays;

public class DetectionManager {

    private ArrayList<Detection> detections = new ArrayList<>();

    public DetectionManager() {
        addDetections(

                // Combat
                new ReachA(),

                // Movement
                new FlightA(),
                new FlightB()

                // Player

                // Misc

                // Exploit

        );
    }

    public void addDetections(Detection... detections) {
        this.detections.addAll(Arrays.asList(detections));
    }

    public ArrayList<Detection> getDetections() {
        return detections;
    }
}
