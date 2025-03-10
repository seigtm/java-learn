import org.spockframework.util.Pair
import spock.lang.Specification

class BaseSpec extends Specification {

    /**
     * Shortcut for list
     */
    static <T> List<T> list(T... p) {
        return List.of(p)
    }

    /**
     * Shortcut for pair
     */
    static p(int a, int b) {
        return Pair.of(a, b)
    }

}
