package judgev2.web;

import judgev2.model.binding.ExerciseAddBindingModel;
import judgev2.model.service.ExerciseServiceModel;
import judgev2.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add(@Valid @ModelAttribute("exerciseAddBindingModel")
                                  ExerciseAddBindingModel exerciseAddBindingModel,
                      BindingResult bindingResult) {
        return "exercise-add";
    }

    @PostMapping("/add")
    public String addPost(@Valid @ModelAttribute("exerciseAddBindingModel")
                                      ExerciseAddBindingModel exerciseAddBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseAddBindingModel", exerciseAddBindingModel);
            return "redirect:/exercise/add";
        } else {

            ExerciseServiceModel exerciseServiceModel = this.modelMapper.map(exerciseAddBindingModel, ExerciseServiceModel.class);
            this.exerciseService.addExercise(exerciseServiceModel);
            return "redirect:/";
        }
    }
}
