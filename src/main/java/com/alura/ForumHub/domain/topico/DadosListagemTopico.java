package com.alura.ForumHub.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(
        String topico,
        String mensagem,
        String curso,
        String login,
        LocalDateTime dataCriacao
) {

    public DadosListagemTopico(Topico topico){
        this(topico.getTopico(), topico.getMensagem(), topico.getCurso(),
                topico.getUsuario() != null ? topico.getUsuario().getLogin() : null,
                topico.getDataCriacao());
    }
}