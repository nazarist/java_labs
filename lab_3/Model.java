import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Model
{
    public static User userCreate(String name, String email, String password)
    {
        CrudDb.insert(name, email, Security.hashMake(password));
        return new User(name, email);
    }


    public static User userRegisterProcess()
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

        return Model.userCreate(name, email, password);
    }


    public static User userLoginProcess() {
        Scanner sc = new Scanner(System.in);
        String name;
        long position;
        while (true) {
            System.out.print("Name: ");
            name = sc.nextLine();

            if ((position = CrudDb.findPositionByName(name)) == -1) {
                System.out.println("This user not exist!!!");
                continue;
            }
            break;
        }

        String[] arrData;
        while (true) {
            System.out.print("Password for -> " + name + ":");
            String password = sc.nextLine();

            arrData = CrudDb.get(position).split(" ");

            if (!Security.hashCheck(password, arrData[User.PASS_POSITION])) {
                System.out.println("This password is incorrect!");
                continue;
            }
            break;
        }

        return new User(arrData);
    }
}
