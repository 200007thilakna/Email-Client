import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

class BirthdayGreetings {


    /**
     * this method sends birthday wishes by traversing the greetings' receiver array
     * and selecting the recipients whom a birthday greeting should be sent
     **/
    static void sendBirthdayWishes(ArrayList<BirthdayGreetingReceiver> birthdayGreetingReceivers) throws IOException, MessagingException {
        System.out.println("Sending Birthday Wishes!");
        // get the current date
        LocalDate date = LocalDate.now();
        String[] YY_MM_DD = date.toString().split("-");
        String month = YY_MM_DD[1];
        String today = YY_MM_DD[2];

        // find the recipients who has birthday today
        for(int i=0; i<birthdayGreetingReceivers.size();i++){


            String type = ((Recipient)birthdayGreetingReceivers.get(i)).type;
            String birthday = ((BirthdayGreetingReceiver)birthdayGreetingReceivers.get(i)).getBirthday();
            String[] birth_date = birthday.split("/");
            String B_Month = birth_date[1];
            String B_date = birth_date[2];
            if(type.equals("Office_friend") && B_date.equals(today )&& B_Month.equals(month))
            {
                Office_friend office_friend = (Office_friend)(birthdayGreetingReceivers.get(i));
                EmailOperations.send(office_friend.getEmail(),"Happy Birthday",office_friend.getWish());

            }
            else if(type.equals("Personal")&& B_date.equals(today )&& B_Month.equals(month))
            {
                Personal personal = (Personal)(birthdayGreetingReceivers.get(i));
                EmailOperations.send(personal.getEmail(),"Happy Birthday",personal.getEmail());


            }

        }
        System.out.println("All the greetings sent successfully!");


    }

    /**
     * this method is used to print all the recipients whom birthday is on a given day
     **/
    public static void findBirthdays(String M, String D ) throws IOException {


        for(int i=0; i<Email_Client.birthdayGreetingReceivers.size();i++){
            String birthday = null;

            String type = ((Recipient)Email_Client.birthdayGreetingReceivers.get(i)).type;
            if(type.equals("Office_friend"))
            {
                Office_friend Office_friend = (Office_friend)(Email_Client.birthdayGreetingReceivers.get(i));
                birthday = Office_friend.getBirthday();
                String[] birth_date = birthday.split("/");
                String B_Month = birth_date[1];
                String B_date = birth_date[2];
                if(B_date.equals(D)&& B_Month.equals(M)){
                    System.out.println(Office_friend.getName());
                }

            }
            else if(type.equals("Personal"))
            {
                Personal personal = (Personal)(Email_Client.birthdayGreetingReceivers.get(i));
                birthday = personal.getBirthday();
                String[] birth_date = birthday.split("/");
                String B_Month = birth_date[1];
                String B_date = birth_date[2];
                if(B_date.equals(D)&& B_Month.equals(M)){
                    System.out.println(personal.getName());
                }

            }
        }
    }

}
