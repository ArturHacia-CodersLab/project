package pl.madld.cms.user;

public interface UserService {
    User findByEmail(String email);
}
