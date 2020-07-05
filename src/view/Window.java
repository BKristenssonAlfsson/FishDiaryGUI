package view;

import model.Fish;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Window {

    private final GridBagConstraints gridBagConstraints = new GridBagConstraints();

    private final JButton loadUser = new JButton("Load User");
    private final JButton addFish = new JButton("Add Fish");
    private final JLabel user = new JLabel("");
    private JTable jTable;
    private DefaultTableModel tableModel;

    public Window() {
        JFrame frame = new JFrame("Fish Diary");
        Container container = new Container();
        frame.add(container);

        BorderLayout window = new BorderLayout();
        container.setLayout(window);

        container.add(header(), BorderLayout.NORTH);
        container.add(diary(), BorderLayout.EAST);
        container.add(user(), BorderLayout.WEST);

        frame.setPreferredSize(new Dimension(1000,500));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel user() {
        JPanel jPanel = new JPanel(new GridBagLayout());

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        JLabel username = new JLabel("User: ");
        jPanel.add(username);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2;
        jPanel.add(user);


/*        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2;
        JLabel userImage = new JLabel();
        userImage.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        jPanel.add(userImage);*/
        return jPanel;
    }

    private JPanel diary() {
        JPanel jPanel = new JPanel();

        jTable = new JTable(getTableModel());
        jTable.setPreferredSize(new Dimension(600,300));
        JScrollPane jScrollPane = new JScrollPane(jTable);

        jPanel.add(jScrollPane);
        return jPanel;
    }

    public void addRow() {

    }

    private JPanel header() {
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(1000,50));
        jPanel.setLayout(new GridBagLayout());

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        JTextField jTextField = new JTextField("Fishing Diary");
        jTextField.setBorder(BorderFactory.createEmptyBorder());
        jTextField.setEditable(false);
        jPanel.add(jTextField,gridBagConstraints);


        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.01;
        jPanel.add(loadUser,gridBagConstraints);

        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        jPanel.add(addFish,gridBagConstraints);
        return jPanel;
    }

    public void loadUser(ActionListener act) {
        loadUser.addActionListener(act);
    }

    public void addFish(ActionListener act) {
        addFish.addActionListener(act);
    }

    public void popupUsers(List<String> users) {
        JPanel jPanel = new JPanel();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (String user : users ) {
            model.addElement(user);
        }

        JComboBox<String> jComboBox = new JComboBox<>(model);

        jPanel.add(jComboBox);

        int result = JOptionPane.showConfirmDialog(null, jPanel, "Registered Users", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (result) {
            case JOptionPane.OK_OPTION:
                displayUser(Objects.requireNonNull(jComboBox.getSelectedItem()));
                break;
            case JOptionPane.CANCEL_OPTION:
                System.out.println("You failed to load a user");
                break;
            default:

        }
    }

    public void displayUser(Object selectedItem) {
        this.user.setText(selectedItem.toString());
    }

    public String getUser() {
        return this.user.getText();
    }

    public void updateDiary(Map<Integer, Fish> fishes ) {
        this.tableModel.setRowCount(0);
        fishes.forEach((key, value) -> tableModel.addRow(new Object[]{"2020", value.getName(), value.getWeight(), value.getLength(), "Gothenburg", "Sunny"}));


    }

    private DefaultTableModel getTableModel() {
        String[][] data = new String[0][];
        String[] headers = { "DATE" , "FISH" , "WEIGHT (g)" , "LENGTH (cm)" , "PLACE" , "WEATHER" };

        tableModel = new DefaultTableModel(data, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return tableModel;
    }
}

