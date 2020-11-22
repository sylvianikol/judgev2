package judgev2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exercise")
public class ExercisesController {

    @GetMapping("/add")
    public String add() {
        return "exercise-add";
    }
}
