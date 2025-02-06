package br.edu.ifba.demo.frontend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.edu.ifba.demo.frontend.dto.GeneroDTO;
import br.edu.ifba.demo.frontend.service.GeneroService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/genero")
public class GeneroController {
    @Autowired
    private GeneroService generoService;

    @PostMapping("/")
    public ModelAndView  salvarGenero(@ModelAttribute("genero") GeneroDTO generoDTO) {
        System.out.println(generoDTO);
        generoService.salvarOuAtualizar(generoDTO);
        ModelAndView mv = new ModelAndView("redirect:/livros/");
        return mv;
    }
}
