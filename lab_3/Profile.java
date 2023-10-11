import java.util.Scanner;

public class Profile
{

    public static User user = null;

    public static void menu()
    {
        while (true){
            if (user == null){
                Profile.user = guest();
            }else {
                auth(Profile.user);
            }
        }
    }
    public static User guest()
    {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;

        while (repeat){
            Helpers.out( "========{----}=========",
                    " -> Chose number: ",
                    " -> login user - 1",
                    " -> register user - 2",
                    " -> to finish this session - 3"
            );

            System.out.print("Chose: ");

            switch (scanner.nextLine()) {
                case "1" -> {return Model.userLoginProcess();}
                case "2" -> {return Model.userRegisterProcess();}
                case "3" -> System.exit(0);
                default -> Helpers.out("is not correct");
            }
        }


        return null;
    }


    public static void auth(User user)
    {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;


        while (repeat){
            if (Profile.user == null) return;

            Helpers.out( Helpers.lineWithUsers(user),
                    " -> Chose number: ",
                    " -> change credentials - 1",
                    " -> display name and email - 2",
                    " -> display all database - 3",
                    " -> logout - 4",
                    " -> to finish this session - 5"
            );

            System.out.print("Chose: ");

            switch (scanner.nextLine()) {
                case "1" -> changeCredentials(user);
                case "2" ->
                        Helpers.out("+-----------------------------+",
                            "name: " + user.getName(),
                            "email: " + user.getEmail(),
                            "+-----------------------------+"
                        );
                case "3" -> Database.printDb();
                case "4" -> {
                    Profile.user = null;
                    System.out.println(" -> logout is successful ;)");
                }
                case "5" -> System.exit(0);
                default -> Helpers.out("is not correct");
            }
        }
    }


    public static void changeCredentials(User user)
    {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;

        while (repeat){
            Helpers.out( Helpers.lineWithUsers(user),
                    " -> what do you want a change: ",
                    " -> name - 1",
                    " -> email - 2",
                    " -> password - 3",
                    " -> exit - 4"
            );

            System.out.print("Chose: ");

            switch (scanner.nextLine()) {
                case "1" -> user.updateName();
                case "2" -> user.updateEmail();
                case "3" -> user.updatePass();
                case "4" -> repeat = false;
                default -> Helpers.out("is not correct");
            }
            Helpers.out(
                    user.get());
        }
    }


}
