public class SimpleReactionController extends ReactionController{

    public SimpleReactionController () {
        super();
    }

    @Override
    public void connect(Gui gui, Random rng) {
        this.gui = gui;
        this.random = rng;
    }

    @Override
    public void init() {
        setCurrentState(getIniState());
        this.gui.setDisplay(Display.INSERTCOIN);
    }

    @Override
    public void coinInserted() {
        getCurrentState().coinInserted(this);
    }

    @Override
    public void goStopPressed() {
        getCurrentState().goStopPressed(this);
    }

    @Override
    public void tick() {
        this.timeElapsed += 1;
        getCurrentState().interrupt(this);
    }
}
