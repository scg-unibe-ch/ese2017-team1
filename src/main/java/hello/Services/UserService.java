package hello.Services;


import hello.Users.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
