public class EResultState implements State{

    private int counter = 0;
    private double[] results = new double[3];

    @Override
    public void coinInserted(ReactionController controller) {

    }

    @Override
    public void goStopPressed(ReactionController controller) {
        this.counter += 1;
        nextState(controller);
    }

    @Override
    public void interrupt(ReactionController controller) {
        if (controller.timeElapsed >= 300) {
            this.counter += 1;
            nextState(controller);
        }
    }

    private void nextState(ReactionController controller) {
        double time = ((CountingState)(controller.getCountingState())).time;
        results[counter -1] = time / 100;
        if (this.counter >= 3) {
            controller.setCurrentState(controller.getAvgState());
            controller.gui.setDisplay(getAvgString());
            iniValues();
        } else {
            ((DelayState)(controller.getDelayState())).setDelayTime(controller.random.getRandom(100,250));
            controller.setCurrentState(controller.getDelayState());
            controller.gui.setDisplay(Display.WAIT);
        }
    }

    private String getAvgString() {
        double sum = 0;
        for (int i = 0; i < counter; i++) {
            sum += results[i];
        }
        return "Average= " + String.format("%.2f", sum / counter);
    }

    public void iniValues() {
        counter = 0;
        for (int i = 0; i < 3; i++) {
            results[i] = 0;
        }
    }
}
