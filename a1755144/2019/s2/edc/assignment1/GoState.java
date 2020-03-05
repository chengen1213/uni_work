public class GoState implements State {
    @Override
    public void coinInserted(ReactionController controller) {

    }

    @Override
    public void goStopPressed(ReactionController controller) {
        ((DelayState)(controller.getDelayState())).setDelayTime(controller.random.getRandom(100,250));
        controller.setCurrentState(controller.getDelayState());
        controller.gui.setDisplay(Display.WAIT);
    }

    @Override
    public void interrupt(ReactionController controller) {
        if (controller.getClass() == EnhancedReactionController.class && controller.timeElapsed >= 1000) {
            controller.setCurrentState(controller.getIniState());
            controller.gui.setDisplay(Display.INSERTCOIN);
        }
    }
}
