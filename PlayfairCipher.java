public class PlayfairCipher {
    private String key;
    private char[][] matrix;

    public PlayfairCipher(String key) {
        this.key = sanitizeKey(key);
        this.matrix = generateMatrix();
    }

    private String sanitizeKey(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", ""); // Remove non-alphabet characters and convert to uppercase
        key = key.replaceAll("J", "I"); // Replace J with I
        return key;
    }

    private char[][] generateMatrix() {
        char[][] playfairMatrix = new char[5][5];
        String keyWithoutDuplicates = removeDuplicateChars(key + "ABCDEFGHIKLMNOPQRSTUVWXYZ");

        int row = 0, col = 0;

        for (char ch : keyWithoutDuplicates.toCharArray()) {
            playfairMatrix[row][col] = ch;
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }

        return playfairMatrix;
    }

    private String removeDuplicateChars(String str) {
        StringBuilder result = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (result.indexOf(String.valueOf(ch)) == -1) {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public String encrypt(String plaintext) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        plaintext = plaintext.replaceAll("J", "I"); // Replace J with I
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char first = plaintext.charAt(i);
            char second = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';

            int[] posFirst = findPosition(first);
            int[] posSecond = findPosition(second);

            int rowFirst = posFirst[0];
            int colFirst = posFirst[1];
            int rowSecond = posSecond[0];
            int colSecond = posSecond[1];

            if (rowFirst == rowSecond) {
                encryptedText.append(matrix[rowFirst][(colFirst + 1) % 5]);
                encryptedText.append(matrix[rowSecond][(colSecond + 1) % 5]);
            } else if (colFirst == colSecond) {
                encryptedText.append(matrix[(rowFirst + 1) % 5][colFirst]);
                encryptedText.append(matrix[(rowSecond + 1) % 5][colSecond]);
            } else {
                encryptedText.append(matrix[rowFirst][colSecond]);
                encryptedText.append(matrix[rowSecond][colFirst]);
            }
        }

        return encryptedText.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char first = ciphertext.charAt(i);
            char second = (i + 1 < ciphertext.length()) ? ciphertext.charAt(i + 1) : 'X';

            int[] posFirst = findPosition(first);
            int[] posSecond = findPosition(second);

            int rowFirst = posFirst[0];
            int colFirst = posFirst[1];
            int rowSecond = posSecond[0];
            int colSecond = posSecond[1];

            if (rowFirst == rowSecond) {
                decryptedText.append(matrix[rowFirst][(colFirst - 1 + 5) % 5]);
                decryptedText.append(matrix[rowSecond][(colSecond - 1 + 5) % 5]);
            } else if (colFirst == colSecond) {
                decryptedText.append(matrix[(rowFirst - 1 + 5) % 5][colFirst]);
                decryptedText.append(matrix[(rowSecond - 1 + 5) % 5][colSecond]);
            } else {
                decryptedText.append(matrix[rowFirst][colSecond]);
                decryptedText.append(matrix[rowSecond][colFirst]);
            }
        }

        return decryptedText.toString();
    }

    private int[] findPosition(char letter) {
        int[] position = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == letter) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return position;
    }

    public static void main(String[] args) {
        String key = "KEYWORD";
        PlayfairCipher playfair = new PlayfairCipher(key);

        String plaintext = "Hello World";
        String encryptedText = playfair.encrypt(plaintext);
        String decryptedText = playfair.decrypt(encryptedText);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);
    }
}
