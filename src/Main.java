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

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.LIGHT_GRAY);
        JButton b1 = new JButton("Undo");
        JButton b2 = new JButton("Erase");
        southPanel.add(b1);
        southPanel.add(b2);

        JPanel westPanel = new JPanel();
        westPanel.setBackground(Color.GRAY);
        String[] colors = { "Black", "Red", "Blue", "Green", "Yellow", "Orange", "Pink" };
        list = new JComboBox(colors);
        JRadioButton rectangle = new JRadioButton("Rectangle");
        JRadioButton circle = new JRadioButton("Circle");
        JRadioButton arc = new JRadioButton("Arc");
        rectangle.setSelected(true);
        group = new ButtonGroup();
        group.add(rectangle);
        group.add(circle);
        group.add(arc);
        GridLayout grid = new GridLayout(7, 1);
        westPanel.setLayout(grid);
        westPanel.add(list);
        westPanel.add(rectangle);
        westPanel.add(circle);
        westPanel.add(arc);

        centerPanel = new DrawPanel();
        centerPanel.setBackground(Color.CYAN);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        ((DrawPanel)centerPanel).setColor(Color.BLACK);

        list.addActionListener(this);
        rectangle.addActionListener(this);
        circle.addActionListener(this);
        arc.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    private Color colorCombo(){
        switch((String)list.getSelectedItem()) {
            case "Red":
                return Color.red;
            case "Blue":
                return Color.blue;
            case "Green":
                return Color.green;
            case "Yellow":
                return Color.yellow;
            case "Orange":
                return Color.orange;
            case "Pink":
                return Color.pink;
            default:
                return Color.BLACK;
        }
    }

    public void actionPerformed(ActionEvent e){
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("Circle") || e.getActionCommand().equals("Rectangle") || e.getActionCommand().equals("Arc")){
            ((DrawPanel)centerPanel).setShape(e.getActionCommand());
        } else if (e.getActionCommand().equals("comboBoxChanged")) { // color change
            ((DrawPanel)centerPanel).setColor(colorCombo());
        } else if (e.getActionCommand().equals("Undo")) { // undo
            ((DrawPanel)centerPanel).popFromStack();
        } else if (e.getActionCommand().equals("Erase")) { // erase
            ((DrawPanel)centerPanel).emptyStack();
        }
    }
}