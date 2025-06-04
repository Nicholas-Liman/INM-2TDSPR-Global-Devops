package com.example.globaljava.controller;


import com.example.globaljava.repositories.EnderecoRepository;
import com.example.globaljava.repositories.FuncionarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.globaljava.model.Funcionario;
import com.example.globaljava.model.Endereco;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;
    private final ObjectMapper objectMapper;
    private final EnderecoRepository enderecoRepository;

    @GetMapping
    public ModelAndView listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return new ModelAndView("Funcionario/lista", "funcionarios", funcionarios);
    }

    @GetMapping("/novo")
    public ModelAndView novoFuncionarioForm() {
        Funcionario funcionarioVazio = new Funcionario();
        return new ModelAndView("Funcionario/cadastrar-funcionario", "funcionario", funcionarioVazio);
    }

    @PostMapping("/novo")
    public ModelAndView novoFuncionario(@Valid @ModelAttribute Funcionario funcionarioParam, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("Funcionario/cadastrar-funcionario");
        }
        if (funcionarioParam.getEndereco() != null) {
            funcionarioParam.getEndereco().setFuncionario(funcionarioParam);
        }
        funcionarioRepository.save(funcionarioParam);

        return new ModelAndView("redirect:/funcionarios").addObject("sucesso", "Funcionário cadastrado com sucesso!");
    }



    @GetMapping("/{registroFuncionario}")
    public ModelAndView listarFuncionario(@PathVariable Long registroFuncionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(registroFuncionario);
        return funcionarioOptional.map(funcionario -> new ModelAndView("Funcionario/listar-funcionario", "funcionario", funcionario)).orElseGet(() -> new ModelAndView("redirect:/funcionario", "erro", "Funcionário não encontrado."));
    }

    @GetMapping("/editar/{registroFuncionario}")
    public ModelAndView editarFuncionarioForm(@PathVariable Long registroFuncionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(registroFuncionario);
        return funcionarioOptional.map(funcionario -> new ModelAndView("Funcionario/editar-funcionario", "funcionario", funcionario)).orElseGet(() -> new ModelAndView("redirect:/funcionario", "erro", "Funcionário não encontrado."));
    }

    @PostMapping("/editar")
    public ModelAndView atualizarFuncionario(@ModelAttribute Funcionario funcionarioParam) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(funcionarioParam.getRegistroFuncionario());

        if (funcionarioOptional.isPresent()) {
            Funcionario antigo = funcionarioOptional.get();
            Funcionario funcionarioAtualizado = Funcionario.builder()
                    .registroFuncionario(antigo.getRegistroFuncionario())
                    .nomeFuncionario(funcionarioParam.getNomeFuncionario())
                    .sobrenomeFuncionario(funcionarioParam.getSobrenomeFuncionario())
                    .telefoneFuncionario(funcionarioParam.getTelefoneFuncionario())
                    .emailFuncionario(antigo.getEmailFuncionario())
                    .tipoFuncionario(antigo.getTipoFuncionario())
                    .dataInscricaoFuncionario(antigo.getDataInscricaoFuncionario())
                    .endereco(antigo.getEndereco())
                    .build();

            try {
                String funcionarioJson = objectMapper.writeValueAsString(funcionarioAtualizado);
                System.out.println("📩 Mensagem enviada para a fila: " + funcionarioJson);
            } catch (JsonProcessingException e) {
                System.err.println("❌ Erro ao serializar o objeto Funcionario: " + e.getMessage());
            }

            return new ModelAndView("redirect:/funcionarios", "sucesso", "Funcionário atualizado com sucesso!");
        }

        return new ModelAndView("Funcionario/editar-funcionario", "erro", "Erro ao atualizar o funcionário.");
    }

    @GetMapping("/deletar/{registroFuncionario}")
    public ModelAndView deletarFuncionario(@PathVariable Long registroFuncionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(registroFuncionario);
        if (funcionarioOptional.isPresent()) {
            funcionarioRepository.deleteById(registroFuncionario);
            return new ModelAndView("redirect:/funcionarios", "sucesso", "Funcionário deletado com sucesso!");
        }
        return new ModelAndView("redirect:/funcionarios", "erro", "Funcionário não encontrado para exclusão.");
    }
}
