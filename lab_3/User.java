public class User
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
        String line = CrudDb.get(this.currentPosition);
        assert line != null;
        return line.split(" ");
    }

    private void insert(String[] arrData)
    {
        String line = CrudDb.buildLine(arrData);

        CrudDb.update(currentPosition, line);
    }


    public void updateName(String name)
    {
        String[] arrData = getUser();
        arrData[NAME_POSITION] = name;

        insert(arrData);
        this.name = name;
    }


    public void updateEmail(String email)
    {
        String[] arrData = getUser();
        arrData[EMAIL_POSITION] = email;

        insert(arrData);
        this.email = email;
    }


    public void updatePass(String password)
    {
        String[] arrData = getUser();
        arrData[PASS_POSITION] = Security.hashMake(password);

        insert(arrData);
    }


    public String getName() {return name;}
    public String getEmail() {return email;}
}
