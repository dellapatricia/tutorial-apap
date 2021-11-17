package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.RoleModel;
import apap.tutorial.cineplux.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> getListUser();
    UserModel getUser(String id);
    UserModel getUserByUsername(String username);
    void deleteUser(UserModel user);
    boolean passwordMatch(String passBaru, String passDB);
    void setPassword(UserModel user, String passBaru);
}
