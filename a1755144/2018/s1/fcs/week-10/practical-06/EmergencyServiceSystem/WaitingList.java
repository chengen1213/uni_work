public class WaitingList {
    protected Node head;
    protected int numNodes;

    public WaitingList() {
        this.head = null;
        this.numNodes = 0;
    }

    /**
     * This method will pop the first patient from the waiting list
     *
     * @return the patient popped from the waiting list
     */
    public Patient popPatient() {
        if (head != null) {
            Patient temp = head.getPatient();
            head = head.getNext();
            numNodes--;
            return temp;
        } else {
            return null;
        }
    }

    //method for option 3
    public Patient popPatientById(int id) {
        if (isInList(id)) {
            Node temp = head;
            Node prev = null;
            while (temp != null) {
                if (temp.getPatient().getId() != id) {
                    prev = temp;
                    temp = temp.getNext();
                } else {
                    if (prev != null) {
                        prev.setNext(temp.getNext());
                        //pop from the first position
                    } else {
                        head = temp.getNext();
                    }
                    break;
                }
            }
            numNodes--;
            return temp.getPatient();
        } else {
            return null;
        }
    }

    //method for option 4
    public int getPatientPos(int id) {
        //position counter
        int pos = 0;
        if (isInList(id)) {
            Node temp = head;
            while (temp != null) {
                pos++;
                if (temp.getPatient().getId() != id) {
                    temp = temp.getNext();
                } else {
                    break;
                }
            }
            return pos;
        } else {
            return -1;
        }
    }

    /**
     * This method will add patient into the waiting list according to the triage level
     *
     * @param patient patient's data
     */
    public void addToList(Patient patient) {
        //prepare data for the first round of the loop
        Node current = head;
        Node prev = null;
        Node temp = new Node(patient);
        //no elements in the queue, place the patient in the head
        if (head == null) {
            head = temp;
        } else {
            while (current != null) {
                if (patient.getTriageLevel() > current.getPatient().getTriageLevel()) {
                    temp.setNext(current);
                    //insert to the queue
                    if (prev != null) {
                        prev.setNext(temp);
                        numNodes++;
                        return;
                        //insert to the head of the queue
                    } else {
                        head = temp;
                        numNodes++;
                        return;
                    }
                } else {
                    //insert to the end of the queue
                    if (current.getNext() == null) {
                        current.setNext(temp);
                        numNodes++;
                        return;
                        //prepare data for the next round of the loop
                    } else {
                        prev = current;
                        current = current.getNext();
                    }
                }
            }
        }
    }

    /**
     * print out the information for each patient in waiting list
     */
    public void printList() {
        Node temp = head;
        int counter = 0;
        System.out.print("::     ");
        System.out.println(Main.spacing("Patient Id ", 15, 0)
                + Main.spacing(" Name ", 15, 0)
                + Main.spacing(" TriageLevel ", 15, 0));
        if (temp == null) {
            System.out.println();
            System.out.println("There is no patient in the waiting list!");
        } else {
            while (temp != null) {
                System.out.print(":: " + ++counter + ".");
                temp.printNode();
                temp = temp.getNext();
            }
        }
    }

    /**
     * Check whether the patient is in this list or not
     *
     * @return
     */
    public boolean isInList(Patient patient) {
        if (this.head == null) {
            return false;
        }
        Node temp = this.head;
        while (temp != null) {
            if (temp.getPatient().getName().equals(patient.getName())
                    && temp.getPatient().getPhoneNumber().equals(patient.getPhoneNumber())) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    //check if the patient is in the queue by id
    public boolean isInList(int id) {
        if (this.head == null) {
            return false;
        }
        Node temp = this.head;
        while (temp != null) {
            if (temp.getPatient().getId() == id) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }
}
