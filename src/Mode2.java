import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class Mode2 implements ActionListener {
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    Border whileLine = BorderFactory.createLineBorder(Color.white, 2);
    Border blackLine = BorderFactory.createLineBorder(Color.black, 5);
    private static List<List> conditions = new ArrayList<List>();
    private static List<Integer> o_Positions = new ArrayList<>();
    private static List<Integer> x_Positions = new ArrayList<>();

    Mode2() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(255, 255, 255));
        textfield.setFont(new Font("Monaco", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);
        button_panel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Monaco", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setForeground(new Color(255, 255, 255));
            buttons[i].setBackground(new Color(25, 25, 25));
        }
        title_panel.add(textfield);
        title_panel.setBorder(whileLine);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        player1_turn = true;
        textfield.setText("X turn");
        setConditions();
    }

    public static void setConditions() {
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
                if (player1_turn && buttons[i].getText() == "") {
                    buttons[i].setText("X");
                    x_Positions.add(i + 1);
                    player1_turn = false;
                    textfield.setText("O turn");
                    if(end() == 0) {
                        frame.setEnabled(false);
                        JOptionPane.showMessageDialog(frame, "X wins.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }
                }
                if (!player1_turn && buttons[i].getText() == "") {
                    buttons[i].setText("O");
                    o_Positions.add(i + 1);
                    player1_turn = true;
                    textfield.setText("X turn");
                    if(end() == 1) {
                        frame.setEnabled(false);
                        JOptionPane.showMessageDialog(frame, "O wins.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }
                }
                if(end() == 2) {
                    frame.setEnabled(false);
                    JOptionPane.showMessageDialog(frame, "Draw!", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }
            }
        }
    }

    private int end() {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            count = !buttons[i].getText().equals("") ? ++count : count;
        }
        if (count == 9) {
            for (List c : conditions) {
                if (x_Positions.containsAll(c)) {
                    xWins((int) c.get(0), (int) c.get(1), (int) c.get(2));
                    return 0;
                } else if (o_Positions.containsAll(c)) {
                    oWins((int) c.get(0), (int) c.get(1), (int) c.get(2));
                    return 1;
                }
            }
            return 2;
        } else {
            for (List list : conditions) {
                if (o_Positions.containsAll(list)) {
                    oWins((int) list.get(0), (int) list.get(1), (int) list.get(2));
                    return 1;
                }
                if (x_Positions.containsAll(list)) {
                    xWins((int) list.get(0), (int) list.get(1), (int) list.get(2));
                    return 0;
                }
            }
            return -1;
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
        buttons[a - 1].setForeground(new Color(255, 0,0));
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
}