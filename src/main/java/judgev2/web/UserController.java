package judgev2.web;

import judgev2.model.binding.UserAddBindingModel;
import judgev2.model.service.UserServiceModel;
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

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("userAddBindingModel")
                                                    UserAddBindingModel userAddBindingModel,
                                        BindingResult bindingResult, ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("redirect:/users/register");
        } else {
            UserServiceModel userServiceModel =
                    this.modelMapper.map(userAddBindingModel, UserServiceModel.class);

            this.userService.registerUser(userServiceModel);
            modelAndView.setViewName("redirect:/users/login");
        }

        return modelAndView;
    }
}
