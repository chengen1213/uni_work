import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;

import static java.lang.Math.*;

public class TransitionIcon extends JComponent implements TransitionListener {

    final private int radius = (int) (StateIcon.diameter * 0.5);
    final private int offSet = +StateIcon.offSet + radius;
    private State fromState;
    private State toState;
    private Circle from;
    private Circle to;
    private int width;
    private int height;
    private int leftY;
    private int rightY;
    private double controlX;
    private double controlY;
    private double boundaryX1;
    private double boundaryY1;
    private double boundaryX2;
    private double boundaryY2;
    private double random = (int) (random() * radius);
    private boolean direction;

    private Transition transition;

    public TransitionIcon(Transition transition) {
        super();
        this.transition = transition;
        updateInfo();
    }

    public void calculateCoordinates() {
        int midX = width / 2;
        int midY = (leftY + rightY) / 2;
        int deltaY = width;
        int deltaX = leftY - rightY;
        double opposite = sqrt((deltaX ^ 2) + (deltaY ^ 2));
        double sin = opposite / (deltaX == 0 ? 1 : deltaX);
        double cos = opposite / (deltaY == 0 ? 1 : deltaY);
        double absSin = abs(sin);
        double absCos = abs(cos);
        if (absSin > absCos) {
            if (absSin == 0)
                absSin = 1;
            sin = sin / absSin;
            cos = cos / absSin;
        } else {
            if (absCos == 0)
                absCos = 1;
            sin = sin / absCos;
            cos = cos / absCos;
        }
        controlX = midX + (random * cos);
        controlY = midY - abs(random * sin);
        double distance = sqrt((controlX * controlX) + (controlY * controlY));
        if (distance == 0)
            distance = 1;
        double proportion = radius / distance;
        if (proportion > 1)
            proportion = 0;
        boundaryX1 = 0 + (controlX * proportion);
        boundaryY1 = leftY + (controlY * proportion);
        boundaryX2 = controlX + ((width - controlX) * (1 - proportion));
        boundaryY2 = controlY + ((rightY - controlY) * (1 - proportion));
    }

    private void updateInfo() {

        fromState = transition.fromState();
        toState = transition.toState();

        from = new Circle(fromState.getXpos() + offSet, fromState.getYpos() + offSet, radius);
        to = new Circle(toState.getXpos() + offSet, toState.getYpos() + offSet, radius);

        width = abs(from.x - to.x);
        height = abs(from.y - to.y) + radius;

        int boundX = from.x > to.x ? to.x : from.x;
        int boundY = from.y > to.y ? to.y - radius : from.y - radius;

        setBounds(boundX, boundY, width + radius, height);

        if (from.x > to.x) {
            direction = false;
            if (from.y > to.y) {
                leftY = radius;
                rightY = height;
            } else {
                leftY = height;
                rightY = radius;
            }
        } else {
            if (from.y > to.y) {
                leftY = height;
                rightY = radius;
            } else {
                leftY = radius;
                rightY = height;
            }
        }

        if (from.x == boundX && from.y == (boundY + leftY))
            direction = true;
        else
            direction = false;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        String eventName = transition.eventName();
        g.drawString(eventName == null ? "?" : eventName, (int) controlX, (int) controlY);
        Graphics2D g2 = (Graphics2D) g;
        QuadCurve2D q = new QuadCurve2D.Float();
        calculateCoordinates();
        q.setCurve(0, leftY, controlX, controlY, width, rightY);
        g2.draw(q);
        if (direction)
            g2.drawString("to", (int) boundaryX2, (int) boundaryY2);
        else
            g2.drawString("to", (int) boundaryX1, (int) boundaryY1);

//        g.drawString("a", (int)controlX, (int)controlY);
//        g2.drawString("a",(int)boundaryX1,(int)boundaryY1);
//        g.drawLine(0, leftY, width, rightY);
    }

    @Override
    public void TransitionHasChanged() {
        updateInfo();
    }

    private class Circle {

        public int x;
        public int y;
        public int radius;

        public Circle(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    }
}
