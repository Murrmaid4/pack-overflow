package learn.packOverflow.data;

import learn.packOverflow.models.User;

public class UserJdbcClientRepository implements UserRepository{
    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findById(int userId) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }
}
