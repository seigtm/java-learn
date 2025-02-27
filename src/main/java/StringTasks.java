public class StringTasks {
    /**
     * Проверьте, что строка состоит только из строчных букв
     * */
    public static boolean isLowerCase(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        return input.chars().allMatch(Character::isLowerCase);
    }

    /**
     * Проверьте, что строка состоит только из заглавных букв
     * */
    public static boolean isUpperCase(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        return input.chars().allMatch(Character::isUpperCase);
    }

    /**
     * Строка начинается с заглавной буквы, а все остальные строчные
     * */
    public static boolean isCapitalized(String input) {
        if (input == null || input.isEmpty() || !Character.isUpperCase(input.charAt(0))) {
            return false;
        }

        for (int i = 1; i < input.length(); i++) {
            if (!Character.isLowerCase(input.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Проверьте, что в строке чередуются буквы, т.е. первая заглавная, вторая строчная,
     * третья заглавная и т.д. Или наоборот, первая строчная, вторая заглавная, третья строчная и т.д.
     * Используйте конструкцию Character.isUpperCase(‘z’), чтобы определить, верно ли, что символ является заглавной
     * буквой. (аналогично Character.isLowerCase())
     * */
    public static boolean isMixedCase(String input) {
        if (input == null || input.length() < 2) {
            return false;
        }

        final char firstCharacter = input.charAt(0);
        if (!Character.isLetter(firstCharacter)) {
            return false;
        }

        boolean shouldBeUpperCase = !Character.isUpperCase(firstCharacter);
        for (int i = 1; i < input.length(); i++) {
            final char character = input.charAt(i);
            if (!Character.isLetter(character) || 
                Character.isUpperCase(character) != shouldBeUpperCase) {
                return false;
            }

            shouldBeUpperCase = !shouldBeUpperCase;
        }

        return true;
    }

    /**
     * Дана строка, проверьте, является ли она палиндромом. Т.е. верно ли, что если ее
     * прочитать с конца в начало, то получится та же строка. Вне алфавитные символы игнорируйте.
     * */
    public static boolean isPalindrome(String input) {
        return isStrictPalindrome(input, false);
    }

    /**
     *  Аналогично функции палиндрома, но функция имеет второй логический параметр
     * strict, если он false, то при проверке нужно учитывать только порядок букв, а не регистр или прочие знаки.
     * */
    public static boolean isStrictPalindrome(String input, boolean isStrict) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        if (!isStrict) {
            // Capacity for the worst case scenario: all letters
            StringBuilder processedInput = new StringBuilder(input.length());
            for (char c : input.toCharArray()) {
                if (Character.isLetter(c)) {
                    processedInput.append(Character.toLowerCase(c));
                }
            }

            // No letters in the processed input left at all
            if (processedInput.isEmpty()) {
                return false;
            }

            input = processedInput.toString();
        }

        int left = 0;
        int right = input.length() - 1;
        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    /**
     *  Надо реализовать метод, который принимает на вход String,
     *  удаляет рядом стоящие парные буквы и возвращает остаток.
     *  Примеры ожидаемого поведения программы:
     *  "aab" -> "b", "aabb" -> "", "abfbaf" -> "abfbaf”, "abccbaf" -> "f"
     *  Решение должно иметь вычислительную сложность O(n).
     */
    public static String pairLetters(String input) {
        if (input == null || input.length() < 2) {
            return input;
        }

        // Pre-size to avoid reallocations for the worst case scenario: no pairs
        StringBuilder result = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            final char currentCharacter = input.charAt(i);
            if (!result.isEmpty() && currentCharacter == result.charAt(result.length() - 1)) {
                result.deleteCharAt(result.length() - 1);
            } else {
                result.append(currentCharacter);
            }
        }

        return result.toString();
    }

}
