import java.util.Date;

class Task {
    private String name;
    private Date dateAndTime;

    public Task(String name, Date date) {
        this.name = name;
        this.dateAndTime = date;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }
    public String getName() {
        return name;
    }
}