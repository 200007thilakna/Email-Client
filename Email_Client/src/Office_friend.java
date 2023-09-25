class Office_friend extends Official implements BirthdayGreetingReceiver {
    private final String birthday;

    public Office_friend(String designation, String birthday) {
        super(designation);
        this.birthday = birthday;
    }

    public Office_friend(String[] arr) {
        super(arr);
        this.type = "Office_friend";
        this.birthday = arr[3];
    }

    @Override
    public String getBirthday() {
        return birthday;
    }

    @Override
    public String getWish() {
        return "Wish you a happy birthday!!\n";
    }
}
