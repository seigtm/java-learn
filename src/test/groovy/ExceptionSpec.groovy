import com.sng.ExceptionTasks
import com.sng.ExceptionTasks.MyException
import spock.lang.Specification

class ExceptionSpec extends Specification {

    def "1.1: wrapping checked exception"() {
        given:
        def runnable = ExceptionTasks.doSomething("do_throw")

        when:
        runnable.run()

        then:
        thrown(RuntimeException.class)
    }

    def "1.2: wrapping checked exception"() {
        given:
        def runnable = ExceptionTasks.doSomething("do nothing")

        when:
        runnable.run()

        then:
        noExceptionThrown()
    }


    def "2: exception without stack trace"() {
        given:
        MyException myException = null

        when:
        try {
            ExceptionTasks.throwMyException()
        } catch (MyException e) {
            myException = e
        }

        then:
        myException != null
        myException.getStackTrace().length == 0
    }

}
