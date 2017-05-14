package app

import org.grails.datastore.mapping.engine.event.PreInsertEvent
import org.grails.datastore.mapping.engine.event.PreUpdateEvent
import grails.events.annotation.gorm.Listener
import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

@Component
@CompileStatic
class UserPasswordEncoderListener  {

    @Listener
    void beforeInsert(PreInsertEvent event) {
        if ( event.entityObject instanceof User ) {
            User u = (event.entityObject as User)
            event.getEntityAccess().setProperty('password', insertEncodePassword(u.password))
        }
    }

    @Listener
    void beforeUpdate(PreUpdateEvent event) {
        if ( event.entityObject instanceof User) {
            User u = (event.entityObject as User)
            if ( u.isDirty('password') ) {
                event.getEntityAccess().setProperty('password', updateEncodePassword(u.password))
            }
        }
    }

    private String insertEncodePassword(String password) {
        'xxxx'
    }

    private String updateEncodePassword(String password) {
        'yyyy'
    }
}
