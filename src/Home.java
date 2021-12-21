import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Home implements ActionListener {
    JFrame frame;
    JPanel title_panel;
    JPanel button_panel;
    JLabel textfield;
    JButton btnPvsP;
    JButton btnPvsCPU;
    Border whileLine;

    public Home() {
        frame = new JFrame();
        title_panel = new JPanel();
        button_panel = new JPanel();
        textfield = new JLabel();
        btnPvsP = new JButton();
        btnPvsCPU = new JButton();
        whileLine = BorderFactory.createLineBorder(Color.white, 2);
        setInterface();
    }

    private void setInterface() {
        frame.setTitle("TicTacToe by NamNP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setVisible(true);
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(255, 255, 255));
        textfield.setFont(new Font("Monaco", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Game Mode");
        textfield.setOpaque(true);
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);
        setButtons();
        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        frame.setLocationRelativeTo(null);
        title_panel.setBorder(whileLine);
        button_panel.setBorder(whileLine);
    }

    private void setButtons() {
        button_panel.setLayout(new GridLayout(2, 0));
        button_panel.add(btnPvsCPU);
        button_panel.add(btnPvsP);
        btnPvsP.setSize(800, 350);
        btnPvsP.addActionListener(this);
        btnPvsP.setFont(new Font("Monaco", Font.BOLD, 120));
        btnPvsP.setFocusable(false);
        btnPvsP.setBackground(new Color(25, 25, 25));
        btnPvsP.setForeground(new Color(255, 255, 255));
        btnPvsP.setText("2P");
        btnPvsCPU.setSize(800, 350);
        btnPvsCPU.addActionListener(this);
        btnPvsCPU.setFont(new Font("Monaco", Font.BOLD, 120));
        btnPvsCPU.setFocusable(false);
        btnPvsCPU.setBackground(new Color(25, 25, 25));
        btnPvsCPU.setForeground(new Color(255, 255, 255));
        btnPvsCPU.setText("1P");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnPvsP)) {
            frame.dispose();
            Mode2 mode2 = new Mode2();
        } else {
            frame.dispose();
            Mode1 mode1 = new Mode1();
        }
    }
}
