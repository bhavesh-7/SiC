public class RailfenceCipher {
    public static String encrypt(String plaintext, int depth) {
        String[] fence = new String[depth];
        for (int i = 0; i < depth; i++) {
            fence[i] = "";
        }
        int level = 0;
        boolean directionDown = true;
        for (char c : plaintext.toCharArray()) {
            fence[level] += c;
            if (level == 0) {
                directionDown = true;
            } else if (level == depth - 1) {
                directionDown = false;
            }
            if (directionDown) {
                level++;
            } else {
                level--;
            }
        }
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            ciphertext.append(fence[i]);
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int rails) {
        int textLength = ciphertext.length();
        String[] fence = new String[rails];
        for (int i = 0; i < rails; i++) {
            fence[i] = "";
        }
        int rail = 0;
        boolean directionDown = true;
        for (int i = 0; i < textLength; i++) {
            fence[rail] += ' '; // Initialize fence with spaces
            if (rail == 0) {
                directionDown = true;
            } else if (rail == rails - 1) {
                directionDown = false;
            }
            if (directionDown) {
                rail++;
            } else {
                rail--;
            }
        }
        rail = 0;
        for (int i = 0; i < textLength; i++) {
            fence[rail] = fence[rail].substring(0, i) + ciphertext.charAt(i) +
                    fence[rail].substring(i + 1);
            if (rail == 0) {
                directionDown = true;
            } else if (rail == rails - 1) {
                directionDown = false;
            }
            if (directionDown) {
                rail++;
            } else {
                rail--;
            }
        }
        char[] plaintext = new char[textLength];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < textLength; j++) {
                if (fence[i].charAt(j) != ' ') {
                    plaintext[j] = fence[i].charAt(j);
                }
            }
        }
        return new String(plaintext);
    }

    public static void main(String[] args) {
        String plaintext = "HELLOWORLD";
        int rails = 3;
        String encrypted = encrypt(plaintext, rails);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = decrypt(encrypted, rails);
        System.out.println("Decrypted: " + decrypted);
    }
}
