package judgev2.web;

import judgev2.model.binding.RoleAddBindingModel;
import judgev2.model.service.RoleServiceModel;
import judgev2.service.RoleService;
import judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleController(RoleService roleService, UserService userService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.addObject("usernames", this.userService.getAllUsernames());
        modelAndView.setViewName("role-add");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addPost(@Valid @ModelAttribute("roleAddBindingModel")
                                      RoleAddBindingModel roleAddBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("roleAddBindingModel", roleAddBindingModel);
            modelAndView.setViewName("redirect:/role/add");
        } else {
            this.userService.addRoleToUser(
                    roleAddBindingModel.getUsername(), roleAddBindingModel.getRole());
            modelAndView.setViewName("redirect:/");
        }
         return modelAndView;
    }
}
