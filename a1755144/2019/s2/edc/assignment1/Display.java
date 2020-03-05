import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display implements Gui{

    Controller controller;

    private JFrame frame;
    private JLabel label;
    private JPanel panel;
    private JButton button1;
    private JButton button2;

    public static String INSERTCOIN = "Insert coin";
    public static final String PRESSGO = "Press GO!";
    public static final String WAIT = "Wait...";

    public Display() {
        frame = new JFrame("ReactionMachine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        label = new JLabel();
        panel = new JPanel();
        button1 = new JButton("INSERT COIN");
        button2 = new JButton("GO/STOP");
        panel.add(button1);
        panel.add(button2);
        frame.getContentPane().add(BorderLayout.CENTER, label);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);
    }

    @Override
    public void connect(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void init() {
        setDisplay(INSERTCOIN);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.coinInserted();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goStopPressed();
            }
        });
    }

    @Override
    public void setDisplay(String s) {
        this.label.setText(s);
    }


    public String getContent() {
        return label.getText();
    }

//    public static void main(String[] args) {
//        Display display = new Display();
//        display.init();
//    }
}
