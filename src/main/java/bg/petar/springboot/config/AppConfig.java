package bg.petar.springboot.config;

import bg.petar.springboot.security.CustomRealm;
import bg.petar.springboot.security.SecurityPractice;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc
//@Configuration
public class AppConfig {

//    @Bean
//    public Realm realm(){
//        return new CustomRealm();
//    }

//    @Bean
//    public SecurityPractice securityManager(){
//        return new SecurityPractice();
//    }

//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(){
//        ShiroFilterFactoryBean shiroFilterObj = new ShiroFilterFactoryBean();
//        SecurityPractice securityObj = securityManager();
//        shiroFilterObj.setSecurityManager((SecurityManager) securityObj);
//        shiroFilterObj.setSuccessUrl("/game");
//        shiroFilterObj.setLoginUrl("/login");
//        shiroFilterObj.setUnauthorizedUrl("/unauthorizedError");
//        shiroFilterObj.setFilterChainDefinitions("/unauthorizedError");
//        return shiroFilterObj;
//    }

//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition filter
//                = new DefaultShiroFilterChainDefinition();
//
//        filter.addPathDefinition("/game", "authc");
//        filter.addPathDefinition("/login", "anon");
//
//        return filter;
//    }

}
