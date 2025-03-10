package com.sng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericsTasks {

    /**
     * Реализуйте метод, меняющий местами элементы по индексам i и j в списке list и
     * возвращающий false, если невозможно выполнить данную операцию. Индекс j нестрого больше i.
     * Обобщите метод с помощью дженериков, чтобы он принимал на вход список элементов произвольного типа.
     * Сигнатуру метода можно и нужно менять для обобщения.
     */
    public static <T> boolean swap(int i, int j, List<T> list) {
        if (list == null
                || i > j
                || i < 0
                || j >= list.size()) {
            return false;
        }

        Collections.swap(list, i, j);
        return true;
    }

    /**
     * Напишите обобщенный метод, возвращающий минимальный элемент списка в диапазоне ячеек, заданных индексами [i, j),
     * где i включается в диапазон, а j - исключается. Используйте дженериики.
     * Бросайте исключение, если на вход передаются невалидные аргументы.
     * Сигнатуру метода можно и нужно менять для обобщения.
     */
    public static <T extends Comparable<? super T>> T minN(int i, int j, List<T> aList)
            throws IllegalArgumentException, NullPointerException {
        if (aList == null) {
            return null;
        }

        if (i >= j || i < 0 || j > aList.size()) {
            throw new IllegalArgumentException("Invalid indices");
        }

        return Collections.min(aList.subList(i, j));
    }

    /**
     * Напишите обобщенный метод, копирующий элементы списка input,
     * которые строго больше element, в список output. 
     * Сигнатуру метода можно и нужно менять для обобщения.
     * Метод testCopyGreater2 должен компилироваться и успешно выполнятся без его измения.
     * @param input - список с исходными данными
     * @param element - элемент, с которым сравниваются остальные элементы списка input
     * @param output - выходной список.
     */
    public static <T extends Comparable<? super T>> void copyGreater(List<T> input, T element, List<? super T> output) {
        if (input == null || element == null || output == null) {
            return;
        }

        for (final T item : input) {
            if (item != null && item.compareTo(element) > 0) {
                output.add(item);
            }
        }
    }

    public static boolean testCopyGreater2() {
        List<Number> numbersGt2 = new ArrayList<>();
        List<Integer> src1 = List.of(1, 2, 3);
        List<Double> src2 = List.of(0.0, 1.4, 3.2);
        copyGreater(src1, 2, numbersGt2);
        copyGreater(src2, 2.0, numbersGt2);
        System.out.println(numbersGt2);
        return numbersGt2.equals(List.of(3, 3.2));
    }

}
