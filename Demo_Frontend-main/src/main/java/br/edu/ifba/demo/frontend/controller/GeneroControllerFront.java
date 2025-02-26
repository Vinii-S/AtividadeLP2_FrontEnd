package br.edu.ifba.demo.frontend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import br.edu.ifba.demo.frontend.dto.GeneroDTO;
import br.edu.ifba.demo.frontend.service.GeneroService;

@Controller
@RequestMapping("/genero")
public class GeneroControllerFront {

    @Autowired
    private GeneroService generoService;

    // Listagem de gêneros
    @GetMapping("/list")
    public ModelAndView listarGeneros() {
        List<GeneroDTO> generos = generoService.listAllGeneros();
        ModelAndView mv = new ModelAndView("genero/list");
        mv.addObject("generos", generos);
        return mv;
    }

    // Exibir formulário para criar um novo gênero
    @GetMapping("/novo")
    public ModelAndView novoGenero() {
        ModelAndView mv = new ModelAndView("genero/form2");
        mv.addObject("genero", new GeneroDTO());
        mv.addObject("view", false);
        return mv;
    }

    // Exibir detalhes de um gênero
    @GetMapping("/view/{id}")
    public ModelAndView exibirGenero(@PathVariable Long id) {
        GeneroDTO genero = generoService.getById(id);
        ModelAndView mv = new ModelAndView("genero/form2");
        mv.addObject("genero", genero);
        mv.addObject("view", true); 
        return mv;
    }

    // Exibir formulário para edição de um gênero
    @GetMapping("/editar/{id}")
    public ModelAndView editarGenero(@PathVariable Long id) {
        GeneroDTO genero = generoService.getById(id);
        ModelAndView mv = new ModelAndView("genero/form2");
        mv.addObject("genero", genero);
        mv.addObject("view", false);
        return mv;
    }

    // Excluir um gênero
    @GetMapping("/excluir/{id}")
    public String excluirGenero(@PathVariable Long id) {
        generoService.delete(id);
        return "redirect:/genero/list";
    }

    // Salvar ou atualizar
    @PostMapping
    public ModelAndView salvarGenero(@ModelAttribute("genero") GeneroDTO genero) {
        boolean saved;
        if (genero.getId_genero() != null) {
            saved = generoService.atualizar(genero);
        } else {
            saved = generoService.salvar(genero);
        }

        if (saved) {
            return new ModelAndView("redirect:/genero/list");
        } else {
            ModelAndView mv = new ModelAndView("genero/form2");
            mv.addObject("genero", genero);
            mv.addObject("error", "Não foi possível salvar o gênero.");
            return mv;
        }
    }

    @PutMapping("/{id}")
    public ModelAndView atualizarGenero(@PathVariable Long id, @ModelAttribute("genero") GeneroDTO genero) {
        boolean updated = generoService.atualizar(genero);
        if (updated) {
            return new ModelAndView("redirect:/genero/list");
        } else {
            ModelAndView mv = new ModelAndView("genero/form2");
            mv.addObject("genero", genero);
            mv.addObject("error", "Não foi possível atualizar o gênero.");
            return mv;
        }
    }
}
