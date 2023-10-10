import java.io.*;

public class Database
{
    protected static final String FILE_NAME = "database.txt";
    protected static File file;


    static {
        file = new File(FILE_NAME);
    }

    public Database()
    {
        createFile();
    }

    public static void createFile()
    {
        try {
            if (file.createNewFile()) System.out.println("file created");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void set(String text)
    {
        BufferedWriter writer = getBfWriter();
        try {
            writer.write(text);
            writer.append("")
                    .append("\n")
                    .flush();

            writer.close();
            System.out.println("text is write");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    protected static BufferedReader getBfReader()
    {
        try {
            return new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    protected static BufferedWriter getBfWriter()
    {
        try {
            return new BufferedWriter(new FileWriter(file, true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void printDb()
    {
        try {
            BufferedReader bfReader = getBfReader();
            String current;

            Helpers.out("+--------------------------------------------------------------------------------------------------+");
            while ((current = bfReader.readLine()) != null){
                Helpers.out(current);
            }
            Helpers.out("+--------------------------------------------------------------------------------------------------+");

            bfReader.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
