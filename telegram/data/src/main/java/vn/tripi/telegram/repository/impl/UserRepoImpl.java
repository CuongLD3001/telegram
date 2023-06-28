package vn.tripi.telegram.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.tripi.telegram.entity.User;
import vn.tripi.telegram.repository.jpa.UserJPARepo;
import vn.tripi.telegram.repository.repo.UserRepo;

import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo {
    private final UserJPARepo userJPARepo;
    @Autowired
    public UserRepoImpl(UserJPARepo userJPARepo) {
        this.userJPARepo = userJPARepo;
    }

    public User findByChatId(String chatId){
        return userJPARepo.findByChatId(chatId);
    }

    public User findByUsername(String username){
        return userJPARepo.findByUsername(username);
    }

    public User save(User user){
        return userJPARepo.save(user);
    }

    public List<User> findAll(){
        return userJPARepo.findAll();
    }

}