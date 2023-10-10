import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security
{
    public static String hashMake(String password)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Конвертуємо байти у шістнадцятковий формат
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean hashCheck(String password, String hash)
    {
        String hashedInput = hashMake(password);
        return hashedInput != null && hashedInput.equals(hash);
    }

}
