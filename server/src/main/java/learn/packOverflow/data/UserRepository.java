package learn.packOverflow.data;

import learn.packOverflow.models.User;

import java.util.List;

public interface UserRepository {

    public List<User> findAll();

    public User findByUsername(String username);

    public User findById(int userId);

    public User create(User user);
}
