public class ResultState implements State {
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
        if (controller.timeElapsed >= 300) {
            controller.setCurrentState(controller.getIniState());
            controller.gui.setDisplay(Display.INSERTCOIN);
        }
    }
}
