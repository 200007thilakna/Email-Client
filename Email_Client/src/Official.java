class Official extends Recipient{

    protected String designation;

    public Official(String designation) {
        this.designation = designation;
    }

    public Official(String[] arr) {
        this.type = "Official";
        this.name = arr[0];
        this.email = arr[1];
        this.designation = arr[2];
    }

    public String getDesignation() {
        return designation;
    }
}
