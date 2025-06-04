package lab6;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.stream.Stream;


public class TaskList {
    private final List<Task> tasks;
    
    public TaskList(List<Task> tasks) {
        this.tasks = List.copyOf(tasks);
    }
    
    public List<Task> tasks() {
        return tasks;
    }
    
    public TaskList add(Task t) {
        List<Task> newList = Stream.concat(tasks.stream(), Stream.of(t))
                                   .collect(Collectors.toList());
        return new TaskList(newList);
    }
    
    public TaskList remove(UUID id) {
        List<Task> newList = tasks.stream()
                                  .filter(t -> !t.id().equals(id))
                                  .collect(Collectors.toList());
        return new TaskList(newList);
    }
    
    public TaskList modify(UUID id, Function<Task, Task> modifier) {
        List<Task> newList = tasks.stream()
            .map(t -> t.id().equals(id) ? modifier.apply(t) : t)
            .collect(Collectors.toList());
        return new TaskList(newList);
    }
    
    public TaskList mapByTag(String tag, Function<Task, Task> modifier) {
        List<Task> newList = tasks.stream()
            .map(t -> t.tags().contains(tag) ? modifier.apply(t) : t)
            .collect(Collectors.toList());
        return new TaskList(newList);
    }
    
    public long countCompleted() {
        return tasks.stream()
                    .filter(Task::completed)
                    .count();
    }
    
    public Map<Priority, Long> countByPriority() {
        return tasks.stream()
                    .collect(Collectors.groupingBy(Task::priority, Collectors.counting()));
    }
}

