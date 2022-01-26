public abstract class Task {

    public boolean done = false;

    public void setDone(boolean newDone) {
        this.done = newDone;
    }

    public boolean isDone() {
        return this.done;
    }

    public abstract String exportToString();

    public static Task importFromString(String exportedTask)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        String[] details = exportedTask.split(" ");
        Task task = null;
        switch (Duke.TaskType.valueOf(details[0])) {
            case TODO:
                task = new Todo(details[1]);
                task.setDone(Boolean.parseBoolean(details[2]));
                break;
            case EVENT:
                task = new Event(details[1], details[3]);
                task.setDone(Boolean.parseBoolean(details[2]));
                break;
            case DEADLINE:
                task = new Deadline(details[1], details[3]);
                task.setDone(Boolean.parseBoolean(details[2]));
                break;
        }
        return task;

    }
}