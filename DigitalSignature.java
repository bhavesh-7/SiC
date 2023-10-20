import java.security.*;

public class DigitalSignature {
    public static void main(String[] args) {
        try {
            String msg = "Enter some text here";
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            Signature signature = Signature.getInstance("SHA256withDSA");
            signature.initSign(keyPair.getPrivate());
            byte[] bytes = msg.getBytes("UTF-8");
            signature.update(bytes);
            byte[] digitalSignature = signature.sign();
            System.out.println("Digital Signature for given text: " + new String(digitalSignature, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
