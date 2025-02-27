package com.sng;

import java.math.BigDecimal;

public class CalculusTasks {

    /**
     * Реализуйте метод, меняющий местами элементы по индексам i и j в массиве anArray и
     * возвращающий false, если невозможно выполнить данную операцию. Индекс j нестрого больше i.
     */
    public static boolean swap(Integer i, int j, int[] anArray) {
        if (anArray == null
                || i == null
                || i > j
                || i < 0
                || j >= anArray.length) {
            return false;
        }

        if (i == j) {
            return true;
        }

        final int temp = anArray[i];
        anArray[i] = anArray[j];
        anArray[j] = temp;

        return true;
    }

    /**
     * @return минимальный из трех аргументов, не используя функции стандартной библиотеки
     */
    public static int min3(int a, int b, int c) {
        final int minAB = a < b ? a : b;

        return minAB < c ? minAB : c;
    }

    /**
     * @return сумму двух аргументов или null если невозможно подсчитать сумму корректно
     * с математической точки зрения. Реализация не должна использовать функции стандартной библиотеки.
     */
    public static Integer sum2(int a, int b) {
        // Overflow check for addition
        if (b > 0 && a > Integer.MAX_VALUE - b) {
            return null; // Positive overflow
        }
        if (b < 0 && a < Integer.MIN_VALUE - b) {
            return null; // Negative overflow
        }

        return a + b;
    }

    /**
     * Возвращает отношение двух аргументов, считая null аргумент как 0.
     * @return a/b или null если результат выполнения операции
     * не является вещественным числом.
     */
    public static Double div2(Double a, Double b) {
        // Handle null input args
        final double numerator = (a == null) ? 0.0 : a;
        final double denominator = (b == null) ? 0.0 : b;

        // Check for division by zero and infinity cases
        if (denominator == 0.0
                || Double.isInfinite(numerator)
                || Double.isInfinite(denominator)
                || Double.isNaN(numerator)
                || Double.isNaN(denominator)) {
            return null;
        }

        double result = numerator / denominator;

        // Check if the result is a valid real number
        if (Double.isInfinite(result) || Double.isNaN(result)) {
            return null;
        }

        return result;
    }

    /**
     * Проверяет, вызовет ли сложение двух аргументов переполнение.
     * 
     * @param a первый аргумент
     * @param b второй аргумент
     * @return true если сложение вызовет переполнение, иначе false
     */
    private static boolean wouldAdditionOverflow(int a, int b) {
        return (b > 0 && a > Integer.MAX_VALUE - b)
                || (b < 0 && a < Integer.MIN_VALUE - b);
    }

    /**
     * Вычисляет сумму a0 и всех элементов массива ai или null, если это сделать невозможно.
     * Если ai пустой, то считаем сумму ai равной нулю.
     * @param a0 - первый аргумент последовательности
     * @param ai - массив остальных аргументов
     * @return сумма всех аргументов
     */
    public static Integer sumN(int a0, int... ai) {
        if (ai == null || ai.length == 0) {
            return a0;
        }

        int sumAi = 0;
        for (int value : ai) {
            if (wouldAdditionOverflow(sumAi, value)) {
                return null;
            }
            sumAi += value;
        }

        return wouldAdditionOverflow(a0, sumAi) ? null : a0 + sumAi;
    }

    /**
     * Вычисляет сумму a0 и всех элементов массива ai или null, если это сделать невозможно.
     * Если ai пустой, то считаем сумму ai равной нулю.
     * @param a0 - первый аргумент последовательности
     * @param ai - массив остальных аргументов
     * @return сумма всех аргументов
     */
    public static Double sumN(double a0, double... ai) {
        if (Double.isNaN(a0) || Double.isInfinite(a0)) {
            return null;
        }
        if (ai == null || ai.length == 0) {
            return a0;
        }

        BigDecimal sum = BigDecimal.valueOf(a0);
        for (double value : ai) {
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                return null;
            }

            sum = sum.add(BigDecimal.valueOf(value));
        }

        // Check if result is within double's range
        final double result = sum.doubleValue();
        return (Double.isInfinite(result) || Double.isNaN(result)) ? null : result;
    }

    /**
     * Вычисляет среднее между всеми аргументами функции или вернет null если результат
     * не является вещественным числом.
     * @param a0 - первый аргумент последовательности
     * @param ai - массив остальных аргументов
     * @return среднее всех аргументов
     */
    public static Double avg(int a0, int... ai) {
        if (ai == null || ai.length == 0) {
            return (double) a0;
        }

        long sum = a0;
        int count = 1;

        for (int value : ai) {
            sum += value;
            count++;
        }

        return (double) sum / count;
    }

    /**
     * Возвращает число, которое соответствует запрошенным правам в команде `chmod`
     * в unix подобных операционных системах.
     * @param unixAccess строка в формате `rwxrwxrwx`, где любой символ может
     *                   быть заменен на `-`, что означает отсутствие данного права
     * @return числовое представление прав доступа или null, если преобразование выполнить невозможно
     * Пример работы: на входе `rwxrw-r--` на выходе число 764
     */
    public static Integer rwx(String unixAccess) {
        if (unixAccess == null || unixAccess.length() != 9) {
            return null;
        }

        int permissions = 0;
        final int[] multipliers = { 100, 10, 1 }; // owner, group, others

        for (int i = 0; i < multipliers.length; i++) {
            final int offset = i * 3;
            final char r = unixAccess.charAt(offset);
            final char w = unixAccess.charAt(offset + 1);
            final char x = unixAccess.charAt(offset + 2);

            if ((r != 'r' && r != '-')
                    || (w != 'w' && w != '-')
                    || (x != 'x' && x != '-')) {
                return null;
            }

            int groupValue = 0;
            if (r == 'r') groupValue += 4;
            if (w == 'w') groupValue += 2;
            if (x == 'x') groupValue += 1;
            permissions += groupValue * multipliers[i];
        }

        return permissions;
    }

    public static final int MASK_READ = 0x0001;
    public static final int MASK_WRITE = 0x0010;
    public static final int MASK_EXEC = 0x0100;
    /**
     * Проверяет, соответствует ли переданное значение битовой маске
     * @param aValue проверяемое значение
     * @param aMask битовая маска
     * Корректные маски {@value MASK_READ}, {@value MASK_WRITE}, {@value MASK_EXEC}
     * */
    public static boolean checkMask(int aValue, int aMask) {
        return (aValue & aMask) == aMask;
    }

    /**
     * Напишите функцию, проверяющую, можно ли из одной строки получить вторую
     * изменением только одного символа. Под модификацией (изменением) понимается -
     * замена символа другим, вставка символа, удаление символа. Сложность алгоритма
     * не должна превышать O(n).
     * Примеры работы функции:
     * canModify("cat", "cats") -> true
     * canModify("cat", "acts") -> false
     */
    public static boolean canModify(String from, String to) {
        if (from == null || to == null || from.equals(to)) {
            return false;
        }

        final int fromStringLength = from.length();
        final int toStringLength = to.length();

        if (Math.abs(fromStringLength - toStringLength) > 1) {
            return false;
        }

        // Check for one character replacement
        if (fromStringLength == toStringLength) {
            int differences = 0;
            for (int i = 0; i < fromStringLength; i++) {
                if (from.charAt(i) != to.charAt(i)) {
                    differences++;
                }
                if (differences > 1) {
                    return false;
                }
            }
            return true;
        }

        // Check for insertion or deletion
        final String shorter = fromStringLength < toStringLength ? from : to;
        final String longer = fromStringLength < toStringLength ? to : from;

        int indexShorter = 0;
        int indexLonger = 0;
        boolean foundDifference = false;

        while (indexShorter < shorter.length() && indexLonger < longer.length()) {
            if (shorter.charAt(indexShorter) != longer.charAt(indexLonger)) {
                // This would trigger on second difference
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;
            } else {
                indexShorter++;
            }
            indexLonger++;
        }

        return true;
    }
}
