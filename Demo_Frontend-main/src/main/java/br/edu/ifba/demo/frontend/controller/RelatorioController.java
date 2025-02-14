package br.edu.ifba.demo.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/relatorios")
public class RelatorioController {
    
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("relatorios/index");
        mv.addObject("data", new double[]{10, 9.9, 9.4, 10, 9.9,9.2, 10, 10, 9.1, 8.3});
        return mv;
    }
}