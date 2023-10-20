public class HillCipher {
    static int[][] keyMatrix = new int[][] {
            { 1, 2, 3 },
            { 2, 3, 2 },
            { 2, 2, 1 }
    };
    static int[][] invKeyMatrix = new int[][] {
            { -1, 0, 1 },
            { 2, -1, 0 },
            { -2, 2, -1 }
    };
    static String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static String encode(char a, char b, char c) {
        String res;
        int x, y, z;
        int posa = (int) a - 65;
        int posb = (int) b - 65;
        int posc = (int) c
                - 65;
        x = posa * keyMatrix[0][0] + posb * keyMatrix[1][0] + posc *
                keyMatrix[2][0];
        y = posa * keyMatrix[0][1] + posb * keyMatrix[1][1] + posc *
                keyMatrix[2][1];
        z = posa * keyMatrix[0][2] + posb * keyMatrix[1][2] + posc *
                keyMatrix[2][2];
        a = key.charAt(x % 26);
        b = key.charAt(y % 26);
        c = key.charAt(z % 26);
        res = "" + a + b + c;
        return res.toString();
    }

    static String decode(char a, char b, char c) {
        String ret;
        int x, y, z;
        int posa = (int) a - 65;
        int posb = (int) b - 65;
        int posc = (int) c
                - 65;
        x = posa * invKeyMatrix[0][0] + posb * invKeyMatrix[1][0] + posc *
                invKeyMatrix[2][0];
        y = posb * invKeyMatrix[0][1] + posb * invKeyMatrix[1][1] + posc *
                invKeyMatrix[2][1];
        z = posa * invKeyMatrix[0][2] + posb * invKeyMatrix[1][2] + posc *
                invKeyMatrix[2][2];
        a = key.charAt((x % 26 < 0) ? (26 + x % 26) : (x % 26));
        b = key.charAt((y % 26 < 0) ? (26 + y % 26) : (y % 26));
        c = key.charAt((z % 26 < 0) ? (26 + z % 26) : (z % 26));
        ret = "" + a + b + c;
        return ret;
    }

    public static void main(String[] args) {
        String enc = "";
        String msg = "";
        String dec = "";
        int n;
        msg = "Hello".toUpperCase().replaceAll("\\s", "");
        n = msg.length() % 3;
        if (n != 0) {
            for (int i = 1; i <= (3 - n); i++) {
                msg += "X";
            }
        }
        System.out.println("Padded Message: " + msg);
        char pdchars[] = msg.toCharArray();
        for (int i = 0; i < msg.length(); i += 3) {
            enc += encode(pdchars[i], pdchars[i + 1], pdchars[i + 2]);
        }
        System.out.println("Encoded: " + enc);
        char dechars[] = enc.toCharArray();
        for (int i = 0; i < msg.length(); i += 3) {
            dec += decode(dechars[i], dechars[i + 1], dechars[i + 2]);
        }
        System.out.println("Decoded: " + dec);
    }
}
