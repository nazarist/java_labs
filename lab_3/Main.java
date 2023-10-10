import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Database.createFile();
        Scanner scanner = new Scanner(System.in);





        boolean repeat = true;
        User user = null;

        while (repeat){
            Helpers.out(Helpers.lineWithUsers(user)  ,
                    " -> Chose number: ",
                    " -> login user - 1",
                    " -> register user - 2",
                    " -> display all database - 3",
                    " -> to finish this session - 4"
            );

            System.out.print("Chose: ");

            switch (scanner.nextLine()) {
                case "1" -> user = Model.userLoginProcess();
                case "2" -> user = Model.userRegisterProcess();
                case "3" -> Database.printDb();
                case "4" -> repeat = false;
                default -> Helpers.out("is not correct");
            }

        }
    }
}
