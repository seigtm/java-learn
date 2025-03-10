<!-- omit in toc -->
# Yet Another Java Practical Assignments

<!-- omit in toc -->
## Table of Contents

- [Overview](#overview)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Running the Tests](#running-the-tests)
- [Implemented Tasks](#implemented-tasks)
  - [String Operations](#string-operations)
  - [Calculus Tasks](#calculus-tasks)
  - [Lunch Builder Pattern](#lunch-builder-pattern)
    - [Core Components](#core-components)
    - [Menu Items](#menu-items)
    - [Builder Methods](#builder-methods)
    - [Implementation Features](#implementation-features)
  - [Exception Handling Tasks](#exception-handling-tasks)
  - [Generics Tasks](#generics-tasks)
  - [Max Guests Task](#max-guests-task)
  - [Person Tasks](#person-tasks)
- [Some Implementation Details](#some-implementation-details)
  - [`isLowerCase` \& `isUpperCase`](#islowercase--isuppercase)
  - [`isMixedCase`](#ismixedcase)
  - [`isPalindrome`](#ispalindrome)
  - [`isStrictPalindrome`](#isstrictpalindrome)
  - [`pairLetters`](#pairletters)
  - [`sum2` and `sumN`](#sum2-and-sumn)
  - [`rwx`](#rwx)
  - [Lunch Builder](#lunch-builder)
  - [MyException \& throwMyException](#myexception--throwmyexception)
  - [Generic Methods \& PECS Principle](#generic-methods--pecs-principle)
  - [Max Guests Algorithm](#max-guests-algorithm)
  - [Person Tasks](#person-tasks-1)

## Overview

This repository contains a series of practical programming tasks designed to demonstrate proficiency with various programming concepts, patterns, and methodologies.

Also see my previous repository with similar tasks: [Java Practical Assignments](https://github.com/seigtm/java-spbpu-homework).

## Getting Started

To get started, clone this repository to your local machine:

```bash
git clone https://github.com/seigtm/java-learn.git
```

### Prerequisites

- [(Java SE Development Kit 18.0.2.1 (JDK)](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)

### Running the Tests

On Unix-like systems:

```bash
./gradlew test
```

or on Windows:

```powershell
.\gradlew.bat test
```

## Implemented Tasks

### String Operations

**Source Code**: [StringTasks.java](./src/main/java/StringTasks.java)

**Test Code**: [StringTasksTest.java](./src/test/groovy/StringTasksSpec.groovy)

**Methods**:

- `isLowerCase(String input): boolean`: checks if the input string consists only of lowercase letters. Returns false for null or empty strings.
- `isUpperCase(String input): boolean`: checks if the input string consists only of uppercase letters. Returns false for null or empty strings.
- `isCapitalized(String input): boolean`:  checks if the input string starts with an uppercase letter followed only by lowercase letters. Returns false for null or empty strings.
- `isMixedCase(String input): boolean`: validates if the input string has alternating letter cases (either uppercase-lowercase-uppercase... or lowercase-uppercase-lowercase...). Returns false for null strings, strings less than 2 characters, or non-letter characters.
- `isPalindrome(String input): boolean`: determines if the input string is a palindrome, ignoring case and non-alphabetic characters. Returns false for null or empty strings
- `isStrictPalindrome(String input, boolean isStrict): boolean`: checks if the input string is a palindrome with optional strict checking. When `isStrict` is false, only considers letter order, ignoring case and non-alphabetical characters. Returns false for null or empty strings.
- `pairLetters(String input): String`: processes a string by removing adjacent identical characters (pairs) and returns the remaining string. **Examples**: "aab" → "b", "aabb" → "", "abfbaf" → "abfbaf", "abccbaf" → "f". Has O(n) time complexity.

### Calculus Tasks

**Source Code**: [CalculusTasks.java](./src/main/java/com/sng/CalculusTasks.java)

**Test Code**: [CalculusSpec.groovy](./src/test/groovy/CalculusSpec.groovy)

**Methods**:

- `swap(Integer i, int j, int[] anArray): boolean`: swaps elements at indices i and j in the array. Returns false if operation is impossible. Index j is greater than or equal to i.
- `min3(int a, int b, int c): int`: returns the minimum of three arguments without using standard library functions.
- `sum2(int a, int b): Integer`: returns the sum of two arguments or null if the sum cannot be correctly calculated due to overflow.
- `div2(Double a, Double b): Double`: returns the ratio a/b, treating null arguments as 0. Returns null if the result is not a real number.
- `sumN(int a0, int... ai): Integer`: calculates the sum of a0 and all elements in ai array, or null if it's impossible. An empty ai is treated as having a sum of zero.
- `sumN(double a0, double... ai): Double`: double-precision version of sumN that handles floating-point values.
- `avg(int a0, int... ai): Double`: calculates the average of all arguments or returns null if the result is not a real number.
- `rwx(String unixAccess): Integer`: converts a Unix-style permission string (e.g., "rwxrw-r--") to its numeric representation (e.g., 764). Returns null if conversion is impossible.
- `checkMask(int aValue, int aMask): boolean`: checks if the given value corresponds to the bit mask.
- `canModify(String from, String to): boolean`: checks if one string can be transformed into another by changing only one character (through replacement, insertion, or deletion) with O(n) complexity.

### Lunch Builder Pattern

**Source Code**: [Lunch.java](./src/main/java/com/sng/Lunch.java)

**Test Code**: [LunchSpec.groovy](./src/test/groovy/LunchSpec.groovy)

This implementation demonstrates the Builder design pattern with a cafeteria menu system.

#### Core Components

- `Lunch`: immutable class representing an ordered lunch with methods to get total weight, calories, and cost
- `Lunch.Builder`: inner class implementing the Builder pattern for creating Lunch objects
- `MenuItem`: nested class within Builder that represents individual food items with their properties

#### Menu Items

**The implementation includes various menu items**:

- Soups: Chicken soup, Ramen, Potato soup
- Main courses: Chicken meatball, Chakhokhbili, Pork chop, Pasta with fish
- Sides: Potato draniki

**Each menu item has defined properties**:

- Price (in rubles)
- Weight (in grams)
- Calorie content

#### Builder Methods

The builder supports both addition and refund operations:

- `addChickenSoup(int count)`, `addRamen(int count)`, etc.: Add specified quantity of items
- Negative count values act as refund operations

#### Implementation Features

- Handles arithmetic overflow correctly for large orders
- Supports order cancellation/refunds through negative count parameters
- Ensures immutability of the final Lunch object
- Properly accumulates price, weight, and calorie totals

### Exception Handling Tasks

**Source Code**: [ExceptionTasks.java](./src/main/java/com/sng/ExceptionTasks.java)

**Test Code**: [ExceptionSpec.groovy](./src/test/groovy/ExceptionSpec.groovy)

**Methods**:

- `MyException`: A custom RuntimeException implementation that avoids collecting stack traces for better performance.
- `throwMyException()`: Demonstrates a technique for creating exceptions without capturing a stack trace, making exception throwing as efficient as conditional branching.
- `doSomething(String riskyParam): Runnable`: Creates a Runnable that calls a risky operation with the given parameter, handling the checked exception and converting it to an unchecked exception.

### Generics Tasks

**Source Code**: [GenericsTasks.java](./src/main/java/com/sng/GenericsTasks.java)

**Test Code**: [GenericsSpec.groovy](./src/test/groovy/GenericsSpec.groovy)

**Methods**:

- `<T> swap(int i, int j, List<T> list): boolean`: Generic method that swaps elements at indices i and j in a list. Returns false if the operation is impossible.
- `<T extends Comparable<? super T>> minN(int i, int j, List<T> aList): T`: Generic method that returns the minimum element in a list within the range `[i, j)`, where i is inclusive and j is exclusive.
- `<T extends Comparable<? super T>> copyGreater(List<T> input, T element, List<? super T> output): void`: Generic method that copies elements from the input list that are strictly greater than the given element to the output list. Demonstrates advanced generic wildcards.

### Max Guests Task

**Source Code**: [MaxGuestsTask.java](./src/main/java/com/sng/MaxGuestsTask.java)

**Test Code**: [MaxGuestsSpec.groovy](./src/test/groovy/MaxGuestsSpec.groovy)

**Methods**:

- `maxGuests(List<Pair<Integer, Integer>> guestsDates): int`: Calculates the maximum number of guests present simultaneously in a hotel given check-in and check-out dates. Implements an `O(n)` algorithm using a map to track guest count changes.

### Person Tasks

**Source Code**: [PersonTask.java](./src/main/java/com/sng/PersonTask.java)

**Test Code**: [PersonSpec.groovy](./src/test/groovy/PersonSpec.groovy)

**Classes**:

- `Person`: A record class implementing Comparable, ordered by surname, name, and patronymic.

**Methods**:

- `sortByNameSurname(List<Person> persons): List<Person>`: Returns a new list sorted by name, then surname.
- `getMostPopularName(List<Person> persons): String`: Returns the most popular name. If there are multiple names with the same frequency, returns the first one alphabetically.
- `getTopNPopularNames(List<Person> persons, int topN): List<String>`: Returns the top N most popular names.
- `getMenAndWomenNumber(List<Person> persons): Pair<Integer, Integer>`: Returns the count of men and women based on patronymic suffix.
- `getHypotheticalRelatives(List<Person> persons): Map<String, List<Person>>`: Groups people who may be relatives based on common surname and patronymic.
- `getDuplicates(List<Person> persons): List<Person>`: Finds and returns duplicate people in the list.
- `getUniqueNames(List<Person> persons): List<String>`: Returns all unique names in alphabetical order.
- `getNameToPersons(List<Person> persons): Map<String, List<Person>>`: Groups people by name, sorting each group by surname, name, and patronymic.
- `getNamePartsLengthMedian(List<Person> persons): Map<Boolean, Integer[]>`: Calculates the median lengths of surnames, names, and patronymics for men and women.
- `getCharPopularity(List<Person> persons, int topN): Map<Boolean, Map<Character, Double>>`: Calculates the top N most popular letters in names for men and women.

## Some Implementation Details

### `isLowerCase` & `isUpperCase`

These methods utilize **Java Stream API** for concise and readable code implementation.

### `isMixedCase`

1. The algorithm assumes that for mixed case pattern validation, the input string must contain **at least two letters**. A single-letter string cannot be considered a sequence of mixed case letters.

    ```java
    public static boolean isMixedCase(String input) {
        if (input == null || input.length() < 2) {
            return false;
        }
        // Code...
    }
    ```

2. The method specifically checks for letters as required by the task specification, ignoring non-letter characters.
3. To verify the mixed case pattern, a boolean flag `shouldBeUpperCase` is used to track the expected case of the next character in the sequence.

### `isPalindrome`

According to the task requirements, this method is essentially a variant of `isStrictPalindrome` with the `isStrict` flag set to `false`.

### `isStrictPalindrome`

1. Implements a two-pointer algorithm for efficient palindrome checking.
2. Single-character strings are considered palindromes. In strict mode (`isStrict == true`), even a single non-letter character like `#` is considered a palindrome.
3. When `isStrict == false`, strings containing only non-letter characters are not considered palindromes since non-letter characters are ignored, resulting in an empty string which cannot be a palindrome.
4. For non-strict mode (`isStrict == false`), the implementation uses `StringBuilder` with an initial capacity equal to the input length to optimize memory allocation:

    ```java
    public static boolean isStrictPalindrome(String input, boolean isStrict) {
        // Code...
        if (!isStrict) {
            // Capacity for the worst case scenario: all letters
            StringBuilder processedInput = new StringBuilder(input.length());
            // Code...
        }
        // Code...
    }
    ```

### `pairLetters`

1. The implementation considers any **Unicode** character as a valid letter.
2. Uses `StringBuilder` with optimal initial capacity to handle the worst case scenario where no letter pairs are found:

    ```java
    public static String pairLetters(String input) {
        // Code...
        StringBuilder result = new StringBuilder(input.length());
        // Code...
    }
    ```

3. The algorithm achieves the required linear time complexity `O(n)` by processing the input string only once.

### `sum2` and `sumN`

1. Both methods implement careful overflow checking to avoid unexpected arithmetic errors.
2. The double version of `sumN` uses `BigDecimal` to ensure precise calculations before converting back to double.

### `rwx`

1. Follows the UNIX-style permission notation where:
   - `r` = read (4)
   - `w` = write (2)
   - `x` = execute (1)
2. Converts string format (e.g., "rwxr-xr--") to numeric (e.g., 754)
3. Thoroughly validates input to ensure it matches the expected 9-character pattern.

### Lunch Builder

1. The Builder pattern implementation allows for fluent, chainable method calls:

   ```java
   Lunch lunch = Lunch.builder()
       .addChickenSoup(2)
       .addPorkChop(1)
       .cook();
   ```

2. Handles edge cases gracefully:
   - Integer overflow for very large orders
   - Refunding more items than ordered
   - Negative initial order counts

### MyException & throwMyException

The implementation demonstrates an optimization technique for exception handling:

1. The custom `MyException` uses a special constructor from `RuntimeException` that disables stack trace collection:

   ```java
   super(null, null, false, false);
   ```

2. This approach significantly improves performance when exceptions are used as part of normal control flow, making exception throwing nearly as efficient as conditional branching.

3. This technique is particularly useful in high-performance applications where exceptions might be thrown frequently as part of the expected program logic rather than for truly exceptional conditions.

### Generic Methods & PECS Principle

1. The `copyGreater` method demonstrates the "Producer Extends, Consumer Super" (PECS) principle:

   ```java
   <T extends Comparable<? super T>> void copyGreater(List<T> input, T element, List<? super T> output)
   ```

2. The input list is a producer (we read from it), hence it uses `<T>` with extends bound.

3. The output list is a consumer (we write to it), hence it uses the wildcard with super bound `<? super T>`.

4. This design enables flexible use cases, such as copying `Integer` values into a `List<Number>` collection.

### Max Guests Algorithm

The `maxGuests` method implements an efficient algorithm with `O(n)` complexity by:

1. Using a map to track the changes in guest count at each day (checkin: +1, checkout: -1).

2. Finding the minimum and maximum dates in the dataset to establish the range for iteration.

3. Iterating through each day in the range, accumulating the guest count changes and tracking the maximum value observed.

4. This approach avoids the need to sort the events or use more complex data structures, maintaining linear time complexity.

### Person Tasks

1. `getHypotheticalRelatives` uses compound keys and normalization:
   - Converts female surnames (ending with "а") to male form by removing the suffix
   - Transforms female patronymics (ending with "на") to male form by replacing the suffix with "ич"
   - This enables family grouping across gender variations

2. `getNamePartsLengthMedian` calculates true medians:
   - For odd-length arrays, returns the middle value
   - For even-length arrays, returns the average of the two middle values

3. `getCharPopularity` uses multi-level sorting to handle ties:
   - First by frequency (descending)
   - Then alphabetically when frequencies are equal
