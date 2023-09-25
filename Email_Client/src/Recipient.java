abstract class Recipient {
    protected String type;
    protected String name;
    protected String email;


    protected Recipient() {

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        name = name.replace(" ","");
        return name;
    }
}

