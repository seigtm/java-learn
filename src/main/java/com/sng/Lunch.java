package com.sng;

/**
 * Согласно приведенному меню столовой реализуйте методы классов Lunch и Lunch.Builder, имплементировав паттерн Строитель
 * МЕНЮ ДНЯ
 * (ChickenSoup) Куриный суп со шпинатом 100.- В порции 250 гр Ккал: 78 Белков: 3 г Жиров: 6 г Углеводов: 3 г
 * (Ramen) Рамен - суп с лапшой и яйцом 160.- В порции 250 гр Ккал: 201 Белков: 11 г Жиров: 11 г Углеводов: 15 г
 * (PotatoSoup) Картофельный крем-суп Сан-Жермен 120.- В порции 250 гр Ккал: 208 Белков: 6 г Жиров: 11 г Углеводов: 22 г
 * (ChickenMeatball) Куриная котлета с сыром, 1 шт 200.- В порции 120 гр Ккал: 358 Белков: 19 г Жиров: 29 г Углеводов: 7 г
 * (Chakhokhbili) Чахохбили из индейки с хмели-сунели 220.- В порции 250 гр Ккал: 200 Белков: 21 г Жиров: 2 г Углеводов: 24 г
 * (PorkChop) Отбивная из свиной корейки с медовой горчицей 200.- В порции 130 гр Ккал: 282 Белков: 23 г Жиров: 17 г Углеводов: 10 г
 * (PastaWithFish) Паста с красной рыбой и сливками 270.- В порции 250 гр Ккал: 301 Белков: 15 г Жиров: 19 г Углеводов: 17 г
 * (PotatoDraniki) Картофельные драники с грибами 140.- В порции 100 гр Ккал: 602 Белков: 3 г Жиров: 53 г Углеводов: 29 г
 * */
@SuppressWarnings("unused")
public class Lunch {
    private final int totalWeightGrams;
    private final long totalKCals;
    private final int priceRubles;

    private Lunch(int totalWeight, long totalKCals, int cost) {
        this.totalWeightGrams = totalWeight;
        this.totalKCals = totalKCals;
        this.priceRubles = cost;
    }

    /**
     * @return общий вес блюд в заказанном обеде
     * */
    public int getTotalWeight() {
        return totalWeightGrams;
    }

    /**
     * @return общее количество Ккал всех блюд в заказанном обеде
     * */
    public long getTotalKCals() {
        return totalKCals;
    }

    /**
     * @return стоимость обеда
     * */
    public int getCost() {
        return priceRubles;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Реализуйте методы паттерна Строитель для формирования объекта Launch.
     * Реализуйте методы класса Launch.
     * */
    public static final class Builder {
        private static final class MenuItem {
            final int priceRubles;
            final int weightGrams;
            final long kcal;
            int quantity = 0;

            MenuItem(int price, int weight, long kcal) {
                this.priceRubles = price;
                this.weightGrams = weight;
                this.kcal = kcal;
            }

            void add(int count) {
                quantity += count;
            }

            int remove(int count) {
                final int actualRemoveCount = Math.min(Math.abs(count), quantity);
                quantity -= actualRemoveCount;
                return actualRemoveCount;
            }
        }

        // Menu items with their properties: price, weight, kCal
        private final MenuItem chickenSoup = new MenuItem(100, 250, 78);
        private final MenuItem ramen = new MenuItem(160, 250, 201);
        private final MenuItem potatoSoup = new MenuItem(120, 250, 208);
        private final MenuItem chickenMeatball = new MenuItem(200, 120, 358);
        private final MenuItem chakhokhbili = new MenuItem(220, 250, 200);
        private final MenuItem porkChop = new MenuItem(200, 130, 282);
        private final MenuItem pastaWithFish = new MenuItem(270, 250, 301);
        private final MenuItem potatoDraniki = new MenuItem(140, 100, 602);

        // To track totals
        private int totalWeightGrams = 0;
        private long totalKCals = 0;
        private int priceRubles = 0;

        public Lunch cook() {
            return new Lunch(totalWeightGrams, totalKCals, priceRubles);
        }

        private Builder addItem(MenuItem item, int count) {
            if (count > 0) {
                // Adding menu items to the order
                item.add(count);

                // Calculate and add the price, weight, and calories
                final long newPrice = priceRubles + (long) item.priceRubles * count;
                final long newWeight = totalWeightGrams + (long) item.weightGrams * count;

                // Edge-case because kcal is long:
                // Check for overflow
                final long kcalToAdd = item.kcal * count;
                if (Long.MAX_VALUE - totalKCals < kcalToAdd) {
                    // Cap at maximum
                    totalKCals = Long.MAX_VALUE;
                } else {
                    totalKCals += kcalToAdd;
                }

                // Handle potential overflows for price and weight
                priceRubles = (int) Math.min(newPrice, Integer.MAX_VALUE);
                totalWeightGrams = (int) Math.min(newWeight, Integer.MAX_VALUE);
            } else if (count < 0) {
                // Removing menu items from the order (refunding)
                final int actualRemoveCount = item.remove(count);

                // Update totals based on actual removed items
                priceRubles -= item.priceRubles * actualRemoveCount;
                totalWeightGrams -= item.weightGrams * actualRemoveCount;
                totalKCals -= item.kcal * actualRemoveCount;
            }

            return this;
        }

        public Builder addChickenSoup(int count) {
            return addItem(chickenSoup, count);
        }

        public Builder addRamen(int count) {
            return addItem(ramen, count);
        }

        public Builder addPotatoSoup(int count) {
            return addItem(potatoSoup, count);
        }

        public Builder addChickenMeatball(int count) {
            return addItem(chickenMeatball, count);
        }

        public Builder addChakhokhbili(int count) {
            return addItem(chakhokhbili, count);
        }

        public Builder addPorkChop(int count) {
            return addItem(porkChop, count);
        }

        public Builder addPastaWithFish(int count) {
            return addItem(pastaWithFish, count);
        }

        public Builder addPotatoDraniki(int count) {
            return addItem(potatoDraniki, count);
        }

    }
}
