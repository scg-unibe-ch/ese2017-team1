package hello.Services;


import hello.Users.User;

public interface UserService {
    public User findUserByEmail(String email);
    public User findUser(Long userId);

    public void saveUser(User user, Long roleId);
    public void deleteUser(Long userId);

    public Iterable<User> listAllUsers();
    public Iterable<User> listLogisticians();
    public Iterable<User> listDrivers();

    public void changePassword(String password);

    public User currentUser();

    boolean userExists(User user);
}
