import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CrudDb extends Database
{

    public static final int LEN = 130;
    public static String buildLine(String[] words)
    {
        StringBuilder line = new StringBuilder();

        for (String word : words){
            line.append(word).append(" ");
        }

        return line.toString();
    }


    public static int checkLen(String line)
    {
        return LEN - line.length();
    }


    public static boolean lenError(String line)
    {
        if (checkLen(line) < 0){
            System.out.println("very long");
            return false;
        }
        return true;
    }

    public static void insert(String... words)
    {
        String line = buildLine(words);
        StringBuilder builder = new StringBuilder(line);

        while (LEN > builder.length()){
            builder.append(' ');
        }

        Database.set(builder.toString());
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
            if (lenError(newLine)){
                file.seek(position);
                StringBuilder paddedLine = new StringBuilder(newLine);

                while (LEN - 1  > paddedLine.length()) {
                    paddedLine.append(' ');
                }

                file.writeBytes(paddedLine.toString());
            }
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
