package pl.raptors.raptorsRobotsApp.domain.robots;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.raptors.raptorsRobotsApp.domain.type.PropulsionType;

@NoArgsConstructor
@Data
@Document(collection = "robot_models")
public class RobotModel {

    @Id
    private String id;
    private String name;
    private String maxLiftingCapacity;
    private String maxVelocity;
    private String length;
    private String width;
    private String height;
    private String turningRadius;
    private PropulsionType propulsionType;
    private BatteryType batteryType;

    public RobotModel(String name, String maxLiftingCapacity, String maxVelocity, String length, String width, String height, String turningRadius, PropulsionType propulsionType, BatteryType batteryType) {
        this.name = name;
        this.maxLiftingCapacity = maxLiftingCapacity;
        this.maxVelocity = maxVelocity;
        this.length = length;
        this.width = width;
        this.height = height;
        this.turningRadius = turningRadius;
        this.propulsionType = propulsionType;
        this.batteryType = batteryType;
    }
}
