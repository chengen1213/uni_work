public class LamportClock {

    private int lamportTime = 0;

    //compare the current time and the event time, set time to the bigger one + 1
    public synchronized int getLamportTime(int event) {
        if (event > lamportTime) {
            lamportTime = event++;
            return lamportTime;
        } else {
            lamportTime++;
            return lamportTime;
        }
    }
    //new event, time + 1
    public synchronized int getLamportTime() {
        return ++lamportTime;
    }
}
