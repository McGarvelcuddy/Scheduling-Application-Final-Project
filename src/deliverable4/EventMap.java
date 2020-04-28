package deliverable4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class EventMap {

    private TreeMap<Double, Event> eventMap;

    public EventMap() {
        eventMap = new TreeMap<>();
    }

    public TreeMap getEventMap() {
        return eventMap;
    }

    public void addEvent(Event event) {
        Double yearDay = Double.valueOf(event.getDateTime().getDayOfYear());
        Double hour = Double.valueOf(event.getDateTime().getHour()) / 100;
        Double minute = Double.valueOf(event.getDateTime().getMinute()) / 10000;
        Double date = yearDay + hour + minute;
        this.eventMap.put(date, event);
    }
    
    public void removeEvent(double date) {
        eventMap.remove(date);
    }
    
    public ArrayList<Event> toArrayList(){
        ArrayList<Event> eventList = new ArrayList<>(this.eventMap.values());
        
        return eventList;
    }
    public ArrayList<Double> toKeyList(){
        ArrayList<Double> keyList = new ArrayList<>(this.eventMap.keySet());
        return keyList;
    }

    @Override
    public String toString() {
        return "{" + eventMap + '}';
    }

    public void stringToMap(String mapString) {
        String[] mapList = mapString.split("},");
        this.eventMap = new TreeMap<>();

        for (int i = 0; i < mapList.length; i++) {
            mapList[i] = mapList[i].replace("{", "");
            mapList[i] = mapList[i].replace("}", "");
            
            
            
            Scanner scan = new Scanner(mapList[i]);
            scan.useDelimiter("=");
            Double theKey = Double.parseDouble(scan.next());
            
            String pattern = "_";
            scan.useDelimiter(pattern);
            scan.next();
            String title = scan.next();
            scan.next();
            String dateTime = scan.next();
            scan.next();
            String date = scan.next();
            scan.next();
            String location = scan.next();
            scan.next();
            String note = scan.next();
            
            this.eventMap.put(theKey, new Event(title, date, location, note));
        }
    }
}
