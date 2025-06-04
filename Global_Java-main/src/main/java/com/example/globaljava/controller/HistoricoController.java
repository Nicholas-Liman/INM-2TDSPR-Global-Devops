package com.example.globaljava.controller;

import com.example.globaljava.model.Historico;
import com.example.globaljava.model.Usuario;
import com.example.globaljava.repositories.HistoricoRepository;
import com.example.globaljava.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/historicos")
@RequiredArgsConstructor
public class HistoricoController {

    private final HistoricoRepository historicoRepository;
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public ModelAndView listarHistoricos() {
        List<Historico> historicos = historicoRepository.findAll();
        return new ModelAndView("Historicos/lista-historicos", "historicos", historicos);
    }

    @GetMapping("/{id}")
    public ModelAndView visualizarHistorico(@PathVariable Long id) {
        Optional<Historico> historico = historicoRepository.findById(String.valueOf(id));
        return historico
                .map(h -> new ModelAndView("Historicos/listar-historico", "historico", h))
                .orElseGet(() -> new ModelAndView("redirect:/historicos", "erro", "Histórico não encontrado."));
    }



    @GetMapping("/deletar/{id}")
    public String deletarHistorico(@PathVariable Long id, Model model) {
        Optional<Historico> historicoOptional = historicoRepository.findById(String.valueOf(id));
        if (historicoOptional.isPresent()) {
            historicoRepository.deleteById(String.valueOf(id));
            return "redirect:/historicos";
        }
        model.addAttribute("erro", "Histórico não encontrado para exclusão.");
        return "redirect:/historicos";
    }
}
