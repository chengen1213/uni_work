import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.Math.abs;

public class FsaPanel extends JPanel implements FsaListener {

    final public static int IDLE = 0;
    final public static int SELECTING = 1;
    final public static int DRAGGING = 2;
    final public static int CREATING_STATE = 3;
    final public static int SELECTING_FROM = 4;
    final public static int SELECTING_TO = 5;
    final private int shift = StateIcon.offSet + (int) (StateIcon.diameter * 0.5);


    private FsaImpl fsa;
    private Map<State, StateIcon> stateIconMap = new HashMap<>();
    private Map<Transition, TransitionIcon> transitionIconMap = new HashMap<>();
    private SelectionComponent selectionComponent;
    private DashedLineComponent dashedLineComponent;

    private int state = IDLE;
    private int beginX = 0;
    private int beginY = 0;
    private int endX = 0;
    private int endY = 0;
    private State newState;
    private State fromState;
    private State toState;
    private String transitionName;

    public FsaPanel(FsaImpl fsa) {
        super();
        this.setLayout(null);
        selectionComponent = new SelectionComponent();
        dashedLineComponent = new DashedLineComponent();
        reset(fsa);
        addMouseListener(new PanelMouseListener());
        addMouseMotionListener(new PanelMouseMotionListener());
    }

    public void reset(FsaImpl fsa) {
        this.fsa = fsa;
        stateIconMap.clear();
        transitionIconMap.clear();
        this.removeAll();
        this.add(selectionComponent);
        this.add(dashedLineComponent, JLayeredPane.POPUP_LAYER);
        this.repaint();
    }

    public void newState(String name) throws Exception {
        newState = fsa.newState(name, 0, 0);
        beginX = 0;
        beginY = 0;
        state = CREATING_STATE;
    }

    public void newTransition(String name) {
        transitionName = name;
        state = SELECTING_FROM;
    }

    @Override
    public void statesChanged() {
        Set<State> newStates = fsa.getStates();
        int before = stateIconMap.size();
        int after = newStates.size();
        if (after > before) {
            for (State state : newStates) {
                if (!stateIconMap.containsKey(state)) {
                    StateIcon stateIcon = new StateIcon(state);
                    stateIcon.addMouseListener(new ComponentMouseListener());
                    stateIcon.addMouseMotionListener(new ComponentMouseMotionListener());
                    state.addListener(stateIcon);
                    stateIconMap.put(state, stateIcon);
                    this.add(stateIcon);
                }
            }
        } else if (after < before) {
            Set<State> copy = new HashSet(stateIconMap.keySet());
            for (State state : copy) {
                if (!newStates.contains(state)) {
                    this.remove(stateIconMap.get(state));
                    stateIconMap.remove(state);
                }
            }
        }
        this.repaint();
    }

    @Override
    public void transitionsChanged() {
        Set<Transition> transitions = fsa.getTransitions();
        int before = transitionIconMap.size();
        int after = transitions.size();
        if (after > before) {
            for (Transition transition : transitions) {
                if (!transitionIconMap.containsKey(transition)) {
                    TransitionIcon transitionIcon = new TransitionIcon(transition);
                    transition.addListener(transitionIcon);
                    transitionIconMap.put(transition, transitionIcon);
                    this.add(transitionIcon);
                }
            }
        } else if (before > after) {
            Set<Transition> copy = new HashSet<>(transitionIconMap.keySet());
            for (Transition transition : copy) {
                if (!transitions.contains(transition)) {
                    this.remove(transitionIconMap.get(transition));
                    transitionIconMap.remove(transition);
                }
            }
        }
        this.repaint();
    }

    @Override
    public void otherChanged() {
    }

    private class ComponentMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (state == IDLE) {
                if (e.getSource() instanceof StateIcon) {
                    StateIcon eventComponent = (StateIcon) e.getSource();
                    if (!eventComponent.isSelected()) {
                        Component[] components = FsaPanel.this.getComponents();
                        for (Component component : components) {
                            if (component instanceof StateIcon) {
                                StateIcon stateIcon = (StateIcon) component;
                                stateIcon.setSelected(false);
                            }
                        }
                        eventComponent.setSelected(true);
                    } else
                        eventComponent.setSelected(false);
                }
            }
            if (state == CREATING_STATE) {
                state = IDLE;
            }
            if (state == SELECTING_FROM) {
                if (e.getSource() instanceof StateIcon) {
                    StateIcon stateIcon = (StateIcon) e.getSource();
                    fromState = stateIcon.getState();
                    beginX = e.getComponent().getX() + shift;
                    beginY = e.getComponent().getY() + shift;
                    state = SELECTING_TO;
                    return;
                }
            }
            if (state == SELECTING_TO) {
                dashedLineComponent.hideLine();
                if (e.getSource() instanceof StateIcon) {
                    StateIcon stateIcon = (StateIcon) e.getSource();
                    toState = stateIcon.getState();
                    try {
                        fsa.newTransition(fromState, toState, transitionName);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FsaPanel.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    fromState = null;
                    toState = null;
                    transitionName = null;
                    state = IDLE;
                }
            }
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (state == IDLE) {
                if (e.getSource() instanceof StateIcon) {
                    state = DRAGGING;
                    beginX = e.getX();
                    beginY = e.getY();
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (state == SELECTING || state == DRAGGING) {
                state = IDLE;
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private class ComponentMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (state == DRAGGING) {
                if (e.getSource() instanceof StateIcon && ((StateIcon) e.getSource()).isSelected()) {
                    int moveX = e.getX() - beginX;
                    int moveY = e.getY() - beginY;
                    for (Component component : FsaPanel.this.getComponents()) {
                        if (component instanceof StateIcon) {
                            StateIcon stateIcon = (StateIcon) component;
                            if (stateIcon.isSelected()) {
                                stateIcon.moveBy(moveX, moveY);
                            }
                        }
                    }
                }
            }
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (state == CREATING_STATE) {
                int x = e.getComponent().getX();
                int y = e.getComponent().getY();
                ((FsaImpl.StateImpl) newState).moveTo(x + e.getX() - shift, y + e.getY() - shift);
            }
            if (state == SELECTING_TO) {
                int x = e.getComponent().getX();
                int y = e.getComponent().getY();
                dashedLineComponent.setLine(beginX, beginY, x + e.getX(), y + e.getY());
            }
            repaint();
        }
    }

    private class PanelMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (state == IDLE) {
                state = SELECTING;
                FsaPanel fsaPanel = (FsaPanel) e.getSource();
                beginX = e.getX();
                beginY = e.getY();
                Component[] components = fsaPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof StateIcon) {
                        StateIcon stateIcon = (StateIcon) component;
                        stateIcon.setSelected(false);
                    }
                }
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (state == SELECTING)
                selectionComponent.hideSelection();
            if (state == SELECTING || state == DRAGGING)
                state = IDLE;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (state == CREATING_STATE) {
                beginX = e.getX();
                beginY = e.getY();
                newState.moveBy(beginX, beginY);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private class PanelMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            FsaPanel fsaPanel = (FsaPanel) e.getSource();
            if (state == SELECTING) {
                endX = e.getX();
                endY = e.getY();
                selectionComponent.setSelectionBox(endX > beginX ? beginX : endX, endY > beginY ? beginY : endY, abs(endX - beginX), abs(endY - beginY));
                Component[] components = fsaPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof StateIcon) {
                        StateIcon stateIcon = (StateIcon) component;
                        if (selectionComponent.getBounds().intersects(component.getBounds()))
                            stateIcon.setSelected(true);
                        else
                            stateIcon.setSelected(false);
                    }
                }
            }
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (state == CREATING_STATE) {
                ((FsaImpl.StateImpl) newState).moveTo(e.getX(), e.getY());
            }
            if (state == SELECTING_TO) {
                dashedLineComponent.setLine(beginX, beginY, e.getX(), e.getY());
            }
            repaint();
        }
    }

    private class SelectionComponent extends JComponent {

        SelectionBox selectionBox = new SelectionBox();

        public SelectionComponent() {
            super();
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setVisible(false);
        }

        private void setSelectionBox(int x, int y, int weight, int height) {
            selectionBox.setWidth(weight);
            selectionBox.setHeight(height);
            setBounds(x, y, weight, height);
            setVisible(true);
        }

        private void hideSelection() {
            selectionBox.setWidth(1);
            selectionBox.setHeight(1);
            setBounds(0, 0, 1, 1);
            setVisible(false);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(selectionBox.getWidth(), selectionBox.getHeight());
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            selectionBox.paintSquare(g);
        }
    }

    private class SelectionBox {

        private int width;
        private int height;

        public void setWidth(int width) {
            this.width = width;
        }

        public int getWidth() {
            return width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public void paintSquare(Graphics g) {
            g.setColor(new Color(255, 0, 0, 127));
            g.fillRect(0, 0, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, width, height);
        }
    }

    private class DashedLineComponent extends JComponent {

        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        public DashedLineComponent() {
            setVisible(false);
        }

        private void setLine(int x1, int y1, int x2, int y2) {
            int newX = x1 > x2 ? x2 : x1;
            int newY = y1 > y2 ? y2 : y1;
            this.x1 = x1 - newX;
            this.y1 = y1 - newY;
            this.x2 = x2 - newX;
            this.y2 = y2 - newY;
            setBounds(newX, newY, abs(x1 - x2), abs(y1 - y2));
            setVisible(true);
        }

        private void hideLine() {
            setBounds(0, 0, 0, 0);
            setVisible(false);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(abs(x1 - x2), abs(y1 - y2));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);
            g2d.drawLine(x1, y1, x2, y2);
//            g2d.dispose();
        }
    }
}
