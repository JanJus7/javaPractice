package lab6;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public final class TaskFactory {
    private TaskFactory() {}
    
    public static Task create(
        String title,
        String description,
        LocalDate dueDate,
        Priority priority,
        Set<String> tags
    ) {
        return new Task(
            UUID.randomUUID(),
            title,
            description,
            dueDate,
            priority,
            false,
            Set.copyOf(tags)
        );
    }
}

