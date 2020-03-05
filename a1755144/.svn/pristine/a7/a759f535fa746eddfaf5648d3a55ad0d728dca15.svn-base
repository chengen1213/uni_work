public class CountingState implements State{

    public int time = 0;

    @Override
    public void coinInserted(ReactionController controller) {

    }

    @Override
    public void goStopPressed(ReactionController controller) {
        time = controller.timeElapsed;
        controller.setCurrentState(controller.getResultState());
        controller.gui.setDisplay(String.format("%.2f",((double)time)/100));
    }

    @Override
    public void interrupt(ReactionController controller) {
        time = controller.timeElapsed;
        controller.gui.setDisplay(String.format("%.2f",((double)time)/100));
        if (controller.timeElapsed >= 200) {
//            controller.setCurrentState(controller.getResultState());
            controller.setCurrentState(controller.getIniState());
            controller.gui.setDisplay(Display.INSERTCOIN);
        }
    }
}
