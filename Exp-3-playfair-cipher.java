import java.util.*;

class Playfair {
    String key, plainText;
    char[][] matrix = new char[5][5];

    public Playfair(String key, String plainText) {
        this.key = key.toLowerCase().replaceAll("j", "i"); // convert to lowercase and replace 'j' with 'i'
        this.plainText = plainText.toLowerCase().replaceAll("j", "i");
        generateCipherKey();
    }

    // Generate key matrix for Playfair Cipher
    private void generateCipherKey() {
        Set<Character> set = new LinkedHashSet<>();
        for (char c : key.toCharArray()) if (c != 'j') set.add(c);
        for (char c = 'a'; c <= 'z'; c++) if (!set.contains(c) && c != 'j') set.add(c);

        Iterator<Character> it = set.iterator();
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = it.next();

        System.out.println("Playfair Cipher Key Matrix:");
        for (char[] row : matrix) System.out.println(Arrays.toString(row));
    }

    // Get character positions in the matrix
    private int[] getCharPos(char ch) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == ch) return new int[]{i, j};
        return null;
    }

    // Format plaintext for Playfair Cipher encryption
    private String formatPlainText() {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            formatted.append(plainText.charAt(i));
            if (i < plainText.length() - 1 && plainText.charAt(i) == plainText.charAt(i + 1))
                formatted.append('x'); // Insert 'x' if two consecutive characters are the same
        }
        if (formatted.length() % 2 != 0) formatted.append('x'); // Add 'x' for odd length
        return formatted.toString();
    }

    // Encrypt the formatted plaintext
    public String encryptMessage() {
        String formattedText = formatPlainText();
        StringBuilder encText = new StringBuilder();

        for (int i = 0; i < formattedText.length(); i += 2) {
            int[] pos1 = getCharPos(formattedText.charAt(i));
            int[] pos2 = getCharPos(formattedText.charAt(i + 1));

            // Same row
            if (pos1[0] == pos2[0]) {
                encText.append(matrix[pos1[0]][(pos1[1] + 1) % 5])
                       .append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            }
            // Same column
            else if (pos1[1] == pos2[1]) {
                encText.append(matrix[(pos1[0] + 1) % 5][pos1[1]])
                       .append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            }
            // Rectangle
            else {
                encText.append(matrix[pos1[0]][pos2[1]])
                       .append(matrix[pos2[0]][pos1[1]]);
            }
        }

        return encText.toString();
    }
}

public class GFG {
    public static void main(String[] args) {
        Playfair pfc1 = new Playfair("Problem", "Playfair");
        System.out.println("Cipher Text for 'Playfair': " + pfc1.encryptMessage());

        Playfair pfc2 = new Playfair("Problem", "Hello");
        System.out.println("Cipher Text for 'Hello': " + pfc2.encryptMessage());
    }
}
