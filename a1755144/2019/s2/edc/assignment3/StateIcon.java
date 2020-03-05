import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class StateIcon extends JComponent implements StateListener {

    final public static int diameter = 80;
    final public static int offSet = 4;
    final public static int stroke = 2;
    final public static Color color = Color.RED;

    private int x;
    private int y;
    private boolean isInitial;
    private boolean isFinal;
    private boolean isCurrent;

    private boolean isSelected = false;
    private State state;

    public StateIcon(State state) {
        super();
        this.state = state;
        updateInfo();
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean setSelected(Boolean b) {
        return isSelected = b;
    }

    public void setInitial(boolean b) {
        state.setInitial(b);
    }

    public void setFinal(boolean b) {
        state.setFinal(b);
    }

    public void moveBy(int x, int y) {
        state.moveBy(x, y);
    }

    public State getState() {
        return this.state;
    }

    private void updateInfo() {
        this.x = state.getXpos();
        this.y = state.getYpos();
        this.isInitial = state.isInitial();
        this.isFinal = state.isFinal();
        this.isCurrent = state.isCurrent();
        setBounds(state.getXpos(), state.getYpos(), offSet + diameter, offSet + diameter);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(offSet + diameter, offSet + diameter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isFinal) {
            g.setColor(Color.BLACK);
            g.fillOval(offSet, offSet, diameter, diameter);
            g.setColor(getParent().getBackground());
            g.fillOval(offSet + stroke, offSet + stroke, diameter - (stroke * 2), diameter - (stroke * 2));
        }
        g.setColor(Color.BLACK);
        g.fillOval(offSet + (stroke * 2), offSet + (stroke * 2), diameter - (stroke * 2 * 2), diameter - (stroke * 2 * 2));
        g.setColor(getParent().getBackground());
        g.fillOval(offSet + (stroke * 3), offSet + (stroke * 3), diameter - (stroke * 2 * 3), diameter - (stroke * 2 * 3));
        if (isInitial) {
            try {
                BufferedImage img = ImageIO.read(new File("lightning.png"));
                g.drawImage(img, 0, 0, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (isSelected()) {
            g.setColor(color);
            g.fillOval(offSet + (stroke * 3), offSet + (stroke * 3), diameter - (stroke * 2 * 3), diameter - (stroke * 2 * 3));
        }
        if (isCurrent) {
            g.setColor(Color.BLACK);
            int shift = (int) (diameter * 0.4);
            g.fillOval(offSet + shift, offSet + shift, diameter - (shift * 2), diameter - (shift * 2));
        }
        g.setColor(Color.BLACK);
        g.drawString(state.getName(), offSet + (diameter / 2) - 10, offSet + (diameter / 2) - 10);
    }

    @Override
    public void StateHasChanged() {
        updateInfo();
    }
}
