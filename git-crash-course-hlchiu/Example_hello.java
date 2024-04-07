import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Example_hello{
    public static void main(String[] args){
        try {
            String content = "Hello, 5xRuby";
            // Git's internal representation includes the type of object, the size of the content,
            // a null byte, and then the content itself.
            String gitInternalFormat = "blob " + content.length() + "\0" + content;
            
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] encodedhash = digest.digest(gitInternalFormat.getBytes(StandardCharsets.UTF_8));
            
            System.out.println("SHA-1 Hash (Git style): " + bytesToHex(encodedhash));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-1 Algorithm not found");
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}