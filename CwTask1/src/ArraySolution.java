import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArraySolution
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String option;
        String[] hotel = new String[12]; //made variables
        // initialise
        initialise(hotel);

        //menu to display the function of each task and what input you should give to proceed
        System.out.println("\nCABIN MANAGEMENT SYSTEM\n");

        System.out.println("A : Add Customer ");
        System.out.println("V : View All Cabins");
        System.out.println("E : Display Empty Cabins");
        System.out.println("D : Clear a Cabin");
        System.out.println("F : Find Cabin from Customer Name");
        System.out.println("S : Save Information to File");
        System.out.println("L : Load Information from File");
        System.out.println("O : Order Customers Alphabetically");
        System.out.println("Q : Quit the program");

        //making the option menu

        while (true) {
            System.out.println("Enter your option : A, V, E, D, F, S, L, O, Q");
            option = input.next();      //taking the user input to decide which task to proceed
            option=option.toLowerCase(); //change the option to lowerCase to proceed the task

            if (option.equals("q")){
                System.out.println("Quitting the program...");
                break;
            }

            switch (option) {
                case "a" :
                    add(hotel); //using add method to add customers to rooms
                    break;

                case "v" :
                    view(hotel);    //using view methods to view all cabins
                    break;

                case "e" :
                    empty(hotel);   //using empty method to look into empty cabins in the hotel
                    break;

                case "d" :
                    delete(hotel);  //using delete method to remove a customer from a cabin
                    break;

                case "f" :
                    find(hotel);    //using find method to find a customer by their name
                    break;

                case "s" :
                    store(hotel);   //using store method to export data into a text file
                    break;

                case "l" :
                    load(); //using load method to load data from the text file
                    break;

                case "o" :
                    ordered(hotel); //using ordered method to sort the customers according to the alphabetical order
                    break;

                default :
                    validoption(); //using validoption method to inform the user to enter a valid option if its not valid option
                    break;
            }
        }
    }



    private static void initialise(String[] cabins) {    //initialise method to assign e for every element in the array
        for (int x = 0; x < 12; x++ )
            cabins[x] = "e";
        System.out.println( "initialise ");
    }

    private static void add(String[] cabins) {  //add method to add passengers to cabins
        Scanner input = new Scanner(System.in);
        int roomNum;
        String roomName;
        try {
            while (true) {  //using for loop to iterate
                System.out.println("Enter room number (0-11) or 12 to stop:");
                roomNum = input.nextInt();
                input.nextLine();
                if (roomNum < 12) { //put if condition to break the loop when its false
                    if (cabins[roomNum].equals("e")) {
                        System.out.println("Enter name for room " + roomNum + " :");
                        roomName = input.nextLine();
                        cabins[roomNum] = roomName;
                    } else {
                        System.out.println("This room is already occupied");
                    }
                }
                else {
                    break;
                }
            }
        }
        catch(InputMismatchException e) {
            System.out.println("Enter a valid Room number.");
        }
    }

    private static void view(String[] cabins) { //view method to view all cabins
        for (int x = 0; x < 12; x++) {
            if (!cabins[x].equals("e"))     //using if condition check whether the value is e or if not considered as occupied
                System.out.println("Room " + x + " occupied by " + cabins[x]);
            else
                System.out.println("Room " + x + " is empty");
        }
    }

    private static void empty(String[] cabins) {    //empty method use to check the empty cabins
        int count = 0;
        for (int x = 0; x < 12; x++) {
            if (cabins[x].equals("e"))      //if array value equals "e" then considered as empty
                System.out.println("Room " + x + " is empty");
            else
                count++;
        }
        if (count == 12)
            System.out.println("There are no empty cabins left.");
    }

    private static void delete(String[] cabins) {       //delete method to delete customers from cabins
        Scanner input = new Scanner(System.in);
        int delroom;
        try {
            System.out.println("Enter room number to remove");
            delroom = input.nextInt();
            cabins[delroom] = "e";
            System.out.println("Removed customer from room " + delroom + " successfully.");
        }
        catch(InputMismatchException e) {
            System.out.println("Enter a valid Room number.");
        }
    }

    private static void find(String[] cabins){      //find method to find a customer by their name
        int count = 0;
        Scanner input = new Scanner(System.in);
        String cname;
        System.out.println("Enter customer name");
        cname = input.next();
        for (int x = 0; x < 12; x++) {
            if (cabins[x].equals(cname))
                System.out.println("The room number of " + cname + " is " + x);
            else
                count++;
        }
        if (count == 12)
        System.out.println("The passenger with " + cname + " doesn't exists.");
    }

    private static void store(String[] cabins){     //store method to store data into a text file
        try {
            FileWriter fOutput = null;      //use file writer utility to write data into a text file
            try {
                fOutput = new FileWriter("C:/Users/This PC/Desktop/SD2 Viva/task1.txt");
                for (int x = 0; x < 12; x++) {
                    if (!cabins[x].equals("e")) {
                        fOutput.write("Room " + x + " occupied by " + cabins[x]);
                        fOutput.write("\n");
                    } else  {
                        fOutput.write("Room " + x + " is Empty. " );
                        fOutput.write("\n");
                    }
                }
            }
            finally{
                fOutput.close();
                System.out.println("Successfully wrote to the file.");
            }
        }
        catch(IOException r){
            System.out.println("Error IO Exception is:" + r);}
    }

    private static void load(){     //use load method to load data from the text file
        try {
            File fInput = new File("C:/Users/This PC/Desktop/SD2 Viva/task1.txt");
            Scanner rf = new Scanner(fInput);   //used file utility to load data from the text file
            String fileLine;
            while (rf.hasNext()) {
                fileLine = rf.nextLine();
                System.out.println(" " + fileLine);
            }
            rf.close();
        }catch (IOException e){
            System.out.println("Error IO Exception is:" + e);
        }
    }

    private static void ordered(String[] cabins){  //use ordered method to order the names alphabetical order using compareTo method
        for(int i = 0; i<12 ; i++){
            for(int j = 0; j<12 ; j++){
                if(cabins[i].compareTo(cabins[j])<0){
                    String temp = cabins[i];
                    cabins[i] = cabins[j];
                    cabins[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(cabins));
    }

    private static void validoption(){      //using validoption method to inform user
        System.out.println("Enter a valid option.");
    }
}
