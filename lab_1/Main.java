import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static Database db = new Database();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        boolean repeat = true;
        while (repeat){
            Helpers.out("===============================",
                    "Chose number: ",
                    "insert text in database - 1",
                    "or display all database - 2",
                    "to finish this session - 3"
            );
            System.out.print("Chose: ");

            switch (scanner.nextLine()) {
                case "1" -> {
                    System.out.print("write text: ");
                    db.set(scanner.nextLine());
                }
                case "2" -> db.printDb();
                case "3" -> repeat = false;
                default -> Helpers.out("is not correct");
            }
        }
    }
}
