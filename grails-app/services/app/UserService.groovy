package app

import grails.compiler.GrailsCompileStatic
import grails.gorm.services.Service

@GrailsCompileStatic
@Service(User)
abstract class UserService {

    abstract User saveUser(String username, String password)

    User updateUser(String username, String password) {
        User u = User.findByUsername(username)
        if(u != null) {
            u.password = password
            u.save()
        }
        u
    }
}