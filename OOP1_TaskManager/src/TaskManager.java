import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void listTasks() {
        System.out.println("Список задач:");
        if (tasks.size() == 0){
            System.out.println("Список задач пока что пуст");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm");
        List<Task> sortedTasks = tasks.stream()
                .sorted(Comparator.comparing(Task::getDateAndTime))
                .collect(Collectors.toList());
        for (int i = 0; i < sortedTasks.size(); i++) {
            Task task = sortedTasks.get(i);
            System.out.println((i + 1) + ") " + dateFormat.format(task.getDateAndTime()) + " - " + task.getName());
        }
    }
    public void deleteTask(int index){
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Неправильный индекс задачи.");
        }
    }
}