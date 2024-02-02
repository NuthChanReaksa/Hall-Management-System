
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    //    Method to Display HAll when person booking
    public static void displayHall(String[][] hall, String[][] personBook, int totalRow, int seatsPerRow) {
        char floorChar = 'A';
        for (int i = 0; i < totalRow; i++) {
            System.out.print("|" + floorChar + "-1::" + (personBook[i][0] == null || personBook[i][0].isEmpty() ? "AV" : "BO") + "| ");
            for (int j = 1; j < seatsPerRow; j++) {
                System.out.print("|" + floorChar + "-" + (j + 1) + "::" + (personBook[i][j] == null || personBook[i][j].isEmpty() ? "AV" : "BO") + "| ");
            }
            System.out.println();
            floorChar++;
        }
    }
    // Method to format booking entry
    public static String formatBookingEntry(String seats, String hall, String studentId, LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy HH:mm"); // Format: day-month-year hour:minute
        String formattedDateTime = dateTime.format(formatter);
        return String.format("#SEATS:\t[%s]\n#HALL:\t%s\n#ID:\t%s\n#CREATED At:\t\n[%s]\n", seats, hall, studentId, formattedDateTime);
    }
//    ID must contain only digits!!!!
    private static String getValidStudentId(Scanner input) {
        boolean validInput = false;
        String studentId = "";
        while (!validInput) {
            System.out.print(">> Please Enter Student ID:  ");
            studentId = input.next();
            validInput = studentId.matches("\\d+");
            if (!validInput) {
                System.out.println("ID must contain only digits.");
            }
        }
        return studentId;
    }
    public static void main(String[] args) {

        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        String magenta = "\u001B[35m";
        String cyan = "\u001B[36m";
        String white = "\u001B[37m";
        String black = "\u001B[30m";
        String blueBackGround = "\u001B[44m";
        String redBackGround = "\u001B[41m";
        Scanner input = new Scanner(System.in);

        String[][] personBook;
        boolean validInput = false;
        int totalRow = 0;
        int seatsPerRow = 0;
        char opt='\0';
        // Title of Hall Management Console Application
        System.out.print(blue + "\n\n|==============|Hall Management Console Application|=============|\n" );
        System.out.println("|------------------------<Setting Up Hall>-----------------------|");
        System.out.println("|================================================================|"+ reset);
        while (!validInput) {
            System.out.print("Enter total rows in the hall: ");
            String totalRowStr = input.nextLine().replaceAll("\\s","");
            validInput = totalRowStr.matches("\\d+");
            if (!validInput) {
                System.out.println("Invalid input. Total rows must contain only digits.");
            } else {
                totalRow = Integer.parseInt(totalRowStr);
            }
        }
        validInput = false; // Reset the flag for the next validation loop

        while (!validInput) {
            System.out.print("Enter total seats per row in the hall: ");
            String seatsPerRowStr = input.nextLine().replaceAll("\\s","");
            validInput = seatsPerRowStr.matches("\\d+");
            if (!validInput) {
                System.out.println("Invalid input. Seats per row must contain only digits.");
            } else {
                seatsPerRow = Integer.parseInt(seatsPerRowStr);
            }
        }

// Initialize hallA, hallB, and hallC arrays after getting user input
        String[][] hallA = new String[totalRow][seatsPerRow];
        String[][] hallB = new String[totalRow][seatsPerRow];
        String[][] hallC = new String[totalRow][seatsPerRow];
        for (int i = 0; i < totalRow; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                hallA[i][j] = "" + (char)('A' + i) + "-" + (j + 1);
                hallB[i][j] = "" + (char)('A' + i) + "-" + (j + 1);
                hallC[i][j] = "" + (char)('A' + i) + "-" + (j + 1);
            }
        }
        // Initialize booking arrays for each hall
        String[][] personBookA = new String[totalRow][seatsPerRow];
        String[][] personBookB = new String[totalRow][seatsPerRow];
        String[][] personBookC = new String[totalRow][seatsPerRow];

        // Initialize booking history array
        String[] bookingHistory = new String[100]; // Assuming a maximum of 100 booking entries
        int bookingCount = 0; // Track the number of bookings

        do {
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println("=============================|Welcome To Our Hall|=========================");
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println("___________________________________________________________________________");
            System.out.println("\n-----------------------------<Application Of MENU>-------------------------\n");
            System.out.println("___________________________________________________________________________");
            System.out.println("|->  A- Booking                                                            |");
            System.out.println("|->  B- Hall                                                               |");
            System.out.println("|->  C- Show Time                                                          |");
            System.out.println("|->  D- Reboot Showtime                                                    |");
            System.out.println("|->  E- History                                                            |");
            System.out.println("|->  F- Exit                                                               |");
            System.out.println("|__________________________________________________________________________|");
            System.out.print("-> Please choose Menu no : ");
            while (!validInput) {
                String inputStr = input.nextLine().trim();
                // Read the input as a string and remove leading/trailing whitespaces
                // Validate the input
                if (inputStr.length() == 1 && Character.isLetter(inputStr.charAt(0))) {
                    opt = Character.toUpperCase(inputStr.charAt(0)); // Convert the input to uppercase
                    if (opt >= 'A' && opt <= 'F') {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input. Please enter a valid menu option (A-F).");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a single letter (A-F).");
                }
            }
            opt = input.nextLine().toUpperCase().replaceAll("\\s", "").charAt(0);
            while (!(opt >= 'A' && opt <= 'F')) {
                System.out.println("Invalid input. Please try again");
                System.out.print(" Please choose Menu no : ");
                opt = input.nextLine().toUpperCase().replaceAll("\\s", "").charAt(0);
            }

            // Local part
            switch (opt) {
                // A- Booking
                case 'a', 'A': {
                    int hallOfRow = 0;
                    int hallOfColumn = 0;
                    char optBook;
                    boolean confirmed = false;
                    do {
                        System.out.println("|*******************************************************|");
                        System.out.println("|   # Start booking process                             |");
                        System.out.println("|*******************************************************|");
                        System.out.println("|# A) Morning (10.00AM - 12:30PM)                       |");
                        System.out.println("|# B) Afternoon (03.00PM - 05:30PM)                     |");
                        System.out.println("|# C) Night (07:00PM - 09:30PM)                         |");
                        System.out.println("|*******************************************************|");
                        System.out.println("=> Please select show time (A|B|C): ");
                        optBook = input.next().charAt(0);
                        switch (optBook) {
                            case 'a', 'A': {
                                System.out.println("# HALL A");
                                displayHall(hallA, personBookA, totalRow, seatsPerRow);
                                System.out.println("-+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+-");
                                System.out.println("\n# INSTRUCTION" + "\n# Single: C-1" + "\n# Multiple (separated by comma): C-1,C-2");
                                System.out.println("> Please select available seat: ");
                                String seatInput = input.next();
                                if (!seatInput.matches("^([A-Z]-\\d+(?:,[A-Z]-\\d+)*)?$")) {
                                    System.out.println("Invalid input. Please enter in the format 'C-1' or 'C-1,C-2'.");
                                    continue;
                                }
//                                System.out.println("> Please Enter Student ID: ");
                                String studentId = getValidStudentId(input);
                                String[] seatsToBook = seatInput.split(",");
                                for (String seat : seatsToBook) {
                                    // Remove the '-' from the seat string to get the row and column indices
                                    String[] indices = seat.split("-");
                                    int rowIndex = Character.toUpperCase(indices[0].charAt(0)) - 'A';
                                    int colIndex = Integer.parseInt(indices[1]) - 1;
                                    // Check if the seat is available
                                    if (personBookA[rowIndex][colIndex] == null || personBookA[rowIndex][colIndex].isEmpty()) {
                                        // If the seat is available, ask the user if they want to book it
                                        System.out.println("Are you sure you want to book seat " + seat + " for student " + studentId + "? (Y/n)");
                                        char confirmation = input.next().charAt(0);
                                        // When booking in Hall A
                                        if (confirmation == 'Y' || confirmation == 'y') {
                                            // If the user confirms, book the seat in the personBook array
                                            personBookA[rowIndex][colIndex] = studentId;
                                            System.out.println(">> Seat " + hallA[rowIndex][colIndex] + " has been successfully booked for student " + studentId + ".");
                                            confirmed = true;

                                            // Add entry to booking history
                                            bookingHistory[bookingCount] = formatBookingEntry(seat, " A", studentId, LocalDateTime.now());
                                            bookingCount++;
                                        } else {
                                            // If the user cancels, don't book the seat
                                            System.out.println("Booking cancelled.");
                                            confirmed = false;
                                        }
                                    } else {
                                        System.out.println("Seat " + seat + " is already booked. Please choose another seat.");
                                        confirmed = false;
                                    }
                                }
                                break; // Exit the switch case

                            }
                            case 'b', 'B': {
                                System.out.println("Hall B");
                                System.out.println("-+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+-");
                                System.out.println("\n# INSTRUCTION" + "\n# Single: C-1" + "\n# Multiple (separated by comma): C-1,C-2");
                                System.out.println("> Please select available seat: ");
                                String seatInput = input.next();
                                if (!seatInput.matches("^([A-Z]-\\d+(?:,[A-Z]-\\d+)*)?$")) {
                                    System.out.println("Invalid input. Please enter in the format 'C-1' or 'C-1,C-2'.");
//                                    continue;
                                }
//                                System.out.println("> Please Enter Student ID: ");
                                String studentId = getValidStudentId(input);
                                String[] seatsToBook = seatInput.split(",");
                                for (String seat : seatsToBook) {
                                    // Remove the '-' from the seat string to get the row and column indices
                                    String[] indices = seat.split("-");
                                    int rowIndex = Character.toUpperCase(indices[0].charAt(0)) - 'A';
                                    int colIndex = Integer.parseInt(indices[1]) - 1;
                                    // Check if the seat is available
                                    if (personBookB[rowIndex][colIndex] == null || personBookB[rowIndex][colIndex].isEmpty()) {
                                        // If the seat is available, ask the user if they want to book it
                                        System.out.println("Are you sure you want to book seat " + seat + " for student " + studentId + "? (Y/n)");
                                        char confirmation = input.next().charAt(0);
                                        // When booking in Hall B
                                        if (confirmation == 'Y' || confirmation == 'y') {
                                            // If the user confirms, book the seat in the personBook array
                                            personBookB[rowIndex][colIndex] = studentId;
                                            System.out.println(">> Seat " + hallB[rowIndex][colIndex] + " has been successfully booked for student " + studentId + ".");
                                            confirmed = true;

                                            // Add entry to booking history
                                            bookingHistory[bookingCount] = formatBookingEntry(seat, " B", studentId, LocalDateTime.now());
                                            bookingCount++;
                                        } else {
                                            // If the user cancels, don't book the seat
                                            System.out.println("Booking cancelled.");
                                            confirmed = false;
                                        }
                                    } else {
                                        System.out.println("Seat " + seat + " is already booked. Please choose another seat.");
                                        confirmed = false;
                                    }
                                }
                            }
                            case 'c', 'C': {
                                System.out.println("# HALL C");
                                // Booking in Hall C
                                System.out.println("-+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+-");
                                System.out.println("\n# INSTRUCTION" + "\n# Single: C-1" + "\n# Multiple (separated by comma): C-1,C-2");
                                System.out.println("> Please select available seat: ");
                                String seatInput = input.next();
                                if (!seatInput.matches("^([A-Z]-\\d+(?:,[A-Z]-\\d+)*)?$")) {
                                    System.out.println("Invalid input. Please enter in the format 'C-1' or 'C-1,C-2'.");
                                    continue;
                                }
//                                System.out.println("> Please Enter Student ID: ");
                                String studentId = getValidStudentId(input);
                                String[] seatsToBook = seatInput.split(",");
                                for (String seat : seatsToBook) {
                                    // Remove the '-' from the seat string to get the row and column indices
                                    String[] indices = seat.split("-");
                                    int rowIndex = Character.toUpperCase(indices[0].charAt(0)) - 'A';
                                    int colIndex = Integer.parseInt(indices[1]) - 1;
                                    // Check if the seat is available
                                    if (personBookC[rowIndex][colIndex] == null || personBookC[rowIndex][colIndex].isEmpty()) {
                                        // If the seat is available, ask the user if they want to book it
                                        System.out.println("Are you sure you want to book seat " + seat + " for student " + studentId + "? (Y/n)");
                                        char confirmation = input.next().charAt(0);
                                        // When booking in Hall C
                                        if (confirmation == 'Y' || confirmation == 'y') {
                                            // If the user confirms, book the seat in the personBook array
                                            personBookC[rowIndex][colIndex] = studentId;
                                            System.out.println(">> Seat " + hallC[rowIndex][colIndex] + " has been successfully booked for student " + studentId + ".");
                                            confirmed = true;

                                            // Add entry to booking history
                                            bookingHistory[bookingCount] = formatBookingEntry(seat, " C", studentId, LocalDateTime.now());
                                            bookingCount++;
                                        } else {
                                            // If the user cancels, don't book the seat
                                            System.out.println("Booking cancelled.");
                                            confirmed = false;
                                        }
                                    } else {
                                        System.out.println("Seat " + seat + " is already booked. Please choose another seat.");
                                        confirmed = false;
                                    }
                                }
                            }

                        } if (confirmed) {
                            break;
                        }

                    } while (true);
                }break;
                // B- Hall
                case 'b', 'B': {
                    System.out.println("=======================================================================");
                    System.out.println("                # Hall A - Morning");
                    System.out.println("-----------------------------------------------------------------------");
                    displayHall(hallA, personBookA, totalRow, seatsPerRow);
                    System.out.println("=======================================================================");
                    System.out.println("                # Hall B - Afternoon");
                    System.out.println("-----------------------------------------------------------------------");
                    displayHall(hallB, personBookB, totalRow, seatsPerRow);
                    System.out.println("=======================================================================");
                    System.out.println("                # Hall C - Evening");
                    System.out.println("-----------------------------------------------------------------------");
                    displayHall(hallC, personBookC, totalRow, seatsPerRow);
                    System.out.println("=======================================================================");
                    break;
                }

                // C- Show Time
                case 'c', 'C': {
                    System.out.println("|*******************************************************|");
                    System.out.println("|************ Daily Showtime of  CSTAD Hall ************|");
                    System.out.println("|# A) Morning (10.00AM - 12:30PM)                       |");
                    System.out.println("|# B) Afternoon (03.00PM - 05:30PM)                     |");
                    System.out.println("|# C) Night (07:00PM - 09:30PM)                         |");
                    System.out.println("|*******************************************************|");
                    break;
                }

                // D- Reboot Showtime
                case 'd', 'D': {
                    System.out.println(">>Are you sure you want to reboot showtime? This will clear all booking history and reset all booked seats in the halls. (Y/n)");
                    char confirmation = input.next().charAt(0);
                    if (confirmation == 'Y' || confirmation == 'y') {
                        // Clear booking history
                        bookingCount = 0;
                        // Reset booked seats in all halls
                        for (int i = 0; i < totalRow; i++) {
                            for (int j = 0; j < seatsPerRow; j++) {
                                personBookA[i][j] = null;
                                personBookB[i][j] = null;
                                personBookC[i][j] = null;
                            }
                        }
                        System.out.println(">>Showtime rebooted successfully. Booking history and booked seats have been cleared.");
                    } else {
                        System.out.println(">>Reboot cancelled.");
                    }
                    break;
                }

                // E- History
                case 'e', 'E': {
                    System.out.println("|-+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+-|");
                    System.out.println("|   #Booking History                                      |");
                    System.out.println("|---------------------------------------------------------|");
                    if (bookingCount == 0) {
                        System.out.println("No booking history available.");
                    } else {

                        for (int i = 0; i < bookingCount; i++) {
                            System.out.println("#NO: " + (i + 1));
                            System.out.println(bookingHistory[i]);
                        }
                    }
                    break;
                }

                // F- Exit
                case 'f', 'F':
                    System.out.println("Bye Bye ...! ");
                    break;
                default:
                    System.out.println("-> Opp! out of option");
            }
        } while (opt != 'f' && opt != 'F');
    }
}

