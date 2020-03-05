public class ServiceCenter {
    private WaitingList awl;

    public ServiceCenter() {
        this.awl = new WaitingList();
    }

    /**
     * Record patient's data and add the patient into waiting list
     *
     * @param name
     * @param phoneNumber
     * @param triageLevel
     * @param location
     */
    public void addPatientIntoList(String name, String phoneNumber, int triageLevel, String location) {
        Patient patient = new Patient(name, phoneNumber, triageLevel, location);
        if (this.awl.isInList(patient)) {
            System.out.println(patient.getName() + " is in waiting list. ");
        } else {
            this.awl.addToList(patient);
            System.out.println("Add " + patient.getName() + " into waiting list. ");
        }
    }

    /**
     * Pop out the first patient in the waiting list and assign an Ambulance for him/her
     *
     * @return the patient object
     */
    public Patient assignAmbulanceForPatient() {
        // TODO: The tester report that the system will crash when waiting list is empty
        Patient patient = this.awl.popPatient();
        if (patient != null) {
            System.out.println("Assigned an ambulance for patient: " + patient.getName());
        } else {
            System.out.println("There is no patient in the waiting list!");
        }
        return patient;
    }

    //function for option 4
    public void assignAmbulanceForPatientById(int id) {
        // TODO: The tester report that the system will crash when waiting list is empty
        Patient patient = this.awl.popPatientById(id);
        if (patient != null) {
            System.out.println("Assigned an ambulance for patient: " + patient.getName());
        } else {
            System.out.println("No such patient in the waiting list!");
        }
    }

    //function for option 5
    public void checkPositionById(int id) {
        // TODO: The tester report that the system will crash when waiting list is empty
        int pos = this.awl.getPatientPos(id);
        if (pos != -1) {
            if (pos == 1) {
                System.out.println("There is no patient before this patient.");
            } else if (pos == 2) {
                System.out.println("There is 1 patient before this patient.");
            } else {
                System.out.println("There are " + (pos - 1) + " patients before this patient.");
            }
        } else {
            System.out.println("No such patient in the waiting list!");
        }
    }

    /**
     * Print out the waiting list
     */
    public void printWaitingList() {
        this.awl.printList();
    }
}
