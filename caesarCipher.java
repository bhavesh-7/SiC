public class caesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder cipher = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char encryptedChar = (char) ('A' + (c - 'A' + shift) % 26);
                cipher.append(encryptedChar);
            } else if (Character.isLowerCase(c)) {
                char encryptedChar = (char) ('a' + (c - 'a' + shift) % 26);
                cipher.append(encryptedChar);
            } else {
                cipher.append(c);
            }
        }
        return cipher.toString();
    }

    public static String decrypt(String ciphertext, int shift) {
        return encrypt(ciphertext, 26 - shift);
    }

    public static void main(String[] args) {
        String txt = "Hello World";
        int shft = 3;
        String cipherText = encrypt(txt, shft);
        String decryptedText = decrypt(cipherText, shft);
        System.out.println("Text: " + txt);
        System.out.println("Shift: " + shft);
        System.out.println("Cipher text: " + cipherText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
