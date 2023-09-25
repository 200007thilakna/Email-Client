// your index number
// 200327L
//KUMARATUNGA K.G.T.R

import java.util.ArrayList;
import java.util.Scanner;

public class Email_Client {

    /**
     * email client have a file object(clientList.txt)
     * email client should maintain each email recipient object when running
     * A list of recipients to whom a birthday greeting should be sent is maintained in the application.
     **/


    /**
     * static array for store recipients
     */
    public static ArrayList<Recipient> recipients = new ArrayList<>();

    /**
     * static array for email_receivers
     */
    public static ArrayList<BirthdayGreetingReceiver> birthdayGreetingReceivers = new ArrayList<>();
    private static String input_1;

    public static void main(String[] args) {

        try {

            /**maintain each email recipient in the application*/
            RecipientDataTextFileOperations.load_recipients(recipients);

            /**maintain each birthday greeting receiver in the application*/
            RecipientDataTextFileOperations.load_greetings_receivers();

            /** sent wishes and save the emails*/
            BirthdayGreetings.sendBirthdayWishes(birthdayGreetingReceivers);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application");

            int option = scanner.nextInt();

            scanner.nextLine();

            switch (option) {
                case 1:
                    String details = scanner.nextLine();

                    //check for validity of the input
                    String[] check01 = details.split(":");
                    String[] check02 = details.split(",");
                    if (check01.length == 2 && check02.length >= 3 && check02.length <= 4) {
                        // writing the details in to the textfile
                        RecipientDataTextFileOperations.write(details);

                    } else
                        System.out.println("Invalid input!\nTry again!");
                    break;

                case 2:
                    try {
                        // input format - email, subject, content
                        String input = scanner.nextLine();
                        String[] input_list = input.split(",");
                        String recipient = input_list[0];
                        String subject = input_list[1];
                        String content = input_list[2];

                        // code to send an email and it will be serialized
                        EmailOperations.send(recipient, subject, content);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid input!\nTry again!");
                    }
                    break;

                case 3:

                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    String input = scanner.nextLine();
                    try {
                        // check the validity of the input
                        String list_3[] = input.split("/");
                        //String Y = list_3[0];
                        String M = list_3[1];
                        String D = list_3[2];

                        // code to print recipients who have birthdays on the given date
                        BirthdayGreetings.findBirthdays(M, D);

                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Enter an valid input!");
                        break;
                    }
                    break;
                case 4:

                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
                    try {
                        String input_1 = scanner.nextLine();
                        SerializingAndDeserializingOperations.deserializeAndPrintEmails(input_1);

                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Enter an valid input!");
                        break;
                    } catch (Exception e) {
                        System.out.println("problem occured while deserializing!");
                        break;
                    }
                    break;
                case 5:

                    // code to print the number of recipient objects in the application
                    System.out.println(recipients.size());
                    break;

            }

        } catch (Exception e) {
            System.out.println("Unexpected error occured!!");
            System.out.println(e.getMessage());
        }

    }

}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)