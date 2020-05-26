package pl.raptors.ra_back.domain.robots;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "logs")
public class Log {
    @Id
    private String id;
    private Robot robot;
    private String timestamp;
    private RobotStatus status;

    public Log(Robot robot, String timestamp, RobotStatus status) {
        this.robot = robot;
        this.timestamp = timestamp;
        this.status = status;
    }
}
