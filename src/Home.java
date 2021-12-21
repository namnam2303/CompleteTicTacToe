import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class Mode2 implements ActionListener {
    JFrame frame;
    JPanel title_panel;
    JPanel button_panel;
    JLabel textfield;
    JButton[] buttons;
    boolean x_turn;
    Border whileLine;
    Border blackLine;
    private List<List> conditions = new ArrayList<List>();
    private List<Integer> o_Positions = new ArrayList<>();
    private List<Integer> x_Positions = new ArrayList<>();

    Mode2() {   // Constructor
        frame = new JFrame();
        title_panel = new JPanel();
        button_panel = new JPanel();
        textfield = new JLabel();
        buttons = new JButton[9];
        x_turn = true;
        whileLine = BorderFactory.createLineBorder(Color.white, 2);
        blackLine = BorderFactory.createLineBorder(Color.black, 5);
        conditions = new ArrayList<List>();
        o_Positions = new ArrayList<>();
        x_Positions = new ArrayList<>();
        setInterface();
        setConditions();
    }

    private void setInterface() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setTitle("TicTacToe by Namnp");
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(255, 255, 255));
        textfield.setFont(new Font("Monaco", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setOpaque(true);
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);
        button_panel.setLayout(new GridLayout(3, 3));
        setButtons();
        title_panel.add(textfield);
        title_panel.setBorder(whileLine);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        x_turn = true;
        textfield.setText("X turn");
    }

    private void setButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Monaco", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setForeground(new Color(255, 255, 255));
            buttons[i].setBackground(new Color(25, 25, 25));
        }
    }

    private void setConditions() {
        conditions.add(Arrays.asList(1, 2, 3));
        conditions.add(Arrays.asList(4, 5, 6));
        conditions.add(Arrays.asList(7, 8, 9));
        conditions.add(Arrays.asList(1, 4, 7));
        conditions.add(Arrays.asList(2, 5, 8));
        conditions.add(Arrays.asList(3, 6, 9));
        conditions.add(Arrays.asList(1, 5, 9));
        conditions.add(Arrays.asList(3, 5, 7));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (x_turn && buttons[i].getText().equals("")) {
                    setX_turn(buttons, i);
                }
                if (!x_turn && buttons[i].getText().equals("")) {
                    setO_turn(buttons, i);
                }
            }
        }
    }

    private void setO_turn(JButton[] buttons, int i) {
        buttons[i].setText("O");
        o_Positions.add(i + 1);
        x_turn = true;
        textfield.setText("X turn");
        if (checkStatus() == 1) {
            frame.setEnabled(false);
            JOptionPane.showMessageDialog(frame, "O wins.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        if (checkStatus() == 2) {
            draw();
        }
    }

    private void setX_turn(JButton[] buttons, int i) {
        buttons[i].setText("X");
        x_Positions.add(i + 1);
        x_turn = false;
        textfield.setText("O turn");
        if (checkStatus() == 0) {
            frame.setEnabled(false);
            JOptionPane.showMessageDialog(frame, "X wins.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        if (checkStatus() == 2) {
            draw();
        }
    }

    private int checkStatus() { //2-Hòa .0-X wins .1-O wins
        int count = 0;
        for (int i = 0; i < 9; i++) {
            count = !buttons[i].getText().equals("") ? ++count : count;
        }
        if (count == 9) {
            for (List conditon : conditions) {
                if (x_Positions.containsAll(conditon)) {
                    xWins((int) conditon.get(0), (int) conditon.get(1), (int) conditon.get(2));
                    return 0;   // X wins
                } else if (o_Positions.containsAll(conditon)) {
                    oWins((int) conditon.get(0), (int) conditon.get(1), (int) conditon.get(2));
                    return 1;   // O wins
                }
            }
            return 2;   // Hòa
        } else {
            for (List condition : conditions) {
                if (o_Positions.containsAll(condition)) {
                    oWins((int) condition.get(0), (int) condition.get(1), (int) condition.get(2));
                    return 1;
                }
                if (x_Positions.containsAll(condition)) {
                    xWins((int) condition.get(0), (int) condition.get(1), (int) condition.get(2));
                    return 0;
                }
            }
            return -1;  // Chưa tìm được người thắng
        }
    }


    public void xWins(int a, int b, int c) {
        buttons[a - 1].setBackground(Color.white);
        buttons[a - 1].setBorder(blackLine);
        buttons[a - 1].setForeground(new Color(255, 0, 0));
        buttons[b - 1].setBackground(Color.white);
        buttons[b - 1].setBorder(blackLine);
        buttons[b - 1].setForeground(new Color(255, 0, 0));
        buttons[c - 1].setBackground(Color.white);
        buttons[c - 1].setBorder(blackLine);
        buttons[c - 1].setForeground(new Color(255, 0, 0));
        textfield.setText("X wins");
        textfield.setBorder(blackLine);
        textfield.setBackground(new Color(255, 255, 255));
        textfield.setForeground(new Color(25, 25, 25));
    }

    public void oWins(int a, int b, int c) {
        buttons[a - 1].setBackground(Color.white);
        buttons[a - 1].setBorder(blackLine);
        buttons[a - 1].setForeground(new Color(255, 0, 0));
        buttons[b - 1].setBackground(Color.white);
        buttons[b - 1].setBorder(blackLine);
        buttons[b - 1].setForeground(new Color(255, 0, 0));
        buttons[c - 1].setBackground(Color.white);
        buttons[c - 1].setBorder(blackLine);
        buttons[c - 1].setForeground(new Color(255, 0, 0));
        textfield.setText("O wins");
        textfield.setBackground(new Color(255, 255, 255));
        textfield.setForeground(new Color(25, 25, 25));
    }

    private void draw() {
        for (JButton button : buttons) {
            button.setBackground(Color.white);
            button.setBorder(blackLine);
            button.setForeground(new Color(255, 0, 0));
        }
        textfield.setText("Draw");
        textfield.setBackground(new Color(255, 255, 255));
        textfield.setForeground(new Color(25, 25, 25));
        title_panel.setBorder(blackLine);
        frame.setEnabled(false);
        JOptionPane.showMessageDialog(frame, "Draw!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
