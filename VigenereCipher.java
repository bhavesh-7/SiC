public class VigenereCipher {
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                int shift = key.charAt(j) - 'A';
                char encryptedChar = (char) ((c + shift - 'A') % 26 + 'A');
                ciphertext.append(encryptedChar);
                j = (j + 1) % key.length();
            } else {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toUpperCase();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                int shift = key.charAt(j) - 'A';
                char decryptedChar = (char) ((c - shift - 'A' + 26) % 26 + 'A');
                plaintext.append(decryptedChar);
                j = (j + 1) % key.length();
            } else {
                plaintext.append(c);
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "HELLO";
        System.out.println("Plain Text : " + plaintext);
        String key = "KEY";
        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted  : " + encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted  : " + decrypted);
    }
}
