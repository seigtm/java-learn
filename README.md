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
- [Some Implementation Details](#some-implementation-details)
  - [`isLowerCase` \& `isUpperCase`](#islowercase--isuppercase)
  - [`isMixedCase`](#ismixedcase)
  - [`isPalindrome`](#ispalindrome)
  - [`isStrictPalindrome`](#isstrictpalindrome)
  - [`pairLetters`](#pairletters)
  - [`sum2` and `sumN`](#sum2-and-sumn)
  - [`rwx`](#rwx)
  - [Lunch Builder](#lunch-builder)

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
