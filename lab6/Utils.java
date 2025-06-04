package lab6;

public class Utils {
    public static Task withTitle(Task t, String newTitle) {
        return new Task(
            t.id(),
            newTitle,
            t.description(),
            t.dueDate(),
            t.priority(),
            t.completed(),
            t.tags()
        );
    }
    
    public static Task markCompleted(Task t) {
        return new Task(
            t.id(),
            t.title(),
            t.description(),
            t.dueDate(),
            t.priority(),
            true,
            t.tags()
        );
    }
    
    public static Task changePriority(Task t, Priority newPriority) {
        return new Task(
            t.id(),
            t.title(),
            t.description(),
            t.dueDate(),
            newPriority,
            t.completed(),
            t.tags()
        );
    }
}

