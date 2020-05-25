package pl.raptors.raptorsRobotsApp.repository.robots;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.raptors.raptorsRobotsApp.domain.robots.BatteryType;
import pl.raptors.raptorsRobotsApp.domain.robots.RobotBattery;

import java.util.List;

@Repository
public interface RobotBatteryRepository extends MongoRepository<RobotBattery, String> {

    List<RobotBattery> findAllByType(BatteryType type);
}
