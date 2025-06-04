package lab6;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record Task(
    UUID id,
    String title,
    String description,
    LocalDate dueDate,
    Priority priority,
    boolean completed,
    Set<String> tags
) {}

