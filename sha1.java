import java.security.*;

public class sha1 {
    public static void main(String[] args) {
        String input = "Hello, World!";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            System.out.println("Algorithm: " + md.getAlgorithm());
            System.out.println("Provider: " + md.getProvider());
            System.out.println("ToString: " + md.toString());
            byte[] inputData = input.getBytes();
            byte[] sha1Hash = md.digest(inputData);
            StringBuilder hexHash = new StringBuilder();
            for (byte b : sha1Hash) {
                hexHash.append(String.format("%02x", b));
            }
            System.out.println("Input: " + input);
            System.out.println("Output: " + hexHash.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
