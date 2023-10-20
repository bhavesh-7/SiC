import javax.crypto.*;

public class DES {
    public static void main(String[] args) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey myDESKey = keyGenerator.generateKey();
            Cipher desCipher;
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            desCipher.init(Cipher.ENCRYPT_MODE, myDESKey);
            byte[] text = "Secret Infomation".getBytes();
            byte[] encryptedText = desCipher.doFinal(text);
            System.out.println("Message: " + new String(text));
            System.out.println("Encrypted: " + encryptedText);
            desCipher.init(Cipher.DECRYPT_MODE, myDESKey);
            byte[] decryptedText = desCipher.doFinal(encryptedText);
            System.out.println("Decrypted: " + new String(decryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
