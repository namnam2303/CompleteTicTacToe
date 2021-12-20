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
    Border whileLine = BorderFactory.createLineBorder(Color.white, 2);
    Border blackLine = BorderFactory.createLineBorder(Color.black, 3);
    private  List<List> conditions = new ArrayList<>();
    private  List<Integer> cpuPositions = new ArrayList<>();
    private  List<Integer> playerPositions = new ArrayList<>();

    Mode1() {   // Constructor
        setInterface();
        setConditions();
        firstTurn();
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
        textfield.setText("Your turn");
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);
        button_panel.setLayout(new GridLayout(3, 3));
        setButtons();
        title_panel.add(textfield);
        title_panel.setBorder(whileLine);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
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
    private void firstTurn() {  // Máy đánh trước hay người đánh trước
        int randomNum = random.nextInt(10);
        if (randomNum % 2 == 0) {     // Máy đánh trước
            int cpuPos = randomCpuPos();
            buttons[cpuPos - 1].setText("O");
            cpuPositions.add(cpuPos);
            textfield.setText("Your turn");
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
            if (e.getSource() == buttons[i] && buttons[i].getText().equals("")) {
                    setPlayer_turn(buttons, i);
                    setCpu_Turn(buttons);
                }
            }
        }
    private void setCpu_Turn(JButton[] buttons) {
        int cpuPos = randomCpuPos();
        buttons[cpuPos - 1].setText("O");
        cpuPositions.add(cpuPos);
        if (checkStatus() == 1) {
            frame.setEnabled(false);
            JOptionPane.showMessageDialog(frame, "CPU wins.", "Defeat", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        if(checkStatus() == 2) {
            draw();
        }
    }
    private void setPlayer_turn(JButton[] buttons, int i) {
        buttons[i].setText("X");
        playerPositions.add(i + 1);
        if (checkStatus() == 0) {
            frame.setEnabled(false);
            JOptionPane.showMessageDialog(frame, "You win.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        if(checkStatus() == 2) {
            draw();
        }
    }

    private int checkStatus() { //2- Hòa    0- Player win   1- Cpu wins
        int count = 0;
        for (int i = 0; i < 9; i++) {
            count = !buttons[i].getText().equals("") ? ++count : count;
        }
        if (count == 9) {
            for (List condition : conditions) {
                if (playerPositions.containsAll(condition)) {
                    playerWin((int) condition.get(0), (int) condition.get(1), (int) condition.get(2));
                    return 0;
                } else if (cpuPositions.containsAll(condition)) {
                    cpuWins((int) condition.get(0), (int) condition.get(1), (int) condition.get(2));
                    return 1;
                }
            }
            return 2;
        } else {
            for (List condion : conditions) {
                if (cpuPositions.containsAll(condion)) {
                    cpuWins((int) condion.get(0), (int) condion.get(1), (int) condion.get(2));
                    return 1;
                }
                if (playerPositions.containsAll(condion)) {
                    playerWin((int) condion.get(0), (int) condion.get(1), (int) condion.get(2));
                    return 0;
                }
            }
            return -1;
        }
    }


    private void playerWin(int a, int b, int c) {
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
        title_panel.setBorder(blackLine);
    }

    private void cpuWins(int a, int b, int c) {
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
        title_panel.setBorder(blackLine);
    }

    private void draw() {   //  trạng thái hòa
        for (JButton button : buttons) {
            button.setBackground(Color.white);
            button.setBorder(blackLine);
            button.setForeground(new Color(255, 0, 0));
        }
        textfield.setText("Draw");
        frame.setEnabled(false);
        JOptionPane.showMessageDialog(frame, "Draw", "Notification", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private int randomCpuPos() {
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
                                cpuPositions.add(cpuPos);
                                return cpuPos;  // Tìm được điều kiện thắng
                            }
                        }
                    }
                }
            }
        }
        cpuPos = preventPlayerWin();
        while (checkPos(cpuPos) || cpuPos == 0) {
            cpuPos = random.nextInt(9) + 1;
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
