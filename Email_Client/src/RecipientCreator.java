class RecipientCreator {

    public static Recipient createRecipient(String type,String[] data) {
        Recipient recipient = null;

        if (type.equals("Official")) {
            recipient = new Official(data);

        }
        else if (type.equals("Office_friend")) {
            recipient = new Office_friend(data);
        }
        else if (type.equals("Personal")) {
            recipient = new Personal(data);
        }

        return recipient;
    }

}
