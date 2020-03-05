public abstract class ReactionController implements Controller{

    private State currentState;
    private State iniState;
    private State goState;
    private State delayState;
    private State countingState;
    private State resultState;

    private State avgState;

    protected Gui gui;
    protected Random random;
    protected int timeElapsed;

    public ReactionController() {
        this.iniState = new IniState();
        this.goState = new GoState();
        this.delayState = new DelayState();
        this.countingState = new CountingState();
        this.resultState = new ResultState();
        this.timeElapsed = 0;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.timeElapsed = 0;
        this.currentState = currentState;
    }

    public State getIniState() {
        return iniState;
    }

    public void setIniState(State iniState) {
        this.iniState = iniState;
    }

    public State getGoState() {
        return goState;
    }

    public void setGoState(State goState) {
        this.goState = goState;
    }

    public State getDelayState() {
        return delayState;
    }

    public void setDelayState(State delayState) {
        this.delayState = delayState;
    }

    public State getCountingState() {
        return countingState;
    }

    public void setCountingState(State countingState) {
        this.countingState = countingState;
    }

    public State getResultState() {
        return resultState;
    }

    public void setResultState(State resultState) {
        this.resultState = resultState;
    }

    public State getAvgState() {
        return avgState;
    }

    public void setAvgState(State avgState) {
        this.avgState = avgState;
    }
}
