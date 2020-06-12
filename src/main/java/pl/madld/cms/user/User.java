package pl.madld.cms.user;

public interface User {
    Long getId();
    String getEmail();
    String getPassword();
    String getConfirmPassword();
}
