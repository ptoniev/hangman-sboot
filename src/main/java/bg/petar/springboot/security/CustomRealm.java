package bg.petar.springboot.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.jdbc.JdbcRealm;

import java.util.*;

public class CustomRealm extends JdbcRealm {
//    private Map<String, String> credentials = new HashMap<>();
//    private Map<String, Set<String>> roles = new HashMap<>();
//    private Map<String, Set<String>> perm = new HashMap<>();
//
//    {
//        credentials.put("user", "password");
//        credentials.put("user2", "password2");
//        credentials.put("user3", "password3");
//
//        roles.put("user", new HashSet<>(Arrays.asList("admin")));
//        roles.put("user2", new HashSet<>(Arrays.asList("editor")));
//        roles.put("user3", new HashSet<>(Arrays.asList("author")));
//
//        perm.put("admin", new HashSet<>(Arrays.asList("*")));
//        perm.put("editor", new HashSet<>(Arrays.asList("articles:*")));
//        perm.put("author",
//                new HashSet<>(Arrays.asList("articles:compose",
//                        "articles:save")));
//    }
//
//    @Override
//    protected AuthenticationInfo
//    doGetAuthenticationInfo(AuthenticationToken token)
//            throws AuthenticationException {
//
//        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
//
//        if(uToken.getUsername() == null
//                || uToken.getUsername().isEmpty()
//                || !credentials.containsKey(uToken.getUsername())) {
//            throw new UnknownAccountException("username not found!");
//        }
//
//        return new SimpleAuthenticationInfo(
//                uToken.getUsername(),
//                credentials.get(uToken.getUsername()),
//                getName());
//    }

}
