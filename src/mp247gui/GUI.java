package mp247gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URI;
import java.util.*;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class GUI extends JFrame {

    public static ArrayList<Bet> bets = new ArrayList<>();
    public static ArrayList<Order> ordersList = new ArrayList<>();
    private JPanel mainPanel;
    private JTable orderTable;
    private JTable betTable;
    private JLabel panelText;

    public GUI() {
        JPanel leftPanel = new JPanel();
        orderTable = new JTable();
        betTable = new JTable();
        setSize(750, 415);
        setTitle("Mario Party 24/7 GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        leftPanel.setLayout(new GridLayout(3, 1));
        JPanel topLeftPanel = new JPanel();
        JPanel midLeftPanel = new JPanel();
        JPanel bottomLeftPanel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        JMenuItem oAuthChange = new JMenuItem("Change Name/OAuth");
        oAuthChange.addActionListener((ActionEvent e) -> {
            JDialog j = new JDialog();
            JLabel nameLabel = new JLabel("Name: ");
            JLabel oauthLabel = new JLabel("OAuth: ");
            JButton ok = new JButton("OK");
            JButton cancel = new JButton("Cancel");
            final JTextField nameField = new JTextField(Main.chat.getBotName(), 36);
            final JTextField oauthField = new JTextField(Main.chat.getoAuth(), 36);
            ok.addActionListener((ActionEvent ee) -> {
                Main.chat.setBotName(nameField.getText());
                Main.chat.setoAuth(oauthField.getText());
                Main.chat.disconnect();
                Main.chat.connect();
                j.setVisible(false);
                j.dispose();
            });
            cancel.addActionListener((ActionEvent ee) -> {
                j.setVisible(false);
                j.dispose();
            });
            JPanel bottom = new JPanel();
            JPanel main = new JPanel();
            j.setLayout(new BorderLayout());
            bottom.setLayout(new FlowLayout());
            main.setLayout(new FlowLayout());
            main.add(nameLabel);
            main.add(nameField);
            main.add(oauthLabel);
            main.add(oauthField);
            bottom.add(ok);
            bottom.add(cancel);
            j.add(main, BorderLayout.CENTER);
            j.add(bottom, BorderLayout.SOUTH);
            j.setSize(500, 130);
            j.setResizable(false);
            j.setModal(true);
            j.setVisible(true);
        });
        JMenuItem about = new JMenuItem("About");
        about.addActionListener((ActionEvent e) -> {
            JDialog j = new JDialog();
            JLabel text = new JLabel("Mario Party GUI " + Main.VERSION + " made by The_Chef1337");
            JButton button = new JButton();
            button.setText("<HTML><FONT color=\"#000099\"><U>Check me out on Github!</U></FONT></HTML>");
            button.addActionListener((ActionEvent ee) -> {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/robomaeyhem/"));
                    } catch (Exception ex) { /* TODO: error handling */ }
                } else { /* TODO: error handling */ }
            });
        });
        file.add(oAuthChange);
        file.add(exit);
        menuBar.add(file);
        this.setJMenuBar(menuBar);
        JButton bet = new JButton("Bet");
        JButton sell = new JButton("Sell");
        JButton cancel = new JButton("Cancel All");
        cancel.addActionListener((ActionEvent e) -> {
            Main.chat.sendMessage("#jtv", "/w mp247 cancel all");
            this.bets.clear();
            this.ordersList.clear();
            updateOrderTable();
            updateBetTable();
        });
        JButton checkOrders = new JButton("Update Bet/Order List");
        checkOrders.addActionListener((ActionEvent e) -> {
            Main.chat.sendMessage("#jtv", "/w mp247 orders");
            Main.chat.sendMessage("#jtv", "/w mp247 bets");
        });
        JButton getBalance = new JButton("Get Balance");
        getBalance.addActionListener((ActionEvent e) -> {
            Main.chat.sendMessage("#jtv", "/w mp247 balance");
        });
        JLabel qtyLabel = new JLabel("Quantity: ");
        JTextField qty = new JTextField(2);
        JLabel amountLabel = new JLabel("Amount ($): ");
        JTextField amt = new JTextField(3);
        JLabel character = new JLabel("Character:");
        JComboBox characterSelect = new JComboBox(Character.values());
        characterSelect.setSelectedIndex(0);
        bet.addActionListener((ActionEvent e) -> {
            if (!qty.getText().isEmpty() && !amt.getText().isEmpty()) {
                Character selected = (Character) characterSelect.getSelectedItem();
                String buyString = "/w mp247 buy " + selected + " $" + amt.getText() + " " + qty.getText();
                Main.chat.sendMessage("#jtv", buyString);
                Order b = new Order(buyString);
                this.addOrder(b);
                Main.chat.sendMessage("#jtv", "/w mp247 orders");
            }
        });
        sell.addActionListener((ActionEvent e) -> {
            if (!qty.getText().isEmpty() && !amt.getText().isEmpty()) {
                Character selected = (Character) characterSelect.getSelectedItem();
                String buyString = "/w mp247 sell " + selected + " $" + amt.getText() + " " + qty.getText();
                Main.chat.sendMessage("#jtv", buyString);
                Order b = new Order(buyString);
                this.addOrder(b);
                Main.chat.sendMessage("#jtv", "/w mp247 orders");
            }
        });
        topLeftPanel.add(checkOrders);
        topLeftPanel.add(getBalance);
        topLeftPanel.add(cancel);
        topLeftPanel.add(bet);
        topLeftPanel.add(sell);
        midLeftPanel.add(qtyLabel);
        midLeftPanel.add(qty);
        midLeftPanel.add(amountLabel);
        midLeftPanel.add(amt);
        //midLeftPanel.add(new JLabel("                          ")); //OneHand im da bes
        midLeftPanel.add(character);
        midLeftPanel.add(characterSelect);
        leftPanel.add(topLeftPanel);
        leftPanel.add(midLeftPanel);
        panelText = new JLabel("");
        bottomLeftPanel.add(panelText);
        leftPanel.add(bottomLeftPanel);
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(2, 1));
        right.add(new JScrollPane(orderTable));
        right.add(new JScrollPane(betTable));
        mainPanel.add(leftPanel);
        mainPanel.add(right);
        add(mainPanel);
        updateOrderTable();
        updateBetTable();
        setVisible(true);
    }

    public static ArrayList<Bet> getBets() {
        return bets;
    }

    public final void addBet(Bet bet) {
        bets.add(bet);
        updateBetTable();
    }

    public void removeBet(int id) {
        bets.remove(id);
        updateBetTable();
    }

    public ArrayList<Order> getOrdersList() {
        return ordersList;
    }

    public final void addOrder(Order order) {
        ordersList.add(order);
        updateOrderTable();
    }

    public Order getOrder(int id) {
        return ordersList.get(id);
    }

    public void clearBets() {
        bets.clear();
        updateBetTable();
    }

    public void clearOrders() {
        ordersList.clear();
        updateOrderTable();
    }

    public void removeOrder(int id) {
        ordersList.remove(id);
        updateOrderTable();
    }

    public final void updateBottomPanel(String text) {
        panelText.setText(text);
    }

    public final void updateBetTable() {
        String[] columnNames = {"Quantity", "Amount", "Character", "Type"};
        Object[][] data;
        data = new Object[GUI.bets.size()][columnNames.length];
        int index = 0;
        for (Bet el : GUI.bets) {
            data[index][0] = el.getQuantity();
            data[index][1] = el.getAmount();
            data[index][2] = el.getCharacter();
            data[index][3] = el.getType();
            index++;
        }
        betTable.setModel(new TableModel() {

            @Override
            public int getRowCount() {
                return data.length;
            }

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return columnNames[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return data[rowIndex][columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                data[rowIndex][columnIndex] = aValue;
            }

            @Override
            public void addTableModelListener(TableModelListener l) {
            }

            @Override
            public void removeTableModelListener(TableModelListener l) {
            }

        });
    }

    public final void updateOrderTable() {
        String[] columnNames = {"ID", "Quantity", "Amount", "Character", "Type", "Cancel"};
        Object[][] data;
        data = new Object[GUI.ordersList.size()][columnNames.length];
        int index = 0;
        for (Order el : GUI.ordersList) {
            data[index][0] = (el.isComplete() ? (el.getID() <= 0 ? el.getID() : "N/A") : el.getID());
            data[index][1] = el.getQuantity();
            data[index][2] = el.getAmount();
            data[index][3] = el.getCharacter();
            data[index][4] = el.getType();
            data[index][5] = "Cancel";
            index++;
        }
        orderTable.setModel(new TableModel() {

            @Override
            public int getRowCount() {
                return data.length;
            }

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return columnNames[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 5;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return data[rowIndex][columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                data[rowIndex][columnIndex] = aValue;
            }

            @Override
            public void addTableModelListener(TableModelListener l) {
            }

            @Override
            public void removeTableModelListener(TableModelListener l) {
            }

        });
        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                Main.chat.sendMessage("#jtv", "/w mp247 cancel " + Main.g.getOrdersList().get(modelRow).getID());
                Main.g.removeOrder(modelRow);
                updateOrderTable();
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(orderTable, delete, 5);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }
}
