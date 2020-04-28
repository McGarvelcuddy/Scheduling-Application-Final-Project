package deliverable4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author reedm
 */
public class StartMenu extends JFrame {

    private final EventController eventController;
    private JPanel buttonPanel;
    private JLabel heading;
    private JPanel loadPanel;
    private JPanel tablePanel;
    private JFileChooser saver;
    private JTextField fileName;
    private JTable eventTable;
    private JScrollPane tableScroller;

    public StartMenu(EventController eventController) {
        this.eventController = eventController;
        initComponents();
    }

    private void initComponents() {
        setTitle("Main Menu");

        setSize(1200, 600);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        heading = new JLabel();
        heading.setText("Welcome To The Scheduling App!");
        heading.setFont(new Font("Courier", Font.PLAIN, 20));
        this.add(heading);
        tablePanel = new JPanel();
        fileName = new JTextField("data", 20);

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete Selected");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load Schedule File");

        JButton loadChooser = new JButton("Find file");

        loadPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        saver = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        DefaultTableModel model = eventController.getTableModel();

        if (model == null) {
            System.out.println("model");
        }

        eventTable = new JTable(model);

        if (eventTable == null) {
            System.out.println("table");
        }

        addButton.addActionListener(event -> eventController.openAdderRemover());
        saveButton.addActionListener(event -> eventController.saveData(fileName.getText()));
        loadButton.addActionListener(event -> eventController.loadData(fileName.getText()));
        loadChooser.addActionListener(event -> chooser());
        deleteButton.addActionListener(event -> delete());

        heading.add(fileName);
        heading.add(loadButton);
        tableScroller = new JScrollPane(eventTable);

        eventTable.setFillsViewportHeight(true);
        tableScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        loadPanel.add(fileName);
        loadPanel.add(loadChooser);
        loadPanel.add(loadButton);

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        loadPanel.add(saveButton);
        tableScroller.setPreferredSize(new Dimension(800, 300));
        tablePanel.add(tableScroller);

        if (tablePanel == null) {
            System.out.println("panel");
        }

        if (tableScroller == null) {
            System.out.println("scroller");
        }

        this.setContentPane(new JPanel(new BorderLayout()));
        this.getContentPane().add(buttonPanel, BorderLayout.EAST);
        this.getContentPane().add(heading, BorderLayout.NORTH);
        this.getContentPane().add(loadPanel, BorderLayout.SOUTH);
        this.getContentPane().add(tablePanel, BorderLayout.CENTER);
        this.pack();
    }

    private void chooser() {
        int item = saver.showOpenDialog(null);
        if (item == JFileChooser.APPROVE_OPTION) {
            fileName.setText(saver.getSelectedFile().getAbsolutePath());
        }
    }
    
    private void delete(){
        int selectedTableRow = eventTable.getSelectedRow();
        int selectedModelRow = eventTable.convertRowIndexToModel(selectedTableRow);
        if(selectedModelRow == -1){
            eventController.popupSelectionError();
        }else{
            this.eventController.deleteElement(selectedModelRow);
        }
    }
}
