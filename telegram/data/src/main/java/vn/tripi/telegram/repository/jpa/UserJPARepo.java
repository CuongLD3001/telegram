package vn.tripi.telegram.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tripi.telegram.entity.User;

@Repository
public interface UserJPARepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByChatId(String chatId);
    User save(User user);

}
