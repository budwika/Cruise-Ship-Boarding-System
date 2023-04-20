import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;

public class Cabin {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String option;

        //making array of objects
        Passenger[][] passenger = new Passenger[12][3];
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 3; ++j) {
                passenger[i][j] = new Passenger();
            }
        }

        Queue queue = new Queue();

        initialise(passenger);  //initialise the array of object

        System.out.println("\nCABIN MANAGEMENT SYSTEM\n");

        System.out.println("A : Add Customer ");
        System.out.println("V : View All Cabins");
        System.out.println("E : Display Empty Cabins");
        System.out.println("D : Clear a Cabin");
        System.out.println("F : Find Cabin from Customer Name");
        System.out.println("O : Order Customers Alphabetically");
        System.out.println("S : Save Information to File");
        System.out.println("L : Load Information from File");
        System.out.println("Q : Quit");

        while (true) {
            System.out.println("Enter your option : A, V, E, D, F, S, L, O, T, Q");
            option = input.next();
            option = option.toLowerCase(); //taking the user input to decide which task to proceed

            if (option.equals("q")){            //if option input equals to "q" the program will break and quit.
                System.out.println("Quitting the program...");
                break;
            }

            switch (option) {
                case "a" :
                    add(passenger, queue);     //using add method to add customers to rooms
                    break;

                case "v" :
                    view(passenger);    //using empty method as well as occupied methods to view all cabins
                    break;

                case "e" :
                    empty(passenger);   //using empty method to look into empty cabins in the hotel
                    break;

                case "d" :
                    delete(passenger, queue);  //using delete method to remove a customer from a cabin
                    break;

                case "f" :
                    find(passenger);    //using find method to find a customer by their name
                    break;

                case "s" :
                    store(passenger);   //using store method to export data into a text file
                    break;

                case "l" :
                    load();             //using load method to load data from the text file
                    break;

                case "o" :
                    ordered(passenger);     //using ordered method to sort the customers according to the alphabetical order
                    break;

                case "t" :
                    expenses(passenger); //using expenses method to show the expenses of each member and total expenses of each cabin
                    break;

                default : validoption();    //using validoption method as the default switch to inform the user to enter a valid option if its not valid option
                    break;
            }
        }
    }

    //initialise method to initialise object of array
    private static void initialise(Passenger[][] psg) {
        for (int x = 0; x < 12; x++) {
            for (int j = 0; j < 3; ++j) {
                psg[x][j].firstName = "e";
                psg[x][j].surName = "e";
                psg[x][j].expenses = 0;
            }
        }
        System.out.println("Initialise");
    }

    //add method to add passengers to cabins
    private static void add(Passenger psg[][], Queue myQueue) {
        Scanner input = new Scanner(System.in);
        String stopadd;
        String firstName;
        String surName;
        int expenses;
        try {
            for (int i = 0; i < 17; i++) {
                if (i < 12) {
                    if (psg[i][0].firstName.equals("e") && psg[i][0].surName.equals("e") && psg[i][0].expenses == 0) {
                        for (int x = 0; x < 3; x++) {
                            System.out.println("Enter the first name of no " + x + " Passenger of cabin " + i); //assigning input values to object's attributes
                            firstName = input.next();
                            System.out.println("Enter the surname of no " + x + " Passenger of cabin " + i);
                            surName = input.next();
                            System.out.println("Enter the expenses of no " + x + " Passenger of cabin " + i);
                            expenses = input.nextInt();

                            psg[i][x] = new Passenger(firstName, surName, expenses);
                            System.out.println("Enter Q if you finished add Passengers.");
                            stopadd = input.next();
                            stopadd = stopadd.toLowerCase();
                            if (stopadd.equals("q")) {
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("Enter the first name of Passenger");
                    firstName = input.next();
                    System.out.println("Enter the surname of Passenger");
                    surName = input.next();
                    System.out.println("Enter the expenses of Passenger");
                    expenses = input.nextInt();
                    myQueue.enQueue(firstName, surName, expenses);
                }
            }
        }
        catch(InputMismatchException e) {           //using try catch exception to handle errors and preventing from crashing
            System.out.println("Enter a expenses in number.");
        }
    }

    private static void empty(Passenger[][] psg) {      //empty method use to check the empty cabins
        int count = 0;
        for (int x = 0; x < 12; x++) {
            //if array value equals "e" then considered as empty
            if (psg[x][0].firstName.equals("e") && psg[x][0].surName.equals("e") && psg[x][0].expenses == 0)
                System.out.println("Cabin " + x + " is empty.");
            else
                count++;
        }
        if (count == 12)
            System.out.println("There are no empty cabins left.");
    }

    private static void view(Passenger[][] psg) {   //view method to view all cabins
        for (int x = 0; x < 12; x++) {
            //using if condition check whether the value is e or if not considered as occupied
            if (!psg[x][0].firstName.equals("e") && !psg[x][0].surName.equals("e") && psg[x][0].expenses != 0)
                System.out.println("Cabin " + x + " is occupied by " + psg[x][0].firstName +" "+ psg[x][0].surName);
            else
                System.out.println("Cabin " + x + " is empty. ");
        }
    }

    //delete method to delete customers from cabins
    private static void delete(Passenger psg[][],Queue myQueue) {
        Scanner input = new Scanner(System.in);
        int delCabin;
        System.out.println("Enter Cabin number to remove");
        delCabin = input.nextInt();
        for (int x = 0; x < 3; x++) {
            psg[delCabin][x] = new Passenger("e", "e", 0);
        }
        psg[delCabin][0].firstName = myQueue.psgq[myQueue.front].firstName;
        psg[delCabin][0].surName = myQueue.psgq[myQueue.front].surName;
        psg[delCabin][0].expenses = myQueue.psgq[myQueue.front].expenses;
        System.out.println("Removed passengers from Cabin " + delCabin + " successfully and added a passenger from a queue.");

        myQueue.deQueue();
    }

    private static void find(Passenger[][] psg) {      //find method to find a customer by their name
        Scanner input = new Scanner(System.in);
        String cname;
        int count = 0;
        System.out.println("Enter customer name: ");
        cname = input.next();
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 3; y++) {
                if (psg[x][y].firstName.equals(cname))
                    System.out.println("The Cabin number of " + cname + " is " + x);
                else
                    count++;
            }
        }
        if (count == 36)
            System.out.println("The passenger with " + cname + " doesn't exists.");
    }

    private static void store(Passenger[][] psg) {     //store method to store data into a text file
        try {
            FileWriter fOutput = null;      //use file writer utility to write data into a text file
            try {
                fOutput = new FileWriter("C:/Users/This PC/Desktop/SD2 Viva/task2.txt");
                for (int x = 0; x < 12; x++) {
                    if (!psg[x][0].firstName.equals("e")) {
                        for (int y = 0; y < 3; y++) {
                            if (!psg[x][y].firstName.equals("e")) {
                                fOutput.write(y + " Passenger of cabin " + x + " is " + psg[x][y].firstName + " " + psg[x][y].surName + "\n" + "Expenses = " + psg[x][y].expenses);
                                fOutput.write("\n");
                            }
                        }
                    } else {
                        fOutput.write("Cabin " + x + " is Empty. ");
                        fOutput.write("\n");
                    }
                }
            } finally {
                fOutput.close();
                System.out.println("Successfully wrote to the file.");
            }
        } catch (IOException e) {
            System.out.println("Error IO Exception is:" + e);
        }
    }

    //use load method to load data from the text file
    private static void load() {
        try {
            File fInput = new File("C:/Users/This PC/Desktop/SD2 Viva/task2.txt");
            Scanner rf = new Scanner(fInput);   //used file utility to load data from the text file
            String fileLine;
            while (rf.hasNext()) {
                fileLine = rf.nextLine();
                System.out.println(" " + fileLine);
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("Error IO Exception is:" + e);
        }
    }

    //use ordered method to order the names alphabetical order using compareTo method
    private static void ordered(Passenger[][] psg) {
        int count = 0;
        String[] snames = new String[36];
        while (count < 36) {
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 3; j++) {
                    snames[count] = psg[i][j].firstName;
                    count++;
                }
            }
        }
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 36; j++) {
                if (snames[i].compareTo(snames[j]) < 0) {
                    String temp = snames[i];
                    snames[i] = snames[j];
                    snames[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(snames));
    }

    //using expenses method to calculate total expenses as well as individual expenses
    private static void expenses(Passenger[][] psg) {
        int total = 0;
        for (int x = 0; x < 12; x++) {
            if (!psg[x][0].firstName.equals("e")) {
                for (int y = 0; y < 3; y++) {
                    if (!psg[x][y].firstName.equals("e")) {
                        System.out.println(y + " Passenger of cabin " + x + " is " + psg[x][y].firstName + " " + psg[x][y].surName + "\n" + "Expenses = " + psg[x][y].expenses);
                        total = total + psg[x][y].expenses;
                    }
                }
                System.out.println("Total expenses of cabin " + x + " is " + total);
            }
        }
    }

    private static void validoption(){      //using validoption method to inform user to enter valid option
        System.out.println("Enter a valid option.");
    }
}