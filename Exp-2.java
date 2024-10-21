import java.util.Scanner;

class ProductCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the input to be encrypted:");
        String input = sc.nextLine();
        System.out.println("Enter a number:");
        int n = sc.nextInt();
        StringBuilder substituted = new StringBuilder();
        for (char c : input.toCharArray()) {
            substituted.append((char) (c + 5));
        }
        // Transposition encryption with padding
        String transpositionInput = substituted.toString();
        int padding = (n - (transpositionInput.length() % n)) % n;
        transpositionInput += "/".repeat(padding); // Add padding
        StringBuilder transposed = new StringBuilder();
        System.out.println("\nTransposition Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = i; j < transpositionInput.length(); j += n) {
                char c = transpositionInput.charAt(j);
                transposed.append(c);
                System.out.print(c); // Print transposition matrix
            }
            System.out.println();
        }
        System.out.println("\nFinal encrypted text:");
        System.out.println(transposed);
        // Transposition decryption
        StringBuilder transpositionPlaintext = new StringBuilder();
        int rows = transposed.length() / n;
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < transposed.length(); j += rows) {
                transpositionPlaintext.append(transposed.charAt(j));
            }
        }
        // Substitution decryption
        StringBuilder plaintext = new StringBuilder();
        for (char c : transpositionPlaintext.toString().toCharArray()) {
            plaintext.append((char) (c - 5));
        }
        System.out.println("\nDecrypted plaintext:");
        System.out.println(plaintext);
        sc.close();
    }
}
