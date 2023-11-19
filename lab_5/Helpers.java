public class Helpers
{
    public static void out(String... text)
    {
        for (String line : text){
            System.out.println(line);
        }
    }


    public static String lineWithUsers(User user)
    {
        if (user != null)
        {
            return "==========={"+ user.getName()+"}===========";
        }
        return "==========={----}===========";
    }



}
