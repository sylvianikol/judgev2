package judgev2.service.impl;

import judgev2.model.entity.Role;
import judgev2.model.service.RoleServiceModel;
import judgev2.repository.RoleRepository;
import judgev2.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        if (this.roleRepository.count() == 0) {
            Role admin = new Role("ADMIN");
            Role user = new Role("USER");

            this.roleRepository.save(admin);
            this.roleRepository.save(user);
        }
    }

    @Override
    public RoleServiceModel findByName(String name) {
        Role role = this.roleRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("%s '%s' does not exists!", Role.class.getSimpleName(), name))
        );

        return this.modelMapper.map(role, RoleServiceModel.class);
    }
}
