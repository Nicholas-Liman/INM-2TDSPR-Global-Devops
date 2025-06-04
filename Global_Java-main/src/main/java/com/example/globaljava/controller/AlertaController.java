package com.example.globaljava.controller;

import com.example.globaljava.model.Alerta;
import com.example.globaljava.model.Historico;
import com.example.globaljava.model.Usuario;
import com.example.globaljava.repositories.AlertaRepository;
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
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaRepository alertaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HistoricoRepository historicoRepository;


    @GetMapping
    public ModelAndView listarAlertas() {
        List<Alerta> alertas = alertaRepository.findAll();
        return new ModelAndView("Alertas/lista-alertas", "alertas", alertas);
    }

    @GetMapping("/novo/{cpf}")
    public ModelAndView novoAlertaForm(@PathVariable String cpf) {
        Alerta alertaVazio = new Alerta();
        ModelAndView mv = new ModelAndView("Alertas/cadastrar-alerta");
        mv.addObject("alerta", alertaVazio);
        mv.addObject("cpf", cpf);
        return mv;
    }


    @PostMapping("/novo/{cpf}")
    public String salvarAlerta(@ModelAttribute Alerta alerta, Model model, @PathVariable String cpf) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByCpfUser(cpf);

        if (usuarioOptional.isEmpty()) {
            model.addAttribute("erro", "Usuário com CPF informado não encontrado.");
            return "Alertas/cadastrar-alerta";
        }

        Usuario usuario = usuarioOptional.get();
        Historico historico = usuario.getHistorico();


        alerta.setUsuario(usuario);
        alerta.setHistorico(historico);

        alertaRepository.save(alerta);

        model.addAttribute("sucesso", "Alerta salvo com sucesso.");
        return "redirect:/alertas";
    }


    @GetMapping("/{id}")
    public ModelAndView listarAlerta(@PathVariable Long id) {
        Optional<Alerta> alertaOptional = alertaRepository.findById(id);
        return alertaOptional
                .map(alerta -> new ModelAndView("Alertas/listar-alerta", "alerta", alerta))
                .orElseGet(() -> new ModelAndView("redirect:/alertas", "erro", "Alerta não encontrado."));
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarAlertaForm(@PathVariable Long id) {
        Optional<Alerta> alertaOptional = alertaRepository.findById(id);
        return alertaOptional.map(alerta -> new ModelAndView("Alertas/editar-alerta", "alerta", alerta)).orElseGet(() -> new ModelAndView("redirect:/alertas", "erro", "Alerta não encontrado."));
    }

    @PostMapping("/editar")
    public ModelAndView atualizarAlerta(@ModelAttribute Alerta alertaParam) {
        Optional<Alerta> alertaOptional = alertaRepository.findById(alertaParam.getId());

        if (alertaOptional.isPresent()) {
            Alerta alertaExistente = alertaOptional.get();

            alertaExistente.setDataAlerta(alertaExistente.getDataAlerta());
            alertaExistente.setHorario(alertaParam.getHorario());
            alertaExistente.setLatitude(alertaExistente.getLatitude());
            alertaExistente.setLongitude(alertaExistente.getLongitude());
            alertaExistente.setEvento(alertaParam.getEvento());
            alertaExistente.setGravidade(alertaExistente.getGravidade());
            alertaExistente.setUsuario(alertaExistente.getUsuario());
            alertaExistente.setHistorico(alertaExistente.getHistorico());

            alertaRepository.save(alertaExistente);

            return new ModelAndView("redirect:/alertas", "sucesso", "Alerta atualizado com sucesso!");
        }

        return new ModelAndView("Alerta/editar-alerta", "erro", "Erro ao atualizar o alerta.");
    }

    // Deletar alerta
    @GetMapping("/deletar/{id}")
    public ModelAndView deletarAlerta(@PathVariable Long id) {
        Optional<Alerta> alertaOptional = alertaRepository.findById(id);
        if (alertaOptional.isPresent()) {
            alertaRepository.deleteById(id);
            return new ModelAndView("redirect:/alertas", "sucesso", "Alerta deletado com sucesso!");
        }
        return new ModelAndView("redirect:/alertas", "erro", "Alerta não encontrado para exclusão.");
    }
}
