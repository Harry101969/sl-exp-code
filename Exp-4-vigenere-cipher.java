class GFG {
    // Generate key by repeating it to match the length of the text
    static String generateKey(String str, String key) {
        StringBuilder result = new StringBuilder(key);
        while (result.length() < str.length()) {
            result.append(key);
        }
        return result.substring(0, str.length());
    }

    static String cipherText(String str, String key) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char x = (char) ((str.charAt(i) + key.charAt(i) - 2 * 'A') % 26 + 'A');
            cipherText.append(x);
        }
        return cipherText.toString();
    }
    static String originalText(String cipherText, String key) {
        StringBuilder originalText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char x = (char) ((cipherText.charAt(i) - key.charAt(i) + 26) % 26 + 'A');
            originalText.append(x);
        }
        return originalText.toString();
    }

    // Convert lowercase letters to uppercase
    static String toUpperCase(String s) {
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        String str = toUpperCase("ACPATILCOLLEGEOFENGINEERING");
        String keyword = toUpperCase("HARSH");
        String key = generateKey(str, keyword);

        String cipherText = cipherText(str, key);
        System.out.println("Ciphertext: " + cipherText);
        System.out.println("Decrypted Text: " + originalText(cipherText, key));
    }
}
