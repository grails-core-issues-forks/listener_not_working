package app

import grails.test.mixin.integration.Integration
import spock.lang.Specification

@Integration
class UserSpec extends Specification {

    void "test something"() {
        when:
        def u
        User.withTransaction {
            u = new User(username: 'sherlock', password: 'elementary')
            u.save()
        }
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
        User.withTransaction {
            u.password = 'foobar'
            u.save()
        }

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
