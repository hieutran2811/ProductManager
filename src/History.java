import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class History {
    private String action;
    private Date date;

    public History(String action, Date date) {
        this.action = action;
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "History{" +
                "action='" + action + '\'' +
                ", date=" + date +
                '}';
    }
}
