package com.sng;

import org.spockframework.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxGuestsTask {

    /**
     * Представьте себе программную базу данных гостиницы.
     * Есть таблица гостей, в каждой строке которой 2 значения - дата заезда, дата выезда.
     * Известно, что дата заезда строго меньше даты выезда (снимают номер минимум на одну ночь).
     * Известно так же, номер освобождается утром, а новые гости заезжают вечером (обычная практика
     * в гостиницах, пример: {1, 2} - заехали в 1 день, выехали в день 2;
     * другой пример {1, 2}, {2, 3} - заехали в день 1 и выехали в день 2, но в тот же день заехали новые гости).
     * Дата заезда представлена целым числом (например, количество дней с начала эпохи).
     * Необходимо написать функцию, которая подсчитает максимальное число гостей единомоментно на всех данных в базе.
     * Задачу необходимо решить за линейное время, то есть с вычислительной сложностью O(n).
     * Примеры работы функции:
     * assert(maxGuests({}) == 0);
     * assert(maxGuests({{1, 2}}) == 1);
     * assert(maxGuests({{1, 2}, {2, 3}}) == 1);
     * assert(maxGuests({{1, 5}, {0, 1}, {4, 5}}) == 2);
     */
    public static int maxGuests(List<Pair<Integer, Integer>> guestsDates) {
        if (guestsDates == null || guestsDates.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> guestChangeTracker = new HashMap<>();
        int minDay = Integer.MAX_VALUE;
        int maxDay = Integer.MIN_VALUE;

        // Record all guest changes and find the min/max days
        for (final Pair<Integer, Integer> stay : guestsDates) {
            final int checkInDay = stay.first();
            final int checkOutDay = stay.second();

            guestChangeTracker.put(checkInDay, guestChangeTracker.getOrDefault(checkInDay, 0) + 1);
            guestChangeTracker.put(checkOutDay, guestChangeTracker.getOrDefault(checkOutDay, 0) - 1);

            minDay = Math.min(minDay, checkInDay);
            maxDay = Math.max(maxDay, checkOutDay);
        }

        int currentGuestCount = 0;
        int maxGuests = 0;

        // Scan through all days and track the total of guests
        for (int day = minDay; day <= maxDay; day++) {
            currentGuestCount += guestChangeTracker.getOrDefault(day, 0);
            maxGuests = Math.max(maxGuests, currentGuestCount);
        }

        return maxGuests;
    }
}
