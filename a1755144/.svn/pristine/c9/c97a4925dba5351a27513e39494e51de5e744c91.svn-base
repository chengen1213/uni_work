import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FsaImpl implements Fsa, FsaSim {

    private Set<State> states;
    private Set<Transition> transitions;
    private Set<FsaListener> fsaListeners;
    private Pattern statePattern;
    private Pattern transitionPattern;

    public FsaImpl() {
        states = new HashSet<>();
        transitions = new HashSet<>();
        fsaListeners = new HashSet<>();
//        statePattern = Pattern.compile("[a-zA-Z$_][a-zA-Z0-9$_]*");
        statePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_]*");
        transitionPattern = Pattern.compile("[a-zA-Z]+");
    }

    @Override
    public State newState(String name, int x, int y) throws IllegalArgumentException {
        if (name == null || !isValidStateName(name))
            throw new IllegalArgumentException("Invalid state name!");
        if (findState(name) != null)
            throw new IllegalArgumentException("Duplicate state name!");
        State state = new StateImpl(name, x, y);
        if (!states.add(state)) {
            throw new IllegalArgumentException("State already exists!");
        }
        notifyStateChanged();
        return state;
    }

    @Override
    public void removeState(State s) {
        Set<Transition> transitionsFrom = new HashSet<>(s.transitionsFrom());
        Set<Transition> transitionsTo = new HashSet<>(s.transitionsTo());
        for (Transition transition : transitionsFrom) {
            removeTransition(transition);
            notifyTransitionChanged();
        }
        for (Transition transition : transitionsTo) {
            removeTransition(transition);
            notifyTransitionChanged();
        }
        states.remove(s);
        notifyStateChanged();
    }

    @Override
    public State findState(String stateName) {
        for (State state : states) {
            if (stateName.equals(state.getName()))
                return state;
        }
        return null;
    }

    @Override
    public Set<State> getStates() {
        return states;
    }

    @Override
    public Transition newTransition(State fromState, State toState, String eventName) throws IllegalArgumentException {
        if (eventName != null && !isValidEventName(eventName))
            throw new IllegalArgumentException("Invalid transition name!");
        if ("?".equals(eventName))
            eventName = null;
        if (states.contains(fromState) && states.contains(toState)) {
            for (Transition transition : findTransition(fromState, toState)) {
                if (isSameEvent(eventName, transition.eventName()))
                    throw new IllegalArgumentException("Duplicate transition!");
            }
        } else
            throw new IllegalArgumentException("State not exists!");
        Transition transition = new TransitionImpl(fromState, toState, eventName);
        ((StateImpl) fromState).addTransitionsFrom(transition);
        ((StateImpl) toState).addTransitionsTo(transition);
        transitions.add(transition);
        notifyTransitionChanged();
        return transition;
    }

    @Override
    public void removeTransition(Transition t) {
        StateImpl fromState = (StateImpl) t.fromState();
        StateImpl toState = (StateImpl) t.fromState();
        fromState.removeTransitionsFrom(t);
//        notifyStateChanged();
        toState.removeTransitionsTo(t);
//        notifyStateChanged();
        transitions.remove(t);
        notifyTransitionChanged();
    }

    @Override
    public Set<Transition> findTransition(State fromState, State toState) {
        if (!states.contains(fromState) || !states.contains(toState))
            throw new IllegalArgumentException();
        Set<Transition> subTransitions = new HashSet<>();
        for (Transition transition : fromState.transitionsFrom()) {
            if (toState.equals(transition.toState())) {
                subTransitions.add(transition);
            }
        }
        return subTransitions;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    @Override
    public Set<State> getInitialStates() {
        Set<State> subStates = new HashSet<>();
        for (State state : states) {
            if (state.isInitial())
                subStates.add(state);
        }
        return subStates;
    }

    @Override
    public Set<State> getFinalStates() {
        Set<State> subStates = new HashSet<>();
        for (State state : states) {
            if (state.isFinal())
                subStates.add(state);
        }
        return subStates;
    }

    @Override
    public Set<State> getCurrentStates() {
        Set<State> subStates = new HashSet<>();
        for (State state : states) {
            if (state.isCurrent())
                subStates.add(state);
        }
        return subStates;
    }

    @Override
    public void addListener(FsaListener fl) {
        fsaListeners.add(fl);
    }

    @Override
    public void removeListener(FsaListener fl) {
        fsaListeners.remove(fl);
    }

    @Override
    public void reset() {
        for (State state : states) {
            if (state.isInitial())
                ((StateImpl) state).setCurrent(true);
            else
                ((StateImpl) state).setCurrent(false);
        }
        notifyOtherChanged();
    }

    @Override
    public void step(String event) {
        if ("?".equals(event) || event == null)
            return;
        Set<State> currentStates = getCurrentStates();
        Set<State> nextStates = new HashSet<>();
        for (State state : currentStates) {
            for (State nextState : getNextStates(state, event))
                nextStates.add(nextState);
        }
        for (State state : currentStates)
            ((StateImpl) state).setCurrent(false);
        for (State state : nextStates)
            ((StateImpl) state).setCurrent(true);
        notifyOtherChanged();
    }

    @Override
    public boolean isRecognised() {
        for (State currentState : getCurrentStates()) {
            Set<State> eClosure = new HashSet<>();
            getEClosure(currentState, eClosure);
            for (State state : eClosure)
                if (state.isFinal())
                    return true;
        }
        return false;
    }

    public boolean isValidStateName(String name) {
        Matcher matcher = statePattern.matcher(name);
        return matcher.matches();
    }

    public boolean isValidEventName(String name) {
        if ("?".equals(name))
            return true;
        Matcher matcher = transitionPattern.matcher(name);
        return matcher.matches();
    }

    public boolean isSameEvent(String lhs, String rhs) {
        if (lhs == null && rhs == null)
            return true;
        if (lhs != null && lhs.equals(rhs))
            return true;
        return false;
    }

    public Set<State> getNextStates(State fromState, String eventName) {
        Set<State> eClosure = new HashSet<>();
        getEClosure(fromState, eClosure);
        Set<State> nextStates = new HashSet<>();
        for (State state : eClosure) {
            for (Transition transition : state.transitionsFrom()) {
                if (isSameEvent(eventName, transition.eventName())) {
                    nextStates.add(transition.toState());
                }
            }
        }
        return nextStates;
    }

    public void getEClosure(State fromState, Set<State> eClosure) {
        if (eClosure.contains(fromState))
            return;
        eClosure.add(fromState);
        for (Transition transition : fromState.transitionsFrom()) {
            if (transition.eventName() == null)
                getEClosure(transition.toState(), eClosure);
        }
        return;
    }

    private void notifyStateChanged() {
        for (FsaListener fsaListener : fsaListeners) {
            fsaListener.statesChanged();
        }
    }

    private void notifyTransitionChanged() {
        for (FsaListener fsaListener : fsaListeners) {
            fsaListener.transitionsChanged();
        }
    }

    private void notifyOtherChanged() {
        for (FsaListener fsaListener : fsaListeners) {
            fsaListener.otherChanged();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (State state : states)
            stringBuilder.append("STATE " + state + "\n");
        for (Transition transition : transitions)
            stringBuilder.append("TRANSITION " + transition + "\n");
        for (State state : states) {
            if (state.isInitial())
                stringBuilder.append("INITIAL " + state.getName() + "\n");
        }
        for (State state : states) {
            if (state.isFinal())
                stringBuilder.append("FINAL " + state.getName() + "\n");
        }
        return stringBuilder.toString();
    }

    public class StateImpl implements State {

        private Set<Transition> transitionsFrom;
        private Set<Transition> transitionsTo;
        private Set<StateListener> stateListeners;
        private String name;
        private int xPos;
        private int yPos;
        private boolean isInitial;
        private boolean isFinal;
        private boolean isCurrent;

        public StateImpl(String name, int xPos, int yPos) {
            this.transitionsFrom = new HashSet<>();
            this.transitionsTo = new HashSet<>();
            this.stateListeners = new HashSet<>();
            this.name = name;
            this.xPos = xPos;
            this.yPos = yPos;
        }

        @Override
        public void addListener(StateListener sl) {
            stateListeners.add(sl);
        }

        @Override
        public void removeListener(StateListener sl) {
            stateListeners.remove(sl);
        }

        @Override
        public Set<Transition> transitionsFrom() {
            return transitionsFrom;
        }

        @Override
        public Set<Transition> transitionsTo() {
            return transitionsTo;
        }

        @Override
        public void moveBy(int dx, int dy) {
            this.xPos += dx;
            this.yPos += dy;
            notifyListeners();
        }

        public void moveTo(int x, int y) {
            this.xPos = x;
            this.yPos = y;
            notifyListeners();
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getXpos() {
            return xPos;
        }

        @Override
        public int getYpos() {
            return yPos;
        }

        @Override
        public void setInitial(boolean b) {
            this.isInitial = b;
            notifyListeners();
        }

        @Override
        public boolean isInitial() {
            return isInitial;
        }

        @Override
        public void setFinal(boolean b) {
            this.isFinal = b;
            notifyListeners();
        }

        @Override
        public boolean isFinal() {
            return isFinal;
        }

        @Override
        public boolean isCurrent() {
            return isCurrent;
        }

        public void setCurrent(boolean b) {
            this.isCurrent = b;
            notifyListeners();
        }

        public void addTransitionsFrom(Transition transition) {
            transitionsFrom.add(transition);
//            notifyListeners();
        }

        public void removeTransitionsFrom(Transition transition) {
            transitionsFrom.remove(transition);
//            notifyListeners();
        }

        public void addTransitionsTo(Transition transition) {
            transitionsTo.add(transition);
//            notifyListeners();
        }

        public void removeTransitionsTo(Transition transition) {
            transitionsTo.remove(transition);
//            notifyListeners();
        }

        private void notifyListeners() {
            for (StateListener stateListener : stateListeners) {
                stateListener.StateHasChanged();
            }
        }

        @Override
        public String toString() {
            int isInitial = this.isInitial ? 1 : 0;
            int isFinal = this.isFinal ? 1 : 0;
            String string = name + "(" + xPos + "," + yPos + ")" + isInitial + isFinal;
            return string;
        }

        @Override
        public boolean equals(Object obj) {
            State state = (State) obj;
            return this.name.equals(state.getName());
        }
    }

    public class TransitionImpl implements Transition, StateListener {

        private Set<TransitionListener> transitionListeners;
        private State fromState;
        private State toState;
        private String eventName;

        public TransitionImpl(State fromState, State toState, String eventName) {
            transitionListeners = new HashSet<>();
            this.fromState = fromState;
            this.toState = toState;
            this.eventName = eventName;
            fromState.addListener(this);
            toState.addListener(this);
        }

        @Override
        public void addListener(TransitionListener tl) {
            transitionListeners.add(tl);
        }

        @Override
        public void removeListener(TransitionListener tl) {
            transitionListeners.remove(tl);
        }

        @Override
        public State fromState() {
            return fromState;
        }

        @Override
        public State toState() {
            return toState;
        }

        @Override
        public String eventName() {
            return eventName;
        }

        @Override
        public String toString() {

            String string = fromState.getName() + "(" + (eventName != null ? eventName : "?") + ")" + toState.getName();
            return string;
        }

        @Override
        public boolean equals(Object obj) {
            Transition transition = (Transition) obj;
            boolean equals =
                    ((this.eventName == null && transition.eventName() == null) || this.eventName.equals(transition.eventName()))
                            && this.fromState.equals(transition.fromState())
                            && this.toState.equals(transition.toState());
            return equals;
        }

        @Override
        public void StateHasChanged() {
            notifyListeners();
        }

        private void notifyListeners() {
            for (TransitionListener transitionListener : transitionListeners) {
                transitionListener.TransitionHasChanged();
            }
        }
    }
}
