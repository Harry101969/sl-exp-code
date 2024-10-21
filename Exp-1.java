import java.util.*;

public class Exp1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the encrypted text: ");
        String encryptedText = sc.nextLine();

        // Perform frequency analysis
        Map<Character, Integer> frequencyMap = getFrequencyMap(encryptedText);

        // Sort characters by frequency
        List<Character> sortedChars = getSortedCharactersByFrequency(frequencyMap);

        // Map frequent characters to English letters
        Map<Character, Character> decryptionMap = getDecryptionMap(sortedChars);

        // Decrypt the text
        String decryptedText = decryptText(encryptedText, decryptionMap);
        System.out.println("Decrypted text: " + decryptedText);

        sc.close();
    }

    // Get frequency map of characters in the encrypted text
    private static Map<Character, Integer> getFrequencyMap(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }

    // Sort characters by frequency in descending order
    private static List<Character> getSortedCharactersByFrequency(Map<Character, Integer> frequencyMap) {
        List<Character> characters = new ArrayList<>(frequencyMap.keySet());
        characters.sort((a, b) -> frequencyMap.get(b) - frequencyMap.get(a)); // Sort by frequency
        return characters;
    }

    // Create a map of frequent characters to corresponding English letters
    private static Map<Character, Character> getDecryptionMap(List<Character> sortedChars) {
        Scanner sc = new Scanner(System.in);
        Map<Character, Character> decryptionMap = new HashMap<>();
        for (char c : sortedChars) {
            System.out.print("Enter the decrypted letter for '" + c + "': ");
            char decryptedChar = sc.nextLine().charAt(0);
            decryptionMap.put(c, decryptedChar);
        }
        return decryptionMap;
    }

    // Decrypt text using the decryption map
    private static String decryptText(String text, Map<Character, Character> decryptionMap) {
        StringBuilder decryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            decryptedText.append(decryptionMap.getOrDefault(c, c));
        }
        return decryptedText.toString();
    }
}
