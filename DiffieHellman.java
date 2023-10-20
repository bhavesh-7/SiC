public class DiffieHellman {
    public static void main(String[] args) {
        int n = 23;
        int g = 5;
        int a = 4;
        int b = 3;
        double aliceSends = (Math.pow(g, a)) % n;
        double bobSends = (Math.pow(g, b)) % n;
        double bobComputes = (Math.pow(aliceSends, b)) % n;
        double aliceComputes = (Math.pow(bobSends, a)) % n;
        double sharedSecret = (Math.pow(g, (a * b))) % n;
        System.out.println("Alice Sends: " + aliceSends);
        System.out.println("Bob Sends: " + bobSends);
        System.out.println("Alice Computes: " + aliceComputes);
        System.out.println("Bob Computes: " + bobComputes);
        System.out.println("Shared Secret: " + sharedSecret);
    }
}
