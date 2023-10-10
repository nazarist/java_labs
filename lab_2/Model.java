import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Scanner;

public class Model
{
    private static final Database db = new Database();

    public static User userCreate(String name, String email, String password)
    {
        (new CrudDb()).insert(name, email, Security.hashMake(password));
        return new User(name, email);
    }


    public static void userCreateProcess()
    {
        Scanner sc = new Scanner(System.in);
        String name;
        while (true){
            System.out.print("Name: ");
            name = sc.nextLine();
            if (CrudDb.findPositionByName(name) == -1)
            {
                break;
            }
            System.out.println("name is exist!!!");
        }

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Model.userCreate(name, email, password);
    }


    private static class User
    {
        private String name;
        private String email;

        private long currentPosition; //позиція у файлі


        public User(String name, String email)
        {
            this.name = name;
            this.email = email;
        }


        public void updateName(String name)
        {

        }
        public void updateEmail(){}
        public void updatePass(){}


        public String getName() {return name;}
        public String getEmail() {return email;}
    }

}
