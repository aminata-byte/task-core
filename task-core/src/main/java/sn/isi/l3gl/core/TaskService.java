package sn.isi.l3gl.core.task_core;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Version 0.0.1-SNAPSHOT
    public Task createTask(String title, String description) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(Task.Status.TODO);
        return taskRepository.save(task);
    }

    // Version 0.1.0-SNAPSHOT
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    // Version 0.2.0-SNAPSHOT
    public Task updateStatus(Long id, Task.Status status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    // Version 0.3.0-SNAPSHOT
    public long countCompletedTasks() {
        return taskRepository.findByStatus(Task.Status.DONE).size();
    }
}