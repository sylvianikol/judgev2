package judgev2.service;

import judgev2.model.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    List<String> getAllUsernames();

    void addRoleToUser(String username, String role);
}
