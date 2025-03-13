package learn.packOverflow.data;

import learn.packOverflow.data.mappers.UserMapper;
import learn.packOverflow.models.User;
import org.springframework.jdbc.core.simple.JdbcClient;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcClientRepository implements UserRepository{
    JdbcClient client;

    public UserJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }


    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return client.sql(sql).query(new UserMapper()).list();
    }

    @Override
    public User findByUsername(String username) {
        final String sql = "SELECT * FROM `user` WHERE username = ?";

        return client.sql(sql)
                .param(username)
                .query(new UserMapper())
                .optional().orElse(null);
    }

    @Override
    public User findById(int userId) {
        final String sql = "SELECT * FROM `user` WHERE user_id = ?";

        return client.sql(sql)
                .param(userId)
                .query(new UserMapper())
                .optional().orElse(null);
    }

    @Override
    public User create(User user) {
        final String sql = "INSERT INTO `user` (username, password, email, first_name, last_name) VALUES (:username, :password, :email, :first_name, :last_name)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = client.sql(sql)
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("email", user.getEmail())
                .param("first_name", user.getFirst_name())
                .param("last_name", user.getLast_name())
                .update(keyHolder, "user_id");

        if (rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }
}
