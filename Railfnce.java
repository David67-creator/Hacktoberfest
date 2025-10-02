import java.util.Scanner;

public class RailFenceCipher {

    // Encrypt function
    public static String encrypt(String text, int key) {
        char[][] rail = new char[key][text.length()];

        // Fill the rail matrix with '\n'
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < text.length(); j++) {
                rail[i][j] = '\n';
            }
        }

        boolean dirDown = false;
        int row = 0, col = 0;

        // Fill the rail matrix in zigzag
        for (int i = 0; i < text.length(); i++) {
            rail[row][col++] = text.charAt(i);

            if (row == 0 || row == key - 1) {
                dirDown = !dirDown;
            }

            row += dirDown ? 1 : -1;
        }

        // Construct ciphertext
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (rail[i][j] != '\n') {
                    result.append(rail[i][j]);
                }
            }
        }

        return result.toString();
    }

    // Decrypt function
    public static String decrypt(String cipher, int key) {
        char[][] rail = new char[key][cipher.length()];

        // Fill the rail matrix with '\n'
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                rail[i][j] = '\n';
            }
        }

        // Mark positions with '*'
        boolean dirDown = false;
        int row = 0, col = 0;
        for (int i = 0; i < cipher.length(); i++) {
            rail[row][col++] = '*';
            if (row == 0 || row == key - 1) {
                dirDown = !dirDown;
            }
            row += dirDown ? 1 : -1;
        }

        // Fill the rail matrix with cipher letters
        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                if (rail[i][j] == '*' && index < cipher.length()) {
                    rail[i][j] = cipher.charAt(index++);
                }
            }
        }

        // Read the matrix in zigzag to get original text
        StringBuilder result = new StringBuilder();
        row = 0;
        col = 0;
        dirDown = false;

        for (int i = 0; i < cipher.length(); i++) {
            result.append(rail[row][col++]);

            if (row == 0 || row == key - 1) {
                dirDown = !dirDown;
            }

            row += dirDown ? 1 : -1;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter key (number of rails): ");
        int key = sc.nextInt();

        String encrypted = encrypt(text, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }
}
