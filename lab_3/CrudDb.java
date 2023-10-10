import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CrudDb extends Database
{
    public static String buildLine(String[] words)
    {
        StringBuilder line = new StringBuilder();

        for (String word : words){
            line.append(word).append(" ");
        }

        return line.toString();
    }
    public static void insert(String... words)
    {
        Database.set(buildLine(words));
    }



    public static long findPositionByName(String name) {
        try (RandomAccessFile file = new RandomAccessFile(Database.file, "r")) {
            long currentPosition = 0;
            String line;
            while ((line = file.readLine()) != null) {
                String nameInFile = line.split(" ")[0];
                if (nameInFile.equals(name)) {
                    return currentPosition;
                }
                currentPosition = file.getFilePointer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String get(long position)
    {
        if (position == -1) return null;
        try (RandomAccessFile file = new RandomAccessFile(Database.file, "r")) {
            file.seek(position);
            return file.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String get(String name)
    {
        long position = findPositionByName(name);
        return get(position);
    }

    public static void update(long position, String newLine)
    {
        if (position == -1) return;
        try (RandomAccessFile file = new RandomAccessFile(Database.file, "rw")) {
            file.seek(position);
            file.writeBytes(newLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(String name, String newLine)
    {
        long position = findPositionByName(name);
        update(position, newLine);
    }
}
