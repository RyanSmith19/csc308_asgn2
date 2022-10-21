import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Main extends JFrame implements ActionListener {
    private JComboBox list;
    private ButtonGroup group;
    private JPanel centerPanel;

    public static void main(String[] args) {
        Main main = new Main();
        main.setSize(500, 500);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setVisible(true);
    }

    public Main(){
        super("My Paint App");

        JPanel westPanel = new JPanel();
        westPanel.setBackground(Color.LIGHT_GRAY);
        JButton b1 = new JButton("Run");
        westPanel.add(b1);

        centerPanel = new DrawPanel();
        centerPanel.setBackground(Color.GRAY);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        ((DrawPanel)centerPanel).setColor(Color.BLACK);

        list.addActionListener(this);
        b1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){

    }
}