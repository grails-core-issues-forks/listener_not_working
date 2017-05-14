package app

import grails.test.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
class UserServiceSpec extends Specification {

    @Autowired UserService userService

    void "test something"() {
        when:
        def u = userService.saveUser('sherlock', 'elementary')

        then:
        !u.hasErrors()

        when:
        sleep(500)
        User.withTransaction {
            u = User.findByUsername('sherlock')
        }

        then:
        u
        u.password == 'xxxx'

        when:
        u = userService.updateUser('sherlock', 'foobar')

        then:
        !u.hasErrors()

        when:
        sleep(500)
        User.withTransaction {
            u = User.findByUsername('sherlock')
        }

        then:
        u
        u.password == 'yyyy'
    }
}
