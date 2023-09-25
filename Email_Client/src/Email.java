import java.io.Serializable;

class Email implements Serializable {

    private static final long serialVersionUID = -2374314867706090224L;
    private String to;
    private String date;
    private String subject;
    private String message;

    public String getDate() {
        return date;
    }


    public Email(String to,String date, String subject, String message) {
        this.to = to;
        this.date = date;
        this.subject = subject;
        this.message = message;
    }

    public String getDetails(){
        String details = "Message Details:\n" +
                "Recipient: "+to+"\n" +
                "Subject: "+subject+"\n";
        return details;
    }

}
