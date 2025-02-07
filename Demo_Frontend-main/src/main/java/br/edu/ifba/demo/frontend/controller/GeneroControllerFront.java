package br.edu.ifba.demo.frontend.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.edu.ifba.demo.frontend.dto.GeneroDTO;
import br.edu.ifba.demo.frontend.service.GeneroService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/genero")
public class GeneroControllerFront {

    @Autowired
    private GeneroService generoService;

    @GetMapping("/list")
    public ModelAndView listarGeneros() {
        List<GeneroDTO> generos = generoService.listAllGeneros();
        ModelAndView mv = new ModelAndView("genero/list");
        mv.addObject("generos", generos);
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView novoGenero() {
        ModelAndView mv = new ModelAndView("genero/form");
        mv.addObject("genero", new GeneroDTO());
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarGenero(@PathVariable Long id) {
        GeneroDTO genero = generoService.getById(id);
        ModelAndView mv = new ModelAndView("genero/form");
        mv.addObject("genero", genero);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluirGenero(@PathVariable Long id) {
        generoService.delete(id);
        return "redirect:/genero/list";
    }
}