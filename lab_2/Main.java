import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Database.createFile();
        Scanner scanner = new Scanner(System.in);


        boolean repeat = true;
        while (repeat){
            Helpers.out(
                    " -> Chose number: ",
                    " -> login user - 1",
                    " -> display all database - 2",
                    " -> to finish this session - 3"
            );
            System.out.print("Chose: ");

            switch (scanner.nextLine()) {
                case "1" -> Model.userCreateProcess();
                case "2" -> Database.printDb();
                case "3" -> repeat = false;
                default -> Helpers.out("is not correct");
            }

        }
    }
}
