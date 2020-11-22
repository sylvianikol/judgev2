package judgev2.service.impl;

import judgev2.model.entity.Role;
import judgev2.model.entity.User;
import judgev2.model.service.RoleServiceModel;
import judgev2.model.service.UserServiceModel;
import judgev2.repository.UserRepository;
import judgev2.service.RoleService;
import judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static judgev2.constants.ErrorMessages.ENTITY_NOT_EXISTS;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        userServiceModel.setRole(this.roleService
                .findByName(this.userRepository.count() == 0
                        ? "ADMIN" : "USER"));

        User user = this.modelMapper.map(userServiceModel, User.class);
        this.userRepository.saveAndFlush(user);

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<String> getAllUsernames() {
        return this.userRepository.findAll().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    @Override
    public void addRoleToUser(String username, String role) {
        User user = this.userRepository.findByUsername(username).orElse(null);

        if (!user.getRole().getName().equals(role)) {
            Role roleEntity = this.modelMapper
                    .map(this.roleService.findByName(role), Role.class);
            user.setRole(roleEntity);
            this.userRepository.saveAndFlush(user);
        }
    }
}
