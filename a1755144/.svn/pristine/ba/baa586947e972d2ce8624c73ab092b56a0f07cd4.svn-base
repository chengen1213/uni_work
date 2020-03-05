public class IniState implements State{
    @Override
    public void coinInserted(ReactionController controller) {
        if (controller.getClass() == EnhancedReactionController.class)
            ((EResultState)(controller.getResultState())).iniValues();
        controller.setCurrentState(controller.getGoState());
        controller.gui.setDisplay(Display.PRESSGO);
    }

    @Override
    public void goStopPressed(ReactionController controller) {

    }

    @Override
    public void interrupt(ReactionController controller) {

    }
}
