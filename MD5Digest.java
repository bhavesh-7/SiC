import java.security.*;

public class MD5Digest {
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hashText = new StringBuilder();
            for (byte b : messageDigest) {
                hashText.append(String.format("%02x", b));
            }
            return hashText.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String str = "Hello World";
        System.out.println(str);
        System.out.println("MD5 Hash: " + getMD5(str));
    }
}