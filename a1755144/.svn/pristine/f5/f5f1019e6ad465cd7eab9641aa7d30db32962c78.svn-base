import java.util.Scanner;

public class Main {
    /**
     * initialize data to the serviceCenter, terminate the system if data is wrong
     *
     * @param serviceCenter
     */
    private static final String nums = "0123456789";
    private static final String[] errorMessages =
            {
                    "Please enter a number!",
                    "Please enter a valid triage level!( 1 - 5 )",
                    "This information should not be empty!"
            };

    public static void initializeSystem(ServiceCenter serviceCenter) {
        String[] name = {"Tod", "David", "Dave", "Roy", "Megan"};
        String[] phoneNumbers = {"0410333222", "0410333223", "0410333224", "0410333225", "0410333226"};
        int[] triageLevel = {1, 5, 2, 4, 3};
        String[] location = {"King William ST", "North Terrace", "Grote St", "Gouger St", "Melbourne St"};
        // Check the length for each array is equal
        if (!(name.length == phoneNumbers.length && name.length == location.length && name.length == triageLevel.length)) {
            System.out.println("Failed to initialize data.");
            return;
        }
        // Initialize data into service center
        for (int i = 0; i < name.length; i++) {
            serviceCenter.addPatientIntoList(name[i], phoneNumbers[i], triageLevel[i], location[i]);
        }

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String getHeader(boolean toPrint) {
        String ans = "\n\n\n";
        ans += "===============================================================================" + "\n";
        ans += ":: Emergency Service Center                                                  ::" + "\n";
        ans += "===============================================================================" + "\n";
        if (toPrint)
            System.out.println(ans);
        return ans;
    }

    public static void printSubHeader(String subHeader) {
        getHeader(true);
        String str = ":: " + subHeader + "\n";
        System.out.println(str);
    }

    public static void printScreenMessage(String message) {
        String ans = getHeader(false) + "\n\n";
        ans += "::            " + message + "\n\n";
        System.out.println(ans);

    }

    public static void exitMessage() {
        clearScreen();
        printScreenMessage("Thank you!!");
    }

    public static void displayMenu() {
        String ans = getHeader(false);
        ans += ":: Options:                                                                  ::" + "\n";
        ans += ":: 1. Add Patient                                                            ::" + "\n";
        ans += ":: 2. Assign Ambulance For Patient                                           ::" + "\n";
        ans += ":: 3. Assign Ambulance For Patient By Id                                     ::" + "\n";
        ans += ":: 4. Check Position By Id                                                   ::" + "\n";
        ans += ":: 5. Print Waiting List                                                     ::" + "\n";
        ans += ":: 0. Exit                                                                   ::" + "\n";
        ans += "===============================================================================" + "\n";
        System.out.println(ans);
    }

    public static int getOption(Scanner inputScan, int[] optionsAvailable) {
        String ans = ":: Your option: ";
        System.out.println(ans);
        String _input = inputScan.nextLine();
        //check for valid option
        if (!isANum(_input)) {
            return -1;
        }
        int _option = Integer.parseInt(_input);
        for (int i = 0; i < optionsAvailable.length; i++)
            if (_option == optionsAvailable[i])
                return _option;
        return -1;
    }

    public static String getInput(Scanner inputScan, String question) {
        System.out.println(question);
        String _input = inputScan.nextLine();
        return _input;
    }

    public static String getLegalInput(Scanner inputScan, String question, int condition) {
        boolean test = true;
        String input = "";
        String errorMessage = "";
        while (test) {
            test = false;
            input = getInput(inputScan, question);
            if (condition == 0 || condition == 1) {
                test = !isANum(input);
                errorMessage = errorMessages[0];
            }
            if (isANum(input) && condition == 1) {
                int level = Integer.parseInt(input);
                if (level < 1 || level > 5) {
                    test = true;
                    errorMessage = errorMessages[condition];
                }
            }
            if (condition == 2) {
                if (input.length() < 1) {
                    test = true;
                    errorMessage = errorMessages[condition];
                }
            }
            if (test) {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

    public static void waitInput(Scanner scan) {
        String ans = "\n\n\n";
        ans += "                        Press any key to continue . . .      " + "\n";
        ans += "===============================================================================" + "\n";
        System.out.println(ans);
        scan.nextLine();
    }

    public static void tryAgain(Scanner scan) {
        clearScreen();
        String ans = getHeader(false);
        ans += ":: Your input is invalid, please select ::" + "\n";
        ans += ":: a valid option.                      ::" + "\n";
        ans += "===============================================================================" + "\n";
        System.out.println(ans);
        waitInput(scan);
    }

    public static boolean performAction(ServiceCenter serviceCenter, int _option, Scanner inputScan) {
        String query = "";
        // TODO: The tester report that the system will crash when the input of triageLevel and id is not a number
        // TODO: The tester report that the system should only accept (1-5) as legal number 
        // TODO: The system should ask the user to input again if there is an illegal input
        String id = "";
        boolean test = true;
        switch (_option) {
            case 1:
                clearScreen();
                printSubHeader("Add Patient");
//                String name = getInput(inputScan, "Please enter the name of patient: ");
                String name = getLegalInput(inputScan, "Please enter the name of patient: ", 2);
                String phoneNumber = getInput(inputScan, "Please enter the phoneNumber of patient: ");
                //check input
                String triageLevel = getLegalInput(inputScan, "Please enter the triage level of patient: ", 1);
                String location = getInput(inputScan, "Please enter the location of patient: ");
                serviceCenter.addPatientIntoList(name, phoneNumber, Integer.parseInt(triageLevel), location);
                waitInput(inputScan);
                break;
            case 2:
                clearScreen();
                printSubHeader("Assign Ambulance");
                serviceCenter.assignAmbulanceForPatient();
                waitInput(inputScan);
                break;
            case 5:
                clearScreen();
                printSubHeader("Waiting List");
                serviceCenter.printWaitingList();
                waitInput(inputScan);
                break;
            //option 4, Assign Ambulance For Patient By Id
            case 3:
                clearScreen();
                printSubHeader("Assign Ambulance For Patient By Id");
                //check input
                id = getLegalInput(inputScan, "Please enter the ID of patient: ", 0);
                //display result
                serviceCenter.assignAmbulanceForPatientById(Integer.parseInt(id));
                waitInput(inputScan);
                break;
            //option 5, Check Position By Id    
            case 4:
                clearScreen();
                printSubHeader("Check Position By Id");
                //check input
                id = getLegalInput(inputScan, "Please enter the ID of patient: ", 0);
                //display position
                serviceCenter.checkPositionById(Integer.parseInt(id));
                waitInput(inputScan);
                break;
            case 0:
                exitMessage();
                return false;
        }
        return true;

    }

    public static void main(String[] args) {
        ServiceCenter serviceCenter = new ServiceCenter();
        initializeSystem(serviceCenter);
        Scanner inputScan = new Scanner(System.in);
        int[] options = new int[]{1, 2, 3, 4, 5, 0};
        boolean _continue = true;
        while (_continue) {
            clearScreen();
            displayMenu();
            int _option = getOption(inputScan, options);
            if (_option != -1)
                _continue = performAction(serviceCenter, _option, inputScan);
            else
                tryAgain(inputScan);
        }
    }

    //check if the string is a number
    public static boolean isANum(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
//        if (num.equals("")) {
//            return false;
//        }
//        String[] chars = num.split("");
//        for (String s : chars) {
//            if (nums.indexOf(s) < 0)
//                return false;
//        }
//        return true;
    }

    //use length parameter to control the width of the context
    public static String spacing(String target, int length, int align) {
        String result = "";
        int spaceNum;
        if (length > target.length()) {
            spaceNum = length - target.length();
        } else {
            spaceNum = target.length();
        }
        int stringPos = 0;
        switch (align) {
            //align left
            case 0:
                stringPos = 0;
                break;
            //align mid
            case 1:
                stringPos = spaceNum / 2;
                break;
            //align right
            case 2:
                stringPos = spaceNum - 1;
                break;
        }
        for (int i = 0; i < spaceNum; i++) {
            if (i == stringPos) {
                result += target;
            }
            result += " ";
        }
        return result;
    }
}
