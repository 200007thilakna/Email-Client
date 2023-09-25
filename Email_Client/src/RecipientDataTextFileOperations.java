import java.io.*;
import java.util.ArrayList;

/**
 * This class does all the operations linked with the
 * recipient database text file
 */

class RecipientDataTextFileOperations {

    /**
     * Email client have a single text file to insert and retrieve data
     * **/
   private static File fileObject;
   //private static RecipientDataTextFileOperations instance;


   public static File getFileObject() {
       String path = System.getProperty("user.dir");
       if( fileObject== null) {
           try {

               fileObject = new File(path+"\\clientList.txt");

               // if there is no such file create a new file
               fileObject.createNewFile();

               fileObject.canWrite();
               fileObject.canRead();

           }
           catch (Exception e) {
               e.printStackTrace();
           }
       }
       return fileObject;
    }




    /**
     * this method writes the user input(recipient details)
     * in to the text file
     **/
    public static void write(String string) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(getFileObject(),true));
        writer.write(string+"\n");
        System.out.println("you have successfully added a new recipient");
        writer.close();
    }

    /**
     * this method loads the recipients in to the application
     * by creating objects based on their types
     **/
    public static ArrayList<Recipient> load_recipients(ArrayList<Recipient> array) throws IOException {

        System.out.println("Recipients loading!");
        BufferedReader reader = new BufferedReader(new FileReader(getFileObject()));
        String details  =  null;
        try {
            while((details = reader.readLine())!=null){

                details.replace("\n","");
                String[] list_1 = details.split(":");


                //taking the type of the recipient
                String type = list_1[0].trim();

                //other details (name,email,etc)
                String[] data = list_1[1].split(",");

                //call the recipient creator and add the returned object to the array
                array.add(RecipientCreator.createRecipient(type,data));
            }
            System.out.println("Recipients Loaded successfully!");
            reader.close();
        }catch (Exception e){

        }

       return array;
    }

    /**
     * this method is used to load the recipients whom a birthday greeting
     * should be sent, to the application
     **/


    public static void load_greetings_receivers() throws IOException {

        System.out.println("Birthday greetings receivers loading!");
        for (int i = 0; i < Email_Client.recipients.size(); i++) {
            if (Email_Client.recipients.get(i).type.equals("Office_friend") || Email_Client.recipients.get(i).type.equals("Personal"))

                Email_Client.birthdayGreetingReceivers.add((BirthdayGreetingReceiver) Email_Client.recipients.get(i));
        }
        System.out.println("Birthday greetings receivers successfully loaded!");
    }



}
