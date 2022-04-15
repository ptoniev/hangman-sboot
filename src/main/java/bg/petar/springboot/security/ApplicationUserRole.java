package bg.petar.springboot.security;
import com.google.common.collect.Sets;

import java.util.Set;

import static bg.petar.springboot.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    GUEST(Sets.newHashSet(HANGMAN_PLAY)),
    USER(Sets.newHashSet(HANGMAN_PLAY)),
    ADMIN(Sets.newHashSet(HANGMAN_PLAY, HANGMAN_CONTROL));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

}
