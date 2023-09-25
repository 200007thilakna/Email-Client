class Personal extends Recipient implements BirthdayGreetingReceiver {

    private final String birthday;
    private final String nickName;

    public Personal(String birthday, String nickName) {
        this.birthday = birthday;
        this.nickName = nickName;
    }

    public Personal(String[] arr) {
        super();
        this.type = "Personal";
        this.name = arr[0];
        this.nickName = arr[1];
        this.email = arr[2];
        this.birthday = arr[3];


    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String getBirthday() {
        return birthday;
    }

    @Override
    public String getWish() {
        return "Hugs and love on your birthday!!\n";
    }
}
