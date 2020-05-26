package pl.raptors.raptorsRobotsApp.service.type;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.raptors.raptorsRobotsApp.domain.robots.Robot;
import pl.raptors.raptorsRobotsApp.domain.robots.RobotStatus;
import pl.raptors.raptorsRobotsApp.repository.type.RobotStatusRepository;
import pl.raptors.raptorsRobotsApp.service.CRUDService;
import pl.raptors.raptorsRobotsApp.service.robots.RobotService;

import java.util.List;
import java.util.Objects;

@Service
public class RobotStatusService implements CRUDService<RobotStatus> {

    @Autowired
    RobotStatusRepository robotStatusRepository;
    @Autowired
    RobotService robotService;

    @Override
    public RobotStatus addOne(RobotStatus robotStatus) {
        if (Objects.isNull((robotStatusRepository.findByName(robotStatus.getName())))) {
            robotStatus.setId(robotStatus.getName());
            robotStatusRepository.save(robotStatus);
            return robotStatus;
        }
        return robotStatusRepository.findByName(robotStatus.getName());
    }

    @Override
    public RobotStatus getOne(String id) {
        return robotStatusRepository.findById(id).orElse(null);
    }

    @Override
    public List<RobotStatus> getAll() {
        return robotStatusRepository.findAll();
    }

    @Override
    public RobotStatus updateOne(RobotStatus robotStatus) {
        List<Robot> robotList = robotService.getByStatus(this.getOne(robotStatus.getId()));
        int index;
        for (Robot robot : robotList) {
            List<RobotStatus> listToUpdate = robot.getStatus();
            for (RobotStatus status : listToUpdate) {
                if (status.getId().equals(robotStatus.getId())) {
                    index = listToUpdate.indexOf(status);
                    listToUpdate.set(index, robotStatus);
                    robot.setStatus(listToUpdate);
                    robotService.updateOne(robot);
                }
            }
        }
        return robotStatusRepository.save(robotStatus);
    }

    @Override
    public void deleteOne(RobotStatus robotStatus) {
        RobotStatus robotStatusToDelete = robotStatusRepository.findByName(robotStatus.getName());
        if (!Objects.isNull((robotStatusToDelete))) {
            List<Robot> robotList = robotService.getByStatus(this.getOne(robotStatus.getId()));
            robotService.deleteAll(robotList);
            robotStatusRepository.delete(robotStatusToDelete);
        }
    }

    @Override
    public void deleteAll(List<RobotStatus> robotStatusList) {
        for (RobotStatus status : robotStatusList) {
            this.deleteOne(status);
        }
    }
}
