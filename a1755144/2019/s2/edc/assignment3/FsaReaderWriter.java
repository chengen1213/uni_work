import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FsaReaderWriter implements FsaIo {
    @Override
    public void read(Reader r, Fsa f) throws IOException, FsaFormatException {
        BufferedReader bufferedReader = new BufferedReader(r);
        String line;
        int lineNum = 0;
        while ((line = bufferedReader.readLine()) != null) {
            lineNum++;
            String[] elements = line.split(" ");
            if (elements[0].startsWith("#") || elements[0].trim().equals(""))
                continue;
            if (elements[0].equals("state")) {
                parseState(lineNum, elements, f);
            } else if (elements[0].equals("transition")) {
                parseTransition(lineNum, elements, f);
            } else if (elements[0].equals("initial")) {
                parseInitial(lineNum, elements, f);
            } else if (elements[0].equals("final")) {
                parseFinal(lineNum, elements, f);
            } else {
                throw new FsaFormatException(lineNum, "Type error!");
            }
        }
        bufferedReader.close();
    }

    @Override
    public void write(Writer w, Fsa f) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Set<State> states = f.getStates();
        Set<Transition> transitions = new HashSet<>();
        for (State state : states) {
            stringBuilder.append("state " + state.getName() + " " + state.getXpos() + " " + state.getYpos() + "\n");
            for (Transition transition : state.transitionsFrom())
                transitions.add(transition);
        }
        for (Transition transition : transitions) {
            String eventName = transition.eventName();
            eventName = eventName != null ? eventName : "?";
            stringBuilder.append("transition " + transition.fromState().getName() + " " + eventName + " " + transition.toState().getName() + "\n");
        }
        for (State state : states) {
            if (state.isInitial())
                stringBuilder.append("initial " + state.getName() + "\n");
        }
        for (State state : states) {
            if (state.isFinal())
                stringBuilder.append("final " + state.getName() + "\n");
        }
        w.write(stringBuilder.toString());
//        System.out.println(stringBuilder.toString());
    }

    private void parseState(int line, String[] content, Fsa f) throws FsaFormatException {
        try {
            f.newState(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]));
        } catch (Exception e) {
            throw new FsaFormatException(line, "State format error!");
        }
    }

    private void parseTransition(int line, String[] content, Fsa f) throws FsaFormatException {
        try {
            f.newTransition(f.findState(content[1]), f.findState(content[3]), content[2]);
        } catch (Exception e) {
            throw new FsaFormatException(line, "Transition format error!");
        }
    }

    private void parseInitial(int line, String[] content, Fsa f) throws FsaFormatException {
        for (int i = 1; i < content.length; i++) {
            State state = f.findState(content[i]);
            if (state == null)
                throw new FsaFormatException(line, "Initial state name not found!");
            else {
                state.setInitial(true);
            }
        }
    }

    private void parseFinal(int line, String[] content, Fsa f) throws FsaFormatException {
        for (int i = 1; i < content.length; i++) {
            State state = f.findState(content[i]);
            if (state == null)
                throw new FsaFormatException(line, "Final state name not found!");
            else
                state.setFinal(true);
        }
    }
}
