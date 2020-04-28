package deliverable4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class EventController {
    
    private final EventMap eventMap;
    private final EventView eventView;
    private EventPopup eventPopup;
    private StartMenu startMenu;
    public String saveFile = "data.txt";
    private DefaultTableModel defTableModel;

    public EventController() {
        eventMap = new EventMap();
        eventView = new EventView(this);
        makeTableModel();
        startMenu = new StartMenu(this);
        startMenu.setVisible(true);
    }

    public void addNewEvent(Event newEvent) {
        eventMap.addEvent(newEvent);
        String eventInfo = newEvent.getTitle() + "\nOn: " + newEvent.formatDateTime() + " \nAt: " + newEvent.getLocation() + "\nNote: " + newEvent.getNote();
        popupConfirmAdd(eventInfo);
        
    }

    public void popupConfirmAdd(String eventInfo) {
        eventPopup = new EventPopup(this, "Adding: " + eventInfo);
        eventPopup.setVisible(true);
    }

    public void popupConfirmRemove() {
        eventPopup = new EventPopup(this, "Selected event removed.");
        eventPopup.setVisible(true);
    }

    private void popupFileNotFound() {
        eventPopup = new EventPopup(this, "No text file found named: " + saveFile);
        eventPopup.setVisible(true);
    }
    
    private void popupReadError() {
        eventPopup = new EventPopup(this, "I/O Exception when accessing: " + saveFile);
        eventPopup.setVisible(true);
    }
    
    public void popupSelectionError(){
        eventPopup = new EventPopup(this, "Please select a row in order to delete.");
        eventPopup.setVisible(true);
    }

    

    public String getCurrentDateTime() {
        LocalDateTime cTime;
        cTime = LocalDateTime.now();

        String currentParse;
        currentParse = cTime.getYear() + "," + cTime.getMonthValue() + "," + cTime.getDayOfMonth() + "," + cTime.getHour() + "," + cTime.getMinute();
        return currentParse;
    }

    public void openAdderRemover() {
        eventView.setVisible(true);
    }

    public void saveData(String fileName) {
        if(fileName.contains(".txt")){
            this.saveFile = fileName;
        }else{
            this.saveFile = fileName + ".txt";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {
            writer.write(this.eventMap.toString());
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData(String fileName) {
        if(fileName.contains(".txt")){
            this.saveFile = fileName;
        }else{
            this.saveFile = fileName + ".txt";
        }
        try {
            File file = new File(saveFile);
            FileReader fRead = new FileReader(file);
            BufferedReader bRead = new BufferedReader(fRead);

            String line;
            try {
                while ((line = bRead.readLine()) != null) {
                    line = line.substring(1, line.length() - 1);
                    String lineFancy = line.replaceAll(" ", "");
                    eventMap.stringToMap(lineFancy);
                }
            } catch (IOException except) {
                popupReadError();
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, except);
            }
        } catch (FileNotFoundException except) {
            popupFileNotFound();
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, except);
        }
        
        startMenu.setVisible(false);
        makeTableModel();
        startMenu = new StartMenu(this);
        
        startMenu.setVisible(true);
    }
    
    private void makeTableModel(){
        ArrayList<Event> eventArray = eventMap.toArrayList();
        
        String[] columnNames = {"Title", "Date", "Location", "Notes"};
        Object infoList[][] = new Object[eventArray.size()][];
        System.out.println(eventArray.size());
        defTableModel = new DefaultTableModel();
        defTableModel.setColumnIdentifiers(columnNames);
        for(int i = 0; i < infoList.length; i++){
            
            Object[] info = new Object[4];
            info[0] = eventArray.get(i).getTitle();
            info[1] = eventArray.get(i).formatDateTime();
            info[2] = eventArray.get(i).getLocation();
            info[3] = eventArray.get(i).getNote();
            defTableModel.addRow(new Object[]{info[0].toString(), info[1].toString(), info[2].toString(), info[3].toString()});
            
            System.out.println(Arrays.toString(new Object[]{eventArray.get(i).getTitle(), eventArray.get(i).formatDateTime(), eventArray.get(i).getLocation(), eventArray.get(i).getNote()}));
            System.out.println("iterate");
        }
    }
    
    public DefaultTableModel getTableModel(){
        return defTableModel;
    }
    
    public void deleteElement(int row){
        startMenu.setVisible(false);
        ArrayList<Double> keyArray = eventMap.toKeyList();
        
        eventMap.removeEvent(keyArray.get(row));
        startMenu.setVisible(true);
        popupConfirmRemove();
    }
}
