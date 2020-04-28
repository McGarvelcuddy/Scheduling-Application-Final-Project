package deliverable4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author reedm
 */
public class EventView extends JFrame {

    private final EventController eventController;

    private final JTextField eventTitle = new JTextField(15);
    private final JTextField eventDate = new JTextField(15);
    private final JTextField eventLocation = new JTextField(15);
    private final JTextField eventNote = new JTextField(15);

    private JPanel inputPanel;
    private JPanel buttonPanel;

    public EventView(EventController controller) {
        this.eventController = controller;
        initComponents();
    }

    private void initComponents() {
        setTitle("Event Adder");
        setSize(400, 300);
        setLocationRelativeTo(null);

        eventTitle.setText("Event Title");
        eventDate.setText(eventController.getCurrentDateTime());
        eventLocation.setText("Location");
        eventNote.setText("Note");

        inputPanel = new JPanel(new GridLayout(5, 1));
        inputPanel.add(new JLabel("Title: "));
        inputPanel.add(eventTitle);
        inputPanel.add(new JLabel("Date(YYYY,M,d,h,m): "));
        inputPanel.add(eventDate);
        inputPanel.add(new JLabel("Location: "));
        inputPanel.add(eventLocation);
        inputPanel.add(new JLabel("Note: "));
        inputPanel.add(eventNote);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Add");

        addButton.addActionListener(event -> getNewEvent());

        buttonPanel.add(addButton);

        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public void getNewEvent() {
        String title = eventTitle.getText();
        String date = eventDate.getText();
        String location = eventLocation.getText();
        String note = eventNote.getText();
        Event newEvent = new Event(title, date, location, note);
        eventController.addNewEvent(newEvent);
    }


}
