package app

import grails.gorm.services.Service
import groovy.transform.CompileStatic

@CompileStatic
@Service(User)
abstract class UserService {

    abstract User saveUser(String username, String password)

    User updateUser(String username, String password) {
        User u = User.where { username == username }.get()
        if(u != null) {
            u.password = password
            u.save()
        }
        u
    }
}