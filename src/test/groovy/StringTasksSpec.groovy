import spock.lang.Specification

class StringTasksSpec extends Specification {

    def "1: is lower case test"() {
        given:
        def result = StringTasks.isLowerCase(input)

        expect:
        result == expectedResult

        where:
        input                        ||   expectedResult
        null                         ||   false
        ""                           ||   false
        " "                          ||   false
        "123"                        ||   false
        "HelloWorld"                 ||   false
        "HELLOWORLD"                 ||   false
        "hello world"                ||   false
        "helloworld"                 ||   true
        "русский"                    ||   true
        "руССкий"                    ||   false
        "🌭"                         ||   false  // Unicode emoji
        "\u0018"                     ||   false  // Control character "Cancel"
    }

    def "2: is upper case test"() {
        given:
        def result = StringTasks.isUpperCase(input)

        expect:
        result == expectedResult

        where:
        input                        ||   expectedResult
        null                         ||   false
        ""                           ||   false
        " "                          ||   false
        "123"                        ||   false
        "HelloWorld"                 ||   false
        "hello world"                ||   false
        "helloworld"                 ||   false
        "HELLOWORLD"                 ||   true
        "РУССКИЙ"                    ||   true
        "РУсский"                    ||   false
        "🫖"                         ||   false  // Unicode emoji
        "\u0000"                     ||   false  // Control character "Null"
    }

    def "3: is camel case test"() {
        given:
        def result = StringTasks.isCapitalized(input)

        expect:
        result == expectedResult

        where:
        input                        ||   expectedResult
        null                         ||   false
        ""                           ||   false
        " "                          ||   false
        "123"                        ||   false
        "HelloWorld"                 ||   false
        "hello world"                ||   false
        "helloworld"                 ||   false
        "HELLOWORLD"                 ||   false
        "Helloworld"                 ||   true
        "русскийтекст"               ||   false
        "РУССКИЙТЕКСТ"               ||   false
        "Русскийтекст"               ||   true
        "РусскийТекст"               ||   false
        "Русский текст"              ||   false
        "Mixedтекст"                 ||   true
        "Mixed-текст и не_буквы!"    ||   false
    }

    def "4: is mixed case test"() {
        given:
        def result = StringTasks.isMixedCase(input)

        expect:
        result == expectedResult

        where:
        input                        ||   expectedResult
        null                         ||   false
        ""                           ||   false
        " "                          ||   false
        "123"                        ||   false
        "HelloWorld"                 ||   false
        "hello world"                ||   false
        "helloworld"                 ||   false
        "HELLOWORLD"                 ||   false
        "Helloworld"                 ||   false
        "HeLlOwOrLd"                 ||   true
        "РусскийТекст"               ||   false
        "русский текст"              ||   false
        "русскийтекст"               ||   false
        "РУССКИЙТЕКСТ"               ||   false
        "рУсСкИйТеКсТ"               ||   true
        "РуСсКиЙтЕкСт"               ||   true
        "я"                          ||   false
        "h E l L o"                  ||   false
        "  lEaDiNgSpAcEs"            ||   false
        "tRaIlInG sPaCeS  "          ||   false
    }

    def "5: is palindrome"() {
        given:
        def result = StringTasks.isPalindrome(input)

        expect:
        result == expectedResult

        where:
        input                                    ||   expectedResult
        null                                     ||   false
        ""                                       ||   false
        "A nut for a jar of tuna"                ||   true
        "Stressed desserts"                      ||   true
        "Madam, in Eden, Im Adam."               ||   true
        "Hello world!"                           ||   false
        "Good morning"                           ||   false
        "@level@"                                ||   true
        "@%@level%"                              ||   true
        "О, духи, от уборки микробу-то и худо"   ||   true
        "@дед@"                                  ||   true
        "@%@дед%"                                ||   true
        "#"                                      ||   false
        "I"                                      ||   true
        "я"                                      ||   true
    }

    def "6: is strict palindrome"() {
        given:
        def result = StringTasks.isStrictPalindrome(input, isStrict)

        expect:
        result == expectedResult

        where:
        input                                    ||   expectedResult  || isStrict
        null                                     ||   false           || true
        null                                     ||   false           || false
        ""                                       ||   false           || true
        ""                                       ||   false           || false
        "A nut for a jar of tuna"                ||   true            || false
        "A nut for a jar of tuna"                ||   false           || true
        "anutforajaroftuna"                      ||   true            || true
        "Good morning"                           ||   false           || true
        "Good morning"                           ||   false           || false
        "@level@"                                ||   true            || true
        "@level@"                                ||   true            || false
        "@%@level%"                              ||   false           || true
        "@%@level%"                              ||   true            || false
        "О, духи, от уборки микробу-то и худо"   ||   false           || true
        "О, духи, от уборки микробу-то и худо"   ||   true            || false
        "@дед@"                                  ||   true            || true
        "@дед@"                                  ||   true            || false
        "@%@дед%"                                ||   false           || true
        "@%@дед%"                                ||   true            || false
        "##"                                     ||   true            || true
        "##"                                     ||   false           || false
        "@"                                      ||   true            || true
        "@"                                      ||   false           || false
        "I"                                      ||   true            || true
        "I"                                      ||   true            || false
        "я"                                      ||   true            || true
        "я"                                      ||   true            || false
        "123 level 123"                          ||   false           || true
        "123 level 123"                          ||   true            || false
        "Дед"                                    ||   false           || true
        "Дед"                                    ||   true            || false
    }


    def "7: pair letters"() {
        given:
        def result = StringTasks.pairLetters(input)

        expect:
        result == expectedResult

        where:
        input               ||   expectedResult
        "aab"               ||   "b"
        "aabb"              ||   ""
        "abfbaf"            ||   "abfbaf"
        "abccbaf"           ||   "f"
        "aabccbaf"          ||   "af"
        "gabccbaf"          ||   "gf"
        null                ||   null
        ""                  ||   ""
        "null"              ||   "nu"
        "@"                 ||   "@"
        "@@"                ||   ""
        "я@##@"             ||   "я"
    }
}
