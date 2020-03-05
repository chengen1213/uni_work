import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FsaEditor {

    FsaImpl fsa;
    FsaReaderWriter fsaReaderWriter;

    JFrame frame;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    FsaPanel fsaPanel;
    JPanel panel;
    JButton button;
    JTextField textField;
    JLabel label;

    public FsaEditor() {

        fsa = new FsaImpl();
        fsaReaderWriter = new FsaReaderWriter();

        frame = new JFrame("FsaEditor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        fsaPanel = new FsaPanel(fsa);
        fsa.addListener(fsaPanel);
        panel = new JPanel();
        button = new JButton("Reset");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fsa.reset();
                String text = fsa.isRecognised() ? "Acceptable" : "Not Acceptable";
                label.setText("Status: " + text);
                fsaPanel.repaint();
            }
        });
        panel.add(button);
        button = new JButton("Step");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = textField.getText();
                if (fsa.isValidEventName(eventName)) {
                    fsa.step(eventName);

                    String text = fsa.isRecognised() ? "Acceptable" : "Not Acceptable";
                    label.setText("Status: " + text);
                } else
                    JOptionPane.showMessageDialog(frame, "Invalid event name!", "Error", JOptionPane.ERROR_MESSAGE);
                fsaPanel.repaint();
            }
        });
        panel.add(button);
        label = new JLabel("Event Name: ");
        panel.add(label);
        textField = new JTextField(10);
        panel.add(textField);
        label = new JLabel("Status: N/A");
        panel.add(label);

        menuBar = new JMenuBar();
        // First Menu
        menu = new JMenu("File");
        menuBar.add(menu);  // the menu-bar adds this menu

        menu.add(openItem());
        menu.add(saveItem());
        menu.add(quitItem());

        // Second Menu
        menu = new JMenu("Edit");
        menuBar.add(menu);  // the menu-bar adds this menu

        menu.add(newStateItem()); // the menu adds this item
        menu.add(newTransitionItem()); // the menu adds this item

        menuItem = new JMenuItem("Set initial");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component component : fsaPanel.getComponents()) {
                    if (component instanceof StateIcon) {
                        StateIcon stateIcon = (StateIcon) component;
                        if (stateIcon.isSelected()) {
                            stateIcon.setInitial(true);
                        }
                    }
                }
                fsaPanel.repaint();
            }
        });
        menu.add(menuItem); // the menu adds this item

        menuItem = new JMenuItem("Unset initial");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component component : fsaPanel.getComponents()) {
                    if (component instanceof StateIcon) {
                        StateIcon stateIcon = (StateIcon) component;
                        if (stateIcon.isSelected()) {
                            stateIcon.setInitial(false);
                        }
                    }
                }
                fsaPanel.repaint();
            }
        });
        menu.add(menuItem); // the menu adds this item

        menuItem = new JMenuItem("Set final");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component component : fsaPanel.getComponents()) {
                    if (component instanceof StateIcon) {
                        StateIcon stateIcon = (StateIcon) component;
                        if (stateIcon.isSelected()) {
                            stateIcon.setFinal(true);
                        }
                    }
                }
                fsaPanel.repaint();
            }
        });
        menu.add(menuItem); // the menu adds this item

        menuItem = new JMenuItem("Unset final");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component component : fsaPanel.getComponents()) {
                    if (component instanceof StateIcon) {
                        StateIcon stateIcon = (StateIcon) component;
                        if (stateIcon.isSelected()) {
                            stateIcon.setFinal(false);
                        }
                    }
                }
                fsaPanel.repaint();
            }
        });
        menu.add(menuItem); // the menu adds this item

        menuItem = new JMenuItem("Delete");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component component : fsaPanel.getComponents()) {
                    if (component instanceof StateIcon) {
                        StateIcon stateIcon = (StateIcon) component;
                        if (stateIcon.isSelected()) {
                            fsa.removeState(stateIcon.getState());
                        }
                    }
                }
            }
        });
        menu.add(menuItem); // the menu adds this item

//        fsaPanel.add(button1);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, fsaPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);
    }

    private JMenuItem openItem() {
        JMenuItem menuItem = new JMenuItem("Open...");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("./"));
                int result = fileChooser.showOpenDialog(menuItem);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        fsa = new FsaImpl();
                        fsaPanel.reset(fsa);
                        fsa.addListener(fsaPanel);
                        fsaReaderWriter.read(new FileReader(selectedFile), fsa);
                        fsaPanel.repaint();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });
        KeyStroke keyStrokeToOpen
                = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        menuItem.setAccelerator(keyStrokeToOpen);
        return menuItem;
    }

    private JMenuItem saveItem() {
        JMenuItem menuItem = new JMenuItem("Save as...");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("./"));
                int retrieval = fileChooser.showSaveDialog(menuItem);
                if (retrieval == JFileChooser.APPROVE_OPTION) {
                    try {
                        String fileName = fileChooser.getSelectedFile().getName();
                        FileWriter fw;
                        if (fileName.endsWith(".fsa"))
                            fw = new FileWriter(fileName);
                        else
                            fw = new FileWriter(fileName + ".fsa");
                        fsaReaderWriter.write(fw, fsa);
                        fw.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                        ex.printStackTrace();
                    }
                }
            }
        });
        KeyStroke keyStrokeToSave
                = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        menuItem.setAccelerator(keyStrokeToSave);
        return menuItem;
    }

    private JMenuItem quitItem() {
        JMenuItem menuItem = new JMenuItem("Quit");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        KeyStroke keyStrokeToClose
                = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
        menuItem.setAccelerator(keyStrokeToClose);
        return menuItem;
    }

    private JMenuItem newStateItem() {
        JMenuItem menuItem = new JMenuItem("New state");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) JOptionPane.showInputDialog(
                        frame,
                        "Please enter a state name:",
                        "New State",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "name");

                if ((name != null) && (name.length() > 0)) {
                    try {
                        fsaPanel.newState(name);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        return menuItem;
    }

    private JMenuItem newTransitionItem() {
        JMenuItem menuItem = new JMenuItem("New transition");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) JOptionPane.showInputDialog(
                        frame,
                        "Please enter a event name:",
                        "New Transition",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "name");

                if ((name != null) && (name.length() > 0)) {
                    if (fsa.isValidEventName(name))
                        fsaPanel.newTransition(name);
                    else
                        JOptionPane.showMessageDialog(frame, "Invalid transition name!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return menuItem;
    }

    public static void main(String[] args) {
        FsaEditor fsaEditor = new FsaEditor();
    }
}
