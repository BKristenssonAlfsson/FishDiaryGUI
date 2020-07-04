package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Window {

    private GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public Window() {
        JFrame frame = new JFrame("Fish Diary");

        Container container = new Container();
        frame.add(container);

        BorderLayout window = new BorderLayout();
        container.setLayout(window);

        container.add(header(), BorderLayout.NORTH);
        container.add(diary(), BorderLayout.EAST);

        frame.setPreferredSize(new Dimension(1000,500));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel diary() {
        JPanel jPanel = new JPanel();
        String[][] data = new String[1][];
        String[] headers = { "DATE" , "FISH" , "WEIGHT (g)" , "LENGTH (cm)" , "PLACE" , "WEATHER" };

        DefaultTableModel model = new DefaultTableModel(data, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable jTable = new JTable(model);
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

        JButton loadUser = new JButton("Ladda användare");
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.01;
        jPanel.add(loadUser,gridBagConstraints);

        JButton addFish = new JButton("Lägg till fisk");
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        jPanel.add(addFish,gridBagConstraints);
        return jPanel;
    }
}
