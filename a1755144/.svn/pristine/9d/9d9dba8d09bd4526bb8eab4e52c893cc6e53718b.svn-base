import java.io.FileReader;
import java.io.FileWriter;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("test4.fsa");
            FsaIo fsaIo = new FsaReaderWriter();
            FsaImpl fsa = new FsaImpl();
            fsaIo.read(reader, fsa);
            FileWriter fileWriter = new FileWriter("hahaha.fsa");
            fsaIo.write(fileWriter, fsa);
            fileWriter.flush();
            System.out.println(fsa);
            State q0 = fsa.findState("q0");
            State q1 = fsa.findState("q1");
            State q2 = fsa.findState("q2");
            State q3 = fsa.findState("q3");
//            printStatus(fsa);
//            fsa.step("a");
//            printStatus(fsa);
//            fsa.step("b");
//            printStatus(fsa);
//            fsa.reset();
//            printStatus(fsa);
//            fsa.step("b");
//            printStatus(fsa);
            fsa.reset();
            fsa.step("z");
            printStatus(fsa);
            fsa.step("z");
            printStatus(fsa);
            fsa.step("o");
            printStatus(fsa);
            fsa.step("o");
            printStatus(fsa);
            fsa.step("c");
            printStatus(fsa);
//            ((FsaImpl.StateImpl)q1).setCurrent(true);
//
//            Set<State> states = new HashSet<>();
//            fsa.getEClosure(q0, states);
//            System.out.println(fsa.isRecognised());
//            Transition transition = fsa.newTransition(q2,q3,null);
//            System.out.println(fsa.isRecognised());
//            fsa.removeTransition(transition);
//            System.out.println(fsa.isRecognised());
//            fsa.step("c");
//            System.out.println(fsa.isRecognised());
//            fsa.reset();
//            states = fsa.getCurrentStates();
//            states = fsa.getNextStates(q0, "b");
//            Transition transition2 = fsa.newTransition(q0,q3,"a");
//            Transition transition3 = fsa.newTransition(q2,q1,null);
//            Transition transition4 = fsa.newTransition(q1,q2,"a");
//            states = fsa.getNextStates(q0, "a");
//            printStates(states);
//            states = fsa.getCurrentStates();
//            printStates(states);
//            states = fsa.getNextStates(q0, "a");
//            printStates(states);
//            fsa.removeState(q1);
//            System.out.println(fsa);

//            System.out.println(fsa.isValidStateName("dsfs0s0sg_"));
//            System.out.println(fsa.isValidEventName("kjhUHiugiugI"));
//            fsa.step("ev");
//            fsaIo.write(fileWriter, fsa);
//            fileWriter.flush();
//            printStatus(fsa);
//            fsa.reset();
//            printStatus(fsa);

            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printStates(Set<State> states) {
        System.out.println("Start------");
        for (State state: states)
            System.out.println(state);
    }

    public static void printStatus(FsaImpl fsa) {
        System.out.println(fsa.isRecognised());
        Set<State> states = fsa.getCurrentStates();
        System.out.println("current");
        for (State state : states)
            System.out.println(state.getName());
//        states = fsa.getInitialStates();
//        System.out.println("initial");
//        for (State state : states)
//            System.out.println(state.getName());
//        states = fsa.getFinalStates();
//        System.out.println("final");
//        for (State state : states)
//            System.out.println(state.getName());
    }
}
