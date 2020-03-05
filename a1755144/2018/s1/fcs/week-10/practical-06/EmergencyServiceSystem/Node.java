public class Node {
    protected Node next;
    protected Patient patient;

    public Node(Patient patient) {
        this.patient = patient;
        this.next = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    //print patient info
    public void printNode() {
        System.out.print(Main.spacing(new Integer(this.patient.getId()).toString(), 18, 1)
                + Main.spacing(this.patient.getName(), 15, 0)
                + Main.spacing(new Integer(this.patient.getTriageLevel()).toString(), 15, 1));
        System.out.println();
    }
}
