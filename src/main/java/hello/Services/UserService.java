package hello.Services;


import hello.Users.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user, Long roleId);

    public Iterable<User> listAllUsers();
    public Iterable<User> listLogisticians();
    public Iterable<User> listDrivers();
}
