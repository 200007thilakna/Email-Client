import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.time.LocalDate;

/**
 * this class does the all the tasks linked with sending emails(birthday greetings and custom emails)
 *      creating an email
 *      sending an email
 *      serialize the email by calling the relevant methods
 **/
class EmailOperations{

    private final static String myAccount = "thilaknakumaratunga@gmail.com";
    private final static String password = "mxztqdqfsshqvmig";

    /**
     * this method send the email to the recipient by connecting to the gmail server
     * **/
    public  static void send(String recipient,String subject, String text) throws MessagingException, IOException {

        Properties properties = new Properties();

        properties.setProperty("mail.smtp.host","smtp.gmail.com");
        // connect to the relevant port
        properties.setProperty("mail.smtp.port","587");
        // enabling a secure connection
        properties.put("mail.smtp.starttls.enable", "true");
        // enable authentication
        properties.setProperty("mail.smtp.auth", "true");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount,password);
            }
        });
        Message message = createMessage(session, recipient,subject,text);
        assert message != null;
        try {
            Transport.send(message);
            //System.out.println("Your message has been sent!");

            // Serialize the email
            String date = LocalDate.now().toString().replace("-","/");
            Email email = new Email(recipient,date,subject,text);
            SerializingAndDeserializingOperations.serialize(email);
            System.out.println("Your message has been sent!");


        }catch (SendFailedException ex){
            System.out.println("Address not found!\n");
            return;
        }

    }

    /**
     * this method creates a message and set the corresponding values in the message
     **/
    private static Message createMessage(Session session, String recipient, String subject, String text) throws MessagingException, IOException {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EmailOperations.myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(text);
            return message;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

}

}
