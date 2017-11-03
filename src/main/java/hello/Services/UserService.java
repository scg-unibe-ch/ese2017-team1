package hello.Services;


import hello.Login.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
