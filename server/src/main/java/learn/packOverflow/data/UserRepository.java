package learn.packOverflow.data;

import learn.packOverflow.models.User;

public interface UserRepository {

    public User findByUsername(String username);

    public User findById(int userId);

    public User create(User user);
}
