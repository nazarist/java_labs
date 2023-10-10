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
        this.createFile();
    }

    public void createFile()
    {
        try {
            if (file.createNewFile()) System.out.println("file created");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void set(String text) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        writer.write(text);
        writer.append("")
                .append("\n")
                .flush();

        writer.close();
        System.out.println("text is write");
    }

    public void printDb() throws IOException
    {

        BufferedReader bfReader = new BufferedReader(new FileReader(file));
        String current;

        Helpers.out("+------------------------+");
        while ((current = bfReader.readLine()) != null){
            Helpers.out(current);
        }
        Helpers.out("+------------------------+");

        bfReader.close();
    }

}
