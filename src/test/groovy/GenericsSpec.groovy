import com.sng.GenericsTasks
import spock.lang.Specification

class GenericsSpec extends Specification {

    def static arr02 = [0, 1, 2]

    def "1: swap"() {
        given:
        def result = GenericsTasks.swap(i, j, inputArray)

        expect:
        result == expectedRetVal
        (inputArray == null && expectedArray == null) || inputArray == expectedArray

        where:
        inputArray        |  i     |  j   ||   expectedRetVal | expectedArray
        ["a", "b", "c"]   |  0     |  1   ||   true           | ["b", "a", "c"]
        [0.0, 1.0, 2.0]   |  0     |  1   ||   true           | [1.0, 0.0, 2.0]
        arr02             |  0     |  1   ||   true           | [1, 0, 2]
        arr02             |  2     |  1   ||   false          | arr02
        arr02             |  0     |  5   ||   false          | arr02
        arr02             |  -1    |  2   ||   false          | arr02
        arr02             |  1     |  -2  ||   false          | arr02
        [0]               |  0     |  0   ||   true           | [0]
        []                |  0     |  0   ||   false          | []
        null              |  0     |  0   ||   false          | null
    }

    def "2: minN"() {
        given:
        def result = GenericsTasks.minN(i, j, inputArray)

        expect:
        result == expectedRetVal

        where:
        inputArray                                   | i | j || expectedRetVal
        new ArrayList<>(List.of( 0.0, 1.0, 2.0))     | 0 | 1 || 0.0
        new LinkedList<>(List.of(0, 0, 10, 2, 4, 7)) | 2 | 5 || 2
        ["f", "b", "c", "d", "e"]                    | 0 | 4 || "b"
        null                                         | 0 | 0 || null
    }

    def "2.1: minN - exceptions"() {
        given:
        def list = [1, 2, 3, 4, 5]

        when:
        GenericsTasks.minN(4, 2, list)

        then:
        thrown(IllegalArgumentException.class)
    }

    def "2.2: minN - exceptions"() {
        given:
        def list = [1, 2, 3, 4, 5]

        when:
        GenericsTasks.minN(-1, 2, list)

        then:
        thrown(IllegalArgumentException.class)
    }

    def "2.3: minN - exceptions"() {
        given:
        def list = [1, 2, 3, 4, 5]

        when:
        GenericsTasks.minN(0, 7, list)

        then:
        thrown(IllegalArgumentException.class)
    }


    def "3: copyGreater"() {
        given:
        def output = []
        GenericsTasks.copyGreater(input, element, output)

        expect:
        output == expectedResult

        where:
        input               | element   || expectedResult
        [1, 2, 3, 4, 5]     | 2         || [3, 4, 5]
        [1, 2, 3, 4, 5]     | 5         || []
        [1, 2, 3, 4, 5]     | 6         || []
        null                | null      || []
    }

    def "3.1: testCopyGreater2"() {
        expect: GenericsTasks.testCopyGreater2() == true
    }
}
