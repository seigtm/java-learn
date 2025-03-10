package com.sng;

import org.jetbrains.annotations.NotNull;
import org.spockframework.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PersonTask {

    /**
     * Определите отношение порядка в данном классе, упорядочивая по фамилии, имени и затем по отчеству.
     * Реализуйте статические методы.
     */
    public record Person(String name, String patronymic, String surname) implements Comparable<Person> {

        @Override
        public int compareTo(@NotNull Person o) {
            return Comparator.comparing((Person p) -> p.surname)
                    .thenComparing(p -> p.name)
                    .thenComparing(p -> p.patronymic)
                    .compare(this, o);
        }
    }

    /**
     * @return новый отсортированный список по имени, затем фамилии.
     */
    public static List<Person> sortByNameSurname(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return List.of();
        }

        return persons.stream()
                .sorted(Comparator.comparing((Person p) -> p.name)
                        .thenComparing(p -> p.surname))
                .toList();
    }

    /**
     * @return самое популярное имя (часто встречающееся). Если их несколько, вернуть первое по алфавиту.
     */
    public static String getMostPopularName(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return null;
        }

        final Map<String, Long> nameFrequency = persons.stream()
                .map(p -> p.name)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final long maxFrequency = nameFrequency.values().stream().max(Long::compare).orElse(0L);

        return nameFrequency.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .map(Map.Entry::getKey)
                .min(String::compareTo)
                .orElse(null);
    }

    /**
     * @return топ N самых популярных имен. Если имена совпадают по количеству, упорядочить их по алфавиту.
     * Например, самые популярные имена Евгения и Александр имеют по 3 человека, тогда список имен вернется в
     * порядке [Александр, Евгения]
     */
    public static List<String> getTopNPopularNames(List<Person> persons, int topN) {
        if (persons == null || persons.isEmpty() || topN <= 0) {
            return List.of();
        }

        final Map<String, Long> nameFrequency = persons.stream()
                .map(p -> p.name)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return nameFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()  // by frequency (descending)
                        .thenComparing(Map.Entry.comparingByKey()))  // alphabetically
                .limit(topN)  // take top N
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * @return количество мужчин и женщин. Разделение производить по эвристике на основе окончаний отчеств.
     */
    public static Pair<Integer, Integer> getMenAndWomenNumber(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return Pair.of(0, 0);
        }

        final int women = (int) persons.stream()
                .filter(p -> p.patronymic != null && p.patronymic.endsWith("на"))
                .count();

        final int men = persons.size() - women;

        return Pair.of(men, women);
    }

    private static String formatSurname(String surname) {
        if (surname == null) {
            return null;
        }

        if (surname.endsWith("а")) {
            return surname.substring(0, surname.length() - 1);
        }

        return surname;
    }

    private static String formatPatronymic(String patronymic) {
        if (patronymic == null) {
            return null;
        }

        if (patronymic.endsWith("на")) {
            return patronymic.substring(0, patronymic.length() - 2) + "ич";
        }

        return patronymic;
    }

    /**
     * Сгруппировать людей, которые потенциально могут являться родственниками по признаку общих фамилии и отчества.
     * Вернуть результат в форме ассоциативного массива вида
     * [Отчество в мужском роде]->[Список потенциальных родственников, отсортированный по имени].
     * Сам ассоциативный массив упорядочить:
     * 1) по убыванию по количеству человек в группе
     * 2) затем по возрастанию в алфавитном порядке по отчеству.
     */
    public static Map<String, List<Person>> getHypotheticalRelatives(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return Map.of();
        }

        // Group people by normalized surname and patronymic
        final Map<Pair<String, String>, List<Person>> groups = persons.stream()
                .collect(Collectors.groupingBy(p ->
                        Pair.of(formatSurname(p.surname), formatPatronymic(p.patronymic))
                ));

        return groups.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)  // groups with > 1 person
                .sorted((e1, e2) -> {  // sort by group size (descending), then by patronymic
                    final int cmp = Integer.compare(e2.getValue().size(), e1.getValue().size());
                    if (cmp == 0) {
                        return e1.getKey().second().compareTo(e2.getKey().second());
                    }
                    return cmp;
                })
                .collect(Collectors.toMap(
                        e -> e.getKey().second(),  // key = male patronymic
                        e -> e.getValue().stream()
                                .sorted(Comparator.comparing(p -> p.name))  // sort within group by name
                                .toList(),
                        (a, b) -> a,  // keep first value in case of duplicates
                        LinkedHashMap::new  // to preserve order
                ));
    }

    /**
     * Найти и вернуть дублирующихся людей в списке, упорядочить полученный список по фамилии, имени.
     */
    public static List<Person> getDuplicates(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return List.of();
        }

        Set<Person> uniquePersons = new HashSet<>();
        Set<Person> duplicates = new HashSet<>();

        for (final Person person : persons) {
            if (!uniquePersons.add(person)) {
                duplicates.add(person);
            }
        }

        return duplicates.stream()
                .sorted(Comparator.comparing((Person p) -> p.surname)
                        .thenComparing(p -> p.name))
                .toList();
    }

    /**
     * Найти и вернуть все уникальные имена, упорядоченные по алфавиту.
     */
    public static List<String> getUniqueNames(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return List.of();
        }

        return persons.stream()
                .map(p -> p.name)
                .distinct()
                .sorted()
                .toList();
    }

    /**
     * Сгруппировать людей по именам, отсортировать в каждой группе людей по ФИО.
     */
    public static Map<String, List<Person>> getNameToPersons(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return Map.of();
        }

        // Using Person's compareTo()
        return persons.stream()
                .collect(Collectors.groupingBy(p -> p.name,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream().sorted().toList())));
    }

    /**
     * Разделить людей на 2 группы - женщины (true) и мужчины (false), в каждой группе
     * посчитать медианы длин фамилии, имени и отчества.
     */
    public static Map<Boolean, Integer[]> getNamePartsLengthMedian(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return Map.of();
        }

        final Map<Boolean, List<Person>> genderLengths = persons.stream()
                .collect(Collectors.partitioningBy(p -> p.patronymic.endsWith("на")));

        Map<Boolean, Integer[]> result = new HashMap<>();

        for (final Map.Entry<Boolean, List<Person>> entry : genderLengths.entrySet()) {
            final Boolean isWomen = entry.getKey();
            final List<Person> genderGroup = entry.getValue();

            if (genderGroup.isEmpty()) {
                continue;
            }

            final int[] surnameLengths = genderGroup.stream().mapToInt(p -> p.surname.length()).sorted().toArray();
            final int[] nameLengths = genderGroup.stream().mapToInt(p -> p.name.length()).sorted().toArray();
            final int[] patronymicLengths = genderGroup.stream().mapToInt(p -> p.patronymic.length()).sorted().toArray();

            final Integer[] medians = new Integer[]{
                    getMedian(surnameLengths),
                    getMedian(nameLengths),
                    getMedian(patronymicLengths)
            };

            result.put(isWomen, medians);
        }

        return result;
    }

    private static int getMedian(int[] sortedArray) {
        if (sortedArray == null || sortedArray.length == 0)
            return 0;

        final int middle = sortedArray.length / 2;
        if (sortedArray.length % 2 == 0) {
            return (sortedArray[middle - 1] + sortedArray[middle]) / 2;
        }

        return sortedArray[middle];
    }

    /**
     * Разделить людей на 2 группы - женщины (true) и мужчины (false), в каждой группе
     * посчитать topN самых популярных (частотных) букв, используемых в именах, без учета регистра.
     * С каждой буквой вернуть соответствующую ей долю по отношению к другим буквам имен данной группы.
     * Например: Мария, Екатерина, Агафья. Top1 популярная буква - "а". Доля А равна 5/20=0.25.
     * Ассоциативный массив буква-доля упорядочить по убыванию по доле. Если доля одинакова - по алфавиту.
     * Результаты типа Double округлите до сотых.
     */
    public static Map<Boolean, Map<Character, Double>> getCharPopularity(List<Person> persons, int topN) {
        if (persons == null || persons.isEmpty() || topN <= 0) {
            return Map.of();
        }

        final Map<Boolean, List<Person>> personsByGender = persons.stream()
                .collect(Collectors.partitioningBy(p -> p.patronymic != null && p.patronymic.endsWith("на")));

        final Map<Boolean, Map<Character, Double>> result = new HashMap<>();

        for (final Map.Entry<Boolean, List<Person>> entry : personsByGender.entrySet()) {
            final Boolean isWomen = entry.getKey();
            final List<Person> genderGroup = entry.getValue();

            if (genderGroup.isEmpty()) {
                continue;
            }

            Map<Character, Long> charCounts = genderGroup.stream()
                    .flatMapToInt(p -> p.name.toLowerCase().chars())
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()));

            if (charCounts.isEmpty()) {
                continue;
            }

            // Calculate total for percentage
            final long totalChars = charCounts.values().stream().mapToLong(Long::longValue).sum();

            final Map<Character, Double> charShares = charCounts.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> Math.round((double) e.getValue() / totalChars * 100.0) / 100.0));

            // Sort by share (descending) and then by character (ascending), limit to topN
            final Map<Character, Double> sortedTopN = charShares.entrySet().stream()
                    .sorted(Map.Entry.<Character, Double>comparingByValue(Comparator.reverseOrder())
                            .thenComparing(Map.Entry.comparingByKey()))
                    .limit(topN)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new));

            result.put(isWomen, sortedTopN);
        }

        return result;
    }
}
