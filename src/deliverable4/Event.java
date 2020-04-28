package deliverable4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {

    private final String title;
    private final LocalDateTime dateTime;
    private final String date;
    private final String location;
    private final String note;

    public Event(String title, String date, String location, String note) {
        this.title = title;
        this.date = date;
        this.dateTime = parseDateTime(date);
        this.location = location;
        this.note = note;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getNote() {
        return note;
    }

    public String formatDateTime() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyy, HH:mm a");
        String formatted = this.dateTime.format(myFormatObj);
        return formatted;
    }

    private LocalDateTime parseDateTime(String date) {
        String[] strArray = date.split(",");
        int[] details = new int[5];

        for (int i = 0; i < details.length; i++) {
            details[i] = Integer.parseInt(strArray[i]);
        }

        LocalDateTime setDate = LocalDateTime.of(details[0], details[1], details[2], details[3], details[4]);
        return setDate;
    }

    @Override
    public String toString() {
        return "Event{title=_" + title + "_,dateTime=_" + dateTime + "_,date=_" + date + "_,location=_" + location + "_,note=_" + note + "_}";
    }
    
    public String[] toListString(){
        String[] info = new String[4];
        info[0] = title;
        info[1] = date;
        info[2] = location;
        info[3] = note;
        return info;
    }
}
