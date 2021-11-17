package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.UserModel;
import apap.tutorial.cineplux.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new  BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<UserModel> getListUser() {
        return userDb.findAll();
    }

    @Override
    public UserModel getUser(String id) {
        return userDb.findUserModelById(id);
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public void deleteUser(UserModel user) {
        userDb.delete(user);
    }

    @Override
    public boolean passwordMatch(String passLama, String passDB) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(passLama, passDB);
    }

    @Override
    public void setPassword(UserModel user, String passBaru) {
        String password = encrypt(passBaru);
        user.setPassword(password);
        userDb.save(user);
    }
}

