package bg.petar.springboot.security;


import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class SecurityPractice {

//    Logger log = LoggerFactory.getLogger(SecurityPractice.class);
//
//    @Autowired
//    Realm realm;
//
//    public void obtainCurrentUser(){
//        DefaultSecurityManager securityManager = new DefaultSecurityManager(realm);
//
//        SecurityUtils.setSecurityManager(securityManager);
//        Subject currentUser = SecurityUtils.getSubject();
//
//        isAuthenticated(currentUser);
//    }
//
//    public SecurityManager getSecurityManager()
//    {
//         return (SecurityUtils.getSecurityManager());
//    }
//
//    public void isAuthenticated(Subject currentUser){
//        if (!currentUser.isAuthenticated()) {
//            UsernamePasswordToken token
//                    = new UsernamePasswordToken("user", "password");
//            token.setRememberMe(true);
//            try {
//                currentUser.login(token);
//            } catch (UnknownAccountException uae) {
//                log.error("Username Not Found!", uae);
//            } catch (IncorrectCredentialsException ice) {
//                log.error("Invalid Credentials!", ice);
//            } catch (LockedAccountException lae) {
//                log.error("Your Account is Locked!", lae);
//            } catch (AuthenticationException ae) {
//                log.error("Unexpected Error!", ae);
//            }
//        }
//    }
//


}
