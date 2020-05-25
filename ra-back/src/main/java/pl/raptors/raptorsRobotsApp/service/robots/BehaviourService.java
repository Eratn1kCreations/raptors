package pl.raptors.raptorsRobotsApp.service.robots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.raptors.raptorsRobotsApp.domain.robots.Behaviour;
import pl.raptors.raptorsRobotsApp.domain.robots.RobotTask;
import pl.raptors.raptorsRobotsApp.repository.robots.BehaviourRepository;
import pl.raptors.raptorsRobotsApp.service.CRUDService;

import java.util.List;

@Service
public class BehaviourService implements CRUDService<Behaviour> {

    @Autowired
    BehaviourRepository behaviourRepository;
    @Autowired
    RobotTaskService robotTaskService;

    @Override
    public Behaviour addOne(Behaviour behaviour) {
        return behaviourRepository.save(behaviour);
    }

    @Override
    public Behaviour getOne(String id) {
        return behaviourRepository.findById(id).orElse(null);
    }

    @Override
    public List<Behaviour> getAll() {
        return behaviourRepository.findAll();
    }

    @Override
    public Behaviour updateOne(Behaviour behaviour) {
        List<RobotTask> taskList = robotTaskService.getByBehaviour(this.getOne(behaviour.getId()));
        int index;
        for (RobotTask task : taskList) {
            List<Behaviour> listToUpdate = task.getBehaviours();
            for (Behaviour toUpdate : listToUpdate) {
                if (toUpdate.getId().equals(behaviour.getId())) {
                    index = listToUpdate.indexOf(toUpdate);
                    listToUpdate.set(index, behaviour);
                    task.setBehaviours(listToUpdate);
                    robotTaskService.updateOne(task);
                }
            }
        }
        return behaviourRepository.save(behaviour);
    }

    @Override
    public void deleteOne(Behaviour behaviour) {
        List<RobotTask> taskList = robotTaskService.getByBehaviour(this.getOne(behaviour.getId()));
        robotTaskService.deleteAll(taskList);
        behaviourRepository.delete(behaviour);
    }

    @Override
    public void deleteAll(List<Behaviour> behaviourList) {
        for (Behaviour behaviour : behaviourList) {
            this.deleteOne(behaviour);
        }
    }
}
