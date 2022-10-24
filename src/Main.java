import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Main extends JFrame implements ActionListener {
    private JPanel centerPanel;
    private JCheckBox cluster;
    private JCheckBox line;

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
        westPanel.setPreferredSize(new Dimension(100,500));

        JButton b1 = new JButton("Run");
        cluster = new JCheckBox("Cluster");
        line = new JCheckBox("Line");
        westPanel.add(cluster);
        westPanel.add(line);
        westPanel.add(b1);

        centerPanel = new DrawPanel();

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        ((DrawPanel)centerPanel).setColor(Color.BLACK);

        cluster.addActionListener(this);
        line.addActionListener(this);
        b1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Run")){
            if(cluster.isSelected()){
                System.out.println("cluster");
            }
            if(line.isSelected()){
                System.out.println("line");
            }
        }
    }
}