import java.io.*;
/**
 * this class handles all the serialization and
 * deserialization of the email objects
 **/
class SerializingAndDeserializingOperations{
    private static File serFile;

    public static File getSerFile() {
        String path = System.getProperty("user.dir");
        if( serFile== null) {
            try {

                serFile = new File(path+"\\sentEmailList.txt");

                serFile.createNewFile();
                serFile.canWrite();
                serFile.canRead();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return serFile;
    }

    /**
     * this method serializes an email and save it in a text file
     **/
    public static void serialize(Email email) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(getSerFile(),true);

        // serialize the objects and save without a header which is given by ObjectOutputStream
        if(getSerFile().length()==0){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(email);
            objectOutputStream.close();
        }

        else{
            CustomObjectOutputStream objectOutputStream = new CustomObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(email);
            objectOutputStream.close();
        }

        fileOutputStream.close();
        //System.out.println("Serialization done!!");

    }

    /**
     * this method deserializes the objects which are saved in the hard disk
     * and print the details of the emails prior to a given date
     **/
    public static void deserializeAndPrintEmails(String date) throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(getSerFile());

        if(getSerFile().length()==0){
            return;
        }

        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);


        while(true)
        {
            try {
                Object object = objectInputStream.readObject();
                Email email = (Email)(object);

                //System.out.println(email.getDate());

                if(date.equals(email.getDate())){
                    System.out.println(email.getDetails());
                }


            }
            catch (EOFException ex){
                System.out.println("Finished!");
                break;
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

        objectInputStream.close();
        fileInputStream.close();
        //System.out.println("Deserialization done!!");

    }




}
/**
 * this is custom object output stream class which overrides the ObjectOutputStream
 * and this will not add a header part when serializing
 **/
class CustomObjectOutputStream extends ObjectOutputStream {

    CustomObjectOutputStream() throws IOException
    {
        super();
    }
    CustomObjectOutputStream(OutputStream outputstream) throws IOException
    {
        super(outputstream);
    }
    public void writeStreamHeader() throws IOException
    {
        return;
    }
}
