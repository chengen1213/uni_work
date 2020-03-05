public class DelayState implements State{

    private double delayTime = 0;

    @Override
    public void coinInserted(ReactionController controller) {

    }

    @Override
    public void goStopPressed(ReactionController controller) {
            controller.setCurrentState(controller.getIniState());
            controller.gui.setDisplay(Display.INSERTCOIN);
    }

    @Override
    public void interrupt(ReactionController controller) {
        if (controller.timeElapsed >= this.delayTime) {
            controller.setCurrentState(controller.getCountingState());
            controller.gui.setDisplay(String.format("%.2f",((double)controller.timeElapsed)/100));
        }
    }

    public void setDelayTime(double delayTime) {
        this.delayTime = delayTime;
    }
}
