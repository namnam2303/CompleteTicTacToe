import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class Mode1 implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player_turn;
    Border whileLine = BorderFactory.createLineBorder(Color.white, 2);
    Border blackLine = BorderFactory.createLineBorder(Color.black, 5);
    private static List<List> conditions = new ArrayList<>();
    private static List<Integer> cpuPositions = new ArrayList<>();
    private static List<Integer> playerPositions = new ArrayList<>();
    private int randomNum;

    Mode1() {
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
        textfield.setOpaque(true);
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);
        button_panel.setLayout(new GridLayout(3, 3));
        frame.setTitle("TicTacToe by Namnp");
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Monaco", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setForeground(new Color(255, 255, 255));
            buttons[i].setBackground(new Color(25, 25, 25));
        }
        randomNum = random.nextInt(10);
        title_panel.add(textfield);
        title_panel.setBorder(whileLine);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        player_turn = randomNum % 2 == 0;
        textfield.setText(player_turn ? "Your turn" : "CPU's turn");
        setConditions();
        if (!player_turn) {
            int cpuPos = randomCpuPos();
            buttons[cpuPos - 1].setText("O");
            cpuPositions.add(cpuPos);
            player_turn = true;
            textfield.setText("Your turn");
        }
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
                if (buttons[i].getText() == "") {
                    buttons[i].setText("X");
                    playerPositions.add(i + 1);
                    player_turn = false;
                    if (end() == 0) {
                        frame.setEnabled(false);
                        JOptionPane.showMessageDialog(frame, "You win.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }
                    int cpuPos = randomCpuPos();
                    buttons[cpuPos - 1].setText("O");
                    cpuPositions.add(cpuPos);
                    player_turn = true;
                    textfield.setText("Your turn");
                    if (end() == 1) {
                        frame.setEnabled(false);
                        JOptionPane.showMessageDialog(frame, "CPU wins.", "Defeat", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }
                    if (end() == 2) {
                        for (JButton button : buttons) {
                            button.setBackground(Color.white);
                            button.setBorder(blackLine);
                            button.setForeground(new Color(255, 0, 0));
                        }
                        textfield.setText("Draw");
                        frame.setEnabled(false);
                        JOptionPane.showMessageDialog(frame, "Draw!", "Notification", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }
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
                if (playerPositions.containsAll(c)) {
                    xWins((int) c.get(0), (int) c.get(1), (int) c.get(2));
                    return 0;
                } else if (cpuPositions.containsAll(c)) {
                    oWins((int) c.get(0), (int) c.get(1), (int) c.get(2));
                    return 1;
                }
            }
            return 2;
        } else {
            for (List list : conditions) {
                if (cpuPositions.containsAll(list)) {
                    oWins((int) list.get(0), (int) list.get(1), (int) list.get(2));
                    return 1;
                }
                if (playerPositions.containsAll(list)) {
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
        textfield.setText("You win");
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
        textfield.setText("CPU wins");
        textfield.setBackground(new Color(255, 255, 255));
        textfield.setForeground(new Color(25, 25, 25));
    }

    private int randomCpuPos() {
        Random random = new Random();
        int cpuPos = 0;
        if (cpuPositions.size() >= 2) {
            for (List condition : conditions) {
                int countCpuPos = 0;
                for (int i = 0; i < condition.size(); i++) {
                    if (cpuPositions.contains(condition.get(i))) {
                        countCpuPos++;
                    }
                }
                if (countCpuPos == 2) {
                    for (int i = 0; i < condition.size(); i++) {
                        if (!cpuPositions.contains(condition.get(i))) {
                            cpuPos = checkPos((int) condition.get(i)) ? 0 : (int) condition.get(i);
                            if (cpuPos != 0) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (cpuPos == 0) {
            cpuPos = preventPlayerWin();
            if (cpuPos == 0) {
                while (checkPos(cpuPos) || cpuPos == 0) {
                    cpuPos = random.nextInt(9) + 1;
                }
            }
        }
        cpuPositions.add(cpuPos);
        return cpuPos;
    }

    private boolean checkPos(int position) {
        return playerPositions.contains(position) || cpuPositions.contains(position);
    }

    private int preventPlayerWin() {
        int cpuPos = 0;
        for (List condition : conditions) {
            int countPlayerPos = 0;
            for (int i = 0; i < condition.size(); i++) {
                if (playerPositions.contains(condition.get(i))) {
                    countPlayerPos++;
                }
            }
            if (countPlayerPos == 2) {
                for (int i = 0; i < condition.size(); i++) {
                    if (!playerPositions.contains(condition.get(i))) {
                        cpuPos = checkPos((int) condition.get(i)) ? 0 : (int) condition.get(i);
                        if (cpuPos != 0) {
                            return cpuPos;
                        }
                    }
                }
            }
        }
        return cpuPos;
    }

}
