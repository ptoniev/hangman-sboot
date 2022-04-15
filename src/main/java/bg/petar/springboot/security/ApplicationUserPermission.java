package bg.petar.springboot.security;

public enum ApplicationUserPermission {
    HANGMAN_PLAY("hangman:play"),
    HANGMAN_CONTROL("hangman:control");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
