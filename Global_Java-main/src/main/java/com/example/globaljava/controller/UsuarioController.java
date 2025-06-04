package com.example.globaljava.controller;

import com.example.globaljava.model.Historico;
import com.example.globaljava.model.Usuario;
import com.example.globaljava.repositories.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;


    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "Usuario/usuario-lista";
    }

    @GetMapping("/novo")
    public String novoUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "Usuario/cadastrar-usuario";
    }

    @PostMapping("/novo")
    public String novoUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model) {

        if (result.hasErrors()) {
            return "Usuario/cadastrar-usuario";
        }

        if (usuarioRepository.findByCpfUser(usuario.getCpfUser()).isPresent()) {
            model.addAttribute("erro", "CPF já cadastrado.");
            return "Usuario/cadastrar-usuario";
        }

        Historico historico = new Historico();
        historico.setUsuario(usuario);
        historico.setQuantidadeAlertas(0);
        usuario.setHistorico(historico);

        if (usuario.getEndereco() != null) {
            usuario.getEndereco().setUsuario(usuario);
        }

        usuarioRepository.save(usuario);
        redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
        return "redirect:/usuarios";
    }


    @GetMapping("/{cpf}")
    public String listarUsuario(@PathVariable String cpf, Model model, RedirectAttributes redirectAttributes) {
        return usuarioRepository.findById(cpf).map(usuario -> {
            model.addAttribute("usuario", usuario);
            return "Usuario/listar-usuario";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("erro", "Usuário não encontrado.");
            return "redirect:/usuarios";
        });
    }

    @GetMapping("/editar/{cpf}")
    public String editarUsuarioForm(@PathVariable String cpf, Model model, RedirectAttributes redirectAttributes) {
        return usuarioRepository.findById(cpf).map(usuario -> {
            model.addAttribute("usuario", usuario);
            return "Usuario/editar-usuario";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("erro", "Usuário não encontrado.");
            return "redirect:/usuarios";
        });
    }

    @PostMapping("/editar")
    public ModelAndView atualizarUsuario(@ModelAttribute Usuario usuarioParam) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioParam.getCpfUser());

        if (usuarioOptional.isPresent()) {
            Usuario usuarioCadastrado = usuarioOptional.get();
            Usuario usuarioAtualizado = Usuario.builder()
                    .cpfUser(usuarioCadastrado.getCpfUser())
                    .dataNascimentoUser(usuarioCadastrado.getDataNascimentoUser())
                    .emailUser(usuarioParam.getEmailUser())
                    .nomeUser(usuarioParam.getNomeUser())
                    .sobrenomeUser(usuarioParam.getSobrenomeUser())
                    .telefoneUser(usuarioParam.getTelefoneUser())
                    .endereco(usuarioCadastrado.getEndereco())
                    .historico(usuarioCadastrado.getHistorico())
                    .build();

            try {
                String usuarioJson = objectMapper.writeValueAsString(usuarioAtualizado);
                System.out.println("📩 Mensagem enviada para a fila: " + usuarioJson);
            } catch (JsonProcessingException e) {
                System.err.println("❌ Erro ao serializar o objeto Usuario: " + e.getMessage());
            }
            return new ModelAndView("redirect:/usuarios", "sucesso", "Usuário atualizado com sucesso!");
        }

        return new ModelAndView("Usuario/editar-usuario", "erro", "Erro ao atualizar o usuário.");
    }


    @GetMapping("/deletar/{cpf}")
    public String deletarUsuario(@PathVariable String cpf, RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuario = usuarioRepository.findById(cpf);
        if (usuario.isPresent()) {
            usuarioRepository.deleteById(cpf);
            redirectAttributes.addFlashAttribute("sucesso", "Usuário deletado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Usuário não encontrado para exclusão.");
        }
        return "redirect:/usuarios";
    }
}

