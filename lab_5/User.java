import java.util.Scanner;

final class User
{
    private String name;
    private String email;

    private final long currentPosition; //позиція у файлі


    // позиція у лінії
    public static final int NAME_POSITION = 0;
    public static final int EMAIL_POSITION = 1;
    public static final int PASS_POSITION = 2;


    public User(String name, String email)
    {
        this.name = name;
        this.email = email;

        this.currentPosition = CrudDb.findPositionByName(name);
    }

    public User(String[] arrData)
    {
        this.name = arrData[NAME_POSITION];
        this.email = arrData[EMAIL_POSITION];

        this.currentPosition = CrudDb.findPositionByName(this.name);
    }

    private String[] getUser()
    {
        String line = get();
        assert line != null;
        return line.split(" ");
    }


    public String get()
    {
        return CrudDb.get(this.currentPosition);
    }

    private void update(String[] arrData)
    {
        String line = CrudDb.buildLine(arrData);

        CrudDb.update(currentPosition, line);
    }


    public void updateName()
    {
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.print("Name: ");
            name = sc.nextLine();
            if (CrudDb.findPositionByName(name) == -1)
            {
                break;
            }
            System.out.println("name is exist!!!");
        }

        String[] arrData = getUser();
        arrData[NAME_POSITION] = name;

        update(arrData);
        this.name = name;
    }


    public void updateEmail()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Email: ");
        String newEmail = sc.nextLine();


        String[] arrData = getUser();
        arrData[EMAIL_POSITION] = newEmail;

        update(arrData);
        this.email = email;
    }



    public void updatePass()
    {
        Scanner sc = new Scanner(System.in);
        String[] arrData;

        while (true) {
            System.out.print("Password for -> " + name + ": ");
            String password = sc.nextLine();

            String passwordFromFile = CrudDb.get(this.currentPosition).split(" ")[User.PASS_POSITION];

            if (!Security.hashCheck(password, passwordFromFile)) {
                System.out.println("This password is incorrect!");
                continue;
            }
            break;
        }

        System.out.print("print new password for -> " + name + ": ");
        String newPass = sc.nextLine();


        String[] userFromFile = this.getUser();
        userFromFile[PASS_POSITION] = Security.hashMake(newPass);

        update(userFromFile);
        System.out.println("password changed ;)");
    }


    public String getName() {return name;}
    public String getEmail() {return email;}
}
