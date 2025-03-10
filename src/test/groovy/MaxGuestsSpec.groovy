import com.sng.MaxGuestsTask
import org.spockframework.util.Pair

class MaxGuestsSpec extends BaseSpec {

    def "1: max guests"() {
        given:
        def result = MaxGuestsTask.maxGuests(guestsDates)

        expect:
        result == expectedMaxGuestsAtDay

        where:
        guestsDates                                 ||   expectedMaxGuestsAtDay
        list(p(1, 2))                               ||   1
        list(p(1, 2), p(3, 4))                      ||   1
        list(p(1, 2), p(2, 3))                      ||   1
        list(p(1, 5), p(0, 1), p(4, 5))             ||   2
        list(p(4, 5), p(0, 1), p(1, 5))             ||   2
        list(p(1, 5), p(2, 3), p(3, 4), p(6, 7))    ||   2
        list(p(7, 9), p(2, 5), p(1, 6), p(0, 7))    ||   3
    }

    def "2: max guests - negative"() {
        given:
        def result = MaxGuestsTask.maxGuests(guestsDates)

        expect:
        result == expectedMaxGuestsAtDay

        where:
        guestsDates               ||   expectedMaxGuestsAtDay
        new ArrayList<Pair>()     ||   0
        null                      ||   0
    }

}
