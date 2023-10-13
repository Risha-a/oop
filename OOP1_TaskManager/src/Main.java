import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Что хотите сделать?");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Удалить задачу из списка");
            System.out.println("3. Показать список задач");
            System.out.println("4. Закрыть программу");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Название задачи: ");
                    String name = scanner.nextLine();
                    System.out.print("К какому сроку сделать задачу (дд.мм чч:мм): ");
                    String dateAndTimeStr = scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm");
                    Date dateAndTime = null;
                    try {
                        dateAndTime = dateFormat.parse(dateAndTimeStr);
                    } catch (ParseException e) {
                        System.out.println("Неверный формат даты и времени.");
                        break;
                    }
                    Task task = new Task(name, dateAndTime);
                    taskManager.addTask(task);
                    break;
                case "2":
                    System.out.print("Какую задачу удалить: ");
                    int index = scanner.nextInt();
                    taskManager.deleteTask(index - 1);
                    break;
                case "3":
                    taskManager.listTasks();
                    break;
                case "4":
                    System.out.println("Завершение работы");
                    System.exit(0);
                default:
                    System.out.println("Такого варианта нет. Выберите другой.");
            }
            System.out.println();
        }
    }
}
