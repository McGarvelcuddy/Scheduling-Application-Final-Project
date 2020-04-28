package deliverable4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EventPopup extends JFrame implements ActionListener {

    private final EventController eventController;
    private final String messageContent;
    private JPanel messagePanel;
    private JPanel buttonPanel;
    private JButton okButton;

    public EventPopup(EventController eventController, String messageContent) {
        this.eventController = eventController;
        this.messageContent = messageContent;
        initComponents();
    }

    private void initComponents() {
        setTitle("Message");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        

        messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.add(new JLabel(this.messageContent));

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        okButton = new JButton("OK");
        okButton.addActionListener(this);
        buttonPanel.add(okButton);

        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(messagePanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        okButton = (JButton) event.getSource();
        this.dispose();

    }

}
