public class EnhancedReactionController extends SimpleReactionController {
    public EnhancedReactionController() {
        super();
        this.setResultState(new EResultState());
        this.setAvgState(new AvgState());
    }
}
