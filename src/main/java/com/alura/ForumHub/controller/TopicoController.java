package com.alura.ForumHub.controller;

import com.alura.ForumHub.domain.topico.DadosListagemTopico;
import com.alura.ForumHub.domain.topico.DadosSalvarTopico;
import com.alura.ForumHub.domain.topico.Topico;
import com.alura.ForumHub.domain.topico.TopicoRepository;
import com.alura.ForumHub.domain.usuario.Usuario;
import com.alura.ForumHub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> novoTopico(@RequestBody @Valid DadosSalvarTopico dados) {
        String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByLogin(nomeUsuario);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
        }

        Topico topico = new Topico(dados);
        topico.setUsuario(usuario);
        topico.setDataCriacao(LocalDateTime.now());
        repository.save(topico);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<DadosListagemTopico> listar(@PageableDefault(size = 10) Pageable paginacao) {
        paginacao = PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize(), Sort.by("dataCriacao").descending());
        Page<Topico> page = repository.findAll(paginacao);
        return page.getContent().stream()
                .map(DadosListagemTopico::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemTopico> buscarPorId(@PathVariable Long id) {
        Optional<Topico> optionalTopico = repository.findById(id);
        return optionalTopico.map(topico -> ResponseEntity.ok(new DadosListagemTopico(topico))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosListagemTopico dados){
        Optional<Topico> optionalTopico = repository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.setTopico(dados.topico());
            topico.setMensagem(dados.mensagem());
            topico.setCurso(dados.curso());
            repository.save(topico);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
